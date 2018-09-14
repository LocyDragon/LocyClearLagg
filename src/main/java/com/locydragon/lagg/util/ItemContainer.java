package com.locydragon.lagg.util;

import com.locydragon.lagg.listeners.ache.Ache;
import org.bukkit.entity.Item;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LocyDragon
 */
public class ItemContainer {
	private static Integer nowId = Integer.MIN_VALUE;
	public static ConcurrentHashMap<UUID,ItemContainer> containerMap = new ConcurrentHashMap<>();
	public Item target;
	public Integer id = -1;

	public ItemContainer(Item itemStack) {
		this.target = itemStack;
		this.id = nowId;
		nowId++;
		containerMap.put(itemStack.getUniqueId(), this);
		Ache.containerVector.add(this);
	}

	public Item getTarget() {
		return this.target;
	}

	public Integer getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ItemContainer)) {
			return false;
		}
		ItemContainer container = (ItemContainer)other;
		if (container.getId().equals(this.id)) {
			return true;
		}
		return false;
	}
	public static ItemContainer getByItemEntity(Item item) {
		return containerMap.get(item.getUniqueId());
	}
}
