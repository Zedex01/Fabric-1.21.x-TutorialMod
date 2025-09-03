package net.matt.tutorialmod.block.custom;

import net.matt.tutorialmod.item.ModItems;
import net.matt.tutorialmod.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class MagicBlock extends Block {
    public MagicBlock(Settings settings) {
        super(settings);
    }


    //onUse called when you right-click a block with no item in your hand
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        //Play sound at player pos
        world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1f, 1f);

        return ActionResult.SUCCESS;
    }


    //When an item is thrown onto the block, transforms the item into something new
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        //Check if the entity is the item
        if(entity instanceof ItemEntity itemEntity){
            //Check the type of item
            if(isValidItem(itemEntity.getStack())){ //Checks the tag from transformable items
                //Converts stack item into diamond, of count stack equal to stack count
                itemEntity.setStack(new ItemStack(Items.DIAMOND, itemEntity.getStack().getCount()));
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    //Returns true if the items is contained within the TRANSFORMABLE ITEMS tag
    private boolean isValidItem(ItemStack stack) {
        return stack.isIn(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    //Set Tooltip!
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        //set key to use in .lang file
        tooltip.add(Text.translatable("tooltip.tutorialmod.magic_block.tooltip"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
