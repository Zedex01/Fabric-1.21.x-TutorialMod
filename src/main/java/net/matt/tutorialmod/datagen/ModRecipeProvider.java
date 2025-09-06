package net.matt.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.block.ModBlocks;
import net.matt.tutorialmod.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.MinecartItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        //groups of items
        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        //Smelting/blasting items
        // (exporter, list of items, category of items, resulting item, experience, cooking time, item group)
        offerSmelting(recipeExporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.2f, 200, "pink_garnet");
        offerBlasting(recipeExporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.2f, 100, "pink_garnet");

        //Crafting
        //Recipe for going between a block and single items (compacting recipes like diamonds iron and gold.)
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);

        //Examples of how to make any recipe not already available
        //shaped recipe example
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_PINK_GARNET)
                .criterion(hasItem(ModItems.RAW_PINK_GARNET),conditionsFromItem(ModItems.RAW_PINK_GARNET)) //Unlocks the recipe once a raw_pink_garnet is in your inventory
                .offerTo(recipeExporter);


        //shapeless recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
                .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);

        //**NOTE: exporter by default saves the recipes based on the output item, Meaning, if you have 2 recipes that result in the same item, it can get confused.
        //This can be avoided by using a custom identifier in the offerTo().
        //shapeless recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
                .input(ModBlocks.MAGIC_BLOCK)
                .criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.MAGIC_BLOCK))
                .offerTo(recipeExporter, Identifier.of(TutorialMod.MOD_ID, "raw_pink_garnet_from_magic_block"));



        //=============Non-block blocks==============
        //Crafting Table
        createStairsRecipe(ModBlocks.PINK_GARNET_STAIRS, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(recipeExporter);

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_SLAB, ModBlocks.PINK_GARNET_BLOCK);

        offerSingleOutputShapelessRecipe(recipeExporter, ModBlocks.PINK_GARNET_BUTTON, ModBlocks.PINK_GARNET_BLOCK, "pink_garnet");
        offerPressurePlateRecipe(recipeExporter, ModBlocks.PINK_GARNET_PRESSURE_PLATE, ModBlocks.PINK_GARNET_BLOCK);

        createFenceRecipe(ModBlocks.PINK_GARNET_FENCE, Ingredient.ofItems(ModItems.PINK_GARNET))
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(recipeExporter);
        createFenceGateRecipe(ModBlocks.PINK_GARNET_FENCE_GATE, Ingredient.ofItems(ModItems.PINK_GARNET))
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(recipeExporter);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_WALL, ModBlocks.PINK_GARNET_BLOCK);

        //Door
        createDoorRecipe(ModBlocks.PINK_GARNET_DOOR, Ingredient.ofItems(ModItems.PINK_GARNET))
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(recipeExporter);
        //trapdoor
        createTrapdoorRecipe(ModBlocks.PINK_GARNET_TRAPDOOR, Ingredient.ofItems(ModItems.PINK_GARNET))
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(recipeExporter);

        //Stone Cutting

        //============= Tools =================
        offerSword(recipeExporter, RecipeCategory.TOOLS, ModItems.PINK_GARNET, ModItems.PINK_GARNET_SWORD);
        offerPickaxe(recipeExporter, RecipeCategory.TOOLS, ModItems.PINK_GARNET, ModItems.PINK_GARNET_PICKAXE);
        offerAxe(recipeExporter, RecipeCategory.TOOLS, ModItems.PINK_GARNET, ModItems.PINK_GARNET_AXE);
        offerShovel(recipeExporter, RecipeCategory.TOOLS, ModItems.PINK_GARNET, ModItems.PINK_GARNET_SHOVEL);
        offerHoe(recipeExporter, RecipeCategory.TOOLS, ModItems.PINK_GARNET, ModItems.PINK_GARNET_HOE);

        //Pink Hammer
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_HAMMER)
                .pattern(" RT")
                .pattern(" SR")
                .pattern("S  ")
                .input('R', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .input('T', Items.STRING)
                .criterion(hasItem(ModItems.PINK_GARNET),conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(recipeExporter);


    }

    //Helpers
    private void offerSword(RecipeExporter recipeExporter, RecipeCategory recipeCategory,  ItemConvertible input, ItemConvertible output){
        ShapedRecipeJsonBuilder.create(recipeCategory, output)
                .pattern(" R ")
                .pattern(" R ")
                .pattern(" S ")
                .input('R', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input),conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

    private void offerPickaxe(RecipeExporter recipeExporter, RecipeCategory recipeCategory,  ItemConvertible input, ItemConvertible output){
        ShapedRecipeJsonBuilder.create(recipeCategory, output)
                .pattern("RRR")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input),conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

    private void offerShovel(RecipeExporter recipeExporter, RecipeCategory recipeCategory,  ItemConvertible input, ItemConvertible output){
        ShapedRecipeJsonBuilder.create(recipeCategory, output)
                .pattern(" R ")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input),conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

    private void offerAxe(RecipeExporter recipeExporter, RecipeCategory recipeCategory,  ItemConvertible input, ItemConvertible output){
        ShapedRecipeJsonBuilder.create(recipeCategory, output)
                .pattern("RR ")
                .pattern("RS ")
                .pattern(" S ")
                .input('R', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input),conditionsFromItem(input))
                .offerTo(recipeExporter);
    }
    private void offerHoe(RecipeExporter recipeExporter, RecipeCategory recipeCategory,  ItemConvertible input, ItemConvertible output){
        ShapedRecipeJsonBuilder.create(recipeCategory, output)
                .pattern("RR ")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input),conditionsFromItem(input))
                .offerTo(recipeExporter);
    }

}
