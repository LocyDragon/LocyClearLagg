package com.locydragon.lagg.util;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;

public class ChunkUsageManager {
	public static boolean isUsing(Chunk chunk) {
		if (chunk.getWorld().isChunkInUse(chunk.getX(), chunk.getZ())) {
			return true;
		}
		for (int x = 0;x < 16;x++) {
			for (int y = 0;y < 16;y++) {
				for (int z = 0;z < 256;z++) {
					Block there = chunk.getBlock(x, y, z);
					if (there.getType() == Material.BURNING_FURNACE
							|| there.getType() == Material.BREWING_STAND)
						if (there.getType() == Material.BREWING_STAND) {
							BrewingStand target = (BrewingStand)there.getState();
							if (target.getBrewingTime() > 0) {
								return true;
							} else {
								return false;
							}
						}
					return true;
				}
			}
		}
		return false;
	}
}
