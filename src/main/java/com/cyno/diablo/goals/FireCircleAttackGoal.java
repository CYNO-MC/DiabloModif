package com.cyno.diablo.goals;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.DiabloEntity;
import com.cyno.diablo.entities.DiabloFireParticleEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class FireCircleAttackGoal extends Goal {
    private final DiabloEntity shooter;
    private final double yOffset;
    private final int particleNumber;

    private int cooldown;
    private final int INTERVAL = 60;  // number of ticks between attacks

    public FireCircleAttackGoal(DiabloEntity shooterIn, int particleNumberIn, double yOffsetIn){
        this.shooter = shooterIn;
        this.particleNumber = particleNumberIn;
        this.yOffset = yOffsetIn;

        resetCooldown();
    }

    @Override
    public boolean shouldExecute() {
        boolean noTarget = shooter.getAttackTarget() == null;
        boolean timerDone = shooter.ticksExisted >= cooldown;
        return noTarget && timerDone;
    }

    @Override
    public void startExecuting() {
        resetCooldown();

        createFireParticlesCircle(particleNumber);
    }

    // release a circle of <numberOfParticles> fire particles evenly spaced around the shooter
    private void createFireParticlesCircle(int numberOfParticles){
        int step = 360 / numberOfParticles;  // degrees on the circle between each particle

        // could increment by step but lack of precision might lead to and extra particle
        for (int i=0;i<numberOfParticles;i++){
            double deg = step * i;
            DiabloFireParticleEntity.createNew(shooter.world, shooter, deg, yOffset);
        }
    }

    private void resetCooldown() {
        cooldown = shooter.ticksExisted + INTERVAL;
    }
}
