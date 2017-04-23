package com.unimi.mobidev.onderoad.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.TravelInfo;

public class TravelInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.createInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_travel);

        //Serve per inserire la freccia per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        travelDisplayed = (TravelInfo) this.getIntent().getSerializableExtra("TravelInfo");

        System.out.println("In TravelInfoActivity: " + travelDisplayed.toString());

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.actualTravelMap);
        mapFragment.getMapAsync(this);

        //Ricordarsi che, per visualizzare i valori relative alle etichette, conviene prendere il testo dell'etichetta
        //ed aggiungerli il valore modificato. Es. Passeggeri: + 1/5

        float actualPrice = travelDisplayed.getPriceTravel() / (travelDisplayed.getPassengersTravel().size() + 1);
        String temp = travelDisplayed.getDataDeparture() + " - " + travelDisplayed.getTimeDeparture();

        passengersActualInfo = (TextView) findViewById(R.id.passengersFractionText);
        priceActualInfo = (TextView) findViewById(R.id.actualPriceText);
        surfboardActualInfo = (TextView) findViewById(R.id.surfboardFractionText);

        dateTimeActualInfo = (TextView) findViewById(R.id.actualDateTravel);
        dateTimeActualInfo.setText(temp);

        priceTotalInfo = (TextView) findViewById(R.id.totalPriceInfo);
        carSupportActualInfo = (TextView) findViewById(R.id.carSupportInfo);

        noteActualText = (EditText) findViewById(R.id.actualNoteText);
        noteActualText.setKeyListener(null);

        temp = passengersActualInfo.getText().toString() + " " + travelDisplayed.getPassengersTravel().size() + "/" + travelDisplayed.getCarTravel().getPassengersNumber();

        passengersActualInfo.setText(temp);

        temp = priceActualInfo.getText().toString() + " " + actualPrice + " €";

        priceActualInfo.setText(temp);

        temp = surfboardActualInfo.getText().toString() + " " + travelDisplayed.getCarTravel().getSurfboardNumber();

        surfboardActualInfo.setText(temp);

        temp = priceTotalInfo.getText().toString() + " " + travelDisplayed.getPriceTravel() + " €";

        priceTotalInfo.setText(temp);

        temp = carSupportActualInfo.getText().toString() + " " + travelDisplayed.getCarTravel().getSurfboardType();

        carSupportActualInfo.setText(temp);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double departureLongitude = travelDisplayed.getAddressDeparture().getLongitudeInfo();
        double departureLatitude= travelDisplayed.getAddressDeparture().getLatitudeInfo();

        double destinationLongitude = travelDisplayed.getSpotDestination().getLongitudeSpot();
        double destinationLatitude = travelDisplayed.getSpotDestination().getLatitudeSpot();

        googleMap.addMarker(new MarkerOptions().position(new LatLng(departureLatitude, departureLongitude)).title("Partenza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(destinationLatitude, destinationLongitude)).title("Arrivo"));

        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(departureLatitude,departureLongitude) , 7.0f) );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.modifySubMenu:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.navigationSubMenu:
                System.out.println("Opening navigation menu...");
                showMap(Uri.parse("geo:0,0?q= " + travelDisplayed.getSpotDestination().getLatitudeSpot() + "," + travelDisplayed.getSpotDestination().getLongitudeSpot() + "(Spot)"));
                return true;

            case R.id.deleteSubMenu:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.shareSubMenu:
                System.out.println("Opening sharing menu...");
                shareTravel();
                return true;

            case R.id.mailSubMenu:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void shareTravel(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
