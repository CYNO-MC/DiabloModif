package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.DiabloModel;
import com.cyno.diablo.entities.DiabloEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DiabloRenderer extends MobRenderer<DiabloEntity, DiabloModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/custom_model.png");
    protected static final ResourceLocation TEXTURE_5 = new ResourceLocation(Diablo.MOD_ID, "textures/entity/custom_model1.png");

    public DiabloRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DiabloModel(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(DiabloEntity entity) {

        if(entity.getHealthData() < 20){
            return TEXTURE_5;
        }
        else{
            return TEXTURE;
        }
    }
}
