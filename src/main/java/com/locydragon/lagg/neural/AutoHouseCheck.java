package com.locydragon.lagg.neural;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.neural.util.Chunk3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写这段代码的时候R键和E键坏了
 * 难受诶大兄弟
 */
public class AutoHouseCheck {
	public static ReentrantLock lock = new ReentrantLock();
	public static boolean isHouse(Chunk chunk) {
		if (houseWeight(chunk) > ClearLagg.paramSecond && houseWeight(chunk) <= ClearLagg.paramLast) {
			return true;
		}
		return false;
	}

	/**
	 * 考虑到多线程的耗时，未必每一次都能获取到正确的值
	 * @param chunk 所需判断的区块
	 * @return 和房子的相似度 开区间: (0,1)
	 */
	public static double houseWeight(Chunk chunk) {
		if (NeuralNetwork.actor.isEmpty()) {
			return 0;
		}
		Double[] arraySort = null;
		try {
			try {
				if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
					List<Double> arrayWeight = new ArrayList<>();
					for (Chunk3D chunkSmaller : Chunk3D.toChunk3D(chunk)) {
						if (chunkSmaller == null) {
							continue;
						}
						arrayWeight.add(chunkSmaller.houseWeight());
					}
					arraySort = arrayWeight.toArray(new Double[arrayWeight.size()]);
					sort(arraySort);
					lock.unlock();
					return arraySort[arraySort.length - 1];
				} else {
					return 0.0;
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	public static void sort(Double[] arr) {
		double temp;
		for (int i = 0;i < arr.length - 1;i++) {
			for (int j = 0;j < arr.length - i - 1;j++){
				if (arr[j+1] < arr[j]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	public static String forString (Chunk chunk) {
		double houseWeight = houseWeight(chunk);
		if (houseWeight < ClearLagg.paramFirst) {
			return ChatColor.RED+"不是房子";
		} else if (houseWeight > ClearLagg.paramFirst && houseWeight < ClearLagg.paramSecond) {
			return ChatColor.GOLD+"疑似房子";
		}
		return  ChatColor.GREEN+"就是房子";
	}
}
