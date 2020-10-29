package com.cyno.diablo;

import com.cyno.diablo.entities.*;
import com.cyno.diablo.init.DiabloBlocks;
import com.cyno.diablo.init.DiabloEntityTypes;
import com.cyno.diablo.init.DiabloItems;
import com.cyno.diablo.init.SoundInit;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("diablomodif")
public class Diablo {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "diablomodif";

    public Diablo() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        SoundInit.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DiabloBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DiabloItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DiabloEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.WARDEN_SOUND_PARTICLES.get(), AmbiantWardenSoundParticleEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.DIABLO_FIRE_PARTICLE.get(), DiabloFireParticleEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.DIABLO.get(), DiabloEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.DISTORTED.get(), DistortedEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.WARDEN.get(), WardenEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) { }

    public static final ItemGroup TAB = new ItemGroup("diabloTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(DiabloItems.THE_HORNS_OF_DIABLO.get());
        }
    };

}