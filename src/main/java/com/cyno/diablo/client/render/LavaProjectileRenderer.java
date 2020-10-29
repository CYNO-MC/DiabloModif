package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.LavaBubbleProjectileModel;
import com.cyno.diablo.entities.LavaBubbleProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class LavaProjectileRenderer extends MobRenderer<LavaBubbleProjectileEntity, LavaBubbleProjectileModel<LavaBubbleProjectileEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/lava_bubble.png");

    public LavaProjectileRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LavaBubbleProjectileModel(), 0.3f);
    }

    protected int getBlockLight(LavaBubbleProjectileEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(LavaBubbleProjectileEntity entity) {
        return TEXTURE;
    }
}
