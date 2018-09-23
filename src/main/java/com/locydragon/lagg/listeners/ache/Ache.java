package com.locydragon.lagg.listeners.ache;

import com.locydragon.lagg.async.sync.SyncInteger;
import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Ache {
	public static final Vector<ItemContainer> containerVector = new Vector<ItemContainer>();
	public static Vector<Thread> loadThreads = new Vector<>();

	public static ConcurrentHashMap<World, Chunk[]> loadChunks = new ConcurrentHashMap<>();
	public static Vector<Entity> needClean = new Vector<>();
	public static Vector<Location> playerLocation = new Vector<>();
	public static SyncInteger itemCount = new SyncInteger(); // i am afraid of ABA problem!So i created a class to do the thread safe.
	public static SyncInteger houseCount = new SyncInteger();
	public static SyncInteger entityCount = new SyncInteger();
	public static final Vector<Thread> cleanItemThread = new Vector<>();
	public static final Vector<Entity> craftEntityVector = new Vector<>();
	public static final Vector<Thread> cleanEntityThread = new Vector<>();
	public static final Vector<Entity> unlessEntity = new Vector<>();
	public static SyncInteger threadCountExtra = new SyncInteger();
}
