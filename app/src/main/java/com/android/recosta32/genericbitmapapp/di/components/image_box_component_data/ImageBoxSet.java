package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

    private @NonNull
    Paint roundedCornersPaint;
    private @NonNull
    Paint circleFillPaint;

    public ImageBoxSet() {
        images = new ArrayList<>();
        init();
    }

    private void init() {
        roundedCornersPaint = new Paint();
        roundedCornersPaint.setAntiAlias(true);
        circleFillPaint = new Paint();
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
                getFirstTop().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.1));
                getFirstTop().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
        if (pressed != ImagePosition.SECOND_TOP) {
            if (isImageSetted(ImagePosition.SECOND_TOP)) {
                getSecondTop().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.6));
                getSecondTop().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
        if (pressed != ImagePosition.FIRST_BOTTOM) {
            if (isImageSetted(ImagePosition.FIRST_BOTTOM)) {
                getFirstBottom().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.6));
                getFirstBottom().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
        }
        if (pressed != ImagePosition.SECOND_BOTTOM) {
            if (isImageSetted(ImagePosition.SECOND_BOTTOM)) {
                getSecondBottom().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.1));
                getSecondBottom().getAttributeSet().setmResizeOffsetRatio(ratio);
            }
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

    private ImageBoxSet drawGenericImageSet(@NonNull final Canvas canvas,
                                            @NonNull final ImageSet imageSet) {
        float marginTop = 0;//(int) (imageSet.getImg().getHeight() * (-1 * imageSet.getAttributeSet().getmOutsideSceneRatio()));
        float marginLeft = 0;

        if (imageSet == getFirstTop()) {
            marginLeft = (imageSet.getImg().getWidth() * (-1 * imageSet.getAttributeSet().getmOutsideSceneRatio()));
        } else if (imageSet == getSecondTop()) {
            marginLeft = (imageSet.getImg().getWidth() + (imageSet.getImg().getWidth() * imageSet.getAttributeSet().getmOutsideSceneRatio()));
        } else if (imageSet == getFirstBottom()) {
            marginLeft = (imageSet.getImg().getWidth() * (-1 * imageSet.getAttributeSet().getmOutsideSceneRatio()));
            marginTop = imageSet.getImg().getHeight();
        } else if (imageSet == getSecondBottom()) {
            marginLeft = (imageSet.getImg().getWidth() + (imageSet.getImg().getWidth() * imageSet.getAttributeSet().getmOutsideSceneRatio()));
            marginTop = imageSet.getImg().getHeight();
        }

        drawBitmap(
                canvas,
                ImageUtils.getRoundedBitmapAndReducedWidth(imageSet.getImg(),
                        (int) (BoxAttributeSet.mImageSetCornerRadius * imageSet.getAttributeSet().getmResizeOffsetRatio()),
                        (int) ((imageSet.getImg().getWidth() / 8) *
                                (1 - imageSet.getAttributeSet().getmResizeOffsetRatio() >= 0.6 ? imageSet.getAttributeSet().getmResizeOffsetRatio() : 0.7)
                        )
                ),
                marginLeft,
                marginTop,
                null
        );

        return this;
    }

}
