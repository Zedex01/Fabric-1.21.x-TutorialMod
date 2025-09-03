package net.matt.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.matt.tutorialmod.block.ModBlocks;
import net.matt.tutorialmod.item.ModItemGroups;
import net.matt.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        //Register Item Groups
        ModItemGroups.registerItemGroups();
        //Registers the ModItems class
        ModItems.registerModItems();
        //Register the ModBlocks Class
        ModBlocks.registerModBlocks();


	}
}