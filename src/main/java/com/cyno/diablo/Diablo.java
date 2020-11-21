package com.cyno.diablo;

import com.cyno.diablo.entities.*;
import com.cyno.diablo.init.*;
import com.cyno.diablo.util.BrewingHandler;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod("diablomodif")
public class Diablo {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "diablomodif";

    public Diablo() {
        GeckoLib.initialize();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);

        SoundInit.SOUNDS.register(eventBus);
        DiabloFluids.FLUIDS.register(eventBus);
        DiabloBlocks.BLOCKS.register(eventBus);
        DiabloItems.ITEMS.register(eventBus);
        DiabloEntityTypes.ENTITY_TYPES.register(eventBus);
        PotionInit.EFFECTS.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.WARDEN_SOUND_PARTICLES.get(), AmbiantWardenSoundParticleEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.DIABLO_FIRE_PARTICLE.get(), DiabloFireParticleEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.DIABLO.get(), DiabloEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.WARDEN.get(), WardenEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.LAVA_BUBBLE.get(), LavaBubbleProjectileEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(DiabloEntityTypes.BURNLING.get(), BurnlingEntity.setCustomAttributes().create());


            BrewingHandler.addPotionRecipes();  // add recipe for arson potion
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) { }

    public static class DiabloItemGroup extends ItemGroup {
        public static final ItemGroup instance = new DiabloItemGroup(ItemGroup.GROUPS.length, "diabloTab");

        private DiabloItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(DiabloItems.THE_HORNS_OF_DIABLO.get());
        }
    }
}