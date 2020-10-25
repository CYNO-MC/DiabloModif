package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.blocks.BlockItemBase;
import com.cyno.diablo.items.ItemBase;
import com.cyno.diablo.util.enums.DiabloItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DiabloItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Diablo.MOD_ID);


    // Items
    public static final RegistryObject<Item> GLASS_VILE = ITEMS.register("glass_vile", ItemBase::new);
    public static final RegistryObject<Item> THE_HORNS_OF_DIABLO = ITEMS.register("the_horns_of_diablo", ItemBase::new);


    //Tools
    public static final RegistryObject<SwordItem> THE_CONFLAGRATE = ITEMS.register("the_conflagrate",
            () ->
            new SwordItem(DiabloItemTier.THE_CONFLAGRATE, 9, +3.0f, new Item.Properties().group(Diablo.TAB)));

    //Block Items

    public static final RegistryObject<Item>REINFORCED_NETHER_BRICK_BLOCK_ITEM = ITEMS.register("reinforced_nether_brick_block",
            () -> new BlockItemBase(DiabloBlocks.REINFORCED_NETHER_BRICK_BLOCK.get()));
    public static final RegistryObject<Item>NETHER_WALL_BLOCK_ITEM = ITEMS.register("nether_brick_plank_block",
            () -> new BlockItemBase(DiabloBlocks.NETHER_BRICK_PLANK_BLOCK.get()));
    public static final RegistryObject<Item>WARPED_GLOWSTONE_BLOCK_ITEM = ITEMS.register("warped_glowstone_block",
            () -> new BlockItemBase(DiabloBlocks.WARPED_GLOWSTONE_BLOCK.get()));
    public static final RegistryObject<Item>ANCIENT_PILLAR_ITEM = ITEMS.register("ancient_pillar",
            () -> new BlockItemBase(DiabloBlocks.ANCIENT_PILLAR.get()));






}
