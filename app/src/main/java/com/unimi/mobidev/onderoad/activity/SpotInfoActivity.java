package com.unimi.mobidev.onderoad.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.SpotInfo;

public class SpotInfoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView regionSpotValue;
    private TextView provinceSpotValue;
    private TextView citySpotValue;
    private TextView nameSpotValue;

    private TextView spotWaveValue;
    private TextView spotWindValue;
    private TextView spotSwellValue;
    private TextView spotSeabedValue;

    private TextView noteSpotValue;

    private SpotInfo spotToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.spotInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_spot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.spotToDisplay = (SpotInfo) this.getIntent().getSerializableExtra("SpotInfo");
        System.out.println("In SpotInfoActivity...\n" + this.spotToDisplay.toString());

        String temp;

        regionSpotValue = (TextView) findViewById(R.id.regionDataSpotInfo);
        regionSpotValue.setText(spotToDisplay.getRegionSpot());

        provinceSpotValue = (TextView) findViewById(R.id.provinceDataSpotInfo);
        provinceSpotValue.setText(spotToDisplay.getProvinceSpot());

        citySpotValue = (TextView) findViewById(R.id.cityDataSpotInfo);
        citySpotValue.setText(spotToDisplay.getCitySpot());

        nameSpotValue = (TextView) findViewById(R.id.nameDataSpotInfo);
        nameSpotValue.setText(spotToDisplay.getTitle());

        spotWaveValue = (TextView) findViewById(R.id.waveDataSpotInfo);
        spotWaveValue.setText(spotToDisplay.getTableSpot().getWaveSpot());

        spotWindValue = (TextView) findViewById(R.id.windDataSpotInfo);
        spotWindValue.setText(spotToDisplay.getTableSpot().getWindSpot());

        spotSwellValue = (TextView) findViewById(R.id.swellDataSpotInfo);
        spotSwellValue.setText(spotToDisplay.getTableSpot().getSwellSpot());

        spotSeabedValue = (TextView) findViewById(R.id.seabedDataSpotInfo);
        spotSeabedValue.setText(spotToDisplay.getTableSpot().getSeabedSpot());

        noteSpotValue = (TextView) findViewById(R.id.spotNoteData);
        temp = spotToDisplay.getSnippet();
        noteSpotValue.setText(temp);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.spotMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double spotLatitude = this.spotToDisplay.getLatitudeSpot();
        double spotLongitude = this.spotToDisplay.getLongitudeSpot();
        LatLng spotCoordinate = new LatLng(spotLatitude, spotLongitude);

        String spotName = this.spotToDisplay.getTitle();

        Marker spot = googleMap.addMarker(new MarkerOptions().position(spotCoordinate).title(spotName));
        spot.showInfoWindow();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spotCoordinate, 10.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.spot_info_settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reviewSubMenu:
                Toast.makeText(this.getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.navigationSubMenu:
                System.out.println("Opening navigation menu...");
                showMap();
                return true;

            case R.id.createSubMenu:
                System.out.println("Opening create menu...");
                createTravel();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showMap() {
        String spotInfo = spotToDisplay.getTitle() + ", " + spotToDisplay.getCitySpot();
        String toParse = "geo:0,0?q= " + spotToDisplay.getLatitudeSpot() + "," + spotToDisplay.getLongitudeSpot() + "(" + spotInfo + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(toParse));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void createTravel() {
        Intent createSpotIntent = new Intent(SpotInfoActivity.this, CreateActivity.class);
        createSpotIntent.putExtra("SpotData", spotToDisplay);
        startActivity(createSpotIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
