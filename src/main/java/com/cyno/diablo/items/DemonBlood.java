package com.cyno.diablo.items;

import com.cyno.diablo.init.DiabloItems;
import net.minecraft.item.ItemStack;

public class DemonBlood extends ItemBase{
    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    // when used in crafting, leaves this stack behind in its place
    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        return new ItemStack(DiabloItems.GLASS_VILE.get());
    }
}
