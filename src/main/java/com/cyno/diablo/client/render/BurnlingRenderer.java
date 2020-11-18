package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.BurnlingModel;
import com.cyno.diablo.client.model.WardenModel;
import com.cyno.diablo.entities.BurnlingEntity;
import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BurnlingRenderer extends GeoEntityRenderer<BurnlingEntity> {
    public BurnlingRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BurnlingModel());
    }

    @Override
    protected int getBlockLight(BurnlingEntity entityIn, BlockPos partialTicks) {
        return 15;
    }
}
