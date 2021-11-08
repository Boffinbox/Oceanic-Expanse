package com.sirsquidly.oe.items;

import com.sirsquidly.oe.Main;
import com.sirsquidly.oe.init.OEItems;

import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(Main.OCEANEXPTAB);
		
		OEItems.ITEMS.add(this);
	}
}