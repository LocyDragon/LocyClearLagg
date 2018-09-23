package com.locydragon.lagg.listeners;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
public class EntitySpawnListener implements Listener {
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		Entity inChunk = e.getEntity();
		if (!(inChunk instanceof Monster || inChunk.getType() == EntityType.ARROW
				|| inChunk.getType() == EntityType.BAT || inChunk.getType() == EntityType.EXPERIENCE_ORB)) {
			return;
		}
		Thread async = new Thread(() -> {
			Ache.craftEntityVector.add(e.getEntity());
			LaggLogger.info("Add entity " + e.getEntity().getUniqueId().toString()
					+ " to Stack.Type: " + e.getEntity().getType().toString());
			removeAsync(e.getEntity());
		});
		async.start();
		Ache.loadThreads.add(async);
	}

	public void removeAsync(Entity entity) {
		Thread async = new Thread(() -> {
			try {
				Thread.sleep(1000 * ClearLagg.config.getInt("settings.EntitySpawnDelay"));
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			if (Ache.craftEntityVector.contains(entity)) {
				Ache.craftEntityVector.remove(entity);
			}
			LaggLogger.info("Remove entity "+entity.getUniqueId().toString()+" from stack.");
		});
		async.setDaemon(true);
		async.start();
		Ache.loadThreads.add(async);
	}
}
