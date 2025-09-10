package net.matt.tutorialmod.world;

import net.matt.tutorialmod.TutorialMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModConfiguredFeatures {
    //Configured Feature --> Placed Feature --> World Gen / Biome Modification
    //Configured Feature - what?
    // - How many ores are contained within a vein?
    // - Is this a single block or maybe a whole structure
    //Placed Feature is the How?
    // - How is this going to be placed, What Y level / how densely packer
    //Biome Modification is what biome they will be in

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TutorialMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
