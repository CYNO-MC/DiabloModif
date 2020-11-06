package com.cyno.diablo.entities;

import com.cyno.diablo.util.Debug;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.task.FindNewAttackTargetTask;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class LavaBubbleProjectileEntity extends MobEntity {

    private final static DataParameter<Rotations> ROTATION_DATA = EntityDataManager.createKey(LavaBubbleProjectileEntity.class, DataSerializers.ROTATIONS);

    public LavaBubbleProjectileEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute ( Attributes.MAX_HEALTH, 1D)
                .createMutableAttribute ( Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 0;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    @Nullable
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {

    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ROTATION_DATA, new Rotations(0, 0, 0));
    }

    private void doSplashLava(int radius){
        if(this.world.getBlockState(this.getPosition()).getBlock().matchesBlock(Blocks.LAVA) || this.world.getBlockState(this.getPosition()).getBlock().matchesBlock(Blocks.MAGMA_BLOCK))
            return;

        int X = this.getPosition().getX();
        int Y = this.getPosition().getY() - 1;
        int Z = this.getPosition().getZ();

        BlockPos pos;

        for(int y = Y - radius; y < Y + radius; y++){
            for(int z = Z - radius; z < Z + radius; z++){
                for(int x = X - radius; x < X + radius; x++){
                    pos = new BlockPos(x, y, z);
                    if(!this.world.getBlockState(pos).getBlock().matchesBlock(Blocks.AIR) && !this.world.getBlockState(pos).getBlock().matchesBlock(Blocks.CAVE_AIR) && !this.world.getBlockState(pos).getBlock().matchesBlock(Blocks.VOID_AIR) && !this.world.getBlockState(pos).getBlock().matchesBlock(Blocks.LAVA) && !this.world.getBlockState(pos).getBlock().matchesBlock(Blocks.MAGMA_BLOCK)){
                        Debug.Log(this.world.getBlockState(pos).getMaterial() != null);
                        if(this.world.getBlockState(pos).getMaterial().blocksMovement()){
                            this.world.setBlockState(pos, Blocks.LAVA.getDefaultState());
                        }
                        else{
                            this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                        }
                    }
                }
            }
        }

    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        super.collideWithEntity(entityIn);
        if(!(entityIn instanceof BurnlingEntity) && !(entityIn instanceof LavaBubbleProjectileEntity) && !entityIn.isImmuneToFire())
        {
            entityIn.attackEntityFrom(DamageSource.LAVA, 4);
            entityIn.setFire(3);
            this.doSplashLava(1);
            this.remove();
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
    }

    public Vector3f getRollDirection(){
        return new Vector3f(this.dataManager.get(ROTATION_DATA).getX(), this.dataManager.get(ROTATION_DATA).getY(), this.dataManager.get(ROTATION_DATA).getZ());
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void livingTick() {
        super.livingTick();

        this.world.addParticle(ParticleTypes.LAVA, this.getPosX(), this.getPosY(), this.getPosZ(), (this.rand.nextDouble() - 0.5D) * 0.01D, (this.rand.nextDouble() - 0.5D) * 0.01D, (this.rand.nextDouble() - 0.5D) * 0.01D);

        if(!this.world.isRemote()){
            this.dataManager.set(ROTATION_DATA, new Rotations((float) (this.prevPosX - this.getPosX()) + getRollDirection().getX(), 0, (float) (this.prevPosZ - this.getPosZ()) + getRollDirection().getZ()));
            if(this.collidedHorizontally || this.collidedVertically){
                this.playSound(SoundEvents.BLOCK_LAVA_AMBIENT, 0.4f, 1.0f);
                this.doSplashLava(1);
                this.remove();
            }
        }

    }
}
