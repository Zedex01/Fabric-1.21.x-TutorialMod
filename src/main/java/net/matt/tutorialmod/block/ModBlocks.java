package net.matt.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.block.custom.MagicBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    /* To register blocks you need to register the block itself as well as the item associated with it. */

    //register blocks
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block", new Block(AbstractBlock.Settings.create().strength(4f) .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK))); //Set sounds and tool settings
    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block", new Block(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    //====Experience Dropping BLocks =========
    public static final Block PINK_GARNET_ORE = registerBlock("pink_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2,5),AbstractBlock.Settings.create().strength(3f).requiresTool()));
    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerBlock("pink_garnet_deepslate_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3,6),AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    //Custom Blocks
    public static final Block MAGIC_BLOCK = registerBlock("magic_block", new MagicBlock(AbstractBlock.Settings.create().strength(4f).sounds(BlockSoundGroup.AMETHYST_BLOCK)));


    //===helper Methods===
    //Register Block
    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return  Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    //Register Block Item
    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

     public static void registerModBlocks(){
         TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

         //Add blocks to inventory
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
             entries.add(ModBlocks.PINK_GARNET_BLOCK);
             entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
             entries.add(ModBlocks.PINK_GARNET_ORE);
             entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
             entries.add(ModBlocks.MAGIC_BLOCK);
         });
     }
}