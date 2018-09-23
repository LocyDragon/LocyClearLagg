package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.neural.AutoHouseCheck;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;

import java.util.concurrent.locks.LockSupport;

public class AsyncEntityCleaner extends Thread {
	public World target;
	private Entity[] entityOnline = null;
	public AsyncEntityCleaner(World inWhich) {
		target = inWhich;
	}

	@Override
	public void run() {
		for (Chunk chunk : Ache.loadChunks.get(this.target)) {
			if (AutoHouseCheck.isHouse(chunk)) {
				Ache.houseCount.incrementAndGet();
				continue;
			}
			Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
				entityOnline = chunk.getEntities();
				LockSupport.unpark(AsyncEntityCleaner.this);
			});
			LockSupport.park();
			Father:
			for (Entity inChunk : entityOnline) { // need to be sync
				if (!(inChunk instanceof Monster || inChunk.getType() == EntityType.ARROW
				|| inChunk.getType() == EntityType.BAT || inChunk.getType() == EntityType.EXPERIENCE_ORB)) {
					continue Father;
				}
				for (Location playerLoc : Ache.playerLocation) {
					if (playerLoc.getWorld().equals(inChunk.getWorld())) {
						if (inChunk.getLocation().distance(playerLoc) <= ClearLagg.maxEntityDistance) {
							continue Father;
						}
					}
				}
				for (Entity nonCleanEntity : Ache.craftEntityVector) {
					if (nonCleanEntity.getUniqueId().equals(inChunk.getUniqueId())) {
						continue Father;
					}
				}
				Ache.unlessEntity.add(inChunk);
				Ache.entityCount.incrementAndGet();
			}
		}
	}
}
