package net.matt.tutorialmod.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGen(){
        //Order Here Matters
        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
    }
}
