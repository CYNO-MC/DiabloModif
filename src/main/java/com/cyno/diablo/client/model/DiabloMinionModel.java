package com.cyno.diablo.client.model;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.DiabloMinionEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class DiabloMinionModel extends AnimatedGeoModel<DiabloMinionEntity> {
    @Override
    public ResourceLocation getModelLocation(DiabloMinionEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "geo/diablominion_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DiabloMinionEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "textures/entity/diablominion_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DiabloMinionEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "animations/diablominion_entity.json");
    }
}