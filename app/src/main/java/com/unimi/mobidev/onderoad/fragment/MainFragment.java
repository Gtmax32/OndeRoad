package com.unimi.mobidev.onderoad.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.adapter.TravelInfoAdapter;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private ListView travelListView;

    private ArrayList<TravelDetail> travelsList;
    private TravelDetail detail;
    private TravelInfoAdapter travelAdapter;

    public MainFragment() {
        System.out.println("In MainFragment...");

        this.travelsList = new ArrayList<>();
        this.travelAdapter = new TravelInfoAdapter(this.getContext(),R.layout.travel_detail,this.travelsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        travelListView = (ListView) v.findViewById(R.id.travelListView);
        travelListView.setAdapter(this.travelAdapter);
        return v;
    }
}
