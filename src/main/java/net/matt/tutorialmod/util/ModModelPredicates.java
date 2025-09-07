package net.matt.tutorialmod.util;

import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.component.ModDataComponentTypes;
import net.matt.tutorialmod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates(){
        ModelPredicateProviderRegistry.register(ModItems.CHISEL, Identifier.of(TutorialMod.MOD_ID, "used"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.COORDINATES) != null ? 1f : 0f);

        //From viewing this, you can pull info from, stack, world, entity, or seed. This means we can reference the player health,
        // or what biome you are in and adjust the textures based on that info
    }
}
