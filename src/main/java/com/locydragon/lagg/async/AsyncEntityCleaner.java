package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.async.sync.FutureEntityGet;
import com.locydragon.lagg.listeners.EntitySpawnListener;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.neural.AutoHouseCheck;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
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
				if (!(inChunk.getType() == EntityType.ZOMBIE || inChunk.getType() == EntityType.ARROW
				|| inChunk.getType() == EntityType.BAT || inChunk.getType() == EntityType.EXPERIENCE_ORB
				|| inChunk.getType() == EntityType.BLAZE || inChunk.getType() == EntityType.CAVE_SPIDER
				|| inChunk.getType() == EntityType.CREEPER || inChunk.getType() == EntityType.ENDERMAN
			    || inChunk.getType() == EntityType.FIREBALL || inChunk.getType() == EntityType.MAGMA_CUBE
				|| inChunk.getType() == EntityType.PIG_ZOMBIE || inChunk.getType() == EntityType.SILVERFISH
				|| inChunk.getType() == EntityType.SKELETON || inChunk.getType() == EntityType.SLIME
			    || inChunk.getType() == EntityType.SPIDER || inChunk.getType() == EntityType.WITCH)) { //一定优化 不会鸽
					continue Father;
				}
				if (inChunk.getType() == EntityType.ENDER_DRAGON || inChunk.getType() == EntityType.WITHER) {
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
