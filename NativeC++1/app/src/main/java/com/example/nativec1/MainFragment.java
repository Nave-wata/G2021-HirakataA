package com.example.nativec1;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;


public class MainFragment extends Fragment {
    static boolean switchFlag = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, UsersVolumeFragment.newInstance(R.string.UsersVolume));
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AssetManager assetManager = Objects.requireNonNull(getActivity()).getAssets();
        SetImage setImage = new SetImage(assetManager);

        final Button runBtn = view.findViewById(R.id.button_id);
        final ButtonOnClickListener bt = new ButtonOnClickListener((MainActivity) getActivity());
        runBtn.setOnClickListener(bt);

        final ImageButton switchButton = view.findViewById(R.id.SwitchButton);
        final ImageButtonOnClickListener ibt = new ImageButtonOnClickListener();
        setImage.setImageButtonBitmapFromAsset(switchButton, "switch.png");
        switchButton.setOnClickListener(ibt);
    }

    private static class ButtonOnClickListener implements Button.OnClickListener {
        MainActivity activity;

        ButtonOnClickListener(MainActivity activity) {
            this.activity = activity;
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            final int id = v.getId();
            final Button btn = v.findViewById(id);

            switch (id) {
                case R.id.button_id:
                    ChangeRunText(btn);
                    activity.RunMethod();
                    break;
                default:
                    break;
            }
        }

        private void ChangeRunText(Button btn) {
            if (activity.bIsRecording) {
                btn.setText(R.string.stopping_label);
            } else {
                btn.setText(R.string.running_label);
            }
        }
    }

    private class ImageButtonOnClickListener implements ImageButton.OnClickListener {
        @Override
        public void onClick(View v) {
            final int id = v.getId();

            switch (id) {
                case R.id.SwitchButton:
                    ChangeFragment();
                    break;
                default:
                    break;
            }
        }

        private void ChangeFragment() {
            if (switchFlag) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, UsersVolumeFragment.newInstance(R.string.UsersVolume));
                fragmentTransaction.commit();
                switchFlag = false;
            } else {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, VolumeFragment.newInstance(R.string.Volume));
                fragmentTransaction.commit();
                switchFlag = true;
            }
        }
    }
}
