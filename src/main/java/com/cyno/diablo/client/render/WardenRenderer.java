package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.WardenModel;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WardenRenderer extends MobRenderer<WardenEntity, WardenModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/warden.png");

    public WardenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WardenModel(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(WardenEntity entity) {
        return TEXTURE;
    }

}
