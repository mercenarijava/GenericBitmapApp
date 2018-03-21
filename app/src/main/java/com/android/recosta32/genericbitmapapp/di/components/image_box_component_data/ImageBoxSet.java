package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class ImageBoxSet {
    public enum ImagePosition {FIRST_TOP, SECOND_TOP, FIRST_BOTTOM, SECOND_BOTTOM}

    private @NonNull
    ArrayList<ImageSet> images; // 0 = firstTop , 1 = secondTop , 2 = firstBottom , 3 = secondBottom
    public ImageBoxSet() {
        images = new ArrayList<>();
    }

    public ImageBoxSet addImage(@NonNull final Bitmap img, @NonNull final String bkgColor) {
        if (images.size() > 4) {
            return this;
        }
        images.add(new ImageSet(img, bkgColor));
        return this;
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

    public void setOutsideSceneRatio(@NonNull final Float ratio, @NonNull final ImagePosition imagePosition) {
        if (imagePosition != ImagePosition.FIRST_TOP) {
            if (isImageSetted(ImagePosition.FIRST_TOP))
                getFirstTop().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.2));
        }
        if (imagePosition != ImagePosition.SECOND_TOP) {
            if (isImageSetted(ImagePosition.SECOND_TOP))
                getSecondTop().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.6));
        }
        if (imagePosition != ImagePosition.FIRST_BOTTOM) {
            if (isImageSetted(ImagePosition.FIRST_BOTTOM))
                getFirstBottom().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.1));
        }
        if (imagePosition != ImagePosition.SECOND_BOTTOM) {
            if (isImageSetted(ImagePosition.SECOND_BOTTOM))
                getSecondBottom().getAttributeSet().setmOutsideSceneRatio((float) (ratio * 1.4));
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
                    drawFirstTop(canvas);

            }
            break;
            case SECOND_TOP: {
                if (isImageSetted(ImagePosition.SECOND_TOP))
                    drawSecondTop(canvas);
            }
            break;
            case FIRST_BOTTOM: {
                if (isImageSetted(ImagePosition.FIRST_BOTTOM))
                    drawFirstBottom(canvas);
            }
            break;
            case SECOND_BOTTOM: {
                if (isImageSetted(ImagePosition.SECOND_BOTTOM))
                    drawSecondBottom(canvas);
            }
            break;
        }
        return this;
    }

    private void drawBitmap(@NonNull final Canvas canvas,
                            @NonNull final Bitmap bitmap,
                            @NonNull final Float left,
                            @NonNull final Float top) {
        canvas.drawBitmap(
                bitmap,
                left,
                top,
                null
        );
    }

    private ImageBoxSet drawFirstTop(@NonNull final Canvas canvas) {
        drawBitmap(
                canvas,
                getFirstTop().getImg(),
                0f,
                getFirstTop().getImg().getHeight() * (-1 * getFirstTop().getAttributeSet().getmOutsideSceneRatio())
        );
        return this;
    }

    private ImageBoxSet drawSecondTop(@NonNull final Canvas canvas) {
        drawBitmap(
                canvas,
                getSecondTop().getImg(),
                (float) getFirstTop().getImg().getWidth(),
                getSecondTop().getImg().getHeight() * (-1 * getSecondTop().getAttributeSet().getmOutsideSceneRatio())
        );
        return this;
    }

    private ImageBoxSet drawFirstBottom(@NonNull final Canvas canvas) {
        drawBitmap(
                canvas,
                getFirstBottom().getImg(),
                0f,
                (float) getFirstTop().getImg().getHeight() + getFirstBottom().getImg().getHeight() * (getFirstBottom().getAttributeSet().getmOutsideSceneRatio())
        );
        return this;
    }

    private ImageBoxSet drawSecondBottom(@NonNull final Canvas canvas) {
        drawBitmap(
                canvas,
                getSecondBottom().getImg(),
                (float) getFirstBottom().getImg().getWidth(),
                (float) getSecondBottom().getImg().getHeight() + getSecondBottom().getImg().getHeight() * (getSecondBottom().getAttributeSet().getmOutsideSceneRatio())
        );
        return this;
    }


}
