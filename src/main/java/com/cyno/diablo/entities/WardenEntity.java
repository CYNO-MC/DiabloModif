package com.cyno.diablo.entities;

import com.cyno.diablo.goals.AlertedBySoundGoal;
import com.cyno.diablo.goals.StandardMeleeAttackGoal;
import com.cyno.diablo.init.DiabloEntityTypes;
import com.cyno.diablo.init.SoundInit;
import com.cyno.diablo.util.Debug;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib.core.IAnimatable;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;
import software.bernie.geckolib.molang.MolangRegistrar;
import software.bernie.geckolib.resource.GeckoLibCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.MolangUtils;
import software.bernie.shadowed.eliotlash.mclib.math.Variable;
import software.bernie.shadowed.eliotlash.molang.MolangParser;
import software.bernie.shadowed.eliotlash.molang.expressions.MolangAssignment;
import software.bernie.shadowed.eliotlash.molang.expressions.MolangExpression;
import software.bernie.shadowed.eliotlash.molang.expressions.MolangMultiStatement;
import software.bernie.shadowed.eliotlash.molang.expressions.MolangValue;


public class WardenEntity extends MonsterEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "moveController", 20, this::animationPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    private static final DataParameter<Float> ANIM_TIME = EntityDataManager.createKey(WardenEntity.class, DataSerializers.FLOAT);
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
    float initWardenSpeed = 1.8f;
    float maxWardenSpeed = 2.8f;
    float wardenSpeed;
    private AnimationFactory animationManager = new AnimationFactory(this);

    public WardenEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        // registerAnimators();
        if(!worldIn.isRemote()){
            if(instance == null)
            {
                instance = this;
                }
            else
            {
                remove();
            }
            this.accelerateMovement(wardenSpeed);
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
        meleeAttackGoal = new StandardMeleeAttackGoal(this, wardenSpeed, true, true);
        alertedBySoundGoal = new AlertedBySoundGoal(this, 1.4f, true);
        this.goalSelector.addGoal(0,  meleeAttackGoal);
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.2f));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, alertedBySoundGoal);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ANIM_SPEED, wardenSpeed);
        this.dataManager.register(IS_ATTACKING, false);
        this.dataManager.register(ANIM_TIME, 0f);
    }


    public void accelerateMovement(float speed){
        if(this.meleeAttackGoal != null){
            this.meleeAttackGoal.setSpeed(speed);
            // this.animationManager.setAnimationSpeed(speed);
            this.dataManager.set(ANIM_SPEED, speed);
            wardenSpeed = speed;

        }
    }

    public Float getAnimationSpeed(){
        // animation speed as a fraction of the initial one times the magic number
        // this is multiplied by the anim time to compress the sine waves used for animations
        float s = this.dataManager.get(ANIM_SPEED) / initWardenSpeed;
        s = ((s-1) * 5) + 1;  // the portion larger than 1 is mulitplied by a magic number
        Debug.Log(s);
        return s;
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
            this.dataManager.set(ANIM_TIME, this.getAnimTime() + 0.001f);

            if (this.getAttackTarget() == null){
                this.accelerateMovement(Math.max(initWardenSpeed, wardenSpeed - 0.01F));
            }
        }

    }

    public Boolean getIsAttacking(){
        return this.dataManager.get(IS_ATTACKING);
    }

    public Float getAnimTime(){
        return this.dataManager.get(ANIM_TIME);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        instance = null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(true || this.getHealth() < (3 * this.getMaxHealth())/4)
        {
            if(wardenSpeed < maxWardenSpeed)
            this.accelerateMovement(wardenSpeed + 0.05F);
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

    public AnimationFactory getAnimationFactory() {
        return animationManager;
    }

    private <E extends WardenEntity> PlayState animationPredicate(AnimationEvent<E> event){
     /*   if (this.getIsAttacking()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.warden.attack", false));

            // doesn't return as this is independant of walking
        } */
        if(event.getAnimatable().getMotion().length() > 0.07f){
            // TODO: change animation speed
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.warden.walking_molang", true));
        }
        else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.warden.idle", true));
        }
        return PlayState.CONTINUE;
        // no condition doesnt have an animation so always returns continue

    }

}
