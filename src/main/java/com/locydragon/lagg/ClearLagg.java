package com.locydragon.lagg;


import com.locydragon.lagg.async.AsyncThreadMonitor;
import com.locydragon.lagg.listeners.PickUpListener;
import com.locydragon.lagg.listeners.ThrowListener;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author LocyDragon
 */
public class ClearLagg extends JavaPlugin {
	public static FileConfiguration config = null;
	public static ClearLagg instance;
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
		AsyncThreadMonitor monitor = new AsyncThreadMonitor();
		monitor.setDaemon(true);
		monitor.start();
		Ache.loadThreads.add(monitor);
	}

	@Override
	public void onDisable() {

	}

	public static void cleanUp() {
		Ache.needClean.clear();
		Ache.loadChunks.clear();
		for (World worldOnline : Bukkit.getWorlds()) {
			Vector<Chunk> chunkList = new Vector<>();
			chunkList.addAll(Arrays.asList(worldOnline.getLoadedChunks()));
			Ache.loadChunks.put(worldOnline, chunkList.toArray(new Chunk[chunkList.size()]));
		}
	}
}
