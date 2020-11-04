package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DiabloBlocks {



    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Diablo.MOD_ID);


    // Ancient Variation Blocks
    public static final RegistryObject<Block> ANCIENT_PILLAR = BLOCKS.register("ancient_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.IRON)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)
            .setRequiresTool()));

    public static final RegistryObject<Block> ANCIENT_BRICK_BLOCK = BLOCKS.register("ancient_brick_block", () -> new Block(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    // Nether Brick Variations
    public static final RegistryObject<Block> REINFORCED_NETHER_BRICK_BLOCK = BLOCKS.register("reinforced_nether_brick_block", () -> new Block(Block.Properties.create(Material.IRON)
            .hardnessAndResistance(50.0f, 1200f)
            .sound(SoundType.METAL)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> NETHER_BRICK_PLANK_BLOCK = BLOCKS.register("nether_brick_plank_block", () -> new Block(Block.Properties.create(Material.IRON)
            .hardnessAndResistance(2.0f, 3.0f)
            .sound(SoundType.STONE)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));


    // Light Source Blocks
    
    public static final RegistryObject<Block> WARPED_GLOWSTONE_BLOCK = BLOCKS.register("warped_glowstone_block", () -> new Block(Block.Properties.create(Material.GLASS)
            .sound(SoundType.GLASS)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(0.3f,0.3f)
            .setLightLevel(value -> 15)));

    // Hardened Variations

    public static final RegistryObject<Block> HARDENED_OBSIDIAN = BLOCKS.register("hardened_obsidian_block", () -> new Block(Block.Properties.create(Material.IRON)
            .hardnessAndResistance(150.0f, 1200f)
            .sound(SoundType.STONE)
            .harvestLevel(4)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> HARDENED_NETHERRACK = BLOCKS.register("hardened_netherrack_block", () -> new Block(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(1.2f, 1.2f)
            .sound(SoundType.NETHERRACK)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));









































}