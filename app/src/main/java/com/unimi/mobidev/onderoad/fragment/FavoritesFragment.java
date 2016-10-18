package com.unimi.mobidev.onderoad.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.activity.CreateActivity;
import com.unimi.mobidev.onderoad.adapter.TravelInfoAdapter;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    private static final int CREATE_ACTIVITY_REQUEST = 1;

    private ListView travelListView;
    //TODO: Probabilmente questo arraylist non servirà più quando ci sarà il server
    private ArrayList<TravelDetail> travelsList;
    private TravelInfo detail;
    private TravelDetail detailToView;
    private TravelInfoAdapter travelAdapter;

    private FloatingActionButton addTrip;

    public FavoritesFragment() { System.out.println("In Favorites Fragment..."); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        this.detail = new TravelInfo();
        this.detailToView = new TravelDetail(this.getActivity().getApplicationContext());
        this.travelsList = new ArrayList<>();
        this.travelAdapter = new TravelInfoAdapter(this.getActivity().getApplicationContext(),R.layout.travel_detail,this.travelsList);

        travelListView = (ListView) v.findViewById(R.id.travelListViewFavorites);
        travelListView.setAdapter(this.travelAdapter);

        addTrip = (FloatingActionButton) v.findViewById(R.id.addTrip);
        addTrip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent create = new Intent(FavoritesFragment.this.getActivity(),CreateActivity.class);

                startActivityForResult(create,CREATE_ACTIVITY_REQUEST);
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if( requestCode == CREATE_ACTIVITY_REQUEST ) {
            if(resultCode == RESULT_OK) {
                detail = (TravelInfo) data.getExtras().get("TravelInfo");
                detailToView.setTravelInfo(detail);
                travelsList.add(detailToView);
                travelAdapter.notifyDataSetChanged();
            }
        }
    }

}
