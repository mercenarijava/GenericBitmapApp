package com.android.recosta32.genericbitmapapp.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.android.recosta32.genericbitmapapp.di.components.image_box_component_data.AnimationsHelper;
import com.android.recosta32.genericbitmapapp.di.components.image_box_component_data.BoxAttributeSet;
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

    private void manageImageSelection(@NonNull final ImageBoxSet.ImagePosition selected) {
        if (selected != null) {
            mPositionSelected = selected;
            dataSet.getFirstTop().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.FIRST_TOP);
            dataSet.getSecondTop().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.SECOND_TOP);
            dataSet.getFirstBottom().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.FIRST_BOTTOM);
            dataSet.getSecondBottom().getAttributeSet().setmPressed(selected == ImageBoxSet.ImagePosition.SECOND_BOTTOM);
            AnimationsHelper.startAnimationExplode(this, "explodeAnimationRatio");
        }
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
        final Point circleCenter = dataSet.getXYCenterCircle();
        if (circleCenter != null) {
            canvas.drawCircle(circleCenter.x, circleCenter.y, (canvas.getWidth() + canvas.getHeight()) * BoxAttributeSet.mCircleExpandRatio, dataSet.getCircleFillPaint());
        }

        dataSet.resizeImageSet(ImageBoxSet.ImagePosition.FIRST_TOP, imgSize)
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
            break;
        }
        return false;
    }

}
