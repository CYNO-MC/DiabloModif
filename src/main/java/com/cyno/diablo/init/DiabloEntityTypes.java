package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.AmbiantWardenSoundParticleEntity;
import com.cyno.diablo.entities.DiabloEntity;
import com.cyno.diablo.entities.DistortedEntity;
import com.cyno.diablo.entities.WardenEntity;
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

}

