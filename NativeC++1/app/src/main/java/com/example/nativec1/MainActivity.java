package com.example.nativec1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.jtransforms.fft.DoubleFFT_1D;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("nativec1");
    }

    public native String stringFromJNI();

    AudioRecord audioRec = null;
    AudioTrack player = null;
    Button btn = null;

    private static final int PERMISSION_RECORD_AUDIO = 1;
    final static int SAMPLING_RATE = 44100;
    boolean bIsRecording = false;
    int bufSize = 1024;
    int seekBarMax = 20;
    int seekBarProgress = 10;
    DoubleFFT_1D fft = new DoubleFFT_1D(bufSize);

    int[] Base_Hz = {0, 2, 4, 6, 14, 26, 50, 96, 188, 374, 746, 1024};
    int[] Base_Hz_sub = {0, 1, 2, 3, 7, 13, 25, 48, 94, 187, 373, 511};
    static double[] vol_ary = {0.5, 0.526, 0.555, 0.588, 0.625, 0.666, 0.714, 0.769, 0.833, 0.909,
            1.0,
            1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0};
    static double[] vol = new double[10];

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++) {
            vol[i] = vol_ary[10];
        }

        btn = findViewById(R.id.button_id);
        btn.setOnClickListener(this);

        // AudioRecordの作成
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        audioRec = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLING_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufSize);

        // AudioTrackの作成
        player = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                SAMPLING_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufSize,
                AudioTrack.MODE_STREAM
        );

        checkRecordable();
    }

    @SuppressLint("ObsoleteSdkInt")
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

    @Override
    public void onClick(View v) {
        if (v == btn) {
            if (bIsRecording) {
                btn.setText(R.string.stopping_label);
                bIsRecording = false;
            } else {
                // 録音開始
                Log.v("AudioRecord", "startRecording");
                player.play();
                audioRec.startRecording();
                bIsRecording = true;
                // 録音スレッド
                new Thread(() -> {
                    byte[] inputBuffer = new byte[bufSize];
                    byte[] outputBuffer;
                    while (bIsRecording) {
                        // 録音データ読み込み
                        audioRec.read(inputBuffer, 0, bufSize);
                        outputBuffer = Amplification(inputBuffer);
                        player.write(outputBuffer, 0, bufSize);
                        // Log.v("AudioRecord", "read " + bufSize + " bytes");
                    }
                    // 録音停止
                    Log.v("AudioRecord", "stop");
                    player.stop();
                    audioRec.stop();
                }).start();
                btn.setText(R.string.running_label);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioRec.release();
    }

    protected byte[] Amplification(byte[] inputBuffer) {
        double[] buf = new double[bufSize];
        byte[] outputBuffer = new byte[bufSize];
        int i;

        for (i = 0; i < bufSize; i++) {
            buf[i] = inputBuffer[i];
            buf[i] /= 2;
        }

        // フーリエ変換
        fft.realForward(buf);

        buf[2] *= vol[0];
        buf[3] *= vol[0];
        buf[4] *= vol[1];
        buf[5] *= vol[1];
        for (i = 6; i <= 11; i++) {
            buf[i] *= vol[2];
        }
        for (i = 12; i <= 19; i++) {
            buf[i] *= vol[3];
        }
        for (i = 20; i <= 37; i++) {
            buf[i] *= vol[4];
        }
        for (i = 38; i <= 73; i++) {
            buf[i] *= vol[5];
        }
        for (i = 74; i <= 141; i++) {
            buf[i] *= vol[6];
        }
        for (i = 142; i <= 271; i++) {
            buf[i] *= vol[7];
        }
        for (i = 272; i <= 461; i++) {
            buf[i] *= vol[8];
        }
        for (i = 462; i <= 1023; i++) {
            buf[i] *= vol[9];
        }

        // 逆フーリエ変換
        fft.realInverse(buf, true);

        for (i = 0; i < bufSize; i++) {
            if (120 < buf[i]) {
                Log.v("i", "" + i + ", buf = " + buf[i]);
            }
            if (buf[i] < -120) {
                Log.v("i", "" + i + ", buf = " + buf[i]);
            }
            outputBuffer[i] = (byte)buf[i];
        }

        return outputBuffer;
    }
}
