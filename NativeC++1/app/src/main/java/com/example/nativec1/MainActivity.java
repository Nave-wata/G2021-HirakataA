package com.example.nativec1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nativec1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static { System.loadLibrary("nativec1"); }
    public native String stringFromJNI();
    private ActivityMainBinding binding;

    private static final int PERMISSION_RECORD_AUDIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        checkRecordable();
    }

    public void checkRecordable(){
        if(!SpeechRecognizer.isRecognitionAvailable(getApplicationContext())) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.RECORD_AUDIO
                        },
                        PERMISSION_RECORD_AUDIO);
            }
        }
    }
}