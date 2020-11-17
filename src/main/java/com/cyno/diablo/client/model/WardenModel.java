package com.cyno.diablo.client.model;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.mclib.math.Variable;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

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
        return new ResourceLocation(Diablo.MOD_ID, "animations/warden_entity_molang.json");
    }

    @Override
    public void setMolangQueries(IAnimatable animatable, double currentTick) {
        super.setMolangQueries(animatable, currentTick);
        MolangParser parser = GeckoLibCache.getInstance().parser;

        // allows the value of WardenEntity#getAnimationSpeed to be access from molang animation with query.anim_speed
        parser.setValue("query.anim_speed", ((WardenEntity)animatable).getAnimationSpeed());
    }
}
