package com.cyno.diablo.util;

import com.cyno.diablo.init.DiabloItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BrewingHandler {
    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, DiabloItems.ORB_OF_WRATH.get(), new ItemStack(DiabloItems.ARSON_POTION.get())));
    }

    private static class BetterBrewingRecipe implements IBrewingRecipe {
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public BetterBrewingRecipe(Potion bottleInputIn, Item itemInputIn, ItemStack outputIn){
            this.bottleInput = bottleInputIn;
            this.itemInput = itemInputIn;
            this.output = outputIn;
        }

        // checks the item where the water bottle would go
        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotionFromItem(input).equals(this.bottleInput);
        }

        // checks the item where the nether wort would go
        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        // gets the output potion. Very important to call copy because ItemStacks are mutable
        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)){
                return this.output.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }
    }
}
