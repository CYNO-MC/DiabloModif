package com.cyno.diablo.client.model;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.BurnlingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BurnlingModel extends AnimatedGeoModel<BurnlingEntity> {
 /*   private final AnimatedModelRenderer Body;
    private final AnimatedModelRenderer UpperJaw;
    private final AnimatedModelRenderer spike8;
    private final AnimatedModelRenderer spike7;
    private final AnimatedModelRenderer spike2;
    private final AnimatedModelRenderer spike1;
    private final AnimatedModelRenderer spike3;
    private final AnimatedModelRenderer spike4;
    private final AnimatedModelRenderer spike5;
    private final AnimatedModelRenderer spike6;
    private final AnimatedModelRenderer spike9;
    private final AnimatedModelRenderer LowerJaw;
    private final AnimatedModelRenderer spike13;
    private final AnimatedModelRenderer spike12;
    private final AnimatedModelRenderer spike11;
    private final AnimatedModelRenderer spike10;
    private final AnimatedModelRenderer Base;
    private final AnimatedModelRenderer Leg4;
    private final AnimatedModelRenderer Leg41;
    private final AnimatedModelRenderer Leg3;
    private final AnimatedModelRenderer Leg31;
    private final AnimatedModelRenderer Leg2;
    private final AnimatedModelRenderer Leg21;
    private final AnimatedModelRenderer Leg1;
    private final AnimatedModelRenderer Leg11;

    public BurnlingModel()
    {
        textureWidth = 128;
        textureHeight = 128;
        Body = new AnimatedModelRenderer(this);
        Body.setRotationPoint(0.0F, 18.0F, 0.0F);

        Body.setModelRendererName("Body");
        this.registerModelRenderer(Body);

        UpperJaw = new AnimatedModelRenderer(this);
        UpperJaw.setRotationPoint(0.0F, -11.0F, 0.0F);
        Body.addChild(UpperJaw);
        UpperJaw.setTextureOffset(0, 16).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 6.0F, 8.0F, 0.0F, false);
        UpperJaw.setTextureOffset(28, 6).addBox(-4.0F, 5.0F, -5.0F, 8.0F, 2.0F, 10.0F, 0.0F, false);
        UpperJaw.setTextureOffset(0, 41).addBox(-5.0F, 5.0F, -4.0F, 10.0F, 2.0F, 8.0F, 0.0F, false);
        UpperJaw.setTextureOffset(28, 48).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);
        UpperJaw.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 6.0F, 10.0F, 0.0F, false);
        UpperJaw.setTextureOffset(0, 41).addBox(-5.75F, 1.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        UpperJaw.setTextureOffset(58, 48).addBox(-5.75F, 1.5F, 1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(57, 0).addBox(-5.75F, 1.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(0, 16).addBox(4.75F, 1.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        UpperJaw.setTextureOffset(54, 12).addBox(4.75F, 1.5F, 1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(56, 38).addBox(4.75F, 1.5F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(28, 41).addBox(-1.5F, 1.0F, 4.75F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(54, 9).addBox(1.5F, 1.5F, 4.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(34, 6).addBox(-2.5F, 1.5F, 4.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(26, 0).addBox(1.5F, 1.5F, -5.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(34, 0).addBox(-1.5F, 1.0F, -5.75F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        UpperJaw.setTextureOffset(28, 18).addBox(-2.5F, 1.5F, -5.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        UpperJaw.setModelRendererName("UpperJaw");
        this.registerModelRenderer(UpperJaw);

        spike8 = new AnimatedModelRenderer(this);
        spike8.setRotationPoint(0.0F, 2.0F, 0.0F);
        UpperJaw.addChild(spike8);
        setRotationAngle(spike8, -0.3491F, 2.3126F, 0.0F);
        spike8.setTextureOffset(50, 0).addBox(-1.0F, -1.5F, 5.1F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike8.setTextureOffset(30, 51).addBox(-0.5F, -1.0F, 7.1F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike8.setModelRendererName("spike8");
        this.registerModelRenderer(spike8);

        spike7 = new AnimatedModelRenderer(this);
        spike7.setRotationPoint(0.0F, 2.0F, 0.0F);
        UpperJaw.addChild(spike7);
        setRotationAngle(spike7, -0.3491F, 0.7854F, 0.0F);
        spike7.setTextureOffset(20, 59).addBox(-1.0F, -1.5F, 5.1F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike7.setTextureOffset(52, 52).addBox(-0.5F, -1.0F, 7.1F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike7.setModelRendererName("spike7");
        this.registerModelRenderer(spike7);

        spike2 = new AnimatedModelRenderer(this);
        spike2.setRotationPoint(0.0F, 2.0F, 0.0F);
        UpperJaw.addChild(spike2);
        setRotationAngle(spike2, -0.3491F, -0.6981F, 0.0F);
        spike2.setTextureOffset(44, 64).addBox(-1.0F, -1.5F, 5.1F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike2.setTextureOffset(52, 48).addBox(-0.5F, -1.0F, 7.1F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike2.setModelRendererName("spike2");
        this.registerModelRenderer(spike2);

        spike1 = new AnimatedModelRenderer(this);
        spike1.setRotationPoint(0.0F, 2.6088F, -0.7934F);
        UpperJaw.addChild(spike1);
        setRotationAngle(spike1, 0.6545F, 0.0F, 0.0F);
        spike1.setTextureOffset(54, 64).addBox(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike1.setTextureOffset(0, 54).addBox(-0.5F, -0.5F, 8.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike1.setModelRendererName("spike1");
        this.registerModelRenderer(spike1);

        spike3 = new AnimatedModelRenderer(this);
        spike3.setRotationPoint(0.0F, 2.0F, 0.0F);
        UpperJaw.addChild(spike3);
        setRotationAngle(spike3, -0.2618F, -2.2689F, 0.0F);
        spike3.setTextureOffset(17, 64).addBox(-1.0F, -1.0F, 5.3F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike3.setTextureOffset(52, 30).addBox(-0.5F, -0.5F, 7.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike3.setModelRendererName("spike3");
        this.registerModelRenderer(spike3);

        spike4 = new AnimatedModelRenderer(this);
        spike4.setRotationPoint(0.0F, 1.8F, 0.0F);
        UpperJaw.addChild(spike4);
        setRotationAngle(spike4, 1.5708F, 0.7418F, 0.0F);
        spike4.setTextureOffset(62, 9).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike4.setTextureOffset(52, 27).addBox(-0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike4.setModelRendererName("spike4");
        this.registerModelRenderer(spike4);

        spike5 = new AnimatedModelRenderer(this);
        spike5.setRotationPoint(0.0F, 2.0F, 0.0F);
        UpperJaw.addChild(spike5);
        setRotationAngle(spike5, 0.6545F, -1.4835F, 0.0F);
        spike5.setTextureOffset(37, 62).addBox(-1.0F, -1.0F, 4.4F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike5.setTextureOffset(52, 21).addBox(-0.5F, -0.5F, 6.4F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike5.setModelRendererName("spike5");
        this.registerModelRenderer(spike5);

        spike6 = new AnimatedModelRenderer(this);
        spike6.setRotationPoint(-0.011F, 2.1612F, 0.2528F);
        UpperJaw.addChild(spike6);
        setRotationAngle(spike6, 0.5672F, 3.098F, 0.0F);
        spike6.setTextureOffset(27, 62).addBox(-1.0F, -1.0F, 5.3F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike6.setTextureOffset(52, 18).addBox(-0.5F, -0.5F, 7.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike6.setModelRendererName("spike6");
        this.registerModelRenderer(spike6);

        spike9 = new AnimatedModelRenderer(this);
        spike9.setRotationPoint(0.0F, 2.0F, 0.0F);
        UpperJaw.addChild(spike9);
        setRotationAngle(spike9, 0.6545F, 1.5708F, 0.0F);
        spike9.setTextureOffset(0, 35).addBox(-1.0F, -1.0F, 4.4F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike9.setTextureOffset(0, 51).addBox(-0.5F, -0.5F, 6.4F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike9.setModelRendererName("spike9");
        this.registerModelRenderer(spike9);

        LowerJaw = new AnimatedModelRenderer(this);
        LowerJaw.setRotationPoint(0.0F, -11.0F, 0.0F);
        Body.addChild(LowerJaw);
        LowerJaw.setTextureOffset(52, 18).addBox(-5.0F, 7.0F, -4.0F, 10.0F, 1.0F, 8.0F, 0.0F, false);
        LowerJaw.setTextureOffset(26, 26).addBox(-4.0F, 5.0F, -5.0F, 8.0F, 2.0F, 10.0F, 0.0F, false);
        LowerJaw.setTextureOffset(28, 38).addBox(-5.0F, 5.0F, -4.0F, 10.0F, 2.0F, 8.0F, 0.0F, false);
        LowerJaw.setTextureOffset(52, 27).addBox(-4.0F, 8.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
        LowerJaw.setTextureOffset(0, 51).addBox(-3.5F, 8.8F, -3.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
        LowerJaw.setTextureOffset(0, 30).addBox(-4.0F, 7.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
        LowerJaw.setModelRendererName("LowerJaw");
        this.registerModelRenderer(LowerJaw);

        spike13 = new AnimatedModelRenderer(this);
        spike13.setRotationPoint(-0.8639F, 5.492F, -0.8639F);
        LowerJaw.addChild(spike13);
        setRotationAngle(spike13, -0.7854F, -2.3126F, 0.0F);
        spike13.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, 3.95F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike13.setTextureOffset(36, 18).addBox(-0.5F, -1.0F, 5.95F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike13.setModelRendererName("spike13");
        this.registerModelRenderer(spike13);

        spike12 = new AnimatedModelRenderer(this);
        spike12.setRotationPoint(-0.8639F, 5.492F, -0.8639F);
        LowerJaw.addChild(spike12);
        setRotationAngle(spike12, -0.7418F, -0.5672F, 0.0F);
        spike12.setTextureOffset(0, 5).addBox(-1.0F, -1.8F, 4.75F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike12.setTextureOffset(44, 18).addBox(-0.5F, -1.3F, 6.75F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike12.setModelRendererName("spike12");
        this.registerModelRenderer(spike12);

        spike11 = new AnimatedModelRenderer(this);
        spike11.setRotationPoint(-0.8639F, 5.492F, -0.8639F);
        LowerJaw.addChild(spike11);
        setRotationAngle(spike11, -0.6545F, 2.138F, 0.0F);
        spike11.setTextureOffset(0, 30).addBox(-1.0F, -1.5F, 4.85F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike11.setTextureOffset(44, 21).addBox(-0.5F, -1.0F, 6.85F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike11.setModelRendererName("spike11");
        this.registerModelRenderer(spike11);

        spike10 = new AnimatedModelRenderer(this);
        spike10.setRotationPoint(-0.8639F, 5.492F, -0.8639F);
        LowerJaw.addChild(spike10);
        setRotationAngle(spike10, -0.6545F, 0.7854F, 0.0F);
        spike10.setTextureOffset(26, 30).addBox(-1.0F, -1.5F, 5.1F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        spike10.setTextureOffset(46, 0).addBox(-0.5F, -1.0F, 7.1F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        spike10.setModelRendererName("spike10");
        this.registerModelRenderer(spike10);

        Base = new AnimatedModelRenderer(this);
        Base.setRotationPoint(0.0F, 0.0F, 0.0F);
        Body.addChild(Base);
        Base.setTextureOffset(21, 51).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        Base.setTextureOffset(26, 6).addBox(-1.0F, -1.4F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Base.setModelRendererName("Base");
        this.registerModelRenderer(Base);

        Leg4 = new AnimatedModelRenderer(this);
        Leg4.setRotationPoint(0.0F, 0.0F, 0.0F);
        Base.addChild(Leg4);
        setRotationAngle(Leg4, -0.2618F, 1.5708F, 0.0F);
        Leg4.setTextureOffset(36, 18).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        Leg4.setModelRendererName("Leg4");
        this.registerModelRenderer(Leg4);

        Leg41 = new AnimatedModelRenderer(this);
        Leg41.setRotationPoint(0.0F, -0.5F, 5.5F);
        Leg4.addChild(Leg41);
        setRotationAngle(Leg41, -1.309F, 0.0F, 0.0F);
        Leg41.setTextureOffset(26, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        Leg41.setTextureOffset(54, 0).addBox(-2.5F, -1.5F, -2.0F, 5.0F, 2.0F, 7.0F, 0.0F, false);
        Leg41.setModelRendererName("Leg41");
        this.registerModelRenderer(Leg41);

        Leg3 = new AnimatedModelRenderer(this);
        Leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        Base.addChild(Leg3);
        setRotationAngle(Leg3, -0.2618F, 3.1416F, 0.0F);
        Leg3.setTextureOffset(44, 19).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        Leg3.setModelRendererName("Leg3");
        this.registerModelRenderer(Leg3);

        Leg31 = new AnimatedModelRenderer(this);
        Leg31.setRotationPoint(0.0F, -0.5F, 5.5F);
        Leg3.addChild(Leg31);
        setRotationAngle(Leg31, -1.309F, 0.0F, 0.0F);
        Leg31.setTextureOffset(28, 18).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        Leg31.setTextureOffset(53, 55).addBox(-2.5F, -1.5F, -2.0F, 5.0F, 2.0F, 7.0F, 0.0F, false);
        Leg31.setModelRendererName("Leg31");
        this.registerModelRenderer(Leg31);

        Leg2 = new AnimatedModelRenderer(this);
        Leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        Base.addChild(Leg2);
        setRotationAngle(Leg2, -0.2618F, -1.5708F, 0.0F);
        Leg2.setTextureOffset(54, 9).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        Leg2.setModelRendererName("Leg2");
        this.registerModelRenderer(Leg2);

        Leg21 = new AnimatedModelRenderer(this);
        Leg21.setRotationPoint(0.0F, -0.5F, 5.5F);
        Leg2.addChild(Leg21);
        setRotationAngle(Leg21, -1.309F, 0.0F, 0.0F);
        Leg21.setTextureOffset(38, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        Leg21.setTextureOffset(56, 36).addBox(-2.5F, -1.5F, -2.0F, 5.0F, 2.0F, 7.0F, 0.0F, false);
        Leg21.setModelRendererName("Leg21");
        this.registerModelRenderer(Leg21);

        Leg1 = new AnimatedModelRenderer(this);
        Leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        Base.addChild(Leg1);
        setRotationAngle(Leg1, -0.2618F, 0.0F, 0.0F);
        Leg1.setTextureOffset(0, 59).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        Leg1.setModelRendererName("Leg1");
        this.registerModelRenderer(Leg1);

        Leg11 = new AnimatedModelRenderer(this);
        Leg11.setRotationPoint(0.0F, -0.5F, 5.5F);
        Leg1.addChild(Leg11);
        setRotationAngle(Leg11, -1.309F, 0.0F, 0.0F);
        Leg11.setTextureOffset(8, 59).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        Leg11.setTextureOffset(57, 45).addBox(-2.5F, -1.5F, -2.0F, 5.0F, 2.0F, 7.0F, 0.0F, false);
        Leg11.setModelRendererName("Leg11");
        this.registerModelRenderer(Leg11);

        this.rootBones.add(Body);
    }
 */
    @Override
    public ResourceLocation getModelLocation(BurnlingEntity burnlingEntity) {
        return new ResourceLocation(Diablo.MOD_ID, "geo/burnling_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BurnlingEntity burnlingEntity) {
        return new ResourceLocation(Diablo.MOD_ID, "textures/entity/burnling.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BurnlingEntity burnlingEntity) {
        return new ResourceLocation("diablomodif", "animations/burnling_entity.json");
    }
}
