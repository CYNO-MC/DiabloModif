package com.cyno.diablo.entities;

import com.cyno.diablo.goals.FireCircleAttackGoal;
import com.cyno.diablo.goals.FlamingTargetMeleeAttackGoal;
import com.cyno.diablo.goals.GrabAttackGoal;
import com.cyno.diablo.init.DiabloItems;
import com.cyno.diablo.init.SoundInit;
import com.cyno.diablo.items.VialItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.core.IAnimatable;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;

import java.util.function.Predicate;

public class DiabloEntity extends MonsterEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    // A predicate to pass into NearestAttackableTargetGoal to check if the entity is on fire
    private static final Predicate<LivingEntity> IS_ON_FIRE = (entity) -> {
        return entity.getFireTimer() > 0;
    };

    // counts down to when you can draw blood again
    private int bloodRemovalTimer;

    // set in GrabAttackGoal, checked in FlamingTargetMeeleAttackGoal. makes it not attack while grabbing someone
    public boolean isCurrentlyGrabbing;


    public float getHealthData (){
        return this.dataManager.get(HEALTH_DATA);
    }

    public DiabloEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        bloodRemovalTimer = 0;
        isCurrentlyGrabbing = false;
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

        // If the target is very nearby, grab them (so they can't move) and deal damage over time until they deal a cetain amount of damage
        this.goalSelector.addGoal(1, new GrabAttackGoal(this));

        // Walk towards and attack the target. Stop if they are no longer on fire
        this.goalSelector.addGoal(2, new FlamingTargetMeleeAttackGoal(this, 1.0d, true));

        // When there are no flaming players nearby, shoot rings of fire particles
        this.goalSelector.addGoal(3, new FireCircleAttackGoal(this, 48, 1.5D));
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

            if (bloodRemovalTimer > 0) bloodRemovalTimer--;
        }
    }

    // when a player right clicks with a Glass Vial in their hand, collect some demon blood
    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote() && bloodRemovalTimer == 0 && stack.getItem() == DiabloItems.GLASS_VILE.get()){
            ItemStack newStack = VialItem.increaseFullness(stack);

            // give it to the player, if inventory full drop it on the ground
            boolean success = player.addItemStackToInventory(newStack);
            if (!success){
                player.entityDropItem(newStack);
            }

            this.attackEntityFrom(DamageSource.MAGIC, 2.0F);
            bloodRemovalTimer = 10;  // wait x ticks before you can draw blood again

            return ActionResultType.SUCCESS;
        }


        return super.func_230254_b_(player, hand);
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

    // decides which animation to play. animationName is from the json file in resources/id/animations
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        // idle if not moving fast
        if(this.getMotion().length() < 0.06){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.diablomodif.diabloentity.idle", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data){
        data.addAnimationController(new AnimationController(this, "moveController", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory(){
        return this.factory;
    }
}

