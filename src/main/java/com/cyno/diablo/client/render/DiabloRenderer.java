package com.cyno.diablo.client.render;

import com.cyno.diablo.client.model.DiabloModel;
import com.cyno.diablo.entities.DiabloEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DiabloRenderer extends GeoEntityRenderer<DiabloEntity> {
    public DiabloRenderer(EntityRendererManager renderManager){
        super(renderManager, new DiabloModel());
    }
}

