package com.cyno.diablo.entities;

import com.cyno.diablo.goals.StandardMeleeAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DistortedEntity extends MonsterEntity {



    public DistortedEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DistortedEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 999.999)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 2.00f)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 100f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(1, new StandardMeleeAttackGoal(this, 1.0d, true, true));
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal(this, VillagerEntity.class, true));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_BONE_BLOCK_BREAK;
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.BLOCK_BONE_BLOCK_PLACE, 1.0f, .70f);

    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.AMBIENT_NETHER_WASTES_LOOP;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }
}



