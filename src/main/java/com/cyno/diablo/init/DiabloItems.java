package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.blocks.BlockItemBase;
import com.cyno.diablo.items.ItemBase;
import com.cyno.diablo.util.enums.DiabloItemTier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DiabloItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Diablo.MOD_ID);

    // Items
    public static final RegistryObject<Item> GLASS_VILE = ITEMS.register("glass_vile", ItemBase::new);
    public static final RegistryObject<Item> THE_HORNS_OF_DIABLO = ITEMS.register("the_horns_of_diablo", ItemBase::new);

    //Tools
    public static final RegistryObject<SwordItem> THE_CONFLAGRATE = ITEMS.register("the_conflagrate",
            () -> new SwordItem(DiabloItemTier.THE_CONFLAGRATE, 9, +3.0f, new Item.Properties().group(Diablo.TAB)));

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        // for each block we registered...
        DiabloBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            // make a block item that places the block
            final BlockItem blockItem = new BlockItemBase(block);

            // register the block item with the same name as the block
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }







}
