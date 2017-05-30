package com.unimi.mobidev.onderoad.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
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
import com.unimi.mobidev.onderoad.model.TravelInfo;

public class TravelInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int CREATE_ACTIVITY_REQUEST = 2;
    private TravelInfo travelDisplayed;

    private TextView passengersActualInfo;
    private TextView priceActualInfo;
    private TextView surfboardActualInfo;

    private TextView dateTimeActualInfo;
    private TextView priceTotalInfo;
    private TextView carSupportActualInfo;

    private EditText noteActualText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.travelInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_travel);

        //Serve per inserire la freccia per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        travelDisplayed = (TravelInfo) this.getIntent().getSerializableExtra("TravelInfo");

        System.out.println("In TravelInfoActivity: " + travelDisplayed.toString());

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.actualTravelMap);
        mapFragment.getMapAsync(this);

        float actualPrice = travelDisplayed.getPriceTravel() / (travelDisplayed.getPassengersTravel().size() + 1);
        String temp = travelDisplayed.getDataDeparture() + " - " + travelDisplayed.getTimeDeparture();

        passengersActualInfo = (TextView) findViewById(R.id.passengersFractionDataText);
        priceActualInfo = (TextView) findViewById(R.id.actualPriceDataText);
        surfboardActualInfo = (TextView) findViewById(R.id.surfboardFractionData);

        dateTimeActualInfo = (TextView) findViewById(R.id.actualDateTravel);
        dateTimeActualInfo.setText(temp);

        priceTotalInfo = (TextView) findViewById(R.id.totalPriceDataInfo);
        carSupportActualInfo = (TextView) findViewById(R.id.carSupportDataInfo);

        noteActualText = (EditText) findViewById(R.id.actualNoteText);
        noteActualText.setKeyListener(null);

        passengersActualInfo.setText(travelDisplayed.getPassengersTravel().size() + "/" + travelDisplayed.getCarTravel().getPassengersNumber());

        temp = actualPrice + " €";

        priceActualInfo.setText(temp);

        temp = travelDisplayed.getCarTravel().getSurfboardNumber() + "";

        surfboardActualInfo.setText(temp);

        temp = travelDisplayed.getPriceTravel() + " €";

        priceTotalInfo.setText(temp);

        carSupportActualInfo.setText(travelDisplayed.getCarTravel().getSurfboardType());

        noteActualText.setText(travelDisplayed.getNoteTravel());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double destinationLatitude = travelDisplayed.getSpotDestination().getLatitudeSpot();
        double destinationLongitude = travelDisplayed.getSpotDestination().getLongitudeSpot();

        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(destinationLatitude, destinationLongitude)).title(travelDisplayed.getSpotDestination().getNameSpot()));
        marker.showInfoWindow();

        //TODO: Controllare il valore dello zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(destinationLatitude, destinationLongitude), 14.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.travel_info_settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.modifySubMenu:
                System.out.println("Opening modification menu...");
                modifyTravel();
                return true;

            case R.id.navigationSubMenu:
                System.out.println("Opening navigation menu...");
                showMap();
                return true;

            case R.id.deleteSubMenu:
                Toast.makeText(this.getApplicationContext(),"Cancello il viaggio!",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.shareSubMenu:
                System.out.println("Opening sharing menu...");
                shareTravel();
                return true;

            case R.id.mailSubMenu:
                composeEmail();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showMap() {
        String toParse = "geo:0,0?q= " + travelDisplayed.getSpotDestination().getLatitudeSpot() + "," + travelDisplayed.getSpotDestination().getLongitudeSpot() + "(" + travelDisplayed.getSpotDestination().getNameSpot() + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(toParse));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void shareTravel() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void modifyTravel() {
        Intent modifyIntent = new Intent(this.getApplication(), ModifyActivity.class);
        modifyIntent.putExtra("TravelInfo", travelDisplayed);
        startActivityForResult(modifyIntent, CREATE_ACTIVITY_REQUEST);
    }

    public void composeEmail() {
        String[] addresses = new String[2];
        addresses[0]="gtmax_32@hotmail.it";
        addresses[1]="gf.trentadue@gmail.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Comunicazione");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                travelDisplayed = (TravelInfo) data.getExtras().get("TravelInfo");
                System.out.println("Receiving data from ModifyActivity..." + travelDisplayed.toString());

                finish();
                this.getIntent().putExtra("TravelInfo", travelDisplayed);
                startActivity(this.getIntent());

            }
        }
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
