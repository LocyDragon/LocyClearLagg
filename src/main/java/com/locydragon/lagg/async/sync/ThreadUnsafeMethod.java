package com.locydragon.lagg.async.sync;

import com.locydragon.lagg.ClearLagg;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;

public class ThreadUnsafeMethod {
	private Entity[] entity = new Entity[0];
	public synchronized Entity[] getEntites(Chunk inWhich) {
		Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
			ThreadUnsafeMethod.this.entity = inWhich.getEntities();
		});
		return this.entity;
	}
}
