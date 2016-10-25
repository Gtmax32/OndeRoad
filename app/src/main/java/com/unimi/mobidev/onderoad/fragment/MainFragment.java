package com.unimi.mobidev.onderoad.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.adapter.TravelDetailAdapter;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private ListView travelListView;

    private ArrayList<TravelInfo> travelsList;
    private TravelDetail detail;
    private TravelDetailAdapter travelAdapter;

    public MainFragment() {
        System.out.println("In MainFragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        this.travelsList = new ArrayList<>();
        //this.travelAdapter = new TravelDetailAdapter(this.getActivity().getApplicationContext(),R.layout.travel_detail,this.travelsList);

        travelListView = (ListView) v.findViewById(R.id.travelListViewMain);
        travelListView.setAdapter(this.travelAdapter);

        return v;
    }
}
