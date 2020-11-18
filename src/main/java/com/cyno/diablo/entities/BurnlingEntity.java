package com.cyno.diablo.entities;

import com.cyno.diablo.goals.BurnlingAttackGoal;
import com.cyno.diablo.goals.BurnlingLavaDefenseGoal;
import com.cyno.diablo.init.DiabloEntityTypes;
import com.cyno.diablo.util.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class BurnlingEntity extends MonsterEntity implements IAnimatable {

    private static final DataParameter<Boolean> IS_ATTACKING = EntityDataManager.createKey(BurnlingEntity.class, DataSerializers.BOOLEAN);

    public boolean canMove = true;
    private float currentAttackStep = 0f; //adds a little delay at the beginning of the "timer" otherwise the animation of the burnling and instantiation of the lava bubbble don't match (replace with the geckolib 3.0.0)
    private float maxAttackInterval = 60f;
    private AnimationFactory factory = new AnimationFactory(this);
    public WaterAvoidingRandomWalkingGoal walkGoal;
    public BurnlingEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
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
        LavaBubbleProjectileEntity lavaBubbleProjectileEntity = new LavaBubbleProjectileEntity(DiabloEntityTypes.LAVA_BUBBLE.get(), this.world);
        lavaBubbleProjectileEntity.setPosition(this.getPosX(), this.getPosY() + 0.5f, this.getPosZ());


        float xPow = MathUtils.getRandomWithExclusion(-3, 6, 0);
        float zPow = MathUtils.getRandomWithExclusion(-3, 6, 0);

        lavaBubbleProjectileEntity.addVelocity(xPow * speed,(this.rand.nextInt(6) + 1) * speed,zPow * speed);
        this.world.addEntity(lavaBubbleProjectileEntity);
     //   Debug.Log();
    }
    private <E extends BurnlingEntity> PlayState animationPredicate(AnimationEvent<E> event){

        if(this.getAttacking()){
            if(this.isSurroundedByLava()){
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.burnling.attack", true));
            }
            else{
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.burnling.defense", false));
            }
            return PlayState.CONTINUE;
        }
        else{
            if(this.getMotion().length() > 0.06){
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.burnling.walking", true));
                return PlayState.CONTINUE;
            }
            else
            {
                return PlayState.STOP;
            }
        }
    }

    public boolean isSurroundedByLava(){

        //return wether the entity is standing on a block (different than lava and water) and that this block is surrounded by lava
        return !this.world.getBlockState(this.getPosition().down()).getBlock().matchesBlock(Blocks.LAVA)
                && !this.world.getBlockState(this.getPosition().down()).getBlock().matchesBlock(Blocks.WATER)
                && this.world.getBlockState(this.getPosition().down().south()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().north()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().west()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().east()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().south().east()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().south().west()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().north().east()).getBlock().matchesBlock(Blocks.LAVA)
                && this.world.getBlockState(this.getPosition().down().north().west()).getBlock().matchesBlock(Blocks.LAVA);
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        if(!(entityIn instanceof LavaBubbleProjectileEntity))
        {
            super.applyEntityCollision(entityIn);
        }
    }

    @Override
    protected void collideWithNearbyEntities() {
            super.collideWithNearbyEntities();
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if(!(entityIn instanceof LavaBubbleProjectileEntity))
        {
            super.collideWithEntity(entityIn);
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
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
        this.goalSelector.addGoal(1, new BurnlingAttackGoal(this, 30));
        this.goalSelector.addGoal(1, new BurnlingLavaDefenseGoal(this, 5));
        this.walkGoal = new WaterAvoidingRandomWalkingGoal(this, 1.2f);
        this.goalSelector.addGoal(1, this.walkGoal);
    }


    @Override
    public void applyKnockback(float strength, double ratioX, double ratioZ) {}

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
        if(!this.getAttacking() && canMove)
        super.travel(travelVector);
        else
            super.travel(new Vector3d(0, travelVector.getY(), 0));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if(!this.world.isRemote())
            this.setAttacking(this.getAttackTarget() != null);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "moveController", 0, this::animationPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
