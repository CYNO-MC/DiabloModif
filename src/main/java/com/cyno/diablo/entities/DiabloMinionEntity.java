package com.cyno.diablo.entities;

import com.cyno.diablo.init.DiabloItems;
import com.cyno.diablo.init.SoundInit;
import com.cyno.diablo.items.VialItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.core.IAnimatable;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;

public class DiabloMinionEntity extends MonsterEntity implements IAnimatable {
    public AnimationFactory factory = new AnimationFactory(this);

    private int bloodRemovalTimer;
    public float getHealthData (){
        return this.dataManager.get(HEALTH_DATA);
    }

    public DiabloMinionEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        bloodRemovalTimer = 0;
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this,0.75f));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DiabloMinionEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.45f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0f)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0f);
    }
    private static final DataParameter<Float> HEALTH_DATA = EntityDataManager.createKey(DiabloMinionEntity.class, DataSerializers.FLOAT);

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(HEALTH_DATA, 10.0f);
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

    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote() && bloodRemovalTimer == 0 && stack.getItem() == DiabloItems.GLASS_VILE.get()){
            ItemStack newStack = VialItem.increaseFullness(stack);


            boolean success = player.addItemStackToInventory(newStack);
            if (!success){
                player.entityDropItem(newStack);
            }

            this.attackEntityFrom(DamageSource.MAGIC, 1.0F);
            bloodRemovalTimer = 10;

            return ActionResultType.SUCCESS;
        }


        return super.func_230254_b_(player, hand);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 5 + this.world.rand.nextInt(9);
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundInit.DSTEP.get(), 1.00f, 1.00f);

    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        if(!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.diablomodif.diablo_minion_entity.walk", true));
        } else  event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.diablomodif.diablo_minion_entity.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "moveController", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
