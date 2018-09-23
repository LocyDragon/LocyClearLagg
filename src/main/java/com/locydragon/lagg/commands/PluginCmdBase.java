package com.locydragon.lagg.commands;

import com.locydragon.lagg.ClearLagg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCmdBase implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (!(sender.isOp())) {
			sender.sendMessage("§c您不是Op.无法使用此命令");
			return false;
		}
		if (args[0].length() <= 0) {
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
		}
		return false;
	}
}
