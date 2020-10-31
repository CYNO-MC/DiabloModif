package com.cyno.diablo.entities;

import com.cyno.diablo.util.CircleHelper;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
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
                CircleHelper.horizontalCircle(world, center, radius, (posAndWorld) -> {
                    processPosition(posAndWorld.pos);
                });

                if (radius == MAX_RADIUS){
                    this.remove();
                }
            }
        }

    }

    // when it hits a block, set the block above to fire and start the circles going outward
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote()){
            BlockRayTraceResult ray = (BlockRayTraceResult) result;
            if (ray.getType() != RayTraceResult.Type.BLOCK) return;
            if (!world.getFluidState(ray.getPos()).isEmpty()) {
                this.remove();
                return;
            }

            BlockPos blockpos1 = ray.getPos().offset(ray.getFace());  // pos to put fire in
            processPosition(blockpos1);

            center = blockpos1;
            hasHitGround = true;
            this.setMotion(Vector3d.ZERO);
        }
    }

    // called for each position the circle decides to effect
    // if its air makes it fire
    private void processPosition(BlockPos pos){
        if (this.world.getBlockState(pos).isAir() && this.world.getBlockState(pos.down()).isOpaqueCube(this.world, pos.down())) {
            this.world.setBlockState(pos, AbstractFireBlock.getFireForPlacement(this.world, pos));
        }
    }
}
