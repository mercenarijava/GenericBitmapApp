package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.recosta32.genericbitmapapp.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class ImageBoxSet {
    public enum ImagePosition {FIRST_TOP, SECOND_TOP, FIRST_BOTTOM, SECOND_BOTTOM}

    private @NonNull
    ArrayList<ImageSet> images; // 0 = firstTop , 1 = secondTop , 2 = firstBottom , 3 = secondBottom
    private @Nullable
    Integer lastPressedIndex;

    private @NonNull
    Paint bitmapPaint;
    private @NonNull
    Paint roundedCornersPaint;
    private @NonNull
    Paint circleFillPaint;

    public ImageBoxSet() {
        images = new ArrayList<>();
        init();
    }

    private void init() {
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        roundedCornersPaint = new Paint();
        roundedCornersPaint.setAntiAlias(true);
        circleFillPaint = new Paint();
        circleFillPaint.setAntiAlias(true);
        circleFillPaint.setStyle(Paint.Style.FILL);
        //circleFillPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    public ImageBoxSet addImage(@NonNull final Bitmap img, @NonNull final String bkgColor) {
        if (images.size() > 4) {
            return this;
        }
        images.add(new ImageSet(img, bkgColor));
        return this;
    }

    @NonNull
    public Paint getRoundedCornersPaint() {
        return roundedCornersPaint;
    }

    @NonNull
    public Paint getCircleFillPaint() {
        return circleFillPaint;
    }

    private @Nullable
    ImageSet getGenericSet(@NonNull Integer pos) {
        if (images.size() >= pos + 1) {
            return images.get(pos);
        }
        return null;
    }

    public @Nullable
    ImageSet getFirstTop() {
        return getGenericSet(0);
    }

    public @Nullable
    ImageSet getSecondTop() {
        return getGenericSet(1);
    }

    public @Nullable
    ImageSet getFirstBottom() {
        return getGenericSet(2);
    }

    public @Nullable
    ImageSet getSecondBottom() {
        return getGenericSet(3);
    }

    public @NonNull
    Boolean isImageSetted(@NonNull final ImagePosition imagePosition) {
        boolean exist = false;
        switch (imagePosition) {
            case FIRST_TOP: {
                exist = getFirstTop() != null;
            }
            break;
            case SECOND_TOP: {
                exist = getSecondTop() != null;
            }
            break;
            case FIRST_BOTTOM: {
                exist = getFirstBottom() != null;
            }
            break;
            case SECOND_BOTTOM: {
                exist = getSecondBottom() != null;
            }
            break;
        }
        return exist;
    }

    public void setOutsideSceneRatio(@NonNull final Float ratio, @NonNull final ImagePosition pressed) {
        BoxAttributeSet.mCircleExpandRatio = ratio;
        if (pressed != ImagePosition.FIRST_TOP) {
            if (isImageSetted(ImagePosition.FIRST_TOP)) {
                this.lastPressedIndex = 0;
                getFirstTop().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.1));
                getFirstTop().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
        if (pressed != ImagePosition.SECOND_TOP) {
            if (isImageSetted(ImagePosition.SECOND_TOP)) {
                this.lastPressedIndex = 1;
                getSecondTop().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.6));
                getSecondTop().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
        if (pressed != ImagePosition.FIRST_BOTTOM) {
            if (isImageSetted(ImagePosition.FIRST_BOTTOM)) {
                this.lastPressedIndex = 2;
                getFirstBottom().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.6));
                getFirstBottom().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
        if (pressed != ImagePosition.SECOND_BOTTOM) {
            if (isImageSetted(ImagePosition.SECOND_BOTTOM)) {
                this.lastPressedIndex = 3;
                getSecondBottom().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.1));
                getSecondBottom().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
    }

    public void setPositionOffsetRatio(@NonNull final Float ratio, @NonNull final ImagePosition pressed) {
        if (pressed == ImagePosition.FIRST_TOP && isImageSetted(ImagePosition.FIRST_TOP)) {
            this.lastPressedIndex = 0;
            getFirstTop().getAttributeSet().setmPositionOffsetRatio(ratio);
        }
        if (pressed == ImagePosition.SECOND_TOP && isImageSetted(ImagePosition.SECOND_TOP)) {
            this.lastPressedIndex = 1;
            getSecondTop().getAttributeSet().setmPositionOffsetRatio(ratio);
        }
        if (pressed == ImagePosition.FIRST_BOTTOM && isImageSetted(ImagePosition.FIRST_BOTTOM)) {
            this.lastPressedIndex = 2;
            getFirstBottom().getAttributeSet().setmPositionOffsetRatio(ratio);
        }
        if (pressed == ImagePosition.SECOND_BOTTOM && isImageSetted(ImagePosition.SECOND_BOTTOM)) {
            this.lastPressedIndex = 3;
            getSecondBottom().getAttributeSet().setmPositionOffsetRatio(ratio);
        }
    }

    public void setSemallGrowingRatio(@NonNull final Float ratio, @NonNull final ImagePosition pressed) {
        if (pressed != ImagePosition.FIRST_TOP && isImageSetted(ImagePosition.FIRST_TOP)) {
            getFirstTop().getAttributeSet().setmSmallGrowRatio(ratio);
        }
        if (pressed != ImagePosition.SECOND_TOP && isImageSetted(ImagePosition.SECOND_TOP)) {
            getSecondTop().getAttributeSet().setmSmallGrowRatio(ratio);
        }
        if (pressed != ImagePosition.FIRST_BOTTOM && isImageSetted(ImagePosition.FIRST_BOTTOM)) {
            getFirstBottom().getAttributeSet().setmSmallGrowRatio(ratio);
        }
        if (pressed != ImagePosition.SECOND_BOTTOM && isImageSetted(ImagePosition.SECOND_BOTTOM)) {
            getSecondBottom().getAttributeSet().setmSmallGrowRatio(ratio);
        }
    }

    public void setImageAdaptingRatio(@NonNull final Float ratio, @NonNull final ImagePosition pressed) {

        if (pressed == ImagePosition.FIRST_TOP && isImageSetted(ImagePosition.FIRST_TOP)) {
            getFirstTop().getAttributeSet().setmImageAdaptingRatio(ratio);
        }
        if (pressed == ImagePosition.SECOND_TOP && isImageSetted(ImagePosition.SECOND_TOP)) {
            getSecondTop().getAttributeSet().setmImageAdaptingRatio(ratio);
        }
        if (pressed == ImagePosition.FIRST_BOTTOM && isImageSetted(ImagePosition.FIRST_BOTTOM)) {
            getFirstBottom().getAttributeSet().setmImageAdaptingRatio(ratio);
        }
        if (pressed == ImagePosition.SECOND_BOTTOM && isImageSetted(ImagePosition.SECOND_BOTTOM)) {
            getSecondBottom().getAttributeSet().setmImageAdaptingRatio(ratio);
        }
    }


    public int getSize() {
        return images.size();
    }

    public ImageBoxSet resizeImageSet(@NonNull final ImagePosition imagePosition, @NonNull final Float imgSize) {
        switch (imagePosition) {
            case FIRST_TOP: {
                if (isImageSetted(ImagePosition.FIRST_TOP))
                    getFirstTop().resizeBitmap(imgSize);
            }
            break;
            case SECOND_TOP: {
                if (isImageSetted(ImagePosition.SECOND_TOP))
                    getSecondTop().resizeBitmap(imgSize);
            }
            break;
            case FIRST_BOTTOM: {
                if (isImageSetted(ImagePosition.FIRST_BOTTOM))
                    getFirstBottom().resizeBitmap(imgSize);
            }
            break;
            case SECOND_BOTTOM: {
                if (isImageSetted(ImagePosition.SECOND_BOTTOM))
                    getSecondBottom().resizeBitmap(imgSize);
            }
            break;
        }
        return this;
    }

    public ImageBoxSet drawImageSet(@NonNull final Canvas canvas,
                                    @NonNull final ImagePosition imagePosition) {
        switch (imagePosition) {
            case FIRST_TOP: {
                if (isImageSetted(ImagePosition.FIRST_TOP))
                    drawGenericImageSet(canvas, getFirstTop());

            }
            break;
            case SECOND_TOP: {
                if (isImageSetted(ImagePosition.SECOND_TOP))
                    drawGenericImageSet(canvas, getSecondTop());
            }
            break;
            case FIRST_BOTTOM: {
                if (isImageSetted(ImagePosition.FIRST_BOTTOM))
                    drawGenericImageSet(canvas, getFirstBottom());
            }
            break;
            case SECOND_BOTTOM: {
                if (isImageSetted(ImagePosition.SECOND_BOTTOM))
                    drawGenericImageSet(canvas, getSecondBottom());
            }
            break;
        }
        return this;
    }

    public ImageBoxSet drawFilledCircle(@NonNull final Canvas canvas) {
        final Point circleCenter = getXYCenterCircle();
        if (circleCenter != null) {
            canvas.drawCircle(circleCenter.x, circleCenter.y, (canvas.getWidth() + canvas.getHeight()) * BoxAttributeSet.mCircleExpandRatio, getCircleFillPaint());
        }
        return this;
    }

    private void drawBitmap(@NonNull final Canvas canvas,
                            @NonNull final Bitmap bitmap,
                            @NonNull final Float left,
                            @NonNull final Float top,
                            @Nullable final Paint paint) {
        canvas.drawBitmap(
                bitmap,
                left,
                top,
                paint
        );
    }

    public Point getXYCenterCircle() {
        if (isImageSetted(ImagePosition.FIRST_TOP)) {
            if (getFirstTop().getAttributeSet().ismPressed()) {
                circleFillPaint.setColor(getFirstTop().getBkgColor());
                return new Point(
                        getFirstTop().getImg().getWidth() / 2,
                        getFirstTop().getImg().getHeight() / 2
                );
            }
        }
        if (isImageSetted(ImagePosition.SECOND_TOP)) {
            if (getSecondTop().getAttributeSet().ismPressed()) {
                circleFillPaint.setColor(getSecondTop().getBkgColor());
                return new Point(
                        getSecondTop().getImg().getWidth() + getSecondTop().getImg().getWidth() / 2,
                        getSecondTop().getImg().getHeight() / 2
                );
            }
        }
        if (isImageSetted(ImagePosition.FIRST_BOTTOM)) {
            if (getFirstBottom().getAttributeSet().ismPressed()) {
                circleFillPaint.setColor(getFirstBottom().getBkgColor());
                return new Point(
                        getFirstBottom().getImg().getWidth() / 2,
                        getFirstBottom().getImg().getHeight() + getFirstBottom().getImg().getHeight() / 2
                );
            }
        }
        if (isImageSetted(ImagePosition.SECOND_BOTTOM)) {
            if (getSecondBottom().getAttributeSet().ismPressed()) {
                circleFillPaint.setColor(getSecondBottom().getBkgColor());
                return new Point(
                        getSecondBottom().getImg().getWidth() + getSecondBottom().getImg().getWidth() / 2,
                        getSecondBottom().getImg().getHeight() + getSecondBottom().getImg().getHeight() / 2
                );
            }
        }
        return null;
    }

    private float getMarginLeft(@NonNull final Canvas canvas,
                                @NonNull final ImageSet imageSet) {
        final int canvasWidth = canvas.getWidth();
        final float marginWidth = canvas.getWidth() / 18;
        if (imageSet == getFirstTop() || imageSet == getFirstBottom()) {
            return marginWidth * imageSet.getAttributeSet().getmPositionOffsetRatio();
        } else {
            return ((canvasWidth / 2 - marginWidth) * imageSet.getAttributeSet().getmPositionOffsetRatio());
        }
    }

    private float getMarginTop(@NonNull final Canvas canvas,
                               @NonNull final ImageSet imageSet) {
        final int canvasHeight = canvas.getHeight();
        final float marginHeight = canvas.getHeight() / 30;
        if (imageSet == getFirstTop() || imageSet == getSecondTop()) {
            return marginHeight * imageSet.getAttributeSet().getmPositionOffsetRatio();
        } else {
            return ((canvasHeight / 2 - marginHeight) * imageSet.getAttributeSet().getmPositionOffsetRatio());
        }

    }

    private float getSmalChildHeight(@NonNull final Canvas canvas,
                                     @NonNull final ImageSet imageSet) {
        return (float) (((canvas.getHeight() / 18) * 2.4) * imageSet.getAttributeSet().getmSmallGrowRatio());
    }

    private float getSmalChildWidth(@NonNull final Canvas canvas,
                                    @NonNull final ImageSet imageSet) {
        return (float) (((canvas.getWidth() / 18) * 2.4) * imageSet.getAttributeSet().getmSmallGrowRatio());
    }


    private ImageBoxSet drawGenericImageSet(@NonNull final Canvas canvas,
                                            @NonNull final ImageSet imageSet) {
        float marginTop = 0;//(int) (imageSet.getImg().getHeight() * (-1 * imageSet.getAttributeSet().getmOutsideSceneRatio()));
        float marginLeft = 0;

        if (imageSet == getFirstTop()) {
            marginLeft = (imageSet.getImg().getWidth() * (-1 * imageSet.getAttributeSet().getmOutsideSceneRatio())) + getMarginLeft(canvas, imageSet);
            marginTop = getMarginTop(canvas, imageSet);
        } else if (imageSet == getSecondTop()) {
            marginLeft = (imageSet.getImg().getWidth() + (imageSet.getImg().getWidth() * imageSet.getAttributeSet().getmOutsideSceneRatio())) - getMarginLeft(canvas, imageSet);
            marginTop = getMarginTop(canvas, imageSet);
        } else if (imageSet == getFirstBottom()) {
            marginLeft = (imageSet.getImg().getWidth() * (-1 * imageSet.getAttributeSet().getmOutsideSceneRatio())) + getMarginLeft(canvas, imageSet);
            marginTop = imageSet.getImg().getHeight() - getMarginTop(canvas, imageSet);
        } else if (imageSet == getSecondBottom()) {
            marginLeft = (imageSet.getImg().getWidth() + (imageSet.getImg().getWidth() * imageSet.getAttributeSet().getmOutsideSceneRatio())) - getMarginLeft(canvas, imageSet);
            marginTop = imageSet.getImg().getHeight() - getMarginTop(canvas, imageSet);
        }

        drawBitmap(
                canvas,
                ImageUtils.getRoundedBitmapAndReducedWidth(
                        ImageUtils.getResizedBitmap(
                                imageSet.getImg(),
                                imageSet.getImg().getWidth() + ((imageSet.getImg().getWidth() / 2) * imageSet.getAttributeSet().getmImageAdaptingRatio()),
                                imageSet.getImg().getHeight() + imageSet.getImg().getHeight() * imageSet.getAttributeSet().getmImageAdaptingRatio()
                        ),
                        (int) (BoxAttributeSet.mImageSetCornerRadius * imageSet.getAttributeSet().getmResizeOffsetRatio()),
                        (int) ((imageSet.getImg().getWidth() / 8) *
                                imageSet.getAttributeSet().getmResizeOffsetRatio()
                        )
                ),
                marginLeft,
                marginTop,
                bitmapPaint
        );

        /**
         * SmallChild drawing
         */
        int imageSetPosition = getFirstTop() == imageSet ? 0 : getSecondTop() == imageSet ? 1 : getFirstBottom() == imageSet ? 2 : 3;
        imageSetPosition = (lastPressedIndex != null && imageSetPosition > lastPressedIndex) ? imageSetPosition - 1 : imageSetPosition;
        final float childHeight = getSmalChildHeight(canvas, imageSet);
        final float childWidth = getSmalChildWidth(canvas, imageSet);
        final float childsMargin = (childHeight + (childHeight / 2)) / 12;
        final float childMarginTop = canvas.getHeight() - ((childsMargin + childHeight) * (imageSetPosition + 1));
        final float childMarginLeft = canvas.getWidth() - childWidth - childsMargin;
        drawBitmap(
                canvas,
                ImageUtils.getCircleBitmap(imageSet.getImg(), (int) (childHeight / 2.1)),
                childMarginLeft,
                childMarginTop,
                bitmapPaint
        );


        return this;
    }

}
