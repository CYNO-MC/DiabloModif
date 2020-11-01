package com.cyno.diablo.client.render;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.model.DemonTridentModel;
import com.cyno.diablo.entities.DemonTridentEntity;
import com.cyno.diablo.util.Debug;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.client.renderer.entity.model.TridentModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DemonTridentRenderer extends EntityRenderer<DemonTridentEntity> {
    private final DemonTridentModel model = new DemonTridentModel();
    public static ResourceLocation TEXTURE = new ResourceLocation(Diablo.MOD_ID, "textures/entity/demon_trident.png");

    public DemonTridentRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(DemonTridentEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        Debug.Log("render called!");

        matrixStackIn.push();

        RenderType renderType = this.model.getRenderType(getEntityTexture(entityIn));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(renderType);
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0,0,0,0);

        matrixStackIn.pop();

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getEntityTexture(DemonTridentEntity entity) {
        Debug.Log("got texture");
        return TEXTURE;
    }
}