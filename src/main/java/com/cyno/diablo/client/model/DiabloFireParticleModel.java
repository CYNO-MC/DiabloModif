package com.cyno.diablo.client.model;

import com.cyno.diablo.entities.AmbiantWardenSoundParticleEntity;
import com.cyno.diablo.entities.DiabloFireParticleEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DiabloFireParticleModel extends EntityModel<DiabloFireParticleEntity>{
    private final ModelRenderer bb_main;
    private final ModelRenderer bone;

    public DiabloFireParticleModel()
    {
        textureWidth = 32;
        textureHeight = 32;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.setTextureOffset(0, 5).addBox(-2.5F, 0.0F, -2.5F, 5, 0, 5, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(bone, 0.0F, 0.0F, 1.5708F);
        bone.setTextureOffset(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5, 0, 5, 0.0F, false);
    }

    @Override
    public void setRotationAngles(DiabloFireParticleEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        bb_main.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red,green,blue, alpha );
        bone.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red,green,blue, alpha );
    }
}
