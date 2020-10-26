package com.cyno.diablo.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class DetectionSystemUtils
{
    public static enum DetectionCondition{
        ON_FIRE,
        IS_MAKING_NOISE
    }
    public static ArrayList<LivingEntity> getEntitiesNear(LivingEntity entity, float radius){
        AxisAlignedBB aabb = new AxisAlignedBB(entity.getPosition()).grow(radius);
       return (ArrayList<LivingEntity>) entity.world.getEntitiesWithinAABB(LivingEntity.class, aabb);
    }

    public static ArrayList<ItemEntity> getItemsNear(LivingEntity entity, float radius){
        AxisAlignedBB aabb = new AxisAlignedBB(entity.getPosition()).grow(radius);

        return (ArrayList<ItemEntity>) entity.world.getEntitiesWithinAABB(ItemEntity.class, aabb);
    }

    @Nullable
    public static ItemEntity getFirstMakingNoiseItem(ArrayList<ItemEntity> items){
        if(items.size() > 0)
        {
            ItemEntity i = null;
            for (ItemEntity item:  items) {
                if(item.isBurning())
                    i = item;
            }
            return i;
        }
        else
            return null;
    }

    @Nullable
    public static LivingEntity getFirstEntityWithCondition(ArrayList<LivingEntity> entities, DetectionCondition condition){

        if(entities != null && entities.size() > 0){
            switch (condition){
                default:
                    return entities.get(0);

                case ON_FIRE:
                    LivingEntity en = null;
                    if (entities.size() > 0){
                        for (LivingEntity entity:  entities) {
                            if(entity.isBurning())
                               en = entity;
                        }
                    }
                    return en;

                case IS_MAKING_NOISE:
                    LivingEntity en1 = null;
                    if (entities.size() > 0){
                        for (LivingEntity entity:  entities) {
                            if(entity.getAIMoveSpeed() > 2 && entity.isOnGround())
                               en1 =  entity;
                        }
                    }
                   return en1;

            }
        }
        else
            return null;
    }
}
