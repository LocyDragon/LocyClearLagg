package com.locydragon.lagg.listeners;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EntitySpawnListener implements Listener {
	public static ExecutorService threadPool = Executors.newCachedThreadPool();
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		Runnable runnable = () -> {
			Ache.craftEntityVector.add(e.getEntity());
			LaggLogger.info("Add entity " + e.getEntity().getUniqueId().toString()
					+ " to Stack.Type: " + e.getEntity().getType().toString());
			removeAsync(e.getEntity());
		};
		threadPool.execute(runnable);
		Ache.threadCountExtra.incrementAndGet();
	}

	private void removeAsync(Entity entity) {
		Runnable runnable = () -> {
			try {
				Thread.sleep(1000 * ClearLagg.config.getInt("settings.EntitySpawnDelay"));
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			if (Ache.craftEntityVector.contains(entity)) {
				Ache.craftEntityVector.remove(entity);
			}
			LaggLogger.info("Remove entity "+entity.getUniqueId().toString()+" from stack.");
		};
		threadPool.execute(runnable);
		Ache.threadCountExtra.incrementAndGet();
	}
}
