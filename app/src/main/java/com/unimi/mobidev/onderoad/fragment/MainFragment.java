package com.unimi.mobidev.onderoad.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.activity.TravelInfoActivity;
import com.unimi.mobidev.onderoad.adapter.TravelDetailAdapter;
import com.unimi.mobidev.onderoad.firebase.FirebaseUtils;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private TextView suggestionNewTravel;
    private ListView travelListView;

    private ArrayList<TravelDetail> travelsList;
    private TravelDetailAdapter travelAdapter;

    private ProgressDialog loadingProgressDialog;

    public MainFragment() {
        System.out.println("In MainFragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        suggestionNewTravel = (TextView) v.findViewById(R.id.addNewTravelMain);

        this.travelsList = new ArrayList<>();
        this.travelAdapter = new TravelDetailAdapter(this.getActivity().getApplicationContext(), R.layout.travel_detail_layout, this.travelsList);

        FirebaseUtils.getDatabaseReference("travels").orderByValue().addValueEventListener(dataToRetrieve);
        //ref.child("travels").addChildEventListener(childToRetrieve);

        travelListView = (ListView) v.findViewById(R.id.travelListViewMain);
        travelListView.setAdapter(this.travelAdapter);
        travelListView.setOnItemClickListener(boxSelectedListener);

        loadingProgressDialog = new ProgressDialog(v.getContext());
        loadingProgressDialog.setMessage(v.getContext().getResources().getString(R.string.loading_travel_list_message));
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

            Intent infoIntent = new Intent(MainFragment.this.getActivity(), TravelInfoActivity.class);
            infoIntent.putExtra("TravelInfo", selectedBoxInfo);
            infoIntent.putExtra("TravelKey", selectedBoxKey);
            startActivity(infoIntent);
        }
    };

    private ValueEventListener dataToRetrieve = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String currentUserKey = FirebaseAuth.getInstance().getCurrentUser().getUid();

            travelAdapter.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                TravelInfo temp = postSnapshot.getValue(TravelInfo.class);
                if (!temp.getOwnerTravel().getIdUser().equals(currentUserKey) && !temp.isPassenger(currentUserKey)) {
                    System.out.println("Adding new travel...");
                    travelAdapter.addItem(new TravelDetail(MainFragment.this.getActivity().getApplicationContext(), temp, postSnapshot.getKey()));
                }
            }

            if (!travelAdapter.isEmpty()) {
                suggestionNewTravel.setVisibility(View.GONE);
                travelListView.setVisibility(View.VISIBLE);
                travelAdapter.notifyDataSetChanged();
            } else {
                suggestionNewTravel.setVisibility(View.VISIBLE);
                travelListView.setVisibility(View.GONE);
            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
