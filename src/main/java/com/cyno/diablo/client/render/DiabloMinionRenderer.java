package com.cyno.diablo.client.render;

import com.cyno.diablo.client.model.DiabloMinionModel;
import com.cyno.diablo.entities.DiabloMinionEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib.renderers.geo.GeoEntityRenderer;

public class DiabloMinionRenderer extends GeoEntityRenderer<DiabloMinionEntity> {
    public DiabloMinionRenderer(EntityRendererManager renderManager){
        super(renderManager, new DiabloMinionModel());
    }

    // Overriding Death Animation
    @Override
    protected float getDeathMaxRotation(DiabloMinionEntity entityLivingBaseIn) {
        return 0.0f;
    }
}