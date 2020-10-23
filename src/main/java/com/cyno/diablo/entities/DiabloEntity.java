package com.cyno.diablo.entities;

import com.cyno.diablo.goals.StandardMeleeAttackGoal;
import com.cyno.diablo.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

public class DiabloEntity extends MonsterEntity implements IAnimatedEntity {
    private EntityAnimationManager manager = new EntityAnimationManager();
    private AnimationController controller = new EntityAnimationController(this, "moveController", 20,
            this::animationPredicate);


    public float getHealthData (){
        return this.dataManager.get(HEALTH_DATA);
    }
    private float INITIAL_SPEED = 0.0f;

    public DiabloEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DiabloEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 10.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 30.0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0);

    }

    private static final DataParameter<Float> HEALTH_DATA = EntityDataManager.createKey(DiabloEntity.class, DataSerializers.FLOAT);
    


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        if(this.INITIAL_SPEED - amount > 0){this.INITIAL_SPEED -= amount;} else {this.INITIAL_SPEED = 0; };
        this.onGroundSpeedFactor = INITIAL_SPEED;

        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 20.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new StandardMeleeAttackGoal(this, 1.0d, true, false));
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        super.collideWithEntity(entityIn);
        if(entityIn == this.getAttackTarget()){
            if(entityIn.isAlive())
                this.attackEntityAsMob(entityIn);
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(HEALTH_DATA, 100.0F);
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
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0f, 0.40f);

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

