package com.cyno.diablo.client.model;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class WardenModel extends AnimatedGeoModel<WardenEntity> {
    @Override
    public ResourceLocation getModelLocation(WardenEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "geo/warden_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WardenEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "textures/entity/warden.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WardenEntity object)
    {
        return new ResourceLocation(Diablo.MOD_ID, "animations/warden_entity.json");
    }
}
