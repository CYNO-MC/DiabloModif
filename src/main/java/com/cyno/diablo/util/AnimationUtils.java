package com.cyno.diablo.util;

import software.bernie.geckolib.event.AnimationTestEvent;

public class AnimationUtils {

    public static double getAnimationTick(AnimationTestEvent event, float animationDuration){
        return (float) event.getAnimationTick()% (20 * animationDuration);
    }
}
