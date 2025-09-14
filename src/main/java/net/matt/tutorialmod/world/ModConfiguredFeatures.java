package net.matt.tutorialmod.world;

import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    //Configured Feature --> Placed Feature --> World Gen / Biome Modification
    //Configured Feature - what?
    // - How many ores are contained within a vein?
    // - Is this a single block or maybe a whole structure
    //Placed Feature is the How?
    // - How is this going to be placed, What Y level / how densely packer
    //Biome Modification is what biome they will be in

    //Need a registry key for each feature we are configuring
    public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_GARNET_ORE_KEY = registerKey("pink_garnet_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_PINK_GARNET_ORE_KEY = registerKey("nether_pink_garnet_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_PINK_GARNET_ORE_KEY = registerKey("end_pink_garnet_ore");
    //Creates a registry key of the configured feature type with the name PINK_GARNET_ORE_KEY with the key name being "pink_garnet_ore"

    public static final RegistryKey<ConfiguredFeature<?, ?>> DRIFTWOOD_KEY = registerKey("driftwood");


    // ====== Helper Methods ======
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){
        //Define Rules, ie what blocks they can replace:

        //List of replaceables
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        //Assign what blocks replace what replaceables:
        List<OreFeatureConfig.Target> overworldPinkGarnetOres =
                List.of(
                        OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.PINK_GARNET_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.PINK_GARNET_DEEPSLATE_ORE.getDefaultState())
                );
        List<OreFeatureConfig.Target> netherPinkGarnetOres =
                List.of(
                        OreFeatureConfig.createTarget(netherReplacables, ModBlocks.PINK_GARNET_NETHER_ORE.getDefaultState())
                );
        List<OreFeatureConfig.Target> endPinkGarnetOres =
                List.of(
                        OreFeatureConfig.createTarget(endReplacables, ModBlocks.PINK_GARNET_END_ORE.getDefaultState())
                );

        //Register
        register(context, PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPinkGarnetOres, 12));
        register(context, NETHER_PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherPinkGarnetOres, 9));
        register(context, END_PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(endPinkGarnetOres, 9));

        //New Tree
        register(context, DRIFTWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.DRIFTWOOD_LOG), // Sets Block type
                new StraightTrunkPlacer(5,6,3), //Sets how they will be placed

                BlockStateProvider.of(ModBlocks.DRIFTWOOD_LEAVES), //Set block
                new BlobFoliagePlacer(ConstantIntProvider.create(4),  ConstantIntProvider.create(1), 3), //How they get placed

                new TwoLayersFeatureSize(1, 0, 2)).build()); //has to do with preventing overlap
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TutorialMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
