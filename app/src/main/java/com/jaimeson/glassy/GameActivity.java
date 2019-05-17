package com.jaimeson.glassy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Dimensions of the Screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        pictureView = new PictureView(this);
        //setContentView(pictureView);
        Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(this);
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

        /*public void shatter(){
            Random random = new Random();
        }*/

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
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.buttonPause:
                //It should Pause the playing session
                //Instead, it returns to Main Menu Screen
                i = new Intent(this, MainActivity.class);
                startActivity(i);

                break;
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