package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class BoxAttributeSet {
    public static final int mImageSetCornerRadius = 20;
    public static float mCircleExpandRatio = 0f;
    private float mOutsideSceneRatio = 0f;
    private float mResizeOffsetRatio = 0f;
    private boolean mPressed = false;

    public float getmOutsideSceneRatio() {
        return mOutsideSceneRatio;
    }

    public void setmOutsideSceneRatio(float mOutsideSceneRatio) {
        this.mOutsideSceneRatio = mOutsideSceneRatio;
    }

    public float getmResizeOffsetRatio() {
        return mResizeOffsetRatio;
    }

    public void setmResizeOffsetRatio(float mResizeOffsetRatio) {
        this.mResizeOffsetRatio = mResizeOffsetRatio;
    }

    public boolean ismPressed() {
        return mPressed;
    }

    public void setmPressed(boolean mPressed) {
        this.mPressed = mPressed;
    }
}
