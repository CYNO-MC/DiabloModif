package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.AmbiantWardenSoundParticleModel;
import com.cyno.diablo.client.model.DiabloFireParticleModel;
import com.cyno.diablo.entities.AmbiantWardenSoundParticleEntity;
import com.cyno.diablo.entities.DiabloFireParticleEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class DiabloFireParticleRenderer extends MobRenderer<DiabloFireParticleEntity, DiabloFireParticleModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/fire_particle.png");

    public DiabloFireParticleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DiabloFireParticleModel(), 0);
    }

    @Override
    protected int getBlockLight(DiabloFireParticleEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(DiabloFireParticleEntity entity) {
        return TEXTURE;
    }
}
