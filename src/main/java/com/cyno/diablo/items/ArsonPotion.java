package com.cyno.diablo.items;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.ArsonPotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ArsonPotion extends Item {
    public ArsonPotion() {
        super(new Item.Properties().group(Diablo.TAB).maxStackSize(1));
    }
    @Override
    public boolean hasEffect(ItemStack stack){
        return true;
    }

    // basically the same as a normal ThrowablePotionItem but
    // instead of making a PotionEntity, make a ArsonPotionEntity entity
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getCooldownTracker().setCooldown(this, 20);  // don't let them spam it too fast

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            PotionEntity potionentity = new ArsonPotionEntity(worldIn, playerIn);

            // shoot the entity. (shooter, pitch, yaw, downwardness? idk, velocity, inaccuracy)
            potionentity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
            worldIn.addEntity(potionentity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}
