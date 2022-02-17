package com.example.nativec1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button runBtn = view.findViewById(R.id.button_id);

        final onClickListener bt = new onClickListener((MainActivity) getActivity());

        runBtn.setOnClickListener(bt);
    }

    private static class onClickListener implements View.OnClickListener {
        MainActivity activity;

        onClickListener(MainActivity activity) {
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
}
