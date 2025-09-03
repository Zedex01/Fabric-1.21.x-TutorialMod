package net.matt.tutorialmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;

public class PinkGarnetHammerItem extends Item {

    public PinkGarnetHammerItem(Settings settings) {
        super(settings);
    }

    //When an entity is hit
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayerEntity serverPlayerEntity){
            ServerWorld serverWorld = (ServerWorld)attacker.getWorld();

            serverWorld.spawnEntity(new LightningEntity(EntityType.LIGHTNING_BOLT, serverWorld));

            //Play Sound
            serverWorld.playSound(
                    null,
                    serverPlayerEntity.getX(),
                    serverPlayerEntity.getY(),
                    serverPlayerEntity.getZ(),
                    SoundEvents.ITEM_MACE_SMASH_GROUND,
                    serverPlayerEntity.getSoundCategory(),
                    1.0F, 1.0F
            );

        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}
