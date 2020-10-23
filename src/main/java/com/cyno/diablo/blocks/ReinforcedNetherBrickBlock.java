package com.cyno.diablo.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ReinforcedNetherBrickBlock extends Block {

    public ReinforcedNetherBrickBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(50.0f, 1200f)
                .sound(SoundType.METAL)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE)
                );
    }

}
