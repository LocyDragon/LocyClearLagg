package com.locydragon.lagg.neural.util;

import org.bukkit.Chunk;
import org.bukkit.block.Block;

import java.util.List;

public class Chunk3D {
	private List<Block> blockList;
	public Chunk3D(List<Block> blockList) {
		this.blockList = blockList;
	}
	public static Chunk3D[] toChunk3D(Chunk chunk2D) {
		return null;
	}
}
