package com.android.recosta32.genericbitmapapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.recosta32.genericbitmapapp.ui.custom.ImageBoxComponent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageBoxComponent imageBoxComponent= (ImageBoxComponent) findViewById(R.id.test);
        imageBoxComponent.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test1), "")
                .addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "")
                .addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test3), "")
                .addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test4), "");

        ImageBoxComponent imageBoxComponent2 = (ImageBoxComponent) findViewById(R.id.test2);
        imageBoxComponent2.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test3), "")
                .addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "")
                .addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test4), "")
                .addImage(BitmapFactory.decodeResource(getResources(), R.drawable.test1), "");
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
