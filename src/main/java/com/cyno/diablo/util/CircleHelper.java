package com.cyno.diablo.util;
import net.minecraft.util.math.BlockPos;

import java.util.function.Consumer;

public class CircleHelper {
    private static void handleBlock(int x, int y, int z, Consumer<BlockPos> action){
        action.accept(new BlockPos(x, y, z));
    }

    private static void drawCirclePart(int xc, int yc, int x, int y,  int height, Consumer<BlockPos> action)
    {
        if(y == 1)
        {
            handleBlock(xc+1, height, yc+1, action);
            handleBlock(xc+1, height, yc-1, action);
            handleBlock(xc-1, height, yc-1, action);
            handleBlock(xc-1, height, yc+1, action);
        }
        handleBlock(xc+x, height, yc+y, action);
        handleBlock(xc-x, height, yc+y,  action);
        handleBlock(xc+x, height, yc-y,  action);
        handleBlock(xc-x, height, yc-y,  action);
        handleBlock(xc+y, height, yc+x,  action);
        handleBlock(xc-y, height, yc+x,  action);
        handleBlock(xc+y, height, yc-x,  action);
        handleBlock(xc-y, height, yc-x,  action);
    }

    // Function for circle-generation using Bresenham's algorithm
    private static void circleBres(int xc, int yc, int r, int height, Consumer<BlockPos> action)
    {
        int x = 0, y = r;
        int d = 3 - 2 * r;
        drawCirclePart(xc, yc, x, y, height, action);
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
            drawCirclePart(xc, yc, x, y, height, action);
        }
    }


    // finds the position for a circle of blocks (centered on <center> with radius <radius>)
    // and calls <action> (method reference / lambda that takes in a BlockPos) for each of them.
    public static void horizontalCircle(BlockPos center, int radius, Consumer<BlockPos> action){
        circleBres(center.getX(), center.getZ(), radius, center.getY(), action);
    }
}
