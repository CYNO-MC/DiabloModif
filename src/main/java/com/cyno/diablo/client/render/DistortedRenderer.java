package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.DistortedModel;
import com.cyno.diablo.entities.DistortedEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DistortedRenderer extends MobRenderer<DistortedEntity, DistortedModel<DistortedEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/distorted.png");

    public DistortedRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DistortedModel<>(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(DistortedEntity entity) {
        return TEXTURE;
    }
}
