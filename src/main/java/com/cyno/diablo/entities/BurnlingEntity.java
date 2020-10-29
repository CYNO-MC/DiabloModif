package com.cyno.diablo.entities;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.goals.AlertedBySoundGoal;
import com.cyno.diablo.goals.BurnlingAttackGoal;
import com.cyno.diablo.goals.StandardMeleeAttackGoal;
import com.cyno.diablo.init.DiabloEntityTypes;
import com.cyno.diablo.util.AnimationUtils;
import com.cyno.diablo.util.Debug;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.event.CustomInstructionKeyframeEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import javax.annotation.Nullable;

public class BurnlingEntity extends MonsterEntity implements IAnimatedEntity {

    private static final DataParameter<Boolean> IS_ATTACKING = EntityDataManager.createKey(BurnlingEntity.class, DataSerializers.BOOLEAN);

    private float currentAttackStep = 0f; //adds a little delay at the beginning of the "timer" otherwise the animation of the burnling and instantiation of the lava bubbble don't match (replace with the geckolib 3.0.0)
    private float maxAttackInterval = 60f;
    private EntityAnimationManager animationManager = new EntityAnimationManager();
    public AnimationController animator = new EntityAnimationController(this, "moveController", 1, this::animationPredicate);

    public WaterAvoidingRandomWalkingGoal walkGoal;
    public BurnlingEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimators();
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationManager;
    }


    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_ATTACKING, false);
    }

    @Override
    protected float getJumpUpwardsMotion() {
        if(!this.getAttacking())
        return super.getJumpUpwardsMotion();
        else return 0;
    }

    public void SpawnLavaBubbles(float speed){
        Debug.Danger("...............");
        LavaBubbleProjectileEntity lavaBubbleProjectileEntity = new LavaBubbleProjectileEntity(DiabloEntityTypes.LAVA_BUBBLE.get(), this.world);
        lavaBubbleProjectileEntity.setPosition(this.getPosX(), this.getPosY() + 0.5f, this.getPosZ());
        lavaBubbleProjectileEntity.addVelocity((this.rand.nextInt(6) - 3) * speed,(this.rand.nextInt(6)) * speed,(this.rand.nextInt(6) - 3) * speed);
        this.world.addEntity(lavaBubbleProjectileEntity);
     //   Debug.Log();
    }
    private <E extends BurnlingEntity> boolean animationPredicate(AnimationTestEvent<E> event){

        if(this.getAttacking()){
            animator.setAnimation(new AnimationBuilder().addAnimation("animation.burnling.attack", true));

            return true;
        }
        else{
            if(this.getMotion().length() > 0.06){
                animator.setAnimation(new AnimationBuilder().addAnimation("animation.burnling.walking", true));
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    private <E extends Entity> boolean instructionPredicate(CustomInstructionKeyframeEvent<E> event){
         if(event.instructions.get(0).equals("spawnParticles")){
          //   ((BurnlingEntity) event.getEntity()).SpawnLavaBubbles(0.2f); //THIS IS WHERE IT FUCKS UP CANT CALL FUNCTION FROM event.getEntity() or from my class directly (i.e this.spawnLavaBubbles)

             return true;
        }
         else{
             return false;
         }
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return BurnlingEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 30.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal(this, VillagerEntity.class, true));
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(1, new BurnlingAttackGoal(this));

        this.walkGoal = new WaterAvoidingRandomWalkingGoal(this, 1.2f);
        this.goalSelector.addGoal(1, this.walkGoal);
    }

    private void registerAnimators(){
        animationManager.addAnimationController(animator);
        animator.registerCustomInstructionListener(this::instructionPredicate);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 2;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Nullable
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.4f, 1.0f);
    }

    public void setAttacking(Boolean bool){
        this.dataManager.set(IS_ATTACKING, bool);
    }

    public Boolean getAttacking(){
       return this.dataManager.get(IS_ATTACKING);
    }

    @Override
    public void travel(Vector3d travelVector) {
        if(!this.getAttacking())
        super.travel(travelVector);
        else
            super.travel(new Vector3d(0, travelVector.getY(), 0));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if(!this.world.isRemote()){
            if(this.getAttacking())
            {
                if(this.currentAttackStep < this.maxAttackInterval)
                {
                    ++this.currentAttackStep;
                }
                else{
                    this.currentAttackStep = 0;
                    this.SpawnLavaBubbles(0.2f);
                }

            }
            else
                if(currentAttackStep > 0)
                    this.currentAttackStep = 0;

            this.setAttacking(this.getAttackTarget() != null);
            if(this.isInWater())
                this.attackEntityFrom(DamageSource.DROWN, 4);
        }

    }
}
