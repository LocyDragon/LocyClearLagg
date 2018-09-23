package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class AsyncMessageCount extends Thread {
	@Override
	public void run() {
		for (Thread asyncItem : Ache.cleanItemThread) {
			try {
				asyncItem.join();
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
		for (Thread async : Ache.cleanEntityThread) {
			try {
				async.join();
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
		System.gc();
		Runtime.getRuntime().freeMemory();
		Runtime.getRuntime().gc();
		Bukkit.getScheduler().runTask(ClearLagg.instance, () -> {
			int amount = 0;
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (!entity.isDead()) {
						amount++;
					}
				}
			}
			LaggLogger.info("====> Before clean entity amount: "+amount);
			LaggLogger.info("====> Clean amount: "+Ache.unlessEntity.size());
			for (Entity entity : Ache.unlessEntity) {
				if (!entity.getLocation().getChunk().isLoaded()) {
					entity.getLocation().getChunk().load();
				}
				entity.remove();
			}
			amount = 0;
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (!entity.isDead()) {
						amount++;
					}
				}
			}
			LaggLogger.info("====> After clean entity amount: "+amount);
		});
		String infoMsg = ClearLagg.cleanMessage;
		infoMsg = infoMsg.replace("{item}", String.valueOf(Ache.itemCount.get()))
						.replace("{monster}", String.valueOf(Ache.entityCount.get()));
		infoMsg = ChatColor.translateAlternateColorCodes('&', infoMsg);
		ClearLagg.broadCastMsg(infoMsg);
		LaggLogger.info(infoMsg);
		LaggLogger.info("Find "+Ache.houseCount.get()+" houses.");
		LaggLogger.info("Used "+Ache.cleanItemThread.size()+" threads to clean drop item.");
		LaggLogger.info("Used "+Ache.cleanEntityThread.size()+" threads to clean monsters.");
	}
}
