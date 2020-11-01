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
import software.bernie.geckolib.core.IAnimatable;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;

public class SculkMawEntity extends MonsterEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);


    public SculkMawEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return SculkMawEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 750)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.07f)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 50f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0);

    }

    @Override
    public void livingTick() {
        super.livingTick();
    }



    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.00f));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));

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

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        // idle if not moving fast
        if(this.getMotion().length() < 0.06){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.diablomodif.sculk_maw_entity.idle", true));
        } else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.diablomodif.sculk_maw_entity.noattackwalk", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data){
        data.addAnimationController(new AnimationController(this, "moveController", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
