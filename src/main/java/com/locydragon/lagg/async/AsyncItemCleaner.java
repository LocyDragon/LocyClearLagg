package com.locydragon.lagg.async;

import org.bukkit.World;

public class AsyncItemCleaner extends Thread {
	public World target;
	public AsyncItemCleaner(World world) {
		this.target = world;
	}
}
