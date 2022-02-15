package com.example.nativec1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {
    final int seekBarMax = 20;
    final int seekBarProgress = 10;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SeekBar seekBar1 = view.findViewById(R.id.seekBar_1);
        final SeekBar seekBar2 = view.findViewById(R.id.seekBar_2);
        final SeekBar seekBar3 = view.findViewById(R.id.seekBar_3);
        final SeekBar seekBar4 = view.findViewById(R.id.seekBar_4);
        final SeekBar seekBar5 = view.findViewById(R.id.seekBar_5);
        final SeekBar seekBar6 = view.findViewById(R.id.seekBar_6);
        final SeekBar seekBar7 = view.findViewById(R.id.seekBar_7);
        final SeekBar seekBar8 = view.findViewById(R.id.seekBar_8);
        final SeekBar seekBar9 = view.findViewById(R.id.seekBar_9);
        final SeekBar seekBar10 = view.findViewById(R.id.seekBar_10);

        final SeekBar[] seekBars = {
                seekBar1,
                seekBar2,
                seekBar3,
                seekBar4,
                seekBar5,
                seekBar6,
                seekBar7,
                seekBar8,
                seekBar9,
                seekBar10
        };

        final onSeekBarChangeListener sb = new onSeekBarChangeListener();

        for (SeekBar seekBar: seekBars) {
            seekBar.setMax(seekBarMax);
            seekBar.setProgress(seekBarProgress);
            seekBar.setOnSeekBarChangeListener(sb);
        }
    }

    private class onSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            final int id = seekBar.getId();
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
