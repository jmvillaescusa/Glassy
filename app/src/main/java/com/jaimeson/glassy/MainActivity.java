package com.jaimeson.glassy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Setups
        Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        Button buttonQuit = (Button)findViewById(R.id.buttonQuit);
        Button buttonHiScores = (Button)findViewById(R.id.buttonHiScore);
        buttonPlay.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);
        buttonHiScores.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.buttonPlay:
                //Start game
                //i = new Intent(this, GameActivity.class);
                //startActivity(i);
                break;
            case R.id.buttonQuit:
                //Exit Game
                System.exit(0);
                break;
            case R.id.buttonHiScore:
                //Display top scores
                break;
        }
    }

    @Override public void onStart() { super.onStart(); }
    @Override public void onResume() { super.onResume(); }
    @Override public void onPause() { super.onPause(); }
    @Override public void onStop() { super.onStop(); }
    @Override public void onDestroy() { super.onDestroy(); }
}