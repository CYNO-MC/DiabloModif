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


    public static final RegistryObject<EntityType<DistortedEntity>> DISTORTED = ENTITY_TYPES.register("distorted",
            () -> EntityType.Builder.create(DistortedEntity::new, EntityClassification.MONSTER).size(1.0f, 2.0f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "distorted").toString()));

    public static final RegistryObject<EntityType<WardenEntity>> WARDEN = ENTITY_TYPES.register("warden",
            () -> EntityType.Builder.create(WardenEntity::new, EntityClassification.MONSTER).size(1.4f, 3.0f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "warden").toString()));

    public static final RegistryObject<EntityType<AmbiantWardenSoundParticleEntity>> WARDEN_SOUND_PARTICLES = ENTITY_TYPES.register("warden_sound_particles",
            () -> EntityType.Builder.create(AmbiantWardenSoundParticleEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "warden_sound_particles").toString()));

    public static final RegistryObject<EntityType<DiabloFireParticleEntity>> DIABLO_FIRE_PARTICLE = ENTITY_TYPES.register("diablo_fire_particle",
            () -> EntityType.Builder.create(DiabloFireParticleEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "diablo_fire_particle").toString()));
    public static final RegistryObject<EntityType<BurnlingEntity>> BURNLING = ENTITY_TYPES.register("burnling",
            () -> EntityType.Builder.create(BurnlingEntity::new, EntityClassification.MONSTER).size(1f, 1f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "burnling").toString()));

    public static final RegistryObject<EntityType<LavaBubbleProjectileEntity>> LAVA_BUBBLE = ENTITY_TYPES.register("lava_bubble",
            () -> EntityType.Builder.create(LavaBubbleProjectileEntity::new, EntityClassification.MONSTER).size(0.6f, 0.6f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "lava_bubble").toString()));

}

