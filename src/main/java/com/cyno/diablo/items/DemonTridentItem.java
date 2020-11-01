package com.cyno.diablo.items;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.DemonTridentEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class DemonTridentItem extends TridentItem {
    public DemonTridentItem() {
        super(new Item.Properties().group(Diablo.TAB).maxDamage(1000));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (itemstack.getDamage() >= itemstack.getMaxDamage() - 1) {
            return ActionResult.resultFail(itemstack);
        } else if (EnchantmentHelper.getRiptideModifier(itemstack) > 0 && !canApplyRiptide(worldIn)) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            int time = this.getUseDuration(stack) - timeLeft;
            if (time >= 10) {
                int riptideLevel = EnchantmentHelper.getRiptideModifier(stack);
                if (riptideLevel <= 0 || canApplyRiptide(worldIn)) {
                    if (!worldIn.isRemote) {
                        stack.damageItem(1, playerentity, (p_220047_1_) -> {
                            p_220047_1_.sendBreakAnimation(entityLiving.getActiveHand());
                        });
                        if (riptideLevel == 0) {
                            throwTrident(worldIn, playerentity, stack, riptideLevel);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));

                    if (riptideLevel > 0) {
                        applyRiptide(worldIn, playerentity, riptideLevel);
                    }
                }
            }
        }
    }

    private void throwTrident(World worldIn, PlayerEntity playerentity, ItemStack stack, int riptideLevel) {
        DemonTridentEntity tridententity = new DemonTridentEntity(worldIn, playerentity, stack);
        tridententity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, 2.5F + (float)riptideLevel * 0.5F, 1.0F);
        if (playerentity.abilities.isCreativeMode) {
            tridententity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
        }

        worldIn.addEntity(tridententity);
        worldIn.playMovingSound((PlayerEntity)null, tridententity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (!playerentity.abilities.isCreativeMode) {
            playerentity.inventory.deleteStack(stack);
        }
    }

    private boolean canApplyRiptide(World world) {
        return world.getDimensionKey() == World.THE_NETHER;
    }

    protected void applyRiptide(World worldIn, PlayerEntity playerentity, int riptideLevel){
        float f7 = playerentity.rotationYaw;
        float f = playerentity.rotationPitch;
        float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
        float f2 = -MathHelper.sin(f * ((float)Math.PI / 180F));
        float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
        float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = 3.0F * ((1.0F + (float)riptideLevel) / 4.0F);
        f1 = f1 * (f5 / f4);
        f2 = f2 * (f5 / f4);
        f3 = f3 * (f5 / f4);
        playerentity.addVelocity((double)f1, (double)f2, (double)f3);
        playerentity.startSpinAttack(20);
        if (playerentity.isOnGround()) {
            float f6 = 1.1999999F;
            playerentity.move(MoverType.SELF, new Vector3d(0.0D, (double)1.1999999F, 0.0D));
        }

        SoundEvent soundevent;
        if (riptideLevel >= 3) {
            soundevent = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;
        } else if (riptideLevel == 2) {
            soundevent = SoundEvents.ITEM_TRIDENT_RIPTIDE_2;
        } else {
            soundevent = SoundEvents.ITEM_TRIDENT_RIPTIDE_1;
        }

        worldIn.playMovingSound((PlayerEntity)null, playerentity, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}