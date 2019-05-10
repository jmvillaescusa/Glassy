package com.jaimeson.glassy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameActivity extends Activity {

    //Display Screen
    Canvas canvas;
    PictureView pictureView;
    int screenWidth;
    int screenHeight;

    //Frames
    int frameWidth;
    int frameHeiht;
    int fps;
    long lastFrameTime;

    //Sound
    private SoundPool soundPool;
    int shatter = -1;
    int correct = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Dimensions of the Screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;



        pictureView = new PictureView(this);
        setContentView(pictureView);

    }

    class PictureView extends SurfaceView implements Runnable {
        Thread ourThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        Paint paint;

        public PictureView (Context context){
            super(context);
            ourHolder = getHolder();
            paint = new Paint();

        }

        public void shatter(){
            Random random = new Random();
        }

        @Override
        public void run() {
            while (playing){
                update();
                draw();
                controlFPS();
            }
        }

        public void update(){

        }

        public void draw(){

        }

        public void controlFPS(){
            long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);
            long timeToSleep = 500 - timeThisFrame;
            if (timeThisFrame > 0) {
                fps = (int)(1000/timeThisFrame);
            }
            if (timeToSleep > 0){
                try{
                    ourThread.sleep(timeToSleep);
                } catch (InterruptedException e) {}
            }

            lastFrameTime = System.currentTimeMillis();
        }

        public void pause(){
            playing = false;
            try {
                ourThread.join();
            } catch (InterruptedException e) {}
        }

        public void resume() {
            playing = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent){

            return true;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        while (true){
            pictureView.pause();
            break;
        }
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();
        pictureView.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        pictureView.pause();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            pictureView.pause();
            finish();
            return true;
        }
        return  false;
    }
}