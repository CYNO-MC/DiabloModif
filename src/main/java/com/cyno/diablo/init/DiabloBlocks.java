package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import net.minecraft.block.*;
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

    public static final RegistryObject<Block> ANCIENT_PLATE = BLOCKS.register("ancient_plate", () -> new Block(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> ANCIENT_BRICK_STAIRS = BLOCKS.register("ancient_brick_stairs", () -> new StairsBlock(() -> ANCIENT_BRICK_BLOCK.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> ANCIENT_BRICK_SLAB = BLOCKS.register("ancient_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> CHISELED_ANCIENT_BRICK = BLOCKS.register("chiseled_ancient_brick", () -> new Block(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> ANCIENT_FENCE = BLOCKS.register("ancient_fence", () -> new FenceBlock(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> ANCIENT_WALL = BLOCKS.register("ancient_wall", () -> new WallBlock(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(30f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS)
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));


    // Nether Brick Variations
    public static final RegistryObject<Block> REINFORCED_NETHER_BRICK_BLOCK = BLOCKS.register("reinforced_nether_brick_block", () -> new Block(Block.Properties.create(Material.IRON)
            .hardnessAndResistance(50.0f, 1200f)
            .sound(SoundType.STONE)
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

    public static final RegistryObject<Block> HARDENED_NETHERRACK = BLOCKS.register("hardened_netherrack_block", () -> new NetherrackBlock(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(1.2f, 1.2f)
            .sound(SoundType.NETHERRACK)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> HARDENED_NETHERRACK_GOLD_ORE = BLOCKS.register("hardened_netherrack_gold_ore_block", () -> new OreBlock(Block.Properties.create(Material.IRON)
            .hardnessAndResistance(9f, 9f)
            .sound(SoundType.NETHER_GOLD)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));


    public static final RegistryObject<Block> HARDENED_SOUL_SAND = BLOCKS.register("hardened_soul_sand_block", () -> new SoulSandBlock(Block.Properties.create(Material.SAND)
            .hardnessAndResistance(1.5f, 1.5f)
            .sound(SoundType.SOUL_SAND)
            .harvestLevel(0)
            .speedFactor(0.1F)
            .harvestTool(ToolType.SHOVEL)));









































}