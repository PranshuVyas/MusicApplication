package com.example.user.musicapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.musicapplication.service.ForegroundService;
import com.example.user.musicapplication.utility.IConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.button1);
        Button stopButton = (Button) findViewById(R.id.button2);
         startButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent startIntent = new Intent(MainActivity.this, ForegroundService.class);
                 startIntent.setAction(IConstants.ACTION.STARTFOREGROUND_ACTION);
                 startService(startIntent);
             }
         });
         stopButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent stopIntent = new Intent(MainActivity.this, ForegroundService.class);
                 stopIntent.setAction(IConstants.ACTION.STOPFOREGROUND_ACTION);
                 startService(stopIntent);
             }
         });
    }
}
