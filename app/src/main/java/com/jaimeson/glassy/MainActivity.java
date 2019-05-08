package com.jaimeson.glassy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        Button buttonQuit = (Button)findViewById(R.id.buttonQuit);
    }

    @Override
    public void onClick(View v) {

    }
}