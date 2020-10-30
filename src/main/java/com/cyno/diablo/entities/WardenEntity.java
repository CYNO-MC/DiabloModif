package com.cyno.diablo.entities;

import com.cyno.diablo.goals.AlertedBySoundGoal;
import com.cyno.diablo.goals.StandardMeleeAttackGoal;
import com.cyno.diablo.init.DiabloEntityTypes;
import com.cyno.diablo.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib.core.IAnimatable;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;


public class WardenEntity extends MonsterEntity implements IAnimatable {
    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return null;
    }

    private static final DataParameter<Float> ANIM_SPEED = EntityDataManager.createKey(WardenEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> IS_ATTACKING = EntityDataManager.createKey(WardenEntity.class, DataSerializers.BOOLEAN);

    public static WardenEntity instance;
    public Vector3d soundPosition = null;
    public AlertedBySoundGoal alertedBySoundGoal;
    public StandardMeleeAttackGoal meleeAttackGoal;
    public float currentParticlesDelay = 0;
    public float maxParticlesDelay = 20;
    public boolean canHear = false;
    public Vector3d lastHeardPos;
    float wardenSpeed = 1.8f;
    float initWardenSpeed = 1.8f;
    float maxWardenSpeed = 2.8f;
    private AnimationFactory animationManager = new AnimationFactory(this);
    private AnimationController animator = new AnimationController(this, "moveController", 20, this::animationPredicate);


    public WardenEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimators();
        if(!worldIn.isRemote()){
            if(instance == null)
                instance = this;

            if(this != instance)
            {
                remove();
            }
            this.accelerateMovement(initWardenSpeed);
        }
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return WardenEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 90.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 45.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        wardenSpeed = 1.8f;
        meleeAttackGoal = new StandardMeleeAttackGoal(this, wardenSpeed, true, true);
        alertedBySoundGoal = new AlertedBySoundGoal(this, 1.4f, true);
        this.goalSelector.addGoal(0,  meleeAttackGoal);
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.2f));
        this.goalSelector.addGoal(0, alertedBySoundGoal);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ANIM_SPEED, wardenSpeed);
        this.dataManager.register(IS_ATTACKING, false);
    }


    public void accelerateMovement(float speed){
        if(this.meleeAttackGoal != null){
            this.meleeAttackGoal.setSpeed(speed);
            this.animationManager.setAnimationSpeed(speed);
            this.dataManager.set(ANIM_SPEED, speed);

        }
    }

    public Float getAnimationSpeed(){
        return this.dataManager.get(ANIM_SPEED);
    }

    private void AddSoundParticlesCoroutineAt(Vector3d p){
        if(WardenEntity.instance != null)
        if(WardenEntity.instance.currentParticlesDelay < WardenEntity.instance.maxParticlesDelay){
            ++WardenEntity.instance.currentParticlesDelay;
        }
        else
        {
            AmbiantWardenSoundParticleEntity particleEntity = new AmbiantWardenSoundParticleEntity(DiabloEntityTypes.WARDEN_SOUND_PARTICLES.get(), WardenEntity.instance.world);
            particleEntity.setWarden(WardenEntity.instance);
            particleEntity.setSoundPosition(p);
            particleEntity.setPosition(p.getX(), p.getY(), p.getZ());
            WardenEntity.instance.world.addEntity(particleEntity);
            WardenEntity.instance.currentParticlesDelay = 0;
            canHear = false;
            WardenEntity.instance.lastHeardPos = null;
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if(!this.world.isRemote()){
            if(canHear)
                this.AddSoundParticlesCoroutineAt(lastHeardPos);

            this.dataManager.set(IS_ATTACKING, this.getAttackTarget() != null);
        }

    }

    public Boolean getIsAttacking(){
        return this.dataManager.get(IS_ATTACKING);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        instance = null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(this.getHealth() < (3 * this.getMaxHealth())/4)
        {
            if(wardenSpeed < maxWardenSpeed)
            this.accelerateMovement(Math.max(wardenSpeed + amount * 0.1f, initWardenSpeed));
        }
        else
        {
            this.accelerateMovement(initWardenSpeed);
        }
        return super.attackEntityFrom(source, amount);
    }

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
        return SoundInit.WAMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }

    @Override
    public void playAmbientSound() {
        super.playAmbientSound();
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationManager;
    }

    private <E extends WardenEntity> boolean animationPredicate(AnimationEvent<E> event){

        if(this.getMotion().length() > 0.06){
            animationManager.setAnimationSpeed((this.getIsAttacking() ? this.getAnimationSpeed() : 1));
            animator.setAnimation(new AnimationBuilder().addAnimation("animation.warden.walking", true));
            return true;
        }
        else
        {
            return false;
        }
        //IF IS ATTACKING SET ATTACK ANIM, WON'T AFFECT THE WALK ANIM AS MUCH AS THE ROTATED GROUPS ARE NOT THE SAME
        //attackAnimator.setAnimationBlablabla
    }

    private void registerAnimators(){
        animationManager.addAnimationController(animator);
    }

}
