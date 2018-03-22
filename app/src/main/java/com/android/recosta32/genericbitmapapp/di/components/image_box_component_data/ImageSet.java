package com.android.recosta32.genericbitmapapp.di.components.image_box_component_data;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.recosta32.genericbitmapapp.utils.ImageUtils;

/**
 * Created by recosta32 on 20/03/2018.
 */

public class ImageSet {
    private @NonNull
    Bitmap img;
    private @NonNull
    String bkg;
    private @NonNull
    int bkgColor;
    private @Nullable
    Float oldImgSize;
    private @NonNull
    BoxAttributeSet attributeSet;


    public ImageSet(@NonNull final Bitmap img, @NonNull final String bkg) {
        this.img = img;
        this.bkg = bkg;
        this.bkgColor = Color.parseColor(this.bkg);
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

    @NonNull
    public int getBkgColor() {
        return bkgColor;
    }

    @Nullable
    public String getBkg() {
        return bkg;
    }

    public void setBkg(@Nullable String bkg) {
        this.bkg = bkg;
        this.bkgColor = Color.parseColor(this.bkg);
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
            this.img = ImageUtils.getResizedBitmap(this.img, imgSize, imgSize);
            oldImgSize = imgSize;
        }
        return this;
    }

}
