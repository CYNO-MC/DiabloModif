package com.cyno.diablo.util;
import com.mojang.datafixers.types.Func;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Function;

public class CircleHelper {
    private static void handleBlock(int x, int y, int z, World world, Consumer<PosAndWorld> action){
        action.accept(new PosAndWorld(new BlockPos(x, y, z), world));
    }

    private static void drawCirclePart(int xc, int yc, int x, int y,  int height, World world, Consumer<PosAndWorld> action)
    {
        handleBlock(xc+x, height, yc+y, world, action);
        handleBlock(xc-x, height, yc+y, world,  action);
        handleBlock(xc+x, height, yc-y, world,  action);
        handleBlock(xc-x, height, yc-y, world,  action);
        handleBlock(xc+y, height, yc+x, world,  action);
        handleBlock(xc-y, height, yc+x, world,  action);
        handleBlock(xc+y, height, yc-x, world,  action);
        handleBlock(xc-y, height, yc-x, world,  action);
    }

    // Function for circle-generation
    // using Bresenham's algorithm
    private static void circleBres(int xc, int yc, int r, int height, World world, Consumer<PosAndWorld> action)
    {
        int x = 0, y = r;
        int d = 3 - 2 * r;
        drawCirclePart(xc, yc, x, y, height, world, action);
        while (y >= x)
        {
            // for each pixel we will
            // draw all eight pixels

            x++;

            // check for decision parameter
            // and correspondingly
            // update d, x, y
            if (d > 0)
            {
                y--;
                d = d + 4 * (x - y) + 10;
            }
            else
                d = d + 4 * x + 6;
            drawCirclePart(xc, yc, x, y, height, world, action);
        }
    }


    // finds the position for a circle of blocks (centered on <center> with radius <radius>)
    // and calls <action> for each of them.
    public static void horizontalCircle(World world, BlockPos center, int radius, Consumer<PosAndWorld> action){
        circleBres(center.getX(), center.getZ(), radius, center.getY(), world, action);
    }

    // this just exists to pass the position and the world to the consumer in one argument
    // yes, that's dumb, I should have made my own interface or maybe java has one
    // but I'm really tired, we can fix it later
    public static class PosAndWorld {
        public BlockPos pos;
        public World world;

        public PosAndWorld(BlockPos posIn, World world){
            this.pos = posIn;
            this.world = world;
        }
    }
}
