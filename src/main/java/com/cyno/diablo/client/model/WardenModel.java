package com.cyno.diablo.client.model;

import com.cyno.diablo.entities.WardenEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.MathHelper;

public class WardenModel<T extends WardenEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer right_arm;
    private final ModelRenderer right_arm2;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer head;

    public WardenModel() {
        textureWidth = 128;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 1.0F, 0.0F);
        body.setTextureOffset(0, 26).addBox(-9.0F, -11.0F, -5.0F, 18.0F, 21.0F, 11.0F, 0.0F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-11.0F, -5.0F, 0.0F);
        right_arm.setTextureOffset(52, 0).addBox(-6.0F, -5.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);

        right_arm2 = new ModelRenderer(this);
        right_arm2.setRotationPoint(11.0F, -5.0F, 0.0F);
        right_arm2.setTextureOffset(84, 0).addBox(-2.0F, -5.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-6.0F, 11.0F, 0.0F);
        right_leg.setTextureOffset(58, 36).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(6.0F, 11.0F, 0.0F);
        left_leg.setTextureOffset(82, 36).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -9.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-8.0F, -17.0F, -6.0F, 16.0F, 16.0F, 10.0F, 0.0F, false);
        head.setTextureOffset(106, 46).addBox(8.0F, -20.0F, -1.0F, 10.0F, 10.0F, 0.0F, 0.0F, false);
        head.setTextureOffset(106, 36).addBox(-18.0F, -20.0F, -1.0F, 10.0F, 10.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * entityIn.getAnimSpeed() * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * entityIn.getAnimSpeed() * limbSwingAmount;
        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * entityIn.getAnimSpeed() * limbSwingAmount;
        this.right_arm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * entityIn.getAnimSpeed() * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm2.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

