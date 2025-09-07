package net.matt.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.matt.tutorialmod.block.ModBlocks;
import net.matt.tutorialmod.component.ModDataComponentTypes;
import net.matt.tutorialmod.item.ModItemGroups;
import net.matt.tutorialmod.item.ModItems;
import net.matt.tutorialmod.util.HammerUsageEvent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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

        //Data Component Types:
        ModDataComponentTypes.registerDataComponentTypes();

        //Define item as Fuel item:
        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

        //Register event
        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

        //Using Event
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult)-> {
            if(entity instanceof SheepEntity sheepEntity && !world.isClient()){
                if(player.getMainHandStack().getItem() == Items.END_ROD){
                    player.sendMessage(Text.literal("The player just hit a sheep with an end rod you sick Freak!"));
                    player.getMainHandStack().decrement(1); //remove 1 count from the stack item
                    sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 6));
                }

                return ActionResult.PASS;
            }
            return ActionResult.PASS;
        });

	}
}