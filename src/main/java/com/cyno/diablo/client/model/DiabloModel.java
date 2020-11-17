package com.cyno.diablo.client.model;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.DiabloEntity;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DiabloModel extends AnimatedGeoModel<DiabloEntity> {
    @Override
    public ResourceLocation getModelLocation(DiabloEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "geo/diablo_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DiabloEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "textures/entity/diablo_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DiabloEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "animations/diablo_entity.json");
    }
}
