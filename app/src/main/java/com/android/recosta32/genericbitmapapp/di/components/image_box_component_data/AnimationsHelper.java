package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.android.recosta32.genericbitmapapp.ui.custom.ImageBoxComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by recosta32 on 21/03/2018.
 */

public class AnimationsHelper {
    static final long ANIMATION_EXPLODE_EXIT_MOVE_TIME = 600;
    static final long ANIMATION_EXPLODE_EXIT_POSITION_TIME = 800;

    @NonNull
    private List<Animator> animators;

    private void setAnimators(@NonNull List<Animator> animators) {
        this.animators = animators;
    }

    public static AnimationsHelper init() {
        final AnimationsHelper animationsHelper = new AnimationsHelper();
        animationsHelper.setAnimators(new ArrayList<Animator>());
        return animationsHelper;
    }

    public AnimationsHelper addAnimationExplodeColorAndOutside(@NonNull final ImageBoxComponent imageBoxComponent,
                                                               @NonNull final String attributeName) {
        final ObjectAnimator mOutsideShiftAnim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, 0f, 1f);
        mOutsideShiftAnim.setRepeatCount(0);
        mOutsideShiftAnim.setDuration(ANIMATION_EXPLODE_EXIT_MOVE_TIME);
        mOutsideShiftAnim.setInterpolator(new AccelerateInterpolator());
        animators.add(mOutsideShiftAnim);
        return this;
    }

    public AnimationsHelper addAnimationPosition(@NonNull final ImageBoxComponent imageBoxComponent,
                                                 @NonNull final String attributeName) {
        final ObjectAnimator mPositionOffsetAnim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, 0f, 1f);
        mPositionOffsetAnim.setRepeatCount(0);
        mPositionOffsetAnim.setDuration(ANIMATION_EXPLODE_EXIT_POSITION_TIME);
        mPositionOffsetAnim.setInterpolator(new OvershootInterpolator());
        animators.add(mPositionOffsetAnim);
        return this;
    }


    public void playTogether() {
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.start();
    }


}
