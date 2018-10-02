package com.locydragon.lagg;


import com.locydragon.lagg.async.AsyncEntityCleaner;
import com.locydragon.lagg.async.AsyncItemCleaner;
import com.locydragon.lagg.async.AsyncMessageCount;
import com.locydragon.lagg.async.AsyncThreadMonitor;
import com.locydragon.lagg.commands.PluginCmdBase;
import com.locydragon.lagg.listeners.EntitySpawnListener;
import com.locydragon.lagg.listeners.PickUpListener;
import com.locydragon.lagg.listeners.ThrowListener;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.listeners.anti.WorldLoaderExtraListener;
import com.locydragon.lagg.listeners.chunk3d.Chunk3DSelectorListener;
import com.locydragon.lagg.neural.NeuralNetwork;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.learning.MomentumBackpropagation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author LocyDragon
 */
public class ClearLagg extends JavaPlugin implements LearningEventListener {
	public static FileConfiguration config = null;
	public static ClearLagg instance;
	public static Integer maxDistance;
	public static String cleanMessage;
	public static String beforeCleanMsg;
	public static boolean enableNoticeBefore = true;
	public static Integer counterRunnable = 0;
	public static Integer period = 0;
	public static Integer noticeBeforeTime;
	public static Integer maxEntityDistance = 0;
	public static String cleanChunkMsg;
	public static Integer afterClean;
	public static boolean useChunkClean = true;
	public static List<String> blockWeight = new ArrayList<>();
	public static ConcurrentHashMap<String,Double> mapWeight = new ConcurrentHashMap<>();
	public static String paramHouse = null;
	public static Double paramFirst = 0.0;
	public static Double paramSecond = 0.0;
	public static Double paramLast = 0.0;
	public static boolean debug = false;
	@Override
	public void onEnable() {
		instance = this;
		LaggLogger.info("====[LocyClearLagg]====", true);
		LaggLogger.info("新一代多线程化智能清理插件已经启动了.", true);
		LaggLogger.info("作者: LocyDragon QQ 2424441676", true);
		saveDefaultConfig();
		config = getConfig();
		Bukkit.getPluginManager().registerEvents(new ThrowListener(), this);
		Bukkit.getPluginManager().registerEvents(new PickUpListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntitySpawnListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldLoaderExtraListener(), this);
		Bukkit.getPluginManager().registerEvents(new Chunk3DSelectorListener(), this);
		maxDistance = config.getInt("settings.ItemCleanDistance");
		AsyncThreadMonitor monitor = new AsyncThreadMonitor();
		monitor.setDaemon(true);
		monitor.start();
		Ache.loadThreads.add(monitor);
		enableNoticeBefore = config.getInt("settings.period") > config.getInt("settings.noticeBeforeTime");
		period = config.getInt("settings.period");
		noticeBeforeTime = config.getInt("settings.noticeBeforeTime");
		cleanMessage = config.getString("message.CleanMsg");
		beforeCleanMsg = config.getString("message.BeforeClean");
		maxEntityDistance = config.getInt("settings.EntityCleanDistance");
		cleanChunkMsg = config.getString("message.ChunkCleanMsg", "&7[&a区块清理&7] &e{chunk} &b个区块已经被我们清理辣~");
		afterClean = config.getInt("settings.ChunkCleanAfterEntity", 30);
		paramHouse = config.getString("HouseWeight", "0.84-0.851-1");
		paramFirst = Double.valueOf(paramHouse.split("-")[0]);
		paramSecond = Double.valueOf(paramHouse.split("-")[1]);
		paramLast = Double.valueOf(paramHouse.split("-")[2]);
		useChunkClean = afterClean < period;
		new BukkitRunnable() {
			@Override
			public void run() {
				counterRunnable++;
				if (enableNoticeBefore && counterRunnable == (period - noticeBeforeTime)) {
					String loggerMsg = ChatColor.translateAlternateColorCodes('&', beforeCleanMsg);
					loggerMsg = loggerMsg.replace("{time}", String.valueOf(noticeBeforeTime));
					broadCastMsg(loggerMsg);
					LaggLogger.info(loggerMsg);
				}
				if (counterRunnable == period) {
					cleanUp();
					counterRunnable = 0;
				}
			}
		}.runTaskTimerAsynchronously(this, 0L, 20);
		Bukkit.getPluginCommand("lagger").setExecutor(new PluginCmdBase());
		blockWeight = getConfig().getStringList("NeuralNetwork.BlockWeight");
		for (String line : blockWeight) {
			int materialID = Integer.valueOf(line.split("-")[0]);
			double weight = Double.valueOf(line.split("-")[1]);
			String materialName = com.locydragon.lagg.neural.data.Material.getMaterial(materialID).toString();
			mapWeight.put(materialName, weight);
		}
		debug = getConfig().getBoolean("settings.debug", false);
		getLogger().info("Loaded "+mapWeight.size()+" model.");
		getLogger().info("开始机器学习..这可能需要一段时间..(1~10分钟，若长时间未响应，请重启服务器)");
		getLogger().info("喝杯茶，少安毋躁~");
		NeuralNetwork.init();
		getLogger().info("加载完成.");
		new Metrics(this);
	}

