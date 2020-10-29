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
    // registers new brewing recipes. call from FMLCommonSetupEvent
    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, DiabloItems.ORB_OF_WRATH.get(), DiabloItems.ARSON_POTION.get()));
    }

    // my tutorial on this: https://youtu.be/uQEfNkY5fJc
    // brewing stand checks the BrewingRecipeRegistry which is basically a list of IBrewingRecipe
    private static class BetterBrewingRecipe implements IBrewingRecipe {
        private final Potion bottleInput;  // potion on the bottle input
        private final Item itemInput;      // item input (like nether wart)
        private final ItemStack output;    // the stack to output when done brewing

        public BetterBrewingRecipe(Potion bottleInputIn, Item itemInputIn, Potion outputIn){
            this(bottleInputIn, itemInputIn, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), outputIn));
        }

        public BetterBrewingRecipe(Potion bottleInputIn, Item itemInputIn, Item outputIn){
            this(bottleInputIn, itemInputIn, new ItemStack(outputIn));
        }

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
