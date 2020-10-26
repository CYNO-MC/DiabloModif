package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Diablo.MOD_ID);

    public static final RegistryObject<SoundEvent> AMBIENT = SOUNDS.register("entity.diablo_entity.ambient", () ->
            new SoundEvent(new ResourceLocation(Diablo.MOD_ID, "entity.diablo_entity.ambient")));

    public static final RegistryObject<SoundEvent> DAMAGE = SOUNDS.register("entity.diablo_entity.damage", () ->
            new SoundEvent(new ResourceLocation(Diablo.MOD_ID, "entity.diablo_entity.damage")));

    public static final RegistryObject<SoundEvent> STEP = SOUNDS.register("entity.warden_entity.step", () ->
            new SoundEvent(new ResourceLocation(Diablo.MOD_ID, "entity.warden_entity.step")));

    public static final RegistryObject<SoundEvent> WDAMAGE = SOUNDS.register("entity.warden_entity.damage", () ->
            new SoundEvent(new ResourceLocation(Diablo.MOD_ID, "entity.warden_entity.damage")));

    public static final RegistryObject<SoundEvent> WAMBIENT = SOUNDS.register("entity.warden_entity.ambient", () ->
            new SoundEvent(new ResourceLocation(Diablo.MOD_ID, "entity.warden_entity.ambient")));
    
}
