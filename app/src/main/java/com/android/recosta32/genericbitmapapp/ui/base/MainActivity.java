package com.android.recosta32.genericbitmapapp.ui.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.android.recosta32.genericbitmapapp.R;
import com.android.recosta32.genericbitmapapp.ui.custom.ImageBoxComponent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageBoxComponent imageBoxComponent = (ImageBoxComponent) findViewById(R.id.test);
        imageBoxComponent.addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.black)), "#607D8B")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.black)), "#9E9E9E")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.black)), "#795548")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.black)), "#FF5722");

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
