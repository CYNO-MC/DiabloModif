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
    static final int MAX_FULLNESS = 5;  // how many times you have to click diablo to get a demon blood

    public VialItem() {
        super();

        // https://mcforge.readthedocs.io/en/1.16.x/models/overrides/
        // create an item prperty override. Allows you to use the value returned by the IItemPropertyGetter (a glorified supplier)
        // by accessing the property <pathIn> to change texture dynamically
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation(Diablo.MOD_ID, "blood_vial_fullness"), (stack, world, entity) -> {
            // returns fullness value from the stack's nbt tag. 0 - 4 inclusive

            CompoundNBT tag = stack.getTag();
            if (tag == null || !tag.contains("fullness")) return 0;
            else {
                return tag.getInt("fullness");
            }
        });
    }

    // shrinks the stack by one and returns a new one that's more full to give the player
    // called by DiabloEntity#processInteract
    public static ItemStack increaseFullness(ItemStack stack){
        int fullness;

        CompoundNBT tag = stack.getTag();
        if (tag == null) tag = new CompoundNBT();

        if (!tag.contains("fullness"))
            fullness = 1;
        else {
            fullness = tag.getInt("fullness");
            fullness++;

            // if they've filled the vial, give them a demon blood item instead
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
