package com.cyno.diablo.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class NetherWallBlock extends Block {

    public NetherWallBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f, 1.0f)
                .sound(SoundType.STONE)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                );
    }
}