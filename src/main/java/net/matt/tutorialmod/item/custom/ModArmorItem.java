package net.matt.tutorialmod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.matt.tutorialmod.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stat;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    //Maps a specific armor material to a list of status effects (only works with custom materials, not vanilla)
    //can add multiple materials by extending off of .put
    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL,
                            List.of(new StatusEffectInstance(StatusEffects.HASTE, 400, 1, false, false),
                                    new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 1, false, false))).build();


    //Constructor
    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }


    //called every tick while this specific item stack is in your inventory
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient){ //is server?
            if(entity instanceof PlayerEntity player){ //is entity a player?
                if(hasFullSuitOfArmorOn(player)){ //do they have a full suit of armor on?
                    evaluateArmorEffects(player); //evaluate armor effects
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }


    //Cycle through each armor material and add all the status effects for each one as required
    private void evaluateArmorEffects(PlayerEntity player){
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)){
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect){
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect){
            for (StatusEffectInstance instance : mapStatusEffect){
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(),instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    //checks if the player is missing any armor
    private boolean hasFullSuitOfArmorOn(PlayerEntity player){
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        //only returns true if the player has all armor on
        return !boots.isEmpty() && !helmet.isEmpty() && !breastplate.isEmpty() && !leggings.isEmpty();
    }

    //Checks if the player has the correct set of armor on
    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player){
        for(ItemStack armorStack:player.getInventory().armor){
            if(!(armorStack.getItem() instanceof ArmorItem)){
                return false; //if there is no armor return false
            }
        }
        //get each armor item
        ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        //only return true if all armor pieces are made of the provided material
        return helmet.getMaterial() == material && breastplate.getMaterial() == material && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

}
