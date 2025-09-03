package net.matt.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.matt.tutorialmod.TutorialMod;
import net.matt.tutorialmod.item.custom.ChiselItem;
import net.matt.tutorialmod.item.custom.PinkGarnetHammerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    //Register Item(s)
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    //Registering custom item
    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));
    public static final Item PINK_GARNET_HAMMER = registerItem("pink_garnet_hammer", new PinkGarnetHammerItem(new Item.Settings().maxDamage(512)));

    //Food Items
    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)) {
        //Anonymous class
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.tutorialmod.cauliflower"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });


    //Fuel Items
    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()));


    //Helper Method to assist in registering items, simplifies the inputs necessary
    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),item);
    }

    //Method to call ModItems Initialization
    public static void registerModItems(){
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        //Add registered items, to Ingredients tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
            entries.add(CHISEL);
            entries.add(PINK_GARNET_HAMMER);
            entries.add(CAULIFLOWER);
            entries.add(STARLIGHT_ASHES);
        });
    }


}
