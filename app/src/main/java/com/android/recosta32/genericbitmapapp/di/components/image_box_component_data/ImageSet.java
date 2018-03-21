package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class ImageSet {
    private @NonNull
    Bitmap img;
    private @Nullable
    String bkg;
    private @Nullable
    Float oldImgSize;
    private @NonNull
    BoxAttributeSet attributeSet;

    public ImageSet(@NonNull Bitmap img, String bkg) {
        this.img = img;
        this.bkg = bkg;
        this.oldImgSize = null;
        this.attributeSet = new BoxAttributeSet();
    }

    @NonNull
    public Bitmap getImg() {
        return img;
    }

    public void setImg(@NonNull Bitmap img) {
        this.img = img;
    }

    @Nullable
    public String getBkg() {
        return bkg;
    }

    public void setBkg(@Nullable String bkg) {
        this.bkg = bkg;
    }

    @NonNull
    public BoxAttributeSet getAttributeSet() {
        return attributeSet;
    }

    public void setAttributeSet(@NonNull BoxAttributeSet attributeSet) {
        this.attributeSet = attributeSet;
    }

    public ImageSet resizeBitmap(@NonNull final Float imgSize) {
        if (oldImgSize == null || oldImgSize != imgSize) {
            this.img = getResizedBitmap(this.img, imgSize, imgSize);
            oldImgSize = imgSize;
        }
        return this;
    }

    private Bitmap getResizedBitmap(@NonNull final Bitmap bm, @NonNull final Float newWidth, @NonNull final Float newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
