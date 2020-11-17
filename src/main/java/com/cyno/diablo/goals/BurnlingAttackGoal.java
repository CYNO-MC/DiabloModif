package com.cyno.diablo.goals;

import com.cyno.diablo.entities.BurnlingEntity;
import com.cyno.diablo.util.Debug;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.util.DamageSource;

public class BurnlingAttackGoal extends Goal {
    private BurnlingEntity creature;

    float currentAttackStep = 0;
    float maxAttackInterval = 30;
    public BurnlingAttackGoal(BurnlingEntity burnling, float maxAttackInterval){
        this.creature = burnling;
        this.maxAttackInterval = maxAttackInterval;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        super.resetTask();
    }

    @Override
    public boolean shouldExecute() {
        return this.creature.getAttackTarget() != null && this.creature.isSurroundedByLava();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.creature.getAttackTarget() != null  && this.creature.isSurroundedByLava();
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.creature.world.isRemote()){
            if(this.creature.getAttacking())
            {
                if(this.currentAttackStep < this.maxAttackInterval)
                {
                    ++this.currentAttackStep;
                }
                else{
                    this.currentAttackStep = 0;
                    this.creature.SpawnLavaBubbles(0.2f);
                }

            }
            else
            if(currentAttackStep > 0)
                this.currentAttackStep = 0;
        }
    }
}
