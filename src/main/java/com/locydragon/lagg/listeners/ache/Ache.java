package com.locydragon.lagg.listeners.ache;

import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Ache {
	public static Vector<ItemContainer> containerVector = new Vector<ItemContainer>();
	public static Vector<Thread> loadThreads = new Vector<>();

	public static ConcurrentHashMap<World,Chunk[]> loadChunks = new ConcurrentHashMap<>();
	public static Vector<Entity> needClean = new Vector<>();
}
