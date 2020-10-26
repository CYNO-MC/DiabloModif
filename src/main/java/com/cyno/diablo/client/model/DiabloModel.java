package com.cyno.diablo.client.model;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko


import com.cyno.diablo.entities.DiabloEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class DiabloModel<T extends DiabloEntity> extends EntityModel<T> {
	private final ModelRenderer diabloWaist;
	private final ModelRenderer diabloBelly;
	private final ModelRenderer diabloChest;
	private final ModelRenderer diabloNeckJoint;
	private final ModelRenderer diabloHead;
	private final ModelRenderer diabloJaw;
	private final ModelRenderer diabloRightHorn;
	private final ModelRenderer diabloRightHornTop;
	private final ModelRenderer diabloLeftHorn;
	private final ModelRenderer diabloLeftHornTop;
	private final ModelRenderer diabloEye;
	private final ModelRenderer diabloArmsJoint;
	private final ModelRenderer diabloRightArm;
	private final ModelRenderer diabloRightFist;
	private final ModelRenderer filament1;
	private final ModelRenderer filament2;
	private final ModelRenderer filament3;
	private final ModelRenderer diabloLeftArm;
	private final ModelRenderer diabloLeftFist;
	private final ModelRenderer filament5;
	private final ModelRenderer filament6;
	private final ModelRenderer filament4;
	private final ModelRenderer diabloNeck;
	private final ModelRenderer diabloLegsJoint;
	private final ModelRenderer diabloRightLeg;
	private final ModelRenderer diabloLeftLeg;

	public DiabloModel() {
		textureWidth = 256;
		textureHeight = 128;

		diabloWaist = new ModelRenderer(this);
		diabloWaist.setRotationPoint(0.0F, -11.0F, 0.0F);
		setRotationAngle(diabloWaist, -0.192F, 0.0F, 0.0F);
		diabloWaist.setTextureOffset(0, 92).addBox(-11.5F, -5.0F, -7.0F, 23.0F, 14.0F, 13.0F, 0.0F, false);

		diabloBelly = new ModelRenderer(this);
		diabloBelly.setRotationPoint(0.0F, -3.5F, 0.5F);
		diabloWaist.addChild(diabloBelly);
		setRotationAngle(diabloBelly, 0.1571F, 0.0F, 0.0F);
		diabloBelly.setTextureOffset(0, 67).addBox(-10.5F, -13.0F, -7.0F, 21.0F, 13.0F, 12.0F, 0.0F, false);

		diabloChest = new ModelRenderer(this);
		diabloChest.setRotationPoint(0.0F, -12.5F, 1.0F);
		diabloBelly.addChild(diabloChest);
		setRotationAngle(diabloChest, 0.2443F, 0.0F, 0.0F);
		diabloChest.setTextureOffset(0, 32).addBox(-17.5F, -21.0F, -9.0F, 35.0F, 21.0F, 14.0F, 0.0F, false);

		diabloNeckJoint = new ModelRenderer(this);
		diabloNeckJoint.setRotationPoint(0.0F, -24.9F, -3.0F);
		diabloChest.addChild(diabloNeckJoint);
		setRotationAngle(diabloNeckJoint, -0.2443F, 0.0F, 0.0F);
		diabloNeckJoint.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);

		diabloHead = new ModelRenderer(this);
		diabloHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		diabloNeckJoint.addChild(diabloHead);
		diabloHead.setTextureOffset(0, 0).addBox(-9.5F, -8.0F, -8.0F, 19.0F, 9.0F, 10.0F, 0.0F, false);

		diabloJaw = new ModelRenderer(this);
		diabloJaw.setRotationPoint(0.0F, -1.5F, -1.0F);
		diabloHead.addChild(diabloJaw);
		diabloJaw.setTextureOffset(0, 19).addBox(-5.5F, 0.0F, -6.0F, 11.0F, 5.0F, 8.0F, 0.0F, false);

		diabloRightHorn = new ModelRenderer(this);
		diabloRightHorn.setRotationPoint(10.0F, -6.0F, -3.0F);
		diabloHead.addChild(diabloRightHorn);
		setRotationAngle(diabloRightHorn, 0.0F, 0.0F, 1.3963F);
		diabloRightHorn.setTextureOffset(42, 19).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);

		diabloRightHornTop = new ModelRenderer(this);
		diabloRightHornTop.setRotationPoint(0.0F, -7.5F, 0.0F);
		diabloRightHorn.addChild(diabloRightHornTop);
		setRotationAngle(diabloRightHornTop, 0.0F, 0.0F, -1.0472F);
		diabloRightHornTop.setTextureOffset(38, 19).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		diabloLeftHorn = new ModelRenderer(this);
		diabloLeftHorn.setRotationPoint(-10.0F, -6.0F, -3.0F);
		diabloHead.addChild(diabloLeftHorn);
		setRotationAngle(diabloLeftHorn, 0.0F, 0.0F, -1.3963F);
		diabloLeftHorn.setTextureOffset(42, 19).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);

		diabloLeftHornTop = new ModelRenderer(this);
		diabloLeftHornTop.setRotationPoint(0.0F, -7.5F, 0.0F);
		diabloLeftHorn.addChild(diabloLeftHornTop);
		setRotationAngle(diabloLeftHornTop, 0.0F, 0.0F, 1.0472F);
		diabloLeftHornTop.setTextureOffset(38, 19).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		diabloEye = new ModelRenderer(this);
		diabloEye.setRotationPoint(0.0F, -15.5F, 4.0F);
		diabloChest.addChild(diabloEye);
		setRotationAngle(diabloEye, -1.4835F, 0.0F, 0.0F);
		diabloEye.setTextureOffset(58, 0).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F, false);

		diabloArmsJoint = new ModelRenderer(this);
		diabloArmsJoint.setRotationPoint(0.0F, -17.0F, -2.0F);
		diabloChest.addChild(diabloArmsJoint);
		setRotationAngle(diabloArmsJoint, -0.2094F, 0.0F, 0.0F);
		diabloArmsJoint.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);

		diabloRightArm = new ModelRenderer(this);
		diabloRightArm.setRotationPoint(17.5F, 0.0F, 0.0F);
		diabloArmsJoint.addChild(diabloRightArm);
		diabloRightArm.setTextureOffset(86, 0).addBox(0.0F, -3.0F, -3.5F, 6.0F, 22.0F, 7.0F, 0.0F, false);

		diabloRightFist = new ModelRenderer(this);
		diabloRightFist.setRotationPoint(3.0F, 18.0F, 0.0F);
		diabloRightArm.addChild(diabloRightFist);
		diabloRightFist.setTextureOffset(112, 0).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 27.0F, 8.0F, 0.0F, false);

		filament1 = new ModelRenderer(this);
		filament1.setRotationPoint(6.0F, 1.0F, 0.2F);
		diabloRightArm.addChild(filament1);
		setRotationAngle(filament1, 0.0873F, -0.089F, -0.1564F);
		filament1.setTextureOffset(50, 19).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);

		filament2 = new ModelRenderer(this);
		filament2.setRotationPoint(5.3F, 6.0F, 0.0F);
		diabloRightArm.addChild(filament2);
		setRotationAngle(filament2, -0.0873F, 0.0873F, -0.2094F);
		filament2.setTextureOffset(54, 19).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);

		filament3 = new ModelRenderer(this);
		filament3.setRotationPoint(5.3F, -3.0F, -0.4F);
		diabloRightArm.addChild(filament3);
		setRotationAngle(filament3, -0.0091F, 0.0091F, -0.2485F);
		filament3.setTextureOffset(54, 19).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);

		diabloLeftArm = new ModelRenderer(this);
		diabloLeftArm.setRotationPoint(-17.5F, 0.0F, 0.0F);
		diabloArmsJoint.addChild(diabloLeftArm);
		diabloLeftArm.setTextureOffset(142, 0).addBox(-6.0F, -3.0F, -3.5F, 6.0F, 22.0F, 7.0F, 0.0F, false);

		diabloLeftFist = new ModelRenderer(this);
		diabloLeftFist.setRotationPoint(-3.0F, 18.0F, 0.0F);
		diabloLeftArm.addChild(diabloLeftFist);
		diabloLeftFist.setTextureOffset(168, 0).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 27.0F, 8.0F, 0.0F, false);

		filament5 = new ModelRenderer(this);
		filament5.setRotationPoint(-6.0F, 2.0F, 0.4F);
		diabloLeftArm.addChild(filament5);
		setRotationAngle(filament5, 0.1264F, -0.089F, 0.1564F);
		filament5.setTextureOffset(50, 19).addBox(0.0F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);

		filament6 = new ModelRenderer(this);
		filament6.setRotationPoint(-5.3F, 6.0F, 0.0F);
		diabloLeftArm.addChild(filament6);
		setRotationAngle(filament6, -0.1473F, -0.2436F, 0.2094F);
		filament6.setTextureOffset(54, 19).addBox(0.0F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);

		filament4 = new ModelRenderer(this);
		filament4.setRotationPoint(-5.8F, -1.0F, 0.0F);
		diabloLeftArm.addChild(filament4);
		setRotationAngle(filament4, 0.0091F, -0.2436F, 0.2094F);
		filament4.setTextureOffset(54, 20).addBox(0.0F, 0.0F, -0.5F, 1.0F, 11.0F, 1.0F, 0.0F, false);

		diabloNeck = new ModelRenderer(this);
		diabloNeck.setRotationPoint(0.0F, -22.0F, 3.0F);
		diabloChest.addChild(diabloNeck);
		setRotationAngle(diabloNeck, 0.8727F, 0.0F, 0.0F);
		diabloNeck.setTextureOffset(58, 14).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 8.0F, 3.0F, 0.0F, false);

		diabloLegsJoint = new ModelRenderer(this);
		diabloLegsJoint.setRotationPoint(0.0F, 0.5F, -0.5F);
		diabloWaist.addChild(diabloLegsJoint);
		setRotationAngle(diabloLegsJoint, 0.192F, 0.0F, 0.0F);
		diabloLegsJoint.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);

		diabloRightLeg = new ModelRenderer(this);
		diabloRightLeg.setRotationPoint(9.5F, 0.0F, 0.0F);
		diabloLegsJoint.addChild(diabloRightLeg);
		diabloRightLeg.setTextureOffset(98, 35).addBox(0.0F, -1.0F, -3.5F, 7.0F, 36.0F, 7.0F, 0.0F, false);

		diabloLeftLeg = new ModelRenderer(this);
		diabloLeftLeg.setRotationPoint(-9.5F, 0.0F, 0.0F);
		diabloLegsJoint.addChild(diabloLeftLeg);
		diabloLeftLeg.setTextureOffset(126, 35).addBox(-7.0F, -1.0F, -3.5F, 7.0F, 36.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DiabloEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)  {
		this.diabloHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.diabloHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.diabloRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.diabloLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.diabloLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.diabloRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		diabloWaist.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}