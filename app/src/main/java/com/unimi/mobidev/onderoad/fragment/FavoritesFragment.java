package com.unimi.mobidev.onderoad.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.activity.CreateActivity;
import com.unimi.mobidev.onderoad.activity.TravelInfoActivity;
import com.unimi.mobidev.onderoad.adapter.TravelDetailAdapter;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private ListView travelListView;
    //TODO: Probabilmente questo arraylist non servirà più quando ci sarà il server
    private ArrayList<TravelDetail> travelsList;
    private TravelDetailAdapter travelAdapter;

    private FloatingActionButton addTravel;

    private FirebaseDatabase database;

    public FavoritesFragment() {
        System.out.println("In Favorites Fragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        this.travelsList = new ArrayList<>();
        this.travelAdapter = new TravelDetailAdapter(this.getActivity().getApplicationContext(), R.layout.travel_detail_layout, this.travelsList);

        database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference();
        ref.child("travels").addValueEventListener(dataToRetrieve);

        travelListView = (ListView) v.findViewById(R.id.travelListViewFavorites);
        travelListView.setAdapter(this.travelAdapter);
        travelListView.setOnItemClickListener(boxSelectedListener);

        addTravel = (FloatingActionButton) v.findViewById(R.id.addTravel);
        addTravel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent createIntent = new Intent(FavoritesFragment.this.getActivity(), CreateActivity.class);

                startActivity(createIntent);
            }
        });

        return v;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                detail = (TravelInfo) data.getExtras().get("TravelInfo");

                this.detailToView = new TravelDetail(this.getActivity().getApplicationContext(), detail);

                travelAdapter.addItem(detailToView);
                travelAdapter.notifyDataSetChanged();
            }
        }
    }*/

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
            infoIntent.putExtra("TravelKey",selectedBoxKey);
            startActivity(infoIntent);
        }
    };

    private ValueEventListener dataToRetrieve = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String currentUserKey = FirebaseAuth.getInstance().getCurrentUser().getUid();

            travelAdapter.clear();
            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                TravelInfo temp = postSnapshot.getValue(TravelInfo.class);
                if(temp.getOwnerTravel().getIdUser().equals(currentUserKey) || temp.isPassenger(currentUserKey)) {
                    System.out.println("Adding new travel...");
                    travelAdapter.addItem(new TravelDetail(FavoritesFragment.this.getActivity().getApplicationContext(), temp, postSnapshot.getKey()));
                }
            }
            travelAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
