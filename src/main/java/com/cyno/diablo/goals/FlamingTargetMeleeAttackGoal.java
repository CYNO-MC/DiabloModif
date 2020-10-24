package com.cyno.diablo.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;

public class FlamingTargetMeleeAttackGoal extends StandardMeleeAttackGoal{
    public FlamingTargetMeleeAttackGoal(CreatureEntity entityIn, double speedIn, boolean useLongMemory, boolean dealDamageOnContact) {
        super(entityIn, speedIn, useLongMemory, dealDamageOnContact);
    }

    @Override
    public boolean shouldContinueExecuting() {
        LivingEntity target = this.attacker.getAttackTarget();

        boolean hasTarget = target != null;
        if (!hasTarget || !target.isAlive()) return false;

        // only continue executing if the target is still on fire
        boolean isOnFire = target.getFireTimer() > 0;
        if (!isOnFire){
            this.attacker.setAttackTarget(null);
        }
        return isOnFire;
    }
}
