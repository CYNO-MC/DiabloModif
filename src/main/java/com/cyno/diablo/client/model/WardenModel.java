package com.cyno.diablo.client.model;

import com.cyno.diablo.entities.WardenEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class WardenModel extends AnimatedEntityModel<WardenEntity> {

    private final AnimatedModelRenderer bone;
    private final AnimatedModelRenderer body;
    private final AnimatedModelRenderer right_arm;
    private final AnimatedModelRenderer right_arm2;
    private final AnimatedModelRenderer right_leg;
    private final AnimatedModelRenderer left_leg;
    private final AnimatedModelRenderer head;
    private final AnimatedModelRenderer earRight;
    private final AnimatedModelRenderer earLeft;

    public WardenModel()
    {
        textureWidth = 128;
        textureHeight = 64;
        bone = new AnimatedModelRenderer(this);
        bone.setRotationPoint(0.0F, 5.0F, 0.0F);

        bone.setModelRendererName("bone");
        this.registerModelRenderer(bone);

        body = new AnimatedModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(body);
        body.setTextureOffset(0, 26).addBox(-9.0F, -15.0F, -5.0F, 18.0F, 21.0F, 11.0F, 0.0F, false);
        body.setModelRendererName("body");
        this.registerModelRenderer(body);

        right_arm = new AnimatedModelRenderer(this);
        right_arm.setRotationPoint(-11.0F, -10.0F, 0.0F);
        bone.addChild(right_arm);
        right_arm.setTextureOffset(52, 0).addBox(-6.0F, -5.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);
        right_arm.setModelRendererName("right_arm");
        this.registerModelRenderer(right_arm);

        right_arm2 = new AnimatedModelRenderer(this);
        right_arm2.setRotationPoint(11.0F, -10.0F, 0.0F);
        bone.addChild(right_arm2);
        right_arm2.setTextureOffset(84, 0).addBox(-2.0F, -5.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);
        right_arm2.setModelRendererName("right_arm2");
        this.registerModelRenderer(right_arm2);

        right_leg = new AnimatedModelRenderer(this);
        right_leg.setRotationPoint(-6.0F, 6.0F, 0.0F);
        bone.addChild(right_leg);
        right_leg.setTextureOffset(58, 36).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);
        right_leg.setModelRendererName("right_leg");
        this.registerModelRenderer(right_leg);

        left_leg = new AnimatedModelRenderer(this);
        left_leg.setRotationPoint(6.0F, 6.0F, 0.0F);
        bone.addChild(left_leg);
        left_leg.setTextureOffset(82, 36).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);
        left_leg.setModelRendererName("left_leg");
        this.registerModelRenderer(left_leg);

        head = new AnimatedModelRenderer(this);
        head.setRotationPoint(-1.0F, -14.0F, 0.0F);
        bone.addChild(head);
        head.setTextureOffset(0, 0).addBox(-8.0F, -17.0F, -6.0F, 16.0F, 16.0F, 10.0F, 0.0F, false);
        head.setModelRendererName("head");
        this.registerModelRenderer(head);

        earRight = new AnimatedModelRenderer(this);
        earRight.setRotationPoint(-8.0F, -12.0F, 0.0F);
        head.addChild(earRight);
        earRight.setTextureOffset(106, 36).addBox(-10.0F, -8.0F, -1.0F, 10.0F, 10.0F, 0.0F, 0.0F, false);
        earRight.setModelRendererName("earRight");
        this.registerModelRenderer(earRight);

        earLeft = new AnimatedModelRenderer(this);
        earLeft.setRotationPoint(8.0F, -12.0F, 0.0F);
        head.addChild(earLeft);
        earLeft.setTextureOffset(106, 46).addBox(0.0F, -8.0F, -1.0F, 10.0F, 10.0F, 0.0F, 0.0F, false);
        earLeft.setModelRendererName("earLeft");
        this.registerModelRenderer(earLeft);

        this.rootBones.add(bone);
    }

    @Override
    public void setRotationAngles(WardenEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        head.rotateAngleX = -headPitch * ((float)Math.PI / 180);
        head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180);
    }

    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("diablomodif", "animations/warden_entity.json");
    }
}

