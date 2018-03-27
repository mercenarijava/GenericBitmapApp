package com.android.recosta32.genericbitmapapp.ui.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.recosta32.genericbitmapapp.R;
import com.android.recosta32.genericbitmapapp.ui.custom.ImageBoxComponent;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;

public class MainActivity extends AppCompatActivity {

    FlowingDrawer drawer;
    FlowingMenuLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DRAWER SETUP*/

        drawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        menu=findViewById(R.id.menulayout);
        drawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        drawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });

        /*END DRAWER SETUP*/

        ImageBoxComponent imageBoxComponent = (ImageBoxComponent) findViewById(R.id.test);
        imageBoxComponent.addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.black)), "#607D8B")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorAccent)), "#9E9E9E")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark)), "#795548")
                .addImage(createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.colorPrimary)), "#FF5722");
        //createImage(1024, 800, ContextCompat.getColor(getBaseContext(), R.color.black)
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
