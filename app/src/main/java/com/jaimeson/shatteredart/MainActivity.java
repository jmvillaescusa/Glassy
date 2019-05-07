package com.jaimeson.shatteredart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {

    Canvas canvas;
    PictureView pictureView;

    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Dimensions of Screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;


    }

    class PictureView extends SurfaceView implements Runnable {
        Thread outThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        Paint paint;

        public PictureView(Context context){
            super(context);
            ourHolder = getHolder();
            paint = new Paint();

        }

        @Override
        public void run() {
            while (playing) {
                update();
            }
        }

        public void update() {
            //ooooooooooo
        }
    }
}
