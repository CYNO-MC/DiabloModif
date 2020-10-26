package com.cyno.diablo.util;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.render.AmbiantWardenSoundParticleRenderer;
import com.cyno.diablo.client.render.DiabloRenderer;
import com.cyno.diablo.client.render.DistortedRenderer;
import com.cyno.diablo.client.render.WardenRenderer;
import com.cyno.diablo.init.DiabloEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Mod.EventBusSubscriber(modid = Diablo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.DIABLO.get(), DiabloRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.DISTORTED.get(), DistortedRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.WARDEN.get(), WardenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.WARDEN_SOUND_PARTICLES.get(), AmbiantWardenSoundParticleRenderer::new);
    }

}
