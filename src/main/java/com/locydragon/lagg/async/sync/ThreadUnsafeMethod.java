package com.locydragon.lagg.async.sync;

import com.locydragon.lagg.ClearLagg;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;

public class ThreadUnsafeMethod {
	public static final Object monitor = new Object();
	private Entity[] entity = new Entity[0];
	@Deprecated
	/**
	 * has a bug.but cannot get entity safely;For getEntites method may be thread safe.
	 */
	public Entity[] getEntites(Chunk inWhich) {
		synchronized (monitor) {
			Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
				ThreadUnsafeMethod.this.entity = inWhich.getEntities();
			});
			return this.entity;
		}
	}
}
