package net.matt.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.matt.tutorialmod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

//Class to generate ModBlock.json files

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    //When Data-gen runs, adds to the PICKAXE_MINEABLE tag
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //Indicates the preference of tool
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.PINK_GARNET_BLOCK)
                .add(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .add(ModBlocks.PINK_GARNET_ORE)
                .add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)
                .add(ModBlocks.MAGIC_BLOCK);

          //Requires this level or higher to drop item.
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PINK_GARNET_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);


        //Non-block blocks
        getOrCreateTagBuilder(BlockTags.STAIRS).add(ModBlocks.PINK_GARNET_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS).add(ModBlocks.PINK_GARNET_SLAB);

        getOrCreateTagBuilder(BlockTags.FENCES).add(ModBlocks.PINK_GARNET_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.PINK_GARNET_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.PINK_GARNET_WALL);

        getOrCreateTagBuilder(BlockTags.DOORS).add(ModBlocks.PINK_GARNET_DOOR);
        getOrCreateTagBuilder(BlockTags.TRAPDOORS).add(ModBlocks.PINK_GARNET_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.BUTTONS).add(ModBlocks.PINK_GARNET_BUTTON);
        getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES).add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);

    }
}
