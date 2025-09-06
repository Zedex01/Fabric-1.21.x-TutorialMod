package net.matt.tutorialmod.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.matt.tutorialmod.item.custom.HammerItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before{
    //Done with the help of the COFHCore and KaupenJoe

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();


    //This event is called just before a block is broken!
    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity) {

        //get the stack currently in the players main hand
        ItemStack mainHandItem = player.getMainHandStack();

        //is this block being broken with a hammer? & are we on the server?
        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayerEntity serverPlayer){
            //Is this block in the harvested blocks set??
            if(HARVESTED_BLOCKS.contains(pos)){
                return true; //destorys the block
            }


            //For each position within ".getBlocksToBeDestoryed()"
            for(BlockPos position : HammerItem.getBlocksToBeDestroyed(1, pos, serverPlayer)){
                if(pos == position || !hammer.isCorrectForDrops(mainHandItem, world.getBlockState(position))){
                    continue; //if it is one we already broke, skip it and go to next block
                }

                HARVESTED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position); //THIS IS RECURSIVE AND CALLS THIS WHOLE METHOD AGAIN
                HARVESTED_BLOCKS.remove(position); //That is why we must remove it!
            }
        }
        return true;
    }
}
