package com.locydragon.lagg.async.sync;

import com.locydragon.lagg.ClearLagg;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;

import java.util.concurrent.Callable;

public class FutureEntityGet implements Callable<Entity[]> {
	private Chunk target;
	private Entity[] entityLoad = null;
	public FutureEntityGet(Chunk inWhich) {
		this.target = inWhich;
	}
	@Override
	public Entity[] call() throws Exception {
		Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
			entityLoad = target.getEntities();
		});
		while (this.entityLoad == null);
		return this.entityLoad;
	}
}
