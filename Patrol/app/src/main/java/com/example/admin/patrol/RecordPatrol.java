package com.example.admin.patrol;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.view.View.OnClickListener;

public class RecordPatrol extends Activity {

    private Chronometer mChronometer;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_patrol);

        mChronometer = (Chronometer) findViewById(R.id.chronometerTimer);

        button = (Button) findViewById(R.id.StartRecord);
        button.setOnClickListener(mStartListener);

        button = (Button) findViewById(R.id.StopRecord);
        button.setOnClickListener(mStopListener);

        button = (Button) findViewById(R.id.SendRecord);
        button.setOnClickListener(mResetListener);
    }

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.start();
        }
    };

    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.stop();
        }
    };

    View.OnClickListener mResetListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };

}
