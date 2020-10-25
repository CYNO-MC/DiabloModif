package com.cyno.diablo.goals;

import com.cyno.diablo.Diablo;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.pathfinding.Path;

public class StandardMeleeAttackGoal extends MeleeAttackGoal // we need to create our own MeleeAttackGoal
{
    protected final double speedTowardsTarget;
    protected final boolean longMemory;
    protected Path path;
    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected int delayCounter;
    protected int field_234037_i_;
    protected final int attackInterval = 3;
    protected int failedPathFindingPenalty = 0;
    protected boolean canPenalize = false;
    protected final CreatureEntity entity;
    protected boolean damageOnContact;

    public StandardMeleeAttackGoal(CreatureEntity entityIn, double speedIn, boolean useLongMemory, boolean dealDamageOnContact) {
        super(entityIn, speedIn, useLongMemory);
        this.entity = entityIn;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.damageOnContact = dealDamageOnContact;
    }

    @Override
    public void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr)
    {
        double d0 = this.getAttackReachSqr(enemy);

        if (distToEnemySqr <= d0) {
            this.attacker.attackEntityAsMob(enemy);
        }

    }

    /**
     * Keep ticking a continuous task that has already been started
     */

    @Override
    public boolean shouldExecute() {
        return this.attacker.getAttackTarget() != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.attacker.getAttackTarget() != null;
    }


    @Override
    public void tick()
    {
        LivingEntity livingentity = this.attacker.getAttackTarget();
        if(livingentity != null && !livingentity.isInWater()){
            this.attacker.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
            double d0 = this.attacker.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
            this.delayCounter = Math.max(this.delayCounter - 1, 0);
            if ((this.longMemory || this.attacker.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
                this.targetX = livingentity.getPosX();
                this.targetY = livingentity.getPosY();
                this.targetZ = livingentity.getPosZ();
                this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                if (this.canPenalize) {
                    this.delayCounter += failedPathFindingPenalty;
                    if (this.attacker.getNavigator().getPath() != null) {
                        net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                        if (finalPathPoint != null && livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if (d0 > 1024.0D) {
                    this.delayCounter += 10;
                } else if (d0 > 256.0D) {
                    this.delayCounter += 5;
                }

                if (!this.attacker.getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
                    this.delayCounter += 15;
                }
            }

            this.field_234037_i_ = Math.max(this.field_234037_i_ - 1, 0);
            if(this.damageOnContact)
                this.checkAndPerformAttack(livingentity, d0);
        }
    }

}
