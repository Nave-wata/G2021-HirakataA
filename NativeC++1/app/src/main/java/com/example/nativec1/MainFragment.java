package com.example.nativec1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    int seekBarMax = 20;
    int seekBarProgress = 10;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SeekBar seekBar1 = view.findViewById(R.id.seekBar_1);
        SeekBar seekBar2 = view.findViewById(R.id.seekBar_2);
        SeekBar seekBar3 = view.findViewById(R.id.seekBar_3);
        SeekBar seekBar4 = view.findViewById(R.id.seekBar_4);
        SeekBar seekBar5 = view.findViewById(R.id.seekBar_5);
        SeekBar seekBar6 = view.findViewById(R.id.seekBar_6);
        SeekBar seekBar7 = view.findViewById(R.id.seekBar_7);
        SeekBar seekBar8 = view.findViewById(R.id.seekBar_8);
        SeekBar seekBar9 = view.findViewById(R.id.seekBar_9);
        SeekBar seekBar10 = view.findViewById(R.id.seekBar_10);

        SeekBar seekBars[] = {seekBar1,
                              seekBar2,
                              seekBar3,
                              seekBar4,
                              seekBar5,
                              seekBar6,
                              seekBar7,
                              seekBar8,
                              seekBar9,
                              seekBar10};

        onSeekBarChangeListener sb = new onSeekBarChangeListener();

        for (SeekBar seekBar: seekBars) {
            seekBar.setMax(seekBarMax);
            seekBar.setProgress(seekBarProgress);
            seekBar.setOnSeekBarChangeListener(sb);
        }
    }

    private class onSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int id = seekBar.getId();
            switch (id) {
                case R.id.seekBar_1:
                    MainActivity.vol[0] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_2:
                    MainActivity.vol[1] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_3:
                    MainActivity.vol[2] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_4:
                    MainActivity.vol[3] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_5:
                    MainActivity.vol[4] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_6:
                    MainActivity.vol[5] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_7:
                    MainActivity.vol[6] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_8:
                    MainActivity.vol[7] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_9:
                    MainActivity.vol[8] = MainActivity.vol_ary[i];
                    break;
                case R.id.seekBar_10:
                    MainActivity.vol[9] = MainActivity.vol_ary[i];
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }
}
