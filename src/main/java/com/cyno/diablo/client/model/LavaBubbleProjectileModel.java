package com.cyno.diablo.client.model;

import com.cyno.diablo.entities.LavaBubbleProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LavaBubbleProjectileModel  <T extends LavaBubbleProjectileEntity> extends EntityModel<T> {

    private final ModelRenderer Body;

    public LavaBubbleProjectileModel() {
        textureWidth = 64;
        textureHeight = 64;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 19.0F, 0.0F);
        Body.setTextureOffset(0, 14).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);
        Body.setTextureOffset(22, 22).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
        Body.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
        this.Body.rotateAngleX = (entityIn.getRollDirection().getX() * limbSwing) * 0.4f;
        this.Body.rotateAngleZ = (entityIn.getRollDirection().getZ() * limbSwing) * 0.4f;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}
