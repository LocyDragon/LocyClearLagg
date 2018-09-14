package com.locydragon.lagg.listeners;

import com.locydragon.lagg.ClearLagg;
import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.ItemContainer;
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
				Ache.containerVector.remove(container);
			}
		});
		async.start();
	}
}
