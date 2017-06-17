package com.unimi.mobidev.onderoad.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.adapter.TabsAdapter;
import com.unimi.mobidev.onderoad.firebase.FirebaseUtils;
import com.unimi.mobidev.onderoad.model.TravelInfo;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, ResultCallback<AppInviteInvitationResult>{
    private ViewPager viewPager;
    private TabsAdapter adapter;

    private GoogleApiClient googleApiClient;
    private ProgressDialog loadingProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        TabLayout.Tab list = tabLayout.newTab(), favorites = tabLayout.newTab(),
                spot = tabLayout.newTab(), settings = tabLayout.newTab();

        View listView = getLayoutInflater().inflate(R.layout.tab_layout, null);
        ImageView listIcon = (ImageView) listView.findViewById(R.id.imageView);
        TextView listLabel = (TextView) listView.findViewById(R.id.textView);
        listIcon.setImageResource(R.drawable.ic_action_home );
        listLabel.setText(R.string.main_fragment);

        View favView = getLayoutInflater().inflate(R.layout.tab_layout, null);
        ImageView favIcon = (ImageView) favView.findViewById(R.id.imageView);
        TextView favLabel = (TextView) favView.findViewById(R.id.textView);
        favIcon.setImageResource(R.drawable.ic_action_favorites);
        favLabel.setText(R.string.favorites_fragment);

        View spotView = getLayoutInflater().inflate(R.layout.tab_layout, null);
        ImageView spotIcon = (ImageView) spotView.findViewById(R.id.imageView);
        TextView spotLabel = (TextView) spotView.findViewById(R.id.textView);
        spotIcon.setImageResource(R.drawable.ic_action_spot);
        spotLabel.setText(R.string.spot_fragment);

        /*View settView = getLayoutInflater().inflate(R.layout.tab_layout, null);
        ImageView settIcon = (ImageView) settView.findViewById(R.id.imageView);
        TextView settLabel = (TextView) settView.findViewById(R.id.textView);
        settIcon.setImageResource(R.drawable.ic_action_settings);
        settLabel.setText(R.string.settings_fragment);*/

        list.setCustomView(listView);
        favorites.setCustomView(favView);
        spot.setCustomView(spotView);
        //settings.setCustomView(settView);

        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);

        tabLayout.addTab(list, 0);
        tabLayout.addTab(favorites, 1);
        tabLayout.addTab(spot, 2);
        //tabLayout.addTab(settings, 3);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        loadingProgressDialog = new ProgressDialog(this);
        loadingProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.loading_travel_message));
        loadingProgressDialog.setCancelable(false);

        // Build GoogleApiClient with AppInvite API for receiving deep links
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(AppInvite.API)
                .build();

        AppInvite.AppInviteApi.getInvitation(googleApiClient, this, false).setResultCallback(this);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_alert_message)
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final Intent exitIntent = new Intent(Intent.ACTION_MAIN);
                        exitIntent.addCategory(Intent.CATEGORY_HOME);
                        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(exitIntent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("Connection failed!");
    }

    @Override
    public void onResult(@NonNull AppInviteInvitationResult result) {
        if (result.getStatus().isSuccess()) {
            loadingProgressDialog.show();
            Intent intent = result.getInvitationIntent();
            String deepLink = AppInviteReferral.getDeepLink(intent);

            System.out.println("Deeplink received!" + "\nData arrived: " + deepLink);

            String[] splitUri = deepLink.split("/");
            System.out.println("Size: " + splitUri.length + "ID: " + splitUri[4]);

            String travelID = splitUri[4];

            FirebaseUtils.getDatabaseReference("travels").child(travelID)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null){
                                TravelInfo temp = dataSnapshot.getValue(TravelInfo.class);
                                System.out.println(temp.toString() + " " + dataSnapshot.getKey());

                                if(temp != null){
                                    Intent sharedTravel = new Intent(MainActivity.this.getApplicationContext(),TravelInfoActivity.class);

                                    sharedTravel.putExtra("TravelInfo", temp);
                                    sharedTravel.putExtra("TravelKey", dataSnapshot.getKey());

                                    loadingProgressDialog.hide();

                                    startActivity(sharedTravel);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        } else {
            System.out.println("getInvitation: no deep link found.");
        }
    }
}

