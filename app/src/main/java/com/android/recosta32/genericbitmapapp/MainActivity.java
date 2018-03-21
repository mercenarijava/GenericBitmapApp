package com.android.recosta32.genericbitmapapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.android.recosta32.genericbitmapapp.ui.custom.ImageBoxComponent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageBoxComponent imageBoxComponent= imageBoxComponent = (ImageBoxComponent) findViewById(R.id.test);
        imageBoxComponent.addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorAccent)), "")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorPrimary)), "")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark)), "")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorAccent)), "");
    }


    public static Bitmap createImage(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        return bitmap;
    }



}
