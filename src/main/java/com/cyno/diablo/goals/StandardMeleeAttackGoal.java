package com.cyno.diablo.goals;

import com.cyno.diablo.util.Debug;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

public class StandardMeleeAttackGoal extends MeleeAttackGoal // we need to create our own MeleeAttackGoal
{
    protected double speedTowardsTarget;
    protected final boolean longMemory;
    protected final CreatureEntity entity;
    protected boolean damageOnContact;
    float maxRange;
    public StandardMeleeAttackGoal(CreatureEntity entityIn, double speedIn, boolean useLongMemory, boolean dealDamageOnContact) {
        super(entityIn, speedIn, useLongMemory);
        this.entity = entityIn;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.damageOnContact = dealDamageOnContact;
        this.maxRange = 15;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        super.resetTask();
    }


    public void checkAndPerformAttack(LivingEntity enemy, float reachDist)
    {

        if (this.entity.getDistance(enemy) <= reachDist) {
            this.entity.attackEntityAsMob(enemy);
        }

    }

    public void setSpeed(float speed){
        this.speedTowardsTarget = speed;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */

    @Override
    public boolean shouldExecute() {

        return this.entity.getAttackTarget() != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.entity.getAttackTarget() != null;
    }


    @Override
    public void tick()
    {
        LivingEntity livingentity = this.entity.getAttackTarget();
        if(livingentity != null){
            if(!livingentity.isAlive() || entity.getDistance(livingentity) > maxRange)
            {
                this.entity.setAttackTarget(null);
                return;
            }

            this.entity.lookAt(EntityAnchorArgument.Type.EYES, livingentity.getEyePosition(1));

            this.entity.getNavigator().tryMoveToXYZ(livingentity.getPosX(),livingentity.getPosY(),livingentity.getPosZ(), speedTowardsTarget);
            Debug.Danger(speedTowardsTarget);
            if(this.damageOnContact)
            {
                this.checkAndPerformAttack(livingentity, 2.4f);
            }
        }
    }

}
