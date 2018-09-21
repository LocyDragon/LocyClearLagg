package com.locydragon.lagg.listeners.ache;

import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Ache {
	public static final Vector<ItemContainer> containerVector = new Vector<ItemContainer>();
	public static Vector<Thread> loadThreads = new Vector<>();

	public static ConcurrentHashMap<World, Chunk[]> loadChunks = new ConcurrentHashMap<>();
	public static Vector<Entity> needClean = new Vector<>();
	public static Vector<Location> playerLocation = new Vector<>();
	public static AtomicInteger itemCount = new AtomicInteger();
	public static AtomicInteger houseCount = new AtomicInteger();
	public static AtomicInteger entityCount = new AtomicInteger();
	public static final Vector<Thread> cleanItemThread = new Vector<>();
	public static final Vector<Entity> craftEntityVector = new Vector<>();
}
