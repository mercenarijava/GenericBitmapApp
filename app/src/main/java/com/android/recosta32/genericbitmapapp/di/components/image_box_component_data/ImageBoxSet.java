package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

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

    public int getSize(){
        return images.size();
    }

    public ImageBoxSet resizeImageSet(@NonNull final ImagePosition imagePosition, @NonNull final Float imgSize) {
        switch (imagePosition) {
            case FIRST_TOP: {
                if (getFirstTop() != null)
                    getFirstTop().resizeBitmap(imgSize);

            }
            break;
            case SECOND_TOP: {
                if (getSecondTop() != null)
                    getSecondTop().resizeBitmap(imgSize);
            }
            break;
            case FIRST_BOTTOM: {
                if (getFirstBottom() != null)
                    getFirstBottom().resizeBitmap(imgSize);
            }
            break;
            case SECOND_BOTTOM: {
                if (getSecondBottom() != null)
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
                if (getFirstTop() != null)
                    drawFirstTop(canvas);

            }
            break;
            case SECOND_TOP: {
                if (getSecondTop() != null)
                    drawSecondTop(canvas);
            }
            break;
            case FIRST_BOTTOM: {
                if (getFirstBottom() != null)
                    drawFirstBottom(canvas);
            }
            break;
            case SECOND_BOTTOM: {
                if (getSecondBottom() != null)
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
        drawBitmap(canvas, getFirstTop().getImg(), 0f, 0f);
        return this;
    }

    private ImageBoxSet drawSecondTop(@NonNull final Canvas canvas) {
        drawBitmap(canvas, getSecondTop().getImg(), (float) getSecondTop().getImg().getWidth(), 0f);
        return this;
    }

    private ImageBoxSet drawFirstBottom(@NonNull final Canvas canvas) {
        drawBitmap(canvas, getFirstBottom().getImg(), 0f, (float) getFirstBottom().getImg().getWidth());
        return this;
    }

    private ImageBoxSet drawSecondBottom(@NonNull final Canvas canvas) {
        drawBitmap(canvas, getSecondBottom().getImg(), (float) getSecondBottom().getImg().getWidth(), (float) getSecondBottom().getImg().getWidth());
        return this;
    }


}
