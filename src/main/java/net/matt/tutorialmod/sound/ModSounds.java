package net.matt.tutorialmod.sound;

import net.matt.tutorialmod.TutorialMod;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent CHISEL_USE = registerSoundEvent("chisel_use");
    public static final SoundEvent MAGIC_BLOCK_BREAK = registerSoundEvent("magic_block_break");
    public static final SoundEvent MAGIC_BLOCK_STEP = registerSoundEvent("magic_block_step");
    public static final SoundEvent MAGIC_BLOCK_PLACE = registerSoundEvent("magic_block_place");
    public static final SoundEvent MAGIC_BLOCK_HIT = registerSoundEvent("magic_block_hit");
    public static final SoundEvent MAGIC_BLOCK_FALL = registerSoundEvent("magic_block_fall");

    //Bundle to a sound group
    public static final BlockSoundGroup MAGIC_SOUNDS_GROUP = new BlockSoundGroup(1f, 1f,
            MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE, MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL);

    public static final SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");
    public static final RegistryKey<JukeboxSong> BAR_BRAWL_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TutorialMod.MOD_ID, "bar_brawl"));

    public static final SoundEvent MOONLIGHT_SONATA = registerSoundEvent("moonlight_sonata");
    public static final RegistryKey<JukeboxSong> MOONLIGHT_SONATA_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TutorialMod.MOD_ID, "moonlight_sonata"));

    public static final SoundEvent KAKARIKO_VILLAGE = registerSoundEvent("kakariko_village");
    public static final RegistryKey<JukeboxSong> KAKARIKO_VILLAGE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TutorialMod.MOD_ID, "kakariko_village"));
    //Helper Method
    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(TutorialMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }


    public static void registerSounds(){
        TutorialMod.LOGGER.info("Registering mod sounds for " + TutorialMod.MOD_ID);
    }
}
