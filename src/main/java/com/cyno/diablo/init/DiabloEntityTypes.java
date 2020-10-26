package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
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
            () -> EntityType.Builder.create(DiabloEntity::new, EntityClassification.MONSTER).size(3.5f, 5.5f)
            .build(new ResourceLocation(Diablo.MOD_ID, "diablo").toString()));


    public static final RegistryObject<EntityType<DistortedEntity>> DISTORTED = ENTITY_TYPES.register("distorted",
            () -> EntityType.Builder.create(DistortedEntity::new, EntityClassification.MONSTER).size(1.0f, 2.0f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "distorted").toString()));

    public static final RegistryObject<EntityType<WardenEntity>> WARDEN = ENTITY_TYPES.register("warden",
            () -> EntityType.Builder.create(WardenEntity::new, EntityClassification.MONSTER).size(2.0f, 2.0f)
                    .build(new ResourceLocation(Diablo.MOD_ID, "warden").toString()));

}

