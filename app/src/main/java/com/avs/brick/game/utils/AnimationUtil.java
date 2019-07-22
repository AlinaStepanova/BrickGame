package com.avs.brick.game.utils;


import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.avs.brick.game.R;

public class AnimationUtil {

    public static Animation getZoomIn(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.zoom_in);
    }

    public static Animation getSlideInLeft(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
    }

    public static Animation getSlideInRight(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
    }
}
