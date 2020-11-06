package com.cyno.diablo.items;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.init.DiabloItems;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class VialItem extends ItemBase {
    static final int MAX_FULLNESS = 5;

    public VialItem() {
        super();

        // https://mcforge.readthedocs.io/en/1.16.x/models/overrides/
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation(Diablo.MOD_ID, "blood_vial_fullness"), (stack, world, entity) -> {
            // returns fullness to be used in the model. 0 - 4 inclusive

            CompoundNBT tag = stack.getTag();
            if (tag == null || !tag.contains("fullness")) return 0;
            else {
                return tag.getInt("fullness");
            }
        });
    }

    // shrinks the stack by one and returns a new one that's more full to give the player
    public static ItemStack increaseFullness(ItemStack stack){
        int fullness;

        CompoundNBT tag = stack.getTag();
        if (tag == null) tag = new CompoundNBT();

        if (!tag.contains("fullness"))
            fullness = 1;
        else {
            fullness = tag.getInt("fullness");
            fullness++;

            if (fullness == MAX_FULLNESS){
                stack.shrink(1);
                return new ItemStack(DiabloItems.DEMON_BLOOD.get());
            };
        }

        stack.shrink(1);
        ItemStack out = new ItemStack(DiabloItems.GLASS_VILE.get());
        CompoundNBT newTag = tag.copy();
        newTag.putInt("fullness", fullness);
        out.setTag(newTag);
        return out;
    }
}
