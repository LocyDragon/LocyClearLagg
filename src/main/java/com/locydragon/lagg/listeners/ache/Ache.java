package com.locydragon.lagg.listeners.ache;

import com.locydragon.lagg.async.sync.SyncInteger;
import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ache {
	public static final ConcurrentLinkedQueue<ItemContainer> containerVector = new ConcurrentLinkedQueue<ItemContainer>();
	public static ConcurrentLinkedQueue<Thread> loadThreads = new ConcurrentLinkedQueue<>();

	public static ConcurrentHashMap<World, Chunk[]> loadChunks = new ConcurrentHashMap<>();
	public static ConcurrentLinkedQueue<Entity> needClean = new ConcurrentLinkedQueue<>();
	public static ConcurrentLinkedQueue<Location> playerLocation = new ConcurrentLinkedQueue<>();
	public static SyncInteger itemCount = new SyncInteger(); 
	public static SyncInteger houseCount = new SyncInteger();
	public static SyncInteger entityCount = new SyncInteger();
	public static final ConcurrentLinkedQueue<Thread> cleanItemThread = new ConcurrentLinkedQueue<>();
	public static final ConcurrentLinkedQueue<Entity> craftEntityVector = new ConcurrentLinkedQueue<>();
	public static final ConcurrentLinkedQueue<Thread> cleanEntityThread = new ConcurrentLinkedQueue<>();
	public static final ConcurrentLinkedQueue<Entity> unlessEntity = new ConcurrentLinkedQueue<>();
	public static SyncInteger threadCountExtra = new SyncInteger();
}
