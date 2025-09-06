package net.matt.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends MiningToolItem {

    //Constructor
    public HammerItem(ToolMaterial material, Settings settings) {
        //Since the effective blocks are the same as the pickaxe, we can use that
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }



    //Create a method that returns a list of all the block positions that will be destroyed
    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayerEntity player){
        /* Range: How many blocks from the hammer is affected
        * InitialBlockPos: the position of the block actually being mined by the player.
        * player: The actual player using the item.
        */

        //Create a list of block positions that will be affected:
        List<BlockPos> positions = new ArrayList<>();

        HitResult hit = player.raycast(20,0,false); //cast a raycast a distance of 20 units (blocks?), instantly, ignoring fluids ()
        /*
        HitResult has 3 main fields
            POS. xyz of the hit item
            TYPE, and enum of type MISS, ENTITY, or BLOCK depending on what is hit.
            The method: SquaredDistanceToENtity. Calculates the distance from the palyer to the hit enemy!
         */

        if(hit.getType() == HitResult.Type.BLOCK){
            BlockHitResult blockHit = (BlockHitResult) hit; //Cast to BlockHitResult type (extends HitResult)

            //if the block was hit on the top or bottom
            if(blockHit.getSide() == Direction.DOWN || blockHit.getSide() == Direction.UP){
                for(int x = -range; x <= range; x++){
                    for (int z = -range; z <= range; z++){
                        positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + z));
                    }
                }
            }

            //if the block was hit from the north or south
            if(blockHit.getSide() == Direction.NORTH || blockHit.getSide() == Direction.SOUTH){
                for(int x = -range; x <= range; x++){
                    for (int y = -range; y <= range; y++){
                        positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                    }
                }
            }

            //if the block was hit from east or west
            if(blockHit.getSide() == Direction.EAST || blockHit.getSide() == Direction.WEST){
                for(int z = -range; z <= range; z++){
                    for (int y = -range; y <= range; y++){
                        positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + z));
                    }
                }
            }
        }

        //Return the list of blocks
        return  positions;
    }

}
