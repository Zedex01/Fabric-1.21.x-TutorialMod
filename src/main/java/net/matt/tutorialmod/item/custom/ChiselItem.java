package net.matt.tutorialmod.item.custom;

import net.matt.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            Blocks.END_STONE, Blocks.END_STONE_BRICKS,
            Blocks.NETHERRACK, Blocks.NETHER_BRICKS,
            Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
            Blocks.OAK_LOG, ModBlocks.PINK_GARNET_BLOCK
    );

    public ChiselItem(Settings settings) {
        super(settings);
    }

    //want to make this item do something when clicking on another block with it:
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) { //ItemUsageContext allows for a lot of information to be gotten
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock(); //gets the block located at the location that was clicked

        //Check if block is part of map
        if(CHISEL_MAP.containsKey(clickedBlock)){
            //Makes this only occur on server side rather than client
            if(!world.isClient()){
                //swap block for new block
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                //damage the held item
                context.getStack().damage(1,((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                //Play sound
                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);
            }
        }
        //Causes the hand to show using of the item
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //Hold Down shift to view tool tip
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.tutorialmod.chisel.shift_down"));

        }
        else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.chisel"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
