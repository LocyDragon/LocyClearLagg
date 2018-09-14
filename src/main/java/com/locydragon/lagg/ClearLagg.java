package com.locydragon.lagg;


import com.locydragon.lagg.listeners.PickUpListener;
import com.locydragon.lagg.listeners.ThrowListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LocyDragon
 */
public class ClearLagg extends JavaPlugin {
	public static FileConfiguration config = null;
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("====[LocyClearLagg]====");
		Bukkit.getLogger().info("新一代智能清理插件已经启动了.");
		Bukkit.getLogger().info("作者: LocyDragon QQ 2424441676");
		saveDefaultConfig();
		config = getConfig();
		Bukkit.getPluginManager().registerEvents(new ThrowListener(), this);
		Bukkit.getPluginManager().registerEvents(new PickUpListener(), this);
	}

	@Override
	public void onDisable() {

	}

	public static void cleanUp() {

	}
}
