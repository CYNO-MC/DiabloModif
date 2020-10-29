package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.BurnlingModel;
import com.cyno.diablo.client.model.WardenModel;
import com.cyno.diablo.entities.BurnlingEntity;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BurnlingRenderer extends MobRenderer<BurnlingEntity, BurnlingModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/burnling.png");

    public BurnlingRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BurnlingModel(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(BurnlingEntity entity) {
        return TEXTURE;
    }
}
