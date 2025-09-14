package net.matt.tutorialmod.world;

import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.block.ModBlocks;
import net.matt.tutorialmod.world.gen.ModOrePlacement;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {

    // === Registry Keys ===
    // Ore Placed Keys
    public static final RegistryKey<PlacedFeature> PINK_GARNET_ORE_PLACED_KEY = registryKey("pink_garnet_ore_placed");
    public static final RegistryKey<PlacedFeature> NETHER_PINK_GARNET_ORE_PLACED_KEY = registryKey("nether_pink_garnet_ore_placed");
    public static final RegistryKey<PlacedFeature> END_PINK_GARNET_ORE_PLACED_KEY = registryKey("end_pink_garnet_ore_placed");

    // Tree Placed Keys
    public static final RegistryKey<PlacedFeature> DRIFTWOOD_PLACED_KEY = registryKey("driftwood_placed");

    public static final RegistryKey<PlacedFeature> HONEY_BERRY_BUSH_PLACED_KEY = registryKey("honey_berry_bush_placed");


    // ==== Helper features ====
    public static void bootstrap(Registerable<PlacedFeature> context){
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);


        // === Ore Placed Features ===
        register(context, PINK_GARNET_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINK_GARNET_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14, //~Veins per chunk
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80)))); //~min level, Max level
        register(context, NETHER_PINK_GARNET_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_PINK_GARNET_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, END_PINK_GARNET_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_PINK_GARNET_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));


        // === Tree Placed Feature ===
        register(context, DRIFTWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRIFTWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive( //This means the tree can only be placed where the sapling would survive
                        PlacedFeatures.createCountExtraModifier(2, 0.1f, 2), ModBlocks.DRIFTWOOD_SAPLING));

        // === Bush placed Feature ===
        register(context, HONEY_BERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HONEY_BERRY_BUSH_KEY),
                RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static RegistryKey<PlacedFeature> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(TutorialMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers){
        register(context, key, configuration, List.of(modifiers));
    }
}
