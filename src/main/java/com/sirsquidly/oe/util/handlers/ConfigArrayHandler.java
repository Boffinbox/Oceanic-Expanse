package com.sirsquidly.oe.util.handlers;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.collect.Lists;
import com.sirsquidly.oe.Main;

/**
 * 	This is to break part arrays in the config for use in other areas of the code.
 * 
 *  I break it up in this class so that I don't have to break the config arrays every time I want to use them.
 */
public class ConfigArrayHandler
{
	/** Entities that drown to become a different entity. */
	public static List<ResourceLocation> DROWNCONVERTFROM = Lists.<ResourceLocation>newArrayList();
	/** Entities created by the former (DROWNCONVERTFROM) drowning. */
	public static List<ResourceLocation> DROWNCONVERTTO = Lists.<ResourceLocation>newArrayList();
	
	/** Entities that drown to become a different entity. */
	public static List<IBlockState> CRABDIGFROM = Lists.<IBlockState>newArrayList();
	/** Loot Tables pulled by the former (CRABDIGFROM). */
	public static List<ResourceLocation> CRABDIGTO = Lists.<ResourceLocation>newArrayList();
	
	
	public static void breakupConfigArrays()
	{
		for(String S : ConfigHandler.vanillaTweak.drownConverting.drownConversionsList)
		{
			String[] split = S.split("=");
			
			if (DROWNCONVERTFROM.contains(new ResourceLocation(split[0])))
			{
				Main.logger.error(split[0] + " has multiple drowned conversions set in the config! Only the first listed will be used!");
			}
			else if (split.length != 2)
			{
				if (split.length == 1)
					Main.logger.error(split[0] + " is missing a drowned conversion! Skipping...");
				else
					Main.logger.error(S + " is improperly written!");
			}
			else
			{
				DROWNCONVERTFROM.add(new ResourceLocation(split[0]));
				DROWNCONVERTTO.add(new ResourceLocation(split[1]));	
			}
		}
		
		for(String S : ConfigHandler.entity.crab.crabDiggingList)
		{
			String[] split = S.split("=");
			
			if (getBlockFromString(split[0]) == null)
			{
				Main.logger.error(split[0] + " is not a proper block!");
			}
			else if (CRABDIGFROM.contains(new ResourceLocation(split[0])))
			{
				Main.logger.error(split[0] + " has multiple crab digging loot tables set in the config! Only the first listed will be used!");
			}
			else if (split.length != 2)
			{
				if (split.length == 1)
					Main.logger.error(split[0] + " is missing a loot table! Skipping...");
				else
					Main.logger.error(S + " is improperly written!");
			}
			else
			{
				CRABDIGFROM.add(getBlockFromString(split[0]));
				CRABDIGTO.add(new ResourceLocation(split[1]));	
			}
		}
	}
	
	/**
     * Rips up a String to return an IBlockState.
     * 
     * Returns null if the string cannot be processed!
     */
	@SuppressWarnings("deprecation")
	public static IBlockState getBlockFromString(String string)
	{
		String[] ripString = string.split(":");
		
		Block block = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation(ripString[0], ripString[1]));
		Integer meta = null;
		
		if(block == null || block == Blocks.AIR)
		{
			Main.logger.error("Could not find" + string + "!");
			return null;
		}
		if(ripString.length > 2)
		{
			meta = Integer.parseInt(ripString[2]);
			
			if(meta == -1) 
			{ meta = null; }
		}

		return meta == null ? block.getDefaultState() : block.getStateFromMeta(meta);
	}
	
	/**
     * A copy of `getBlockFromString`, but for ItemStacks.
     */
	public static ItemStack getItemStackFromString(String string)
	{
		String[] ripString = string.split(":");
		
		Item item = GameRegistry.findRegistry(Item.class).getValue(new ResourceLocation(ripString[0], ripString[1]));
		Integer meta = null;
		
		if(item == null || item == Items.AIR)
		{
			Main.logger.error("Could not find" + string + "!");
			return null;
		}
		if(ripString.length > 2)
		{
			meta = Integer.parseInt(ripString[2]);
			
			if(meta == -1) 
			{ meta = null; }
		}

		return new ItemStack(item, 1, meta);
	}
}