/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Aug 6, 2014, 9:55:23 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.tool;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemGlassPick extends ItemManasteelPick {

	private static final int MANA_PER_DAMAGE = 160;
	private static final ToolMaterial MATERIAL = EnumHelper.addToolMaterial("MANASTEEL_GLASS", 0, 125, 4.8F, 1F, 10);

	public ItemGlassPick() {
		super(MATERIAL, LibItemNames.GLASS_PICK);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onBlockDrops(HarvestDropsEvent event) {
		if(event.harvester != null && event.block != null && event.drops.isEmpty() && event.harvester.getCurrentEquippedItem() != null && event.harvester.getCurrentEquippedItem().getItem() == this && event.block.getMaterial() == Material.glass && event.block.canSilkHarvest(event.world, event.harvester, event.x, event.y, event.z, event.blockMetadata))
			event.drops.add(new ItemStack(event.block, 1, event.blockMetadata));
	}

	@Override
	public int getManaPerDmg() {
		return MANA_PER_DAMAGE;
	}

}
