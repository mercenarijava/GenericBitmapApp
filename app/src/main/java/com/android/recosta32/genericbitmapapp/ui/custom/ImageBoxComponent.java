package com.android.recosta32.genericbitmapapp.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.android.recosta32.genericbitmapapp.di.components.image_box_component_data.AnimationsHelper;
import com.android.recosta32.genericbitmapapp.di.components.image_box_component_data.ImageBoxSet;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class ImageBoxComponent extends View implements View.OnTouchListener {

    public enum ANIMATION {EXPLODE}

    public enum SIZE {FULL, NONE}

    private @NonNull
    ImageBoxSet dataSet;
    private @NonNull
    ANIMATION mAnimation = ANIMATION.EXPLODE;
    private @NonNull
    SIZE mSizeOn = SIZE.NONE;
    private @Nullable
    ImageBoxSet.ImagePosition mPositionSelected;
    private @NonNull
    Boolean isOpen = false;

    public ImageBoxComponent(Context context) {
        super(context);
        initState();
    }

    public ImageBoxComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initState();
    }

    public ImageBoxComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initState();
    }

    private void initState() {
        dataSet = new ImageBoxSet();
        mPositionSelected = null;
        setOnTouchListener(this);
    }

    public ImageBoxComponent addImage(@NonNull final Bitmap img, @NonNull final String bkgColor) {
        dataSet.addImage(img, bkgColor);
        invalidate();
        return this;
    }

    @NonNull
    public ANIMATION getmAnimation() {
        return mAnimation;
    }

    public void setmAnimation(@NonNull final ANIMATION mAnimation) {
        this.mAnimation = mAnimation;
    }


    /**
     * Value from 0 to 1 animated from animationHelper
     *
     * @param ratio
     */
    public void setExplodeAnimationRatio(float ratio) {
        this.dataSet.setOutsideSceneRatio(ratio, mPositionSelected);
        invalidate();
    }

    /**
     * Value from 0 to 1 animated from animationHelper
     *
     * @param ratio
     */
    public void setPositionAnimationRatio(float ratio) {
        this.dataSet.setPositionOffsetRatio(ratio, mPositionSelected);
        invalidate();
    }

    /**
     * Value from 0 to 1 animated from animationHelper
     *
     * @param ratio
     */
    public void setSmallGrowingRatio(float ratio) {
        this.dataSet.setSemallGrowingRatio(ratio, mPositionSelected);
        invalidate();
    }

    /**
     * Value from 0 to 1 animated from animationHelper
     *
     * @param ratio
     */
    public void setImageAdaptingRatio(float ratio) {
        this.dataSet.setImageAdaptingRatio(ratio, mPositionSelected);
        invalidate();
    }

    private void manageImageSelection(@NonNull final ImageBoxSet.ImagePosition selected) {
        isOpen = true;
        mPositionSelected = selected;
        if (dataSet.isImageSetted(ImageBoxSet.ImagePosition.FIRST_TOP))
            dataSet.getFirstTop().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.FIRST_TOP);
        if (dataSet.isImageSetted(ImageBoxSet.ImagePosition.SECOND_TOP))
            dataSet.getSecondTop().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.SECOND_TOP);
        if (dataSet.isImageSetted(ImageBoxSet.ImagePosition.FIRST_BOTTOM))
            dataSet.getFirstBottom().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.FIRST_BOTTOM);
        if (dataSet.isImageSetted(ImageBoxSet.ImagePosition.SECOND_BOTTOM))
            dataSet.getSecondBottom().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.SECOND_BOTTOM);
        AnimationsHelper.init()
                .addAnimationExplodeColorAndOutside(this, "explodeAnimationRatio", 0f, 1f)
                .addAnimationPosition(this, "positionAnimationRatio", 0f, 1f)
                .addSmallGrowing(this, "smallGrowingRatio", 0f, 1f)
                .addAnimationAdapting(this, "imageAdaptingRatio", 0f, 1f)
                .playTogether();
    }

    private void measureView(@NonNull final Integer width) {
        if (mSizeOn == SIZE.NONE) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
            if (params == null) {
                params = new LinearLayout.LayoutParams((int) (width * 2), 0);
            }
            params.height = width;
            setLayoutParams(params);
            mSizeOn = SIZE.FULL;
        }
    }

    private void drawExplodeLayout(@NonNull final Canvas canvas,
                                   @NonNull final Integer width,
                                   @NonNull final Integer height,
                                   @NonNull final Float imgSize) {
        dataSet.drawFilledCircle(canvas)
                .resizeImageSet(ImageBoxSet.ImagePosition.FIRST_TOP, imgSize)
                .drawImageSet(canvas, ImageBoxSet.ImagePosition.FIRST_TOP)
                .resizeImageSet(ImageBoxSet.ImagePosition.SECOND_TOP, imgSize)
                .drawImageSet(canvas, ImageBoxSet.ImagePosition.SECOND_TOP)
                .resizeImageSet(ImageBoxSet.ImagePosition.FIRST_BOTTOM, imgSize)
                .drawImageSet(canvas, ImageBoxSet.ImagePosition.FIRST_BOTTOM)
                .resizeImageSet(ImageBoxSet.ImagePosition.SECOND_BOTTOM, imgSize)
                .drawImageSet(canvas, ImageBoxSet.ImagePosition.SECOND_BOTTOM);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getWidth();
        final int height = getHeight();
        final float imgSize = getWidth() / 2;

        measureView(width);

        switch (mAnimation) {
            case EXPLODE: {
                drawExplodeLayout(canvas, width, height, imgSize);
            }
            break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (!isOpen) {
                    final Float x = event.getX();
                    final Float y = event.getY();
                    final Integer width = getWidth();
                    final Integer midView = width / 2;
                    if (x < midView && y < midView) {
                        manageImageSelection(ImageBoxSet.ImagePosition.FIRST_TOP);
                    } else if (x > midView && y < midView) {
                        manageImageSelection(ImageBoxSet.ImagePosition.SECOND_TOP);
                    } else if (x < midView && y > midView) {
                        manageImageSelection(ImageBoxSet.ImagePosition.FIRST_BOTTOM);
                    } else if (x > midView && y > midView) {
                        manageImageSelection(ImageBoxSet.ImagePosition.SECOND_BOTTOM);
                    }
                }
            }
            break;
        }
        return false;
    }

}
