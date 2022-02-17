package com.example.nativec1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class UsersVolumeFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usersvolume, container, false);
    }

    public static UsersVolumeFragment newInstance(int data) {
        Bundle args = new Bundle();
        args.putInt("DATA", data);
        UsersVolumeFragment fragment = new UsersVolumeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}