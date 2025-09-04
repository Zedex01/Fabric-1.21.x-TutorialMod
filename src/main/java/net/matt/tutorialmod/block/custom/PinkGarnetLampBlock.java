package net.matt.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PinkGarnetLampBlock extends Block {
    //static bool would not work, would cause all the blocks in the world to be shared
    //public bool would not work either
    //so:
    public static final BooleanProperty CLICKED = BooleanProperty.of("clicked");

    public PinkGarnetLampBlock(Settings settings) {
        super(settings);

        //set the default state of this block when placed to be false
        setDefaultState(this.getDefaultState().with(CLICKED, false));
    }

    //on right click to change state


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient()){
            //At position cycle the state of property
            world.setBlockState(pos, state.cycle(CLICKED));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        //Add the boolean property to the block
        builder.add(CLICKED);
    }
}
