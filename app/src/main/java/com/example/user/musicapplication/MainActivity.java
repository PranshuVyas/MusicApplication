package com.example.user.musicapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.musicapplication.service.ForegroundService;
import com.example.user.musicapplication.utility.IConstants;

public class MainActivity extends AppCompatActivity {
    TextView txtView;
    private IntentFilter mIntentFilter;

    BroadcastReceiver myReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                txtView.setText("Action = " + intent.getAction());
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.button1);
        Button stopButton = (Button) findViewById(R.id.button2);
         txtView = (TextView)findViewById(R.id.textView1);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(IConstants.ACTION.NEXT_ACTION);
        mIntentFilter.addAction(IConstants.ACTION.PLAY_ACTION);
        mIntentFilter.addAction(IConstants.ACTION.PREV_ACTION);

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

    @Override
    protected void onResume() {

        super.onResume();
        registerReceiver(myReciver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReciver);
        super.onPause();

    }
}
