package com.cyno.diablo.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import java.util.Random;

// sets you on fire and greatly damages your armor / held items

public class ArsonEffect extends Effect {
    Random rand = new Random();
    public ArsonEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 10 == 0;  // twice a second
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        entity.attackEntityFrom(DamageSource.ON_FIRE, 2.0F);
        entity.setFire(1);  // won't actually deal damage cause more is delt above and you get breif immunity. is just visual

        Iterable<ItemStack> armor = entity.getArmorInventoryList();
        for (ItemStack stack : armor){
            stack.attemptDamageItem(10 * (amplifier + 1), rand, null);
        }

        entity.getHeldItemMainhand().attemptDamageItem(10 * (amplifier + 1), rand, null);
        entity.getHeldItemOffhand().attemptDamageItem(10 * (amplifier + 1), rand, null);
    }
}
