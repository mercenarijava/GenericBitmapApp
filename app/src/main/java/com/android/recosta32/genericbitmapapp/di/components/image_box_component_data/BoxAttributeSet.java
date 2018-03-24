package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class BoxAttributeSet {
    static final int mImageSetCornerRadius = 20;
    static float mCircleExpandRatio = 0f;
    private float mOutsideSceneRatio = 0f;
    private float mResizeOffsetRatio = 0f;
    private float mPositionOffsetRatio = 0f;
    private float mSmallGrowRatio = 0f;
    private boolean mPressed = false;

    float getmOutsideSceneRatio() {
        return mOutsideSceneRatio;
    }

    void setmOutsideSceneRatio(float mOutsideSceneRatio) {
        this.mOutsideSceneRatio = mOutsideSceneRatio;
    }

    float getmResizeOffsetRatio() {
        return mResizeOffsetRatio;
    }

    void setmResizeOffsetRatio(float mResizeOffsetRatio) {
        this.mResizeOffsetRatio = mResizeOffsetRatio;
    }

    boolean ismPressed() {
        return mPressed;
    }

    public void setmPressed(boolean mPressed) {
        this.mPressed = mPressed;
    }

    float getmPositionOffsetRatio() {
        return mPositionOffsetRatio;
    }

    void setmPositionOffsetRatio(float mPositionOffsetRatio) {
        this.mPositionOffsetRatio = mPositionOffsetRatio;
    }

    public float getmSmallGrowRatio() {
        return mSmallGrowRatio;
    }

    public void setmSmallGrowRatio(float mSmallGrowRatio) {
        this.mSmallGrowRatio = mSmallGrowRatio;
    }
}
