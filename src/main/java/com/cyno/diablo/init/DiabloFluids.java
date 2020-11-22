package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DiabloFluids {
    public static final ResourceLocation MAGMA_STILL_RL = new ResourceLocation(Diablo.MOD_ID, "blocks/magma_still");
    public static final ResourceLocation MAGMA_FLOWING_RL = new ResourceLocation(Diablo.MOD_ID, "blocks/magma_flowing");
    public static final ResourceLocation MAGMA_OVERLAY_RL = new ResourceLocation(Diablo.MOD_ID, "blocks/magma_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Diablo.MOD_ID);

    public static final RegistryObject<FlowingFluid> MAGMA_FLUID = FLUIDS.register("magma_fluid", () -> new ForgeFlowingFluid.Source(DiabloFluids.MAGMA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MAGMA_FLOWING = FLUIDS.register("magma_flowing", () -> new ForgeFlowingFluid.Flowing(DiabloFluids.MAGMA_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MAGMA_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MAGMA_FLUID.get(), () -> MAGMA_FLOWING.get(), FluidAttributes.builder(MAGMA_STILL_RL, MAGMA_FLOWING_RL).sound(SoundEvents.BLOCK_LAVA_AMBIENT).sound(SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA)
            .density(1000).rarity(Rarity.RARE).viscosity(1000).temperature(1500).overlay(MAGMA_OVERLAY_RL)).block(() -> DiabloBlocks.MAGMA_FLUID_BLOCK.get());


}
