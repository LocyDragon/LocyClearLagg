package com.locydragon.lagg.neural.util;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.neural.NeuralNetwork;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Chunk3D {
	public List<String> blockList;
	public Chunk3D(List<String> block) {
		this.blockList = block;
	}

	public double houseWeight() {
		Integer air = 0;
		double blockWeight = 0;
		for (String eachBlock : this.blockList) {
			if (eachBlock.equals("AIR")) {
				air++;
			} else if (ClearLagg.mapWeight.keySet().contains(eachBlock)) {
				blockWeight += ClearLagg.mapWeight.get(eachBlock);
			}
		}
		return NeuralNetwork.get(air, blockWeight);
	}

	public static Chunk3D[] toChunk3D(Chunk chunk2D) {
		List<Chunk3D> ThreeDArray = new ArrayList<>();
		List<Material> blockArray = new ArrayList<>();
		for (int x = 0;x < 256;x++) {
			if (x % 16 == 0 && x != 0) {
				if (blockArray.size() == 0) {
					continue;
				}
				List<String> materialList = new ArrayList<>();
				for (Material material : blockArray) {
					materialList.add(material.toString());
				}
				Chunk3D newChunk = new Chunk3D(materialList);
				ThreeDArray.add(newChunk);
				blockArray.clear();
			}
			for (int y = 0; y < 16; y++) {
				for (int z = 0; z < 16; z++) {
					blockArray.add(chunk2D.getBlock(y, x, z).getType());
				}
			}
		}
		/**
		for (Chunk3D d : ThreeDArray) {
			List<Material> materials = new ArrayList<>();
			d.blockList.forEach(x -> materials.add(x.getType()));
			System.out.print(materials);
		}
		 **/
		return ThreeDArray.toArray(new Chunk3D[16]);
	}
}
