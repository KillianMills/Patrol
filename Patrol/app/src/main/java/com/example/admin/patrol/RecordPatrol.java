package com.example.admin.patrol;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RecordPatrol extends Activity implements SensorEventListener {

    final Context context = this;
    private Chronometer mChronometer;
    private Button button;
    private boolean stopped;

    private String filename = "journeyData.txt";
    private String string = "Hello world!";
    private FileOutputStream outputStream;
    private File journeyFile = new File(context.getFilesDir(), filename);

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float linear_acceleration[] = new float[3];
    private float gravity[] = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_patrol);

        mChronometer = (Chronometer) findViewById(R.id.chronometerTimer);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        button = (Button) findViewById(R.id.StartRecord);
        button.setOnClickListener(mStartListener);

        button = (Button) findViewById(R.id.StopRecord);
        button.setOnClickListener(mStopListener);

        button = (Button) findViewById(R.id.SendRecord);
        button.setOnClickListener(mResetListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.start();
            startRecording();

            Toast.makeText(context, "Recording Started",
                    Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.stop();
            stopRecording();

            Toast.makeText(context, "Recording Stopped",
                    Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener mResetListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.setBase(SystemClock.elapsedRealtime());
            sendRecording();

            Toast.makeText(context, "Recording Finished and Sent",
                    Toast.LENGTH_LONG).show();
        }
    };

    // start recording data
    public void startRecording(){
        stopped = false;

        //if() {
            File journeyFile = new File(context.getFilesDir(), filename);
        //}
        while(true) {
            // end the method when stopped is true
            if(stopped == true){
                return;
            }
            // write these to file
            //linear_acceleration[0];
            //linear_acceleration[1];
            //linear_acceleration[2];
        }
    }

    // stop recording data
    public void stopRecording(){
        stopped = true;
    }

    // send recording to server when the user is finished
    public void sendRecording(){
        stopped = true;
        // send file before deleting
        //journeyFile.delete();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    // this will constantly update the acceleromete data
    @Override
    public void onSensorChanged(SensorEvent event) {

        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final float alpha = (float) 0.8;

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

    }

}