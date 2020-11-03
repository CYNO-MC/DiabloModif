package com.cyno.diablo.entities;

import com.cyno.diablo.util.CircleHelper;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ArsonPotionEntity extends PotionEntity {
    boolean hasHitGround;
    BlockPos center;
    int timer = 0;

    int MAX_RADIUS = 4;  // number of rings (not counting the center)
    int INTERVAL = 2;  // ticks between each ring

    public ArsonPotionEntity(EntityType<? extends PotionEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        hasHitGround = false;
    }

    public ArsonPotionEntity(World worldIn, LivingEntity livingEntityIn) {
        super(worldIn, livingEntityIn);
        hasHitGround = false;
    }

    @Override
    public void tick() {
        if (!hasHitGround){
            super.tick();
        } else if (!world.isRemote() && this.isAlive()){
            timer++;

            // add a new ring of fire every <INTERVAL> ticks
            boolean addRingNow = (timer % INTERVAL) == 0;
            if (addRingNow) {
                int radius = timer / INTERVAL;
                CircleHelper.horizontalCircle(center, radius, this::addFireAtPos);

                if (radius == MAX_RADIUS){
                    this.remove();
                }
            }
        }

    }

    // when it hits a block, set the block above to fire and start the circles going outward
    // if it hits entity, set on fire but keep falling until it hits a block
    @Override
    protected void onImpact(RayTraceResult result) {
        // should never be true becuase this is only called on hits
        if (result.getType() == RayTraceResult.Type.MISS) return;

        if (!world.isRemote()){
            if (result.getType() == RayTraceResult.Type.ENTITY){
                ((EntityRayTraceResult)result).getEntity().setFire(10);
                return;
            }

            BlockRayTraceResult ray = (BlockRayTraceResult) result;

            // if it hit in liquid, don't do the fire circles
            if (!world.getFluidState(ray.getPos()).isEmpty()) {
                this.remove();
                return;
            }

            BlockPos firePos = ray.getPos().offset(ray.getFace());
            addFireAtPos(firePos);

            center = firePos;  // center of the fire circle
            hasHitGround = true;  // trigger fire circle adding logic

            this.setMotion(Vector3d.ZERO);
            this.setInvisible(true);
        }
    }

    // called for each position the circle decides to effect, if its air makes it fire
    private void addFireAtPos(BlockPos pos){
        if (this.world.getBlockState(pos).isAir() && this.world.getBlockState(pos.down()).isOpaqueCube(this.world, pos.down())) {
            this.world.setBlockState(pos, AbstractFireBlock.getFireForPlacement(this.world, pos));
        }
    }
}
