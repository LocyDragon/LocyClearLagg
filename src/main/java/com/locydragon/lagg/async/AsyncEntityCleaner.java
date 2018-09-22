package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.async.sync.ThreadUnsafeMethod;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.neural.AutoHouseCheck;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

public class AsyncEntityCleaner extends Thread {
	public World target;
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
			Father:
			for (Entity inChunk : new ThreadUnsafeMethod().getEntites(chunk)) {
				if (!(inChunk instanceof Monster)) {
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
				inChunk.remove();
				Ache.entityCount.incrementAndGet();
			}
		}
	}
}
