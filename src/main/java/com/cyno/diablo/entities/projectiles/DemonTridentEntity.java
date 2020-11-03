package com.cyno.diablo.entities.projectiles;

import com.cyno.diablo.init.DiabloEntityTypes;
import com.cyno.diablo.init.DiabloItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

// same as a normal trident but enchants that normally work while in water work in the nether instead and has a flame effect
// this started by just copying everything from TridentEntity, can't just extend it becuase I need to accesss some private things

public class DemonTridentEntity extends AbstractArrowEntity {
    private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.createKey(DemonTridentEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Boolean> field_226571_aq_ = EntityDataManager.createKey(DemonTridentEntity.class, DataSerializers.BOOLEAN);
    private ItemStack thrownStack = new ItemStack(DiabloItems.DEMON_TRIDENT.get());
    private boolean dealtDamage;
    public int returningTicks;

    public DemonTridentEntity(EntityType<? extends DemonTridentEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DemonTridentEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(DiabloEntityTypes.DEMON_TRIDENT.get(), thrower, worldIn);
        this.thrownStack = thrownStackIn.copy();
        this.dataManager.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyaltyModifier(thrownStackIn));
        this.dataManager.set(field_226571_aq_, thrownStackIn.hasEffect());
    }

    @OnlyIn(Dist.CLIENT)
    public DemonTridentEntity(World worldIn, double x, double y, double z) {
        super(DiabloEntityTypes.DEMON_TRIDENT.get(), x, y, z, worldIn);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(LOYALTY_LEVEL, (byte)0);
        this.dataManager.register(field_226571_aq_, false);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        if (this.timeInGround > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.func_234616_v_();
        if ((this.dealtDamage || this.getNoClip()) && entity != null) {
            int i = this.dataManager.get(LOYALTY_LEVEL);
            if (i > 0 && !this.shouldReturnToThrower()) {
                if (!this.world.isRemote && this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.entityDropItem(this.getArrowStack(), 0.1F);
                }

                this.remove();
            } else if (i > 0) {
                this.setNoClip(true);
                Vector3d vector3d = new Vector3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(), entity.getPosZ() - this.getPosZ());
                this.setRawPosition(this.getPosX(), this.getPosY() + vector3d.y * 0.015D * (double)i, this.getPosZ());
                if (this.world.isRemote) {
                    this.lastTickPosY = this.getPosY();
                }

                double d0 = 0.05D * (double)i;
                this.setMotion(this.getMotion().scale(0.95D).add(vector3d.normalize().scale(d0)));
                if (this.returningTicks == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.returningTicks;
            }
        }

        super.tick();
    }

    private boolean shouldReturnToThrower() {
        Entity entity = this.func_234616_v_();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    protected ItemStack getArrowStack() {
        return this.thrownStack.copy();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean func_226572_w_() {
        return this.dataManager.get(field_226571_aq_);
    }

    /**
     * Gets the EntityRayTraceResult representing the entity hit
     */
    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
        return this.dealtDamage ? null : super.rayTraceEntities(startVec, endVec);
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        Entity target = p_213868_1_.getEntity();
        float f = 8.0F;
        if (target instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)target;
            f += EnchantmentHelper.getModifierForCreature(this.thrownStack, livingentity.getCreatureAttribute());
        }

        Entity shooter = this.func_234616_v_();
        DamageSource damagesource = DamageSource.causeTridentDamage(this, (Entity)(shooter == null ? this : shooter));
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;
        if (target.attackEntityFrom(damagesource, f)) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)target;
                target.setFire(5);  // I added this
                if (shooter instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingTarget, shooter);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity)shooter, livingTarget);
                }

                this.arrowHit(livingTarget);
            }
        }

        // do the thing where it looks like it bounces of the target
        this.setMotion(this.getMotion().mul(-0.01D, -0.1D, -0.01D));

        float volume = 1.0F;
        boolean didChanneling = handleChanneling(shooter, target);
        if (didChanneling){
            soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
            volume = 5.0F;
        }


        this.playSound(soundevent, volume, 1.0F);
    }

    private boolean handleChanneling(Entity shooter, Entity target){
        // I changed this condidtion from isThundering, also removed canSeeSky check
        boolean isNether = world.getDimensionKey() == World.THE_NETHER;
        if (this.world instanceof ServerWorld && isNether && EnchantmentHelper.hasChanneling(this.thrownStack)) {
            BlockPos blockpos = target.getPosition();
            LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.world);
            lightningboltentity.moveForced(Vector3d.copyCenteredHorizontally(blockpos));
            lightningboltentity.setCaster(shooter instanceof ServerPlayerEntity ? (ServerPlayerEntity)shooter : null);
            this.world.addEntity(lightningboltentity);
            return true;
        }
        return false;
    }

    /**
     * The sound made when an entity is hit by this projectile
     */
    protected SoundEvent getHitEntitySound() {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(PlayerEntity entityIn) {
        Entity entity = this.func_234616_v_();
        if (entity == null || entity.getUniqueID() == entityIn.getUniqueID()) {
            super.onCollideWithPlayer(entityIn);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("Trident", 10)) {
            this.thrownStack = ItemStack.read(compound.getCompound("Trident"));
        }

        this.dealtDamage = compound.getBoolean("DealtDamage");
        this.dataManager.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyaltyModifier(this.thrownStack));
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("Trident", this.thrownStack.write(new CompoundNBT()));
        compound.putBoolean("DealtDamage", this.dealtDamage);
    }

    public void func_225516_i_() {
        int i = this.dataManager.get(LOYALTY_LEVEL);
        if (this.pickupStatus != AbstractArrowEntity.PickupStatus.ALLOWED || i <= 0) {
            super.func_225516_i_();
        }

    }


    // If a projectile doesn't render (is just randomly invisible), you need to add this function. Thanks Adrien!
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected float getWaterDrag() {
        return 0.99F;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }
}
