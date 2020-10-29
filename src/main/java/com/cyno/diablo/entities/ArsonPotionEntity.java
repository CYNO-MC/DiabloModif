package com.cyno.diablo.entities;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Optional;

public class ArsonPotionEntity extends PotionEntity {
    public ArsonPotionEntity(EntityType<? extends PotionEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public ArsonPotionEntity(World worldIn, LivingEntity livingEntityIn) {
        super(worldIn, livingEntityIn);
    }

    // when it hits a block or entity
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote()){
            doFireExplosion();
        }
    }

    // called for each position doFireExplosion decides to effect
    private void processPosition(BlockPos pos){
        if (this.rand.nextInt(3) == 0 && this.world.getBlockState(pos).isAir() && this.world.getBlockState(pos.down()).isOpaqueCube(this.world, pos.down())) {
            this.world.setBlockState(pos, AbstractFireBlock.getFireForPlacement(this.world, pos));
        }
    }

    // decide what positions we care about. logic from explosions
    // I really don't understand what this does, pls help
    private void doFireExplosion(){
        final float RADIUS = 5;  // base this on amplifier?

        int i = 16;
        for(int j = 0; j < 16; ++j) {
            for(int k = 0; k < 16; ++k) {
                for(int l = 0; l < 16; ++l) {
                    if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                        double d0 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                        double d1 = (double)((float)k / 15.0F * 2.0F - 1.0F);
                        double d2 = (double)((float)l / 15.0F * 2.0F - 1.0F);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 = d0 / d3;
                        d1 = d1 / d3;
                        d2 = d2 / d3;
                        float f = RADIUS * (0.7F + this.world.rand.nextFloat() * 0.6F);
                        double d4 = this.getPosX();
                        double d6 = this.getPosY();
                        double d8 = this.getPosZ();

                        for(float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
                            BlockPos blockpos = new BlockPos(d4, d6, d8);
                            BlockState blockstate = this.world.getBlockState(blockpos);
                            float res = blockstate.isAir() ? 0 : 4;  // normally is based on explosion resistance but we only care about air cause we aren't breaking anything
                            if (res > 0) {
                                f -= (res + 0.3F) * 0.3F;
                            }

                            if (f > 0.0F) {
                                processPosition(blockpos);  // Actually do things with this position
                            }

                            d4 += d0 * (double)0.3F;
                            d6 += d1 * (double)0.3F;
                            d8 += d2 * (double)0.3F;
                        }
                    }
                }
            }
        }
    }

    // from ExplosionContext
    private Optional<Float> getExplosionResistance(Explosion explosion, IBlockReader reader, BlockPos pos, BlockState state, FluidState fluid) {
        return state.isAir(reader, pos) && fluid.isEmpty() ? Optional.empty() : Optional.of(Math.max(state.getExplosionResistance(reader, pos, explosion), fluid.getExplosionResistance(reader, pos, explosion)));
    }
}
