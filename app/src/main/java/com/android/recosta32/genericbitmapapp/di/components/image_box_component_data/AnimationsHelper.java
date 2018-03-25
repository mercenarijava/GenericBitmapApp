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
    static final long ANIMATION_EXPLODE_EXIT_POSITION_TIME = 900;
    static final long ANIMATION_EXPLODE_SMALL_GROWING_TIME = 1100;
    static final long ANIMATION_EXPLODE_ADAPTING_POSITION_TIME = 800;

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
                                                               @NonNull final String attributeName,
                                                               @NonNull final Float startF,
                                                               @NonNull final Float stopF) {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, startF, stopF);
        anim.setRepeatCount(0);
        anim.setDuration(ANIMATION_EXPLODE_EXIT_MOVE_TIME);
        anim.setInterpolator(new AccelerateInterpolator());
        animators.add(anim);
        return this;
    }

    public AnimationsHelper addAnimationPosition(@NonNull final ImageBoxComponent imageBoxComponent,
                                                 @NonNull final String attributeName,
                                                 @NonNull final Float startF,
                                                 @NonNull final Float stopF) {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, startF, stopF);
        anim.setRepeatCount(0);
        anim.setDuration(ANIMATION_EXPLODE_EXIT_POSITION_TIME);
        anim.setInterpolator(new OvershootInterpolator());
        animators.add(anim);
        return this;
    }

    public AnimationsHelper addSmallGrowing(@NonNull final ImageBoxComponent imageBoxComponent,
                                            @NonNull final String attributeName,
                                            @NonNull final Float startF,
                                            @NonNull final Float stopF) {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, startF, stopF);
        anim.setRepeatCount(0);
        anim.setDuration(ANIMATION_EXPLODE_SMALL_GROWING_TIME);
        anim.setInterpolator(new OvershootInterpolator());
        animators.add(anim);
        return this;
    }

    public AnimationsHelper addAnimationAdapting(@NonNull final ImageBoxComponent imageBoxComponent,
                                                 @NonNull final String attributeName,
                                                 @NonNull final Float startF,
                                                 @NonNull final Float stopF) {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(
                imageBoxComponent, attributeName, startF, stopF);
        anim.setRepeatCount(0);
        anim.setDuration(ANIMATION_EXPLODE_ADAPTING_POSITION_TIME);
        anim.setInterpolator(new OvershootInterpolator());
        animators.add(anim);
        return this;
    }


    public void playTogether() {
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.start();
    }


}
