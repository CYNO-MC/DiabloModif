package com.cyno.diablo.goals;

import com.cyno.diablo.entities.BurnlingEntity;
import com.cyno.diablo.util.Debug;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

public class BurnlingAttackGoal extends Goal {
    private BurnlingEntity creature;

    public BurnlingAttackGoal(BurnlingEntity burnling){
        this.creature = burnling;
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
        return this.creature.getAttackTarget() != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.creature.getAttackTarget() != null;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
