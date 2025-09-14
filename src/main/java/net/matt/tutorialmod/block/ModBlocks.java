package net.matt.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.block.custom.*;
import net.matt.tutorialmod.sound.ModSounds;
import net.matt.tutorialmod.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import static net.minecraft.block.Blocks.*;

public class ModBlocks {

    /* To register blocks you need to register the block itself as well as the item associated with it. */

    //register blocks
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block", new Block(AbstractBlock.Settings.create().strength(4f) .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK))); //Set sounds and tool settings
    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block", new Block(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    //====Experience Dropping BLocks =========
    public static final Block PINK_GARNET_ORE = registerBlock("pink_garnet_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,5),AbstractBlock.Settings.create()
                    .strength(3f).requiresTool()));
    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerBlock("pink_garnet_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3,6),AbstractBlock.Settings.create()
                    .strength(4f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block PINK_GARNET_NETHER_ORE = registerBlock("pink_garnet_nether_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(1,5),AbstractBlock.Settings.create()
                    .strength(3f).requiresTool().sounds(BlockSoundGroup.NETHER_ORE)));
    public static final Block PINK_GARNET_END_ORE = registerBlock("pink_garnet_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4,8),AbstractBlock.Settings.create()
                    .strength(7f).requiresTool()));

    //Custom Blocks
    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create().strength(4f).sounds(ModSounds.MAGIC_SOUNDS_GROUP)));

    //Pink Garnet Lamp
    public static final Block PINK_GARNET_LAMP = registerBlock("pink_garnet_lamp",
            new PinkGarnetLampBlock(AbstractBlock.Settings.create()
                    .strength(1.0f).luminance(state -> state.get(PinkGarnetLampBlock.CLICKED) ? 15 : 0)));

    //Cauliflower Block
    public static final Block CAULIFLOWER_CROP = registerBlockWithoutBlockItem("cauliflower_crop",
            new CauliflowerCropBlock(AbstractBlock.Settings.create().noCollision().ticksRandomly().breakInstantly()
                    .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));

    public static final Block HONEY_BERRY_BUSH = registerBlockWithoutBlockItem("honey_berry_bush",
            new HoneyBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    // === Custom Tree ===
    public static final Block DRIFTWOOD_LOG = registerBlock("driftwood_log", new PillarBlock(AbstractBlock.Settings.copy(OAK_LOG)));
    public static final Block DRIFTWOOD_WOOD = registerBlock("driftwood_wood", new PillarBlock(AbstractBlock.Settings.copy(OAK_WOOD)));
    public static final Block STRIPPED_DRIFTWOOD_LOG = registerBlock("stripped_driftwood_log", new PillarBlock(AbstractBlock.Settings.copy(STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_DRIFTWOOD_WOOD = registerBlock("stripped_driftwood_wood", new PillarBlock(AbstractBlock.Settings.copy(STRIPPED_OAK_WOOD)));

    public static final Block DRIFTWOOD_PLANKS = registerBlock("driftwood_planks", new Block(AbstractBlock.Settings.copy(OAK_PLANKS)));
    public static final Block DRIFTWOOD_LEAVES = registerBlock("driftwood_leaves",  new LeavesBlock(AbstractBlock.Settings.copy(OAK_LEAVES)));

    public static final Block DRIFTWOOD_SAPLING = registerBlock("driftwood_sapling", new ModSaplingBlock(ModSaplingGenerators.DRIFTWOOD,AbstractBlock.Settings.copy(OAK_SAPLING), Blocks.STONE));


    //==================== Non-block blocks ============================
    //Stairs/slabs
    public static final Block PINK_GARNET_STAIRS = registerBlock("pink_garnet_stairs",
            new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(), AbstractBlock.Settings.create().strength(2.0f)));
    public static final Block PINK_GARNET_SLAB = registerBlock("pink_garnet_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2.0f)));

    //Redstone
    public static final Block PINK_GARNET_BUTTON = registerBlock("pink_garnet_button",
            new ButtonBlock(BlockSetType.IRON, 2, AbstractBlock.Settings.create().strength(2.0f).noCollision()));
    public static final Block PINK_GARNET_PRESSURE_PLATE = registerBlock("pink_garnet_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2.0f)));

    //Fences
    public static final Block PINK_GARNET_FENCE = registerBlock("pink_garnet_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2.0f)));
    public static final Block PINK_GARNET_FENCE_GATE = registerBlock("pink_garnet_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2.0f)));
    public static final Block PINK_GARNET_WALL = registerBlock("pink_garnet_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2.0f)));

    //Doors
    public static final Block PINK_GARNET_DOOR = registerBlock("pink_garnet_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2.0f).nonOpaque()));
    public static final Block PINK_GARNET_TRAPDOOR = registerBlock("pink_garnet_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2.0f).nonOpaque()));


    //===helper Methods===
    //Register Block
    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return  Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block){
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