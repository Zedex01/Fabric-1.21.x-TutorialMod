package net.matt.tutorialmod.component;

import net.matt.tutorialmod.TutorialMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    //CODEC is a Block Position
    //Serialized and de-serialized via the codec
    public static final ComponentType<BlockPos> COORDINATES =
            register("coordinates", builder -> builder.codec(BlockPos.CODEC));

    //Data component builder helper method:
    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TutorialMod.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    //register method called in TutorialMod.Java
    public static void registerDataComponentTypes(){
        TutorialMod.LOGGER.info("Registering Data Component Types for " + TutorialMod.MOD_ID);
    }
}
