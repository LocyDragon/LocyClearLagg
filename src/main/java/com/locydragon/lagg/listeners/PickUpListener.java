package com.locydragon.lagg.listeners;

import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.ItemContainer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickUpListener implements Listener {
	@EventHandler
	public void onPlayerPick(PlayerPickupItemEvent e) {
		ItemContainer container = ItemContainer.getByItemEntity(e.getItem());
		if (container != null) {
			asyncRemove(container);
		}
	}

	public void asyncRemove(ItemContainer container) {
		Thread async = new Thread(() -> {
			if (Ache.containerVector.contains(container)) {
				ItemContainer.containerMap.remove(container.getTarget().getUniqueId());
				Ache.containerVector.remove(container);
			}
		});
		async.start();
	}
}
