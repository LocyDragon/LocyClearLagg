package com.locydragon.lagg.listeners;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.ItemContainer;
import com.locydragon.lagg.util.logger.LaggLogger;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ThrowListener implements Listener {
	@EventHandler
	public void onPlayerThrow(PlayerDropItemEvent e) {
		Item item = e.getItemDrop();
		ItemContainer container = ItemContainer.getByItemEntity(item);
		if (container == null) {
			new ItemContainer(item);
		} else {
			Ache.containerVector.remove(container);
			ItemContainer.containerMap.remove(item.getUniqueId());
			new ItemContainer(item);
		}
		removeAsync(container);
	}

	@EventHandler
	public void onItemSpawn(ItemSpawnEvent e) {
		Item item = e.getEntity();
		ItemContainer container = ItemContainer.getByItemEntity(item);
		if (container == null) {
			new ItemContainer(item);
		} else {
			Ache.containerVector.remove(container);
			ItemContainer.containerMap.remove(item.getUniqueId());
			new ItemContainer(item);
			LaggLogger.info("Add id: "+container.getId()+
					" item to stack,name: "+container.getTarget().getCustomName()+"!");
		}
		removeAsync(container);
	}

	public void removeAsync(ItemContainer container) {
		Thread async = new Thread(() -> {
			try {
				Thread.sleep(1000 * ClearLagg.config.getInt("settings.ItemDropDelay"));
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			if (Ache.containerVector.contains(container)) {
				ItemContainer.containerMap.remove(container.getTarget().getUniqueId());
				Ache.containerVector.remove(container);
				LaggLogger.info("Remove id: "+container.getId()+" from stack.");
			}
		});
		async.start();
	}
}
