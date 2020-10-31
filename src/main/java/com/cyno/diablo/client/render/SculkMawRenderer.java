package com.cyno.diablo.client.render;

import com.cyno.diablo.client.model.SculkMawModel;
import com.cyno.diablo.entities.SculkMawEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib.renderers.geo.GeoEntityRenderer;

public class SculkMawRenderer extends GeoEntityRenderer<SculkMawEntity> {
    public SculkMawRenderer(EntityRendererManager renderManager){
        super(renderManager, new SculkMawModel());
    }
}
