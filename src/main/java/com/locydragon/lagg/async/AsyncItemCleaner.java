package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.neural.AutoHouseCheck;
import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;

import java.util.concurrent.locks.LockSupport;


public class AsyncItemCleaner extends Thread {
	public World target;
	private Entity[] entityOnline = null;
	public AsyncItemCleaner(World world) {
		this.target = world;
	}

	@Override
	public void run() {
		for (Chunk chunk : Ache.loadChunks.get(target)) {
			if (AutoHouseCheck.isHouse(chunk)) {
				Ache.houseCount.incrementAndGet();
				continue;
			}
			Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
				entityOnline = chunk.getEntities();
				LockSupport.unpark(AsyncItemCleaner.this);
			});
			LockSupport.park();
			Father:
			for (Entity inChunk : entityOnline) {
				if (inChunk.getType() != EntityType.DROPPED_ITEM) {
					continue Father;
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
				Ache.unlessEntity.add(inChunk);
				Ache.itemCount.incrementAndGet();
			}
		}
	}
}
