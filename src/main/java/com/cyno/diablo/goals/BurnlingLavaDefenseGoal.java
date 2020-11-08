package com.cyno.diablo.goals;

import com.cyno.diablo.entities.BurnlingEntity;
import com.cyno.diablo.util.CircleHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

public class BurnlingLavaDefenseGoal extends Goal {
    private BurnlingEntity creature;
    boolean magmaSet = false;
    boolean lavaSet = false;
    int radius = 30;
    float currentLavaMeltTime = 0;
    float maxLavaMeltTime = 40;
    public BurnlingLavaDefenseGoal(BurnlingEntity burnling, int radius){
        this.creature = burnling;
        this.radius = radius;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        super.resetTask();
    }

    @Override
    public boolean shouldExecute() {
        return this.creature.getAttackTarget() != null && !lavaSet;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.creature.getAttackTarget() != null && !lavaSet;
    }

    @Override
    public void tick() {
        super.tick();

        if(!magmaSet){
            for(int i = 0; i <= radius; i++){
                if(i > 0)
                {
                    if(currentLavaMeltTime < maxLavaMeltTime)
                        ++this.currentLavaMeltTime;
                    else
                    {
                        this.currentLavaMeltTime = 0;
                        CircleHelper.horizontalCircle(this.creature.getPosition().down(), i, this::addMagmaPos);

                        if(i == radius)
                            magmaSet = true;
                    }

                }
                else
                    this.creature.world.setBlockState(this.creature.getPosition().down(), Blocks.MAGMA_BLOCK.getDefaultState());
            }
        }
        else{
            for(int i = 0; i < radius; i++){
                if(i > 0)
                {
                    if(currentLavaMeltTime < maxLavaMeltTime)
                        ++this.currentLavaMeltTime;
                    else
                    {
                        this.currentLavaMeltTime = 0;
                        CircleHelper.horizontalCircle(this.creature.getPosition().down(), i, this::addLavaPos);

                        if(i == radius - 1)
                        {
                            lavaSet = true;
                            this.creature.canMove = false;
                        }
                    }

                }
            }
        }


    }

    // called for each position the circle decides to effect, if its air makes it fire

    private void addMagmaPos(BlockPos pos){
        if (this.creature.world.getBlockState(pos.down()).isOpaqueCube(this.creature.world, pos.down())) {
            this.creature.world.setBlockState(pos, Blocks.MAGMA_BLOCK.getDefaultState());
        }
    }

    private void addLavaPos(BlockPos pos){
        if (this.creature.world.getBlockState(pos.down()).isOpaqueCube(this.creature.world, pos.down())) {
            this.creature.world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        }
    }
}
