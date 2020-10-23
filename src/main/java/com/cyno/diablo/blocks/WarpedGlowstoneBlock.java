package com.cyno.diablo.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class WarpedGlowstoneBlock extends Block {

    public WarpedGlowstoneBlock() {
        super(Block.Properties.create(Material.GLASS)
                .sound(SoundType.GLASS)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .zeroHardnessAndResistance()
                .setLightLevel(value -> 15)
        );
    }

}

