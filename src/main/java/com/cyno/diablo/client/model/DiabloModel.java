package com.cyno.diablo.client.model;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko


import com.cyno.diablo.entities.DiabloEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class DiabloModel extends AnimatedEntityModel<DiabloEntity> {

	private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer right_arm;
	private final AnimatedModelRenderer right_arm2;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer right_leg;
	private final AnimatedModelRenderer left_leg;

	public DiabloModel()
	{
		textureWidth = 128;
		textureHeight = 128;
		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, -6.0F, -2.0F);
		body.setTextureOffset(0, 0).addBox(-9.0F, -11.0F, -3.0F, 18.0F, 29.0F, 11.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		right_arm = new AnimatedModelRenderer(this);
		right_arm.setRotationPoint(-9.0F, -13.0F, 0.0F);
		right_arm.setTextureOffset(58, 0).addBox(-8.0F, -4.0F, -4.0F, 8.0F, 30.0F, 8.0F, 0.0F, false);
		right_arm.setModelRendererName("right_arm");
		this.registerModelRenderer(right_arm);

		right_arm2 = new AnimatedModelRenderer(this);
		right_arm2.setRotationPoint(9.0F, -13.0F, 0.0F);
		right_arm2.setTextureOffset(48, 48).addBox(0.0F, -4.0F, -4.0F, 8.0F, 30.0F, 8.0F, 0.0F, false);
		right_arm2.setModelRendererName("right_arm2");
		this.registerModelRenderer(right_arm2);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -16.0F, -1.0F);
		head.setTextureOffset(0, 40).addBox(-7.0F, -17.0F, -6.0F, 14.0F, 16.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(58, 38).addBox(7.0F, -20.0F, -1.0F, 9.0F, 10.0F, 0.0F, 0.0F, false);
		head.setTextureOffset(38, 40).addBox(-16.0F, -20.0F, -1.0F, 9.0F, 10.0F, 0.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		right_leg = new AnimatedModelRenderer(this);
		right_leg.setRotationPoint(-6.0F, 12.0F, 0.0F);
		right_leg.setTextureOffset(24, 66).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);
		right_leg.setModelRendererName("right_leg");
		this.registerModelRenderer(right_leg);

		left_leg = new AnimatedModelRenderer(this);
		left_leg.setRotationPoint(6.0F, 12.0F, 0.0F);
		left_leg.setTextureOffset(0, 66).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);
		left_leg.setModelRendererName("left_leg");
		this.registerModelRenderer(left_leg);

		this.rootBones.add(body);
		this.rootBones.add(right_arm);
		this.rootBones.add(right_arm2);
		this.rootBones.add(head);
		this.rootBones.add(right_leg);
		this.rootBones.add(left_leg);
	}


	@Override
	public ResourceLocation getAnimationFileLocation()
	{
		return new ResourceLocation("diablomodif", "animations/diablo_entity.json");
	}
}