package com.locydragon.lagg.commands;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.chunk3d.Chunk3DSelectorListener;
import com.locydragon.lagg.neural.AutoHouseCheck;
import com.locydragon.lagg.neural.NeuralNetwork;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PluginCmdBase implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (!(sender.isOp())) {
			sender.sendMessage("§c您不是Op.无法使用此命令");
			return false;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c您不是Op.无法使用此命令");
			return false;
		}
		if (args.length <= 0) {
			sender.sendMessage("§a指令输入错误!");
			return false;
		}
		if (args[0].equalsIgnoreCase("clean")) {
			ClearLagg.cleanUp();
		} else if (args[0].equalsIgnoreCase("memory")) {
			System.gc();
			Runtime.getRuntime().gc();
			Runtime.getRuntime().freeMemory();
			sender.sendMessage("§a内存优化完成.");
		} else if (args[0].equalsIgnoreCase("select")) {
			boolean hasInSelector = false;
			Father:
			for (int i = 0;i < Chunk3DSelectorListener.selectQueue.size();i++) {
				if (Chunk3DSelectorListener.selectQueue.get(i).equalsIgnoreCase(sender.getName())) {
					Chunk3DSelectorListener.selectQueue.remove(i);
					hasInSelector = true;
					break Father;
				}
			}
			if (hasInSelector) {
				sender.sendMessage("§a您退出了调试选择模式...");
			} else {
				Chunk3DSelectorListener.selectQueue.add(sender.getName());
				sender.sendMessage("§a您进入了调试选择模式...");
			}
		} else if (args[0].equalsIgnoreCase("cinfo")) {
			if (!Chunk3DSelectorListener.selectQueue.contains(sender.getName())) {
				sender.sendMessage(ChatColor.RED+"你没有进入调试选择模式!");
				return false;
			}
			Location selectOne = Chunk3DSelectorListener.selectLocationFirst.get(sender.getName());
			Location selectSecond = Chunk3DSelectorListener.selectLocationSecond.get(sender.getName());
			if (selectOne == null) {
				sender.sendMessage(ChatColor.RED+"你没有选择点A.");
				return false;
			}
			if (selectSecond == null) {
				sender.sendMessage(ChatColor.RED+"你没有选择点B.");
				return false;
			}
			if ((Math.pow(selectOne.distance(selectSecond), 2) != 675)) { //勾股定理算出的16x16x16正方体的对角线距离
				sender.sendMessage(ChatColor.RED+"不规范的选择区(需要16x16x16)");
				sender.sendMessage(ChatColor.RED+"实际大小: "+Math.pow(selectOne.distance(selectSecond), 2));
				return false;
			}
			/**
			if (distanceLoc(selectOne.getBlockX(), selectSecond.getBlockX()) != 16) {
				sender.sendMessage(ChatColor.RED+"不符合规范的X轴选择区，需要(16x16x16).");
				sender.sendMessage(ChatColor.RED+"实际距离: "+distanceLoc(selectOne.getBlockX(), selectSecond.getBlockX()));
				return false;
			}
			if (distanceLoc(selectOne.getBlockY(), selectSecond.getBlockY()) != 16) {
				sender.sendMessage(ChatColor.RED+"不符合规范的Y轴选择区，需要(16x16x16).");
				sender.sendMessage(ChatColor.RED+"实际距离: "+distanceLoc(selectOne.getBlockX(), selectSecond.getBlockX()));
				return false;
			}
			if (distanceLoc(selectOne.getBlockZ(), selectSecond.getBlockZ()) != 16) {
				sender.sendMessage(ChatColor.RED+"不符合规范的Z轴选择区，需要(16x16x16).");
				sender.sendMessage(ChatColor.RED+"实际距离: "+distanceLoc(selectOne.getBlockX(), selectSecond.getBlockX()));
				return false;
			}
			 **/
			Integer air = 0;
			double blockWeight = 0;
			for (Block eachBlock : forEach(selectOne, selectSecond)) {
				if (eachBlock.getType() == Material.AIR) {
					air++;
				} else if (ClearLagg.mapWeight.keySet().contains(eachBlock.getType().toString())) {
					blockWeight += ClearLagg.mapWeight.get(eachBlock.getType().toString());
				}
			}
			sender.sendMessage(ChatColor.RED+"输出: [<value1>]: "+air+" ;[<value2>]: "+blockWeight+".");
		} else if (args[0].equalsIgnoreCase("input")) {
			if (args.length == 3) {
				double valueOne = Double.valueOf(args[1]);
				double valueSecond = Double.valueOf(args[2]);
				sender.sendMessage(ChatColor.RED+"输出: "+NeuralNetwork.get(valueOne, valueSecond));
			} else {
				sender.sendMessage(ChatColor.RED+"使用/lagger input [<value1>] [<value2>]");
			}
		} else if (args[0].equalsIgnoreCase("chunk")) {
			Thread async = new Thread(new Runnable() {
				@Override
				public void run() {
					sender.sendMessage(AutoHouseCheck.forString(((Player) sender).getLocation().getChunk()));
					sender.sendMessage(AutoHouseCheck.houseWeight(((Player)sender).getLocation().getChunk())+"");
				}
			});
			async.start();
		} else if (args[0].equalsIgnoreCase("here")) {
			Location where = ((Player)sender).getLocation();
		}
		return false;
	}

	public static int distanceLoc(int a, int b) {
		return Math.abs(a - b);
	}

	public static List<Block> forEach(Location AA, Location BB) {
		List<Block> arrayBlock = new ArrayList<>();
		int xMax = AA.getBlockX() > BB.getBlockX() ? AA.getBlockX() : BB.getBlockX();
		int xMin = AA.getBlockX() > BB.getBlockX() ? BB.getBlockX() : AA.getBlockX();
		int yMax = AA.getBlockY() > BB.getBlockY() ? AA.getBlockY() : BB.getBlockY();
		int yMin = AA.getBlockY() > BB.getBlockY() ? BB.getBlockY() : AA.getBlockY();
		int zMax = AA.getBlockZ() > BB.getBlockZ() ? AA.getBlockZ() : BB.getBlockZ();
		int zMin = AA.getBlockZ() > BB.getBlockZ() ? BB.getBlockZ() : AA.getBlockZ();
		for (int x = xMin;x <= xMax;x++) {
			for (int y = yMin;y <= yMax;y++) {
				for (int z = zMin;z <= zMax;z++) {
					arrayBlock.add(new Location(AA.getWorld(), x, y, z).getBlock());
				}
			}
		}
		return arrayBlock;
	}
}
