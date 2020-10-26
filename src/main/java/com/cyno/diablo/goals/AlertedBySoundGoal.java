package com.cyno.diablo.goals;

import com.cyno.diablo.entities.WardenEntity;
import com.cyno.diablo.util.Debug;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

public class AlertedBySoundGoal extends Goal {

    WardenEntity entity;
    float speed;
    boolean damageOnContact;
    public float maxInspectionTime = 100;
    public float currentInspectionTime = 0;
    AxisAlignedBB aabb;
    List<Entity> entities;
    public AlertedBySoundGoal(WardenEntity entityIn, float speedIn, boolean damageOnContact){
        this.entity = entityIn;
        this.speed = speedIn;
        this.damageOnContact = damageOnContact;
    }
    @Override
    public boolean shouldExecute() {
        return entity.soundPosition != null && entity.getAttackTarget() == null;
    }

    public void setCurrentInspectionTime(float val){
        currentInspectionTime = val;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return entity.soundPosition != null && entity.getAttackTarget() == null;
    }

    public void checkAndPerformAttack(LivingEntity enemy, float reachDist)
    {

        if (this.entity.getDistance(enemy) <= reachDist) {
            this.entity.attackEntityAsMob(enemy);
        }

    }

    @Override
    public void tick()
    {
        if(!this.entity.world.isRemote()){

            if(entity.soundPosition != null){

                this.entity.lookAt(EntityAnchorArgument.Type.EYES, entity.soundPosition);
                this.entity.getNavigator().tryMoveToXYZ(entity.soundPosition.getX(),entity.soundPosition.getY(), entity.soundPosition.getZ(), speed);


                if(currentInspectionTime < maxInspectionTime){
                    ++this.currentInspectionTime;
                }
                else
                {
                    this.currentInspectionTime = 0;
                    entity.soundPosition = null;
                }
            }

            aabb = new AxisAlignedBB(this.entity.getPosition()).grow(4);
            entities = entity.world.getEntitiesWithinAABBExcludingEntity(this.entity, aabb);
            if(this.damageOnContact && entities != null && entities.size() > 0 && entities.get(0) != null)
            {
               for(Entity en : entities){
                   if(en instanceof VillagerEntity || en instanceof PlayerEntity)
                       this.entity.setAttackTarget((LivingEntity) entities.get(entities.indexOf(en)));
               }
            }

        }
    }
}
