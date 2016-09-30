package com.unimi.mobidev.onderoad.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unimi.mobidev.onderoad.R;

public class MainFragment extends Fragment {
    private FloatingActionButton addTrip;

    public MainFragment() {
        System.out.println("In MainFragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
