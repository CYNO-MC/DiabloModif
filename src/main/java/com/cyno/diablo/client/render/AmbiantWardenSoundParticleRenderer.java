package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.AmbiantWardenSoundParticleModel;
import com.cyno.diablo.client.model.DiabloModel;
import com.cyno.diablo.entities.AmbiantWardenSoundParticleEntity;
import com.cyno.diablo.entities.DiabloEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class AmbiantWardenSoundParticleRenderer extends MobRenderer<AmbiantWardenSoundParticleEntity, AmbiantWardenSoundParticleModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/sound_particle.png");

    public AmbiantWardenSoundParticleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AmbiantWardenSoundParticleModel(), 0);
    }

    @Override
    protected int getBlockLight(AmbiantWardenSoundParticleEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(AmbiantWardenSoundParticleEntity entity) {
        return TEXTURE;
    }
}
