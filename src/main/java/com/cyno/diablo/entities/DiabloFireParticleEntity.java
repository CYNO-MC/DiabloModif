package com.cyno.diablo.entities;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.init.DiabloEntityTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.UUID;

public class DiabloFireParticleEntity extends AmbientEntity {
    private static final double SPEED = 2.5D;
    private UUID shooterUUID;
    private int age;

    // would be private but EntityTypes needs to access it
    public DiabloFireParticleEntity(EntityType<? extends AmbientEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
        this.setInvulnerable(true);
        this.age = 0;
        this.noClip = true;  // so that it doesn't stop before it hits the wall
        this.setFire(5);
    }

    // crashes if you don't do these even though they don't effect anything
    // also you need to bind this to the entity on FMLCommonSetupEvent
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DiabloFireParticleEntity.registerAttributes().createMutableAttribute(Attributes.FOLLOW_RANGE, 0.0);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!world.isRemote()){
            age++;
            if (age > 40) this.remove();

            // I have to call this manually becuase I set noClip to true
            this.doBlockCollisions();

            // if I do this only when it hits a block, its does it while inside a block
            // and sometimes thinks it hit the opposite side.
            ingniteBlock();
        }
    }

    // create a new one, set its shooter and send it in a horizontal direction (specified with degrees
    // around a circle centered on the shooter, 0 is positive x). yOffset is how much up from the bottom
    // of the shooter degrees can be from 0 - 360 (outside range just loops around)
    // could fairly easily refactor to not need a shooter
    public static void createNew(World world, LivingEntity shooter, double degrees, double yOffset) {
        DiabloFireParticleEntity particle = new DiabloFireParticleEntity(DiabloEntityTypes.DIABLO_FIRE_PARTICLE.get(), world);

        particle.setPosition(shooter.getPosX(), shooter.getPosY() + yOffset, shooter.getPosZ());
        particle.shooterUUID = shooter.getUniqueID();

        // convert degrees around unit circle to their point on the circle
        double rad = Math.toRadians(degrees);
        double x = Math.cos(rad);
        double z = Math.sin(rad);

        Vector3d motion = (new Vector3d(x, 0, z)).scale(SPEED);
        particle.setMotion(motion);

        world.addEntity(particle);
    }

    // when it hits an entity other than the shooter light them on fire and remove itself
    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (shooterUUID == null || entityIn instanceof DiabloFireParticleEntity) return;
        boolean isShooter = entityIn.getUniqueID() == shooterUUID;

        if (!world.isRemote() && entityIn instanceof LivingEntity && !isShooter){
            entityIn.setFire(10);
            this.remove();
        }
    }

    // without noClip=true this seems to never fire because it stops moving before its in the block
    @Override
    protected void onInsideBlock(BlockState state) {
        if (state.isSolid()){
            this.remove();
        }
    }

    // lights the block in front on fire.
    private void ingniteBlock() {
        BlockRayTraceResult ray = this.world.rayTraceBlocks(new RayTraceContext(this.getPositionVec(), this.getPositionVec().add(this.getLookVec()), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
        if (ray.getType() != RayTraceResult.Type.BLOCK) return;

        // from flint and steel code
        BlockPos blockpos1 = ray.getPos().offset(ray.getFace());  // pos to put fire in
        if (AbstractFireBlock.canLightBlock(world, blockpos1, ray.getFace().getOpposite())) {
            BlockState blockstate1 = AbstractFireBlock.getFireForPlacement(world, blockpos1);
            world.setBlockState(blockpos1, blockstate1, 11);
        }
    }
}
