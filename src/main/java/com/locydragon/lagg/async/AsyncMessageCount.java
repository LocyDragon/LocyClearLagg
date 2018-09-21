package com.locydragon.lagg.async;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.ChatColor;

public class AsyncMessageCount extends Thread {
	@Override
	public void run() {
		Ache.cleanItemThread.forEach(x -> {
			try {
				x.join();
				String infoMsg = ClearLagg.cleanMessage;
				infoMsg = infoMsg.replace("{item}", String.valueOf(Ache.itemCount.get()))
						.replace("{monster}", String.valueOf(Ache.entityCount.get()));
				infoMsg = ChatColor.translateAlternateColorCodes('&', infoMsg);
				ClearLagg.broadCastMsg(infoMsg);
				LaggLogger.info(infoMsg);
				LaggLogger.info("Find "+Ache.houseCount.get()+" houses.");
				LaggLogger.info("Used "+Ache.cleanItemThread.size()+" to clean drop item.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
