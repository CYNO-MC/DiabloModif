package com.cyno.diablo.items;

import com.cyno.diablo.Diablo;
import net.minecraft.item.Item;

public class ItemBase extends Item {


    public ItemBase() {
        super(new Item.Properties().group(Diablo.DiabloItemGroup.instance));
    }
}
