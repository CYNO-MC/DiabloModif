package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.WardenModel;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.provider.GeoModelProvider;
import software.bernie.geckolib.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib.renderers.geo.IGeoRenderer;

public class WardenRenderer extends GeoEntityRenderer<WardenEntity> {
    public WardenRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WardenModel());
    }
}
