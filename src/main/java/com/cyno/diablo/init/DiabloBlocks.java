package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.blocks.AncientPillar;
import com.cyno.diablo.blocks.NetherWallBlock;
import com.cyno.diablo.blocks.ReinforcedNetherBrickBlock;
import com.cyno.diablo.blocks.WarpedGlowstoneBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DiabloBlocks {



    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Diablo.MOD_ID);


    //Blocks
    public static final RegistryObject<Block> REINFORCED_NETHER_BRICK_BLOCK = BLOCKS.register("reinforced_nether_brick_block", ReinforcedNetherBrickBlock:: new);
    public static final RegistryObject<Block> NETHER_WALL_BLOCK = BLOCKS.register("nether_wall_block", NetherWallBlock:: new);
    public static final RegistryObject<Block> WARPED_GLOWSTONE_BLOCK = BLOCKS.register("warped_glowstone_block", WarpedGlowstoneBlock:: new);
    public static final RegistryObject<Block> ANCIENT_PILLAR = BLOCKS.register("ancient_pillar", AncientPillar:: new);
}
