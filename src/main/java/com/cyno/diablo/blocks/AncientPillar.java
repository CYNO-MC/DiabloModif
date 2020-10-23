package com.cyno.diablo.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class AncientPillar extends Block {

    public AncientPillar() {
        super(AbstractBlock.Properties.create(Material.IRON)
            .hardnessAndResistance(0.8f, 0.2f)
                .sound(SoundType.ANCIENT_DEBRIS)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool()

        );
    }
}
