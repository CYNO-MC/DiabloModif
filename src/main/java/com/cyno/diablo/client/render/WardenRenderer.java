package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.provider.GeoModelProvider;
import software.bernie.geckolib.renderers.geo.IGeoRenderer;

public class WardenRenderer extends MobRenderer implements IGeoRenderer {

    protected static final ResourceLocation WTEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/warden.png");

    public WardenRenderer(EntityRendererManager renderManagerIn, EntityModel entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return WTEXTURE;
    }

    @Override
    public GeoModelProvider getGeoModelProvider() {
        return W;
    }

    @Override
    public ResourceLocation getTextureLocation(Object o) {
        return WTEXTURE;
    }
}
