package com.unimi.mobidev.onderoad.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.activity.CreateActivity;

public class MainFragment extends Fragment{
    private FloatingActionButton addTrip;

    public MainFragment() {
        System.out.println("In MainFragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        addTrip = (FloatingActionButton) v.findViewById(R.id.addTrip);
        addTrip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent create = new Intent(MainFragment.this.getActivity(),CreateActivity.class);

                startActivity(create);
            }
        });
        return v;
    }
}
