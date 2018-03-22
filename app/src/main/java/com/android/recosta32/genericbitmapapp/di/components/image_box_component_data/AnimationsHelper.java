package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateInterpolator;

import com.android.recosta32.genericbitmapapp.ui.custom.ImageBoxComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by recosta32 on 21/03/2018.
 */

public class AnimationsHelper {
    public static final long ANIMATION_EXPLODE_EXIT_MOVE_TIME = 600;

    public static void startAnimationExplode(@NonNull final ImageBoxComponent imageBoxComponent,
                                             @NonNull final String attributeName) {
        final List<Animator> animators = new ArrayList<>();
        //Animate the mOutsideSceneRatio
        final ObjectAnimator mOutsideShiftAnim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, 0f, 1f);
        mOutsideShiftAnim.setRepeatCount(0);
        mOutsideShiftAnim.setDuration(ANIMATION_EXPLODE_EXIT_MOVE_TIME);
        mOutsideShiftAnim.setInterpolator(new AccelerateInterpolator());
        animators.add(mOutsideShiftAnim);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.start();
    }


}
