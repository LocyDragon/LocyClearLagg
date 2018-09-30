package com.locydragon.lagg.listeners.chunk3d;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chunk3DSelectorListener implements Listener {
	public static List<String> selectQueue = new ArrayList<>();
	public static HashMap<String,Location> selectLocationFirst = new HashMap<>();
	public static HashMap<String,Location> selectLocationSecond = new HashMap<>();
	@EventHandler
	public void onSelect(PlayerInteractEvent e) {
		boolean containsIgnoreCase = false;
		for (String obj : selectQueue) {
			if (obj.equalsIgnoreCase(e.getPlayer().getName())) {
				containsIgnoreCase = true;
			}
		}
		if (containsIgnoreCase) {
			if (e.getPlayer().getItemInHand() != null
					&& e.getPlayer().getItemInHand().getType() == Material.WOOD_SPADE) {
				e.setCancelled(true);
				if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
					selectLocationFirst.remove(e.getPlayer().getName());
					selectLocationFirst.put(e.getPlayer().getName(), e.getClickedBlock().getLocation());
					e.getPlayer().sendMessage(ChatColor.RED+"选取选择点A.");
				} else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					selectLocationSecond.remove(e.getPlayer().getName());
					selectLocationSecond.put(e.getPlayer().getName(), e.getClickedBlock().getLocation());
					e.getPlayer().sendMessage(ChatColor.RED+"选取选择点B.");
				}
			}
		}
	}
}
