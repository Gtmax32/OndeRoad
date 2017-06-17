package com.unimi.mobidev.onderoad.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.activity.CreateActivity;
import com.unimi.mobidev.onderoad.activity.TravelInfoActivity;
import com.unimi.mobidev.onderoad.adapter.TravelDetailAdapter;
import com.unimi.mobidev.onderoad.firebase.FirebaseUtils;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private ListView driverTravelListView;
    private ArrayList<TravelDetail> driverTravelsList;
    private TravelDetailAdapter driverTravelAdapter;
    private RelativeLayout driverLabelLayout;

    private ListView passengerTravelListView;
    private ArrayList<TravelDetail> passengerTravelsList;
    private TravelDetailAdapter passengerTravelAdapter;
    private RelativeLayout passengerLabelLayout;

    private TextView addTravelTextView;

    private FloatingActionButton addTravel;

    private ProgressDialog loadingProgressDialog;

    public FavoritesFragment() {
        System.out.println("In Favorites Fragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        addTravelTextView = (TextView) v.findViewById(R.id.addNewTravelFavorites);

        this.driverTravelsList = new ArrayList<>();
        this.driverTravelAdapter = new TravelDetailAdapter(this.getActivity().getApplicationContext(), R.layout.travel_detail_layout, this.driverTravelsList);

        this.passengerTravelsList = new ArrayList<>();
        this.passengerTravelAdapter = new TravelDetailAdapter(this.getActivity().getApplicationContext(), R.layout.travel_detail_layout, this.passengerTravelsList);

        this.driverLabelLayout = (RelativeLayout) v.findViewById(R.id.driverTravelsLayout);
        this.passengerLabelLayout = (RelativeLayout) v.findViewById(R.id.passengerTravelsLayout);

        driverTravelListView = (ListView) v.findViewById(R.id.driverTravelListView);
        driverTravelListView.setAdapter(this.driverTravelAdapter);
        driverTravelListView.setOnItemClickListener(boxSelectedListener);

        passengerTravelListView = (ListView) v.findViewById(R.id.passengerTravelListView);
        passengerTravelListView.setAdapter(this.passengerTravelAdapter);
        passengerTravelListView.setOnItemClickListener(boxSelectedListener);

        FirebaseUtils.getDatabaseReference("travels").addValueEventListener(dataToRetrieve);

        addTravel = (FloatingActionButton) v.findViewById(R.id.addTravel);
        addTravel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent createIntent = new Intent(FavoritesFragment.this.getActivity(), CreateActivity.class);

                startActivity(createIntent);
            }
        });

        loadingProgressDialog = new ProgressDialog(getActivity());
        loadingProgressDialog.setMessage(getActivity().getResources().getString(R.string.loading_travel_message));
        loadingProgressDialog.setCancelable(false);

        return v;
    }

    private AdapterView.OnItemClickListener boxSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TravelDetail selectedBox = (TravelDetail) parent.getItemAtPosition(position);
            TravelInfo selectedBoxInfo = selectedBox.getCurrentTravel();
            String selectedBoxKey = selectedBox.getTravelKey();

            System.out.println("Clicked item: " + selectedBox.toString());
            System.out.println("Clicked travel: " + selectedBoxInfo.toString());

            Intent infoIntent = new Intent(FavoritesFragment.this.getActivity(), TravelInfoActivity.class);
            infoIntent.putExtra("TravelInfo", selectedBoxInfo);
            infoIntent.putExtra("TravelKey", selectedBoxKey);
            startActivity(infoIntent);
        }
    };

    private ValueEventListener dataToRetrieve = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            loadingProgressDialog.show();

            String currentUserKey = FirebaseAuth.getInstance().getCurrentUser().getUid();
            int driverSize, passengerSize;
            driverTravelAdapter.clear();
            passengerTravelAdapter.clear();

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                TravelInfo temp = postSnapshot.getValue(TravelInfo.class);
                if (temp.getOwnerTravel().getIdUser().equals(currentUserKey)) {
                    driverTravelAdapter.addItem(new TravelDetail(FavoritesFragment.this.getActivity().getApplicationContext(), temp, postSnapshot.getKey()));
                } else if (temp.isPassenger(currentUserKey)) {
                    passengerTravelAdapter.addItem(new TravelDetail(FavoritesFragment.this.getActivity().getApplicationContext(), temp, postSnapshot.getKey()));
                }
            }

            driverSize = driverTravelAdapter.size();
            passengerSize = passengerTravelAdapter.size();

            if (driverSize > 0) {
                driverTravelListView.setVisibility(View.VISIBLE);
                driverLabelLayout.setVisibility(View.VISIBLE);
                driverTravelAdapter.notifyDataSetChanged();
            } else {
                driverTravelListView.setVisibility(View.GONE);
                driverLabelLayout.setVisibility(View.GONE);
            }

            if (passengerSize > 0) {
                passengerTravelListView.setVisibility(View.VISIBLE);
                passengerLabelLayout.setVisibility(View.VISIBLE);
                passengerTravelAdapter.notifyDataSetChanged();
            } else {
                passengerTravelListView.setVisibility(View.GONE);
                passengerLabelLayout.setVisibility(View.GONE);
            }

            if (driverSize == 0 && passengerSize == 0)
                addTravelTextView.setVisibility(View.VISIBLE);
            else
                addTravelTextView.setVisibility(View.GONE);

            loadingProgressDialog.hide();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
