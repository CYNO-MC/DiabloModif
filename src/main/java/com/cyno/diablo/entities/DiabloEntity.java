package com.cyno.diablo.entities;

import com.cyno.diablo.goals.FireCircleAttackGoal;
import com.cyno.diablo.goals.FlamingTargetMeleeAttackGoal;
import com.cyno.diablo.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import java.util.function.Predicate;

public class DiabloEntity extends MonsterEntity implements IAnimatedEntity {
    private EntityAnimationManager manager = new EntityAnimationManager();
    private AnimationController controller = new EntityAnimationController(this, "moveController", 20,
            this::animationPredicate);

    // A predicate to pass into NearestAttackableTargetGoal to check if the entity is on fire
    private static final Predicate<LivingEntity> IS_ON_FIRE = (entity) -> {
        return entity.getFireTimer() > 0;
    };


    public float getHealthData (){
        return this.dataManager.get(HEALTH_DATA);
    }

    public DiabloEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DiabloEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 500.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.35f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 10.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 15.0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 100.0);

    }

    private static final DataParameter<Float> HEALTH_DATA = EntityDataManager.createKey(DiabloEntity.class, DataSerializers.FLOAT);

    @Override
    protected void registerGoals(){
        super.registerGoals();

        // If there are any flaming players nearby, set them to attackTarget
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 1, true, false, IS_ON_FIRE));

        // Walk towards and attack the target. Stop if they are no longer on fire
        this.goalSelector.addGoal(1, new FlamingTargetMeleeAttackGoal(this, 1.0d, true));

        // When there are no flaming players nearby, shoot rings of fire particles
        this.goalSelector.addGoal(2, new FireCircleAttackGoal(this, 48, 1.5D));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(HEALTH_DATA, 100.0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        // Have to do this so it doesnt take damage when it shoots out its fire particles
        // could make them spawn further out but then if you're close enough you don't get hit
        if (source == DamageSource.CRAMMING) return false;

        return super.attackEntityFrom(source, amount);
    }

    // could do this in the entity type like other mobs but might as well keep everything together in one class
    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if(!this.world.isRemote){
            if(this.getAttackTarget() != null)
                this.dataManager.set(HEALTH_DATA, this.getHealth());
        }
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 30 + this.world.rand.nextInt(50);
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundInit.DAMAGE.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundInit.DSTEP.get(), 1.00f, 1.00f);

    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.DAMAGE.get();
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends DiabloEntity> boolean animationPredicate(AnimationTestEvent<E> event) {
        if (event.isWalking()) {}
        else controller.setAnimation(new AnimationBuilder().addAnimation("animation.diablomodif.diabloentity.idle", true));

        return true;
    }
    private void registerAnimationControllers() {
        manager.addAnimationController(controller);
    }
}

