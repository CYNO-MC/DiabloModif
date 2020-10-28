package com.cyno.diablo.entities;

import com.cyno.diablo.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SculkMawEntity extends MonsterEntity {

    public SculkMawEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    // I made it quite spaced out for ya, I am only creating the basic monster entity.


















    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return SculkMawEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 999)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 2.00f)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 100f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0f, true));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.2f));
        this.goalSelector.addGoal(2, new Attack);

    }

    // These are placeholders for Sound Effects we will get later
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundInit.WDAMAGE.get();
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundInit.STEP.get(), 1.00f, 1.00f);

    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDERMAN_DEATH;
    }





}
