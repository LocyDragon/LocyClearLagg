package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.async.sync.ThreadUnsafeMethod;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.neural.AutoHouseCheck;
import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;


public class AsyncItemCleaner extends Thread {
	public World target;
	public AsyncItemCleaner(World world) {
		this.target = world;
	}

	@Override
	public void run() {
		synchronized (Ache.containerVector) {
			for (Chunk chunk : Ache.loadChunks.get(target)) {
				if (AutoHouseCheck.isHouse(chunk)) {
					Ache.houseCount.incrementAndGet();
					continue;
				}
				Father:
				for (Entity inChunk : new ThreadUnsafeMethod().getEntites(chunk)) {
					if (!(inChunk instanceof Item)) {
						continue;
					}
					for (Location playerLoc : Ache.playerLocation) {
						if (playerLoc.getWorld().equals(inChunk.getWorld())) {
							if (inChunk.getLocation().distance(playerLoc) <= ClearLagg.maxDistance) {
								continue Father;
							}
						}
					}
					for (ItemContainer container : Ache.containerVector) {
						if (container.getTarget().getUniqueId().equals(inChunk.getUniqueId())) {
							continue Father;
						}
					}
					inChunk.remove();
					Ache.itemCount.incrementAndGet();
				}
			}
		}
	}
}