	@Override
	public void onDisable() {
		LaggLogger.info("====[LocyClearLagg]====", true);
		LaggLogger.info("新一代多线程化智能清理插件已经关闭啦!", true);
		LaggLogger.info("作者: LocyDragon QQ 2424441676", true);
	}

	public static void cleanUp() {
		LaggLogger.info("====>Last cleaned monster "+Ache.entityCount.get()+". Item count "+Ache.itemCount.get());
		Ache.needClean.clear();
		Ache.loadChunks.clear();
		Ache.playerLocation.clear();
		Ache.cleanItemThread.clear();
		Ache.itemCount.set(0);
		Ache.houseCount.set(0);
		Ache.entityCount.set(0);
		Ache.cleanEntityThread.clear();
		Ache.unlessEntity.clear();
		for (World worldOnline : Bukkit.getWorlds()) {
			Ache.loadChunks.put(worldOnline, worldOnline.getLoadedChunks());
		}
		for (Player online : Bukkit.getOnlinePlayers()) {
			Ache.playerLocation.add(online.getLocation());
		}
		for (World world : Ache.loadChunks.keySet()) {
			{
				AsyncItemCleaner cleaner = new AsyncItemCleaner(world);
				Ache.loadThreads.add(cleaner);
				Ache.cleanItemThread.add(cleaner);
				cleaner.setDaemon(true);
				cleaner.setPriority(Thread.MAX_PRIORITY);
				cleaner.start();
			}
			{
				AsyncEntityCleaner cleanerEntity = new AsyncEntityCleaner(world);
				Ache.loadThreads.add(cleanerEntity);
				Ache.cleanEntityThread.add(cleanerEntity);
				cleanerEntity.setDaemon(true);
				cleanerEntity.setPriority(Thread.MAX_PRIORITY);
				cleanerEntity.start();
			}
		}
		AsyncMessageCount counter = new AsyncMessageCount();
		counter.start();
		Ache.loadThreads.add(counter);
	}

	public static synchronized void consoleInfo(String msg) {
		instance.getLogger().info(msg);
	}

	public static synchronized void broadCastMsg(String msg) {
		Bukkit.broadcastMessage(msg);
	}

	@Override
	public void handleLearningEvent(LearningEvent learningEvent) {
		MomentumBackpropagation object = (MomentumBackpropagation) learningEvent.getSource();
		if (object.getCurrentIteration() > 50000) {
			NeuralNetwork.actor.stopLearning();
			NeuralNetwork.actor.reset();
		}
		if (debug) {
			System.out.print(object.getCurrentIteration() + ". Total network error : " + object.getTotalNetworkError());
		}
	}
}
