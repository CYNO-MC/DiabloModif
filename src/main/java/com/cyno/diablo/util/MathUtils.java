package com.cyno.diablo.util;

import java.util.Random;

public class MathUtils {

    static Random rnd = new Random();

    public static float getRandomWithExclusion( int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
}
