
package com.android.recosta32.genericbitmapapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Provides helper methods for image operations
 ***/
public class ImageUtils {

    private final static String TAG = ImageUtils.class.getSimpleName();

    private static final String ERROR_URI_NULL = "Uri cannot be null";

    /**
     * Scales the image depending upon the display density of the device. Maintains image aspect
     * ratio.
     * <p>
     * When dealing with the bitmaps of bigger size, this method must be called from a non-UI
     * thread.
     **/
    public static Bitmap scaleDownBitmap(Context ctx, Bitmap source, int newHeight) {
        final float densityMultiplier = Utils.getDensityMultiplier(ctx);

        // Log.v( TAG, "#scaleDownBitmap Original w: " + source.getWidth() + " h: " +
        // source.getHeight() );

        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * source.getWidth() / ((double) source.getHeight()));

        // Log.v( TAG, "#scaleDownBitmap Computed w: " + w + " h: " + h );

        Bitmap photo = Bitmap.createScaledBitmap(source, w, h, true);

        // Log.v( TAG, "#scaleDownBitmap Final w: " + w + " h: " + h );

        return photo;
    }

    /**
     * Scales the image independently of the screen density of the device. Maintains image aspect
     * ratio.
     * <p>
     * When dealing with the bitmaps of bigger size, this method must be called from a non-UI
     * thread.
     */
    public static Bitmap scaleBitmap(Context ctx, Bitmap source, int newHeight) {

        // Log.v( TAG, "#scaleDownBitmap Original w: " + source.getWidth() + " h: " +
        // source.getHeight() );

        int w = (int) (newHeight * source.getWidth() / ((double) source.getHeight()));

        // Log.v( TAG, "#scaleDownBitmap Computed w: " + w + " h: " + newHeight );

        Bitmap photo = Bitmap.createScaledBitmap(source, w, newHeight, true);

        // Log.v( TAG, "#scaleDownBitmap Final w: " + w + " h: " + newHeight );

        return photo;
    }

    /**
     * Scales the image independently of the screen density of the device. Maintains image aspect
     * ratio.
     *
     * @param uri Uri of the source bitmap
     **/
    public static Bitmap scaleDownBitmap(Context ctx, Uri uri, int newHeight) throws FileNotFoundException, IOException {
        Bitmap original = Media.getBitmap(ctx.getContentResolver(), uri);
        return scaleBitmap(ctx, original, newHeight);
    }

    /**
     * Scales the image independently of the screen density of the device. Maintains image aspect
     * ratio.
     *
     * @param uri Uri of the source bitmap
     **/
    public static Uri scaleDownBitmapForUri(Context ctx, Uri uri, int newHeight) throws FileNotFoundException, IOException {

        if (uri == null)
            throw new NullPointerException(ERROR_URI_NULL);

        if (!MediaUtils.isMediaContentUri(uri))
            return null;

        Bitmap original = Media.getBitmap(ctx.getContentResolver(), uri);
        Bitmap bmp = scaleBitmap(ctx, original, newHeight);

        Uri destUri = null;
        String uriStr = Utils.writeImageToMedia(ctx, bmp, "", "");

        if (uriStr != null) {
            destUri = Uri.parse(uriStr);
        }

        return destUri;
    }

    /**
     * Gets the orientation of the image pointed to by the parameter uri
     *
     * @return Image orientation value corresponding to <code>ExifInterface.ORIENTATION_*</code> <br/>
     * Returns -1 if the row for the {@link Uri} is not found.
     **/
    public static int getOrientation(Context context, Uri uri) {

        int invalidOrientation = -1;
        if (uri == null) {
            throw new NullPointerException(ERROR_URI_NULL);
        }

        if (!MediaUtils.isMediaContentUri(uri)) {
            return invalidOrientation;
        }

        String filePath = Utils.getPathForMediaUri(context, uri);
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = invalidOrientation;
        if (exif != null) {
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, invalidOrientation);
        }

        return orientation;
    }

    /**
     * Rotate the image at the specified uri. For the rotation of the image the
     * {@link ExifInterface} data in the image will be used.
     *
     * @param uri Uri of the image to be rotated.
     **/
    public static Uri rotateImage(Context context, Uri uri) throws FileNotFoundException, IOException {
        // rotate the image
        if (uri == null) {
            throw new NullPointerException(ERROR_URI_NULL);
        }

        if (!MediaUtils.isMediaContentUri(uri)) {
            return null;
        }

        int invalidOrientation = -1;
        byte[] data = Utils.getMediaData(context, uri);

        int orientation = getOrientation(context, uri);

        Uri newUri = null;

        try {

            Log.d(TAG, "#rotateImage Exif orientation: " + orientation);

            if (orientation != invalidOrientation) {
                Matrix matrix = new Matrix();

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        matrix.postRotate(90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        matrix.postRotate(180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        matrix.postRotate(270);
                        break;
                }

                // set some options so the memory is manager properly
                BitmapFactory.Options options = new BitmapFactory.Options();
                // options.inPreferredConfig = Bitmap.Config.RGB_565; // try to enable this if
                // OutOfMem issue still persists
                options.inPurgeable = true;
                options.inInputShareable = true;

                Bitmap original = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true); // rotating
                // bitmap
                String newUrl = Media.insertImage(((Activity) context).getContentResolver(), rotatedBitmap, "", "");

                if (newUrl != null) {
                    newUri = Uri.parse(newUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newUri;
    }

    public static Bitmap getResizedBitmap(@NonNull final Bitmap bm, @NonNull final Float newWidth, @NonNull final Float newHeight) {
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
        return resizedBitmap;
    }

    public static Bitmap getRoundedBitmapAndReducedWidth(Bitmap srcBitmap, final int cornerRadius, final int offset) {
        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                srcBitmap.getWidth(), // Width
                srcBitmap.getHeight(), // Height

                Bitmap.Config.ARGB_8888 // Config
        );
        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Rect rect = new Rect(0, offset, srcBitmap.getWidth(), srcBitmap.getHeight() - offset);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        return dstBitmap;
    }

    public static Bitmap getSquareCornerRounded(@NonNull Bitmap srcBitmap, final int radius, final int cornerRadius) {

        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                (radius * 2) <= 0? 1 : radius * 2, // Width
                (radius * 2) <= 0? 1 : radius * 2, // Height

                Bitmap.Config.ARGB_8888 // Config
        );
        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, (radius * 2) + 1, (radius * 2) + 1);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        return dstBitmap;
    }

    public static Bitmap getCircleBitmap(@NonNull Bitmap srcBitmap, final int radius) {

        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                (radius * 2) <= 0? 1 : radius * 2, // Width
                (radius * 2) <= 0? 1 : radius * 2, // Height

                Bitmap.Config.ARGB_8888 // Config
        );
        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        return dstBitmap;
    }


    /**
     * Get the size of parameter {@link Bitmap}. This maybe a heavy operation.
     * Prefer not calling from main thread of the activity.
     *
     * @param data
     * @return
     */
    public static int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return data.getByteCount();
        } else {
            return data.getAllocationByteCount();
        }
    }
}
