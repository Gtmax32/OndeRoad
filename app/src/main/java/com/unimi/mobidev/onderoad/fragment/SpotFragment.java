package com.unimi.mobidev.onderoad.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.activity.SpotInfoActivity;
import com.unimi.mobidev.onderoad.model.RegionSpotDict;
import com.unimi.mobidev.onderoad.model.SpotInfo;

import java.util.ArrayList;

public class SpotFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, ClusterManager.OnClusterItemClickListener {

    private MapView spotMap;
    private GoogleMap googleMap;

    private FloatingActionButton searchTravel;

    private ClusterManager<SpotInfo> clusterManager;

    private SpotInfo selectedSpot = null;

    public SpotFragment() {
        System.out.println("In Spot Fragment...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_spot, container, false);

        /*searchTravel = (FloatingActionButton) root.findViewById(R.id.searchTravel);
        searchTravel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //SpotFragment.this.getActivity().onSearchRequested();
                Toast.makeText(SpotFragment.this.getContext(),"Search button pressed!",Toast.LENGTH_SHORT).show();
            }
        });*/

        spotMap = (MapView) root.findViewById(R.id.spotMap);

        spotMap.onCreate(savedInstanceState);

        spotMap.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        spotMap.getMapAsync(this);

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(SpotFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(SpotFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // For showing a move to my location button
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(SpotFragment.this.getActivity(), "I permessi sono necessari per migliorare\nl'efficienza dell'applicazione!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        this.googleMap = gMap;

        int a = 0;

        if (ContextCompat.checkSelfPermission(SpotFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SpotFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            this.googleMap.setMyLocationEnabled(true);

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(SpotFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SpotFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {

                Toast.makeText(SpotFragment.this.getActivity(), "I permessi sono necessari per migliorare\nl'efficienza dell'applicazione!", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(SpotFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);
            } else {
                ActivityCompat.requestPermissions(SpotFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);
            }
        }

        // Stabilisco il centro dell'Italia per posizionare la camera sulla mappa
        LatLng italyCenter = new LatLng(42.416055,12.848037);

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(italyCenter).zoom(5.3f).build();
        this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = new ClusterManager<>(this.getContext(), this.googleMap);
        clusterManager.setOnClusterItemClickListener(this);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        this.googleMap.setOnCameraIdleListener(clusterManager);
        this.googleMap.setOnMarkerClickListener(clusterManager);
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        // Add cluster items (markers) to the cluster manager.
        //addItems();

        setSpotMap();

        this.googleMap.setOnInfoWindowClickListener(this);
    }

    // TODO: Dopo aver inserito la descrizione degli spot in RegionSpotDict, sostituire la stringa in snippet con la descrizione in SpotInfo
    private void setSpotMap(){
        ArrayList<String> spotRegions = RegionSpotDict.getKeys();

        for(String region : spotRegions){
            ArrayList<SpotInfo> spot = RegionSpotDict.getElemFromKey(region);

            if(spot != null){
                clusterManager.addItems(spot);

            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (this.selectedSpot != null){
            Intent infoIntent = new Intent(SpotFragment.this.getContext(), SpotInfoActivity.class);
            infoIntent.putExtra("SpotInfo", this.selectedSpot);
            startActivity(infoIntent);
        }

    }

    @Override
    public boolean onClusterItemClick(ClusterItem clusterItem) {
        this.selectedSpot = (SpotInfo) clusterItem;

        //System.out.println(selectedSpot.toString());

        return false;
    }
}
