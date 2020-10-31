package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DiabloEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Diablo.MOD_ID);

    //Entity Types
    public static final RegistryObject<EntityType<DiabloEntity>> DIABLO = ENTITY_TYPES.register("diablo",
            () -> EntityType.Builder.create(DiabloEntity::new, EntityClassification.MONSTER).size(1.4f, 5.5f)
            .build(new ResourceLocation(Diablo.MOD_ID, "diablo").toString()));

    public static final RegistryObject<EntityType<WardenEntity>> WARDEN = ENTITY_TYPES.register("warden",
            () -> EntityType.Builder.create(WardenEntity::new, EntityClassification.MONSTER).size(1.4f, 3.0f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "warden").toString()));

    public static final RegistryObject<EntityType<WardenEntity>> SCULKMAW = ENTITY_TYPES.register("sculkmaw",
            () -> EntityType.Builder.create(WardenEntity::new, EntityClassification.MONSTER).size(1.4f, 3.0f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "warden").toString()));

    public static final RegistryObject<EntityType<AmbiantWardenSoundParticleEntity>> WARDEN_SOUND_PARTICLES = ENTITY_TYPES.register("warden_sound_particles",
            () -> EntityType.Builder.create(AmbiantWardenSoundParticleEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "warden_sound_particles").toString()));

    public static final RegistryObject<EntityType<DiabloFireParticleEntity>> DIABLO_FIRE_PARTICLE = ENTITY_TYPES.register("diablo_fire_particle",
            () -> EntityType.Builder.create(DiabloFireParticleEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "diablo_fire_particle").toString()));

}

