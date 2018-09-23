package com.locydragon.lagg.async;
import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.ChunkUsageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.*;
public class AsyncChunkUnLoader extends Thread {
	private List<Chunk> arrayChunk = new ArrayList<>();
	@Override
	public void run() {
		try {
			Thread.sleep(1000 * ClearLagg.afterClean);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Map.Entry<World,Chunk[]> entry : Ache.loadChunks.entrySet()) {
			for (Chunk chunk : entry.getValue()) {
				if (!ChunkUsageManager.isUsing(chunk)) {
					arrayChunk.add(chunk);
				}
			}
		}
		Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
			arrayChunk.forEach(x -> x.unload());
			String msg = ClearLagg.cleanChunkMsg;
			msg = ChatColor.translateAlternateColorCodes('&', msg)
					.replace("{chunk}", String.valueOf(arrayChunk.size()));
			Bukkit.getLogger().info(msg);
		});
	}
}
