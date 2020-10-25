package com.cyno.diablo.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

/* The same as MeleeAttackGoal but it checks that the target is on fire before it continues executing  */

public class FlamingTargetMeleeAttackGoal extends MeleeAttackGoal {
    public FlamingTargetMeleeAttackGoal(CreatureEntity entityIn, double speedIn, boolean useLongMemory) {
        super(entityIn, speedIn, useLongMemory);
    }

    @Override
    public boolean shouldContinueExecuting() {
        boolean targetable = super.shouldContinueExecuting();  // this includes a null check for the target

        if (targetable){
            LivingEntity target = this.attacker.getAttackTarget();
            assert target != null;
            boolean isOnFire = target.getFireTimer() > 0;
            if (!isOnFire) this.attacker.setAttackTarget(null);
            return isOnFire;
        } else {
            return false;
        }
    }
}
