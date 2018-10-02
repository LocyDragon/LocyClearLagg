package com.locydragon.lagg.listeners.anti;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldLoaderExtraListener implements Listener {
	@EventHandler
	public void onWorldInit(WorldInitEvent e) {
		if (!e.getWorld().isAutoSave()) {
			e.getWorld().setAutoSave(true);
		}
		e.getWorld().setKeepSpawnInMemory(true);
	}
}
