package com.locydragon.lagg;


import com.locydragon.lagg.async.AsyncEntityCleaner;
import com.locydragon.lagg.async.AsyncItemCleaner;
import com.locydragon.lagg.async.AsyncMessageCount;
import com.locydragon.lagg.async.AsyncThreadMonitor;
import com.locydragon.lagg.listeners.EntitySpawnListener;
import com.locydragon.lagg.listeners.PickUpListener;
import com.locydragon.lagg.listeners.ThrowListener;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Vector;

/**
 * @author LocyDragon
 */
public class ClearLagg extends JavaPlugin {
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
				if (counterRunnable.equals(period)) {
					cleanUp();
					counterRunnable = 0;
				}
			}
		}.runTaskTimerAsynchronously(this, 0L, 20);
	}

	@Override
	public void onDisable() {

	}

	public static void cleanUp() {
		Ache.needClean.clear();
		Ache.loadChunks.clear();
		Ache.playerLocation.clear();
		Ache.cleanItemThread.clear();
		Ache.itemCount.set(0);
		Ache.houseCount.set(0);
		Ache.entityCount.set(0);
		Ache.cleanEntityThread.clear();
		for (World worldOnline : Bukkit.getWorlds()) {
			Vector<Chunk> chunkList = new Vector<>();
			chunkList.addAll(Arrays.asList(worldOnline.getLoadedChunks()));
			Ache.loadChunks.put(worldOnline, chunkList.toArray(new Chunk[chunkList.size()]));
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
}
