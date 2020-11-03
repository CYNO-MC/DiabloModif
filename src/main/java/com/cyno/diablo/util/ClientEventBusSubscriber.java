package com.cyno.diablo.util;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.client.render.*;
import com.cyno.diablo.init.DiabloEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Diablo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.DIABLO.get(), DiabloRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.DISTORTED.get(), DistortedRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.WARDEN.get(), WardenRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.WARDEN_SOUND_PARTICLES.get(), AmbiantWardenSoundParticleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.DIABLO_FIRE_PARTICLE.get(), DiabloFireParticleRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(DiabloEntityTypes.DEMON_TRIDENT.get(), DemonTridentRenderer::new);
    }

}
