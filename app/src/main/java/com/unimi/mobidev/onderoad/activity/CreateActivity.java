package com.unimi.mobidev.onderoad.activity;

import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.fragment.DateFragment;
import com.unimi.mobidev.onderoad.fragment.TimeFragment;
import com.unimi.mobidev.onderoad.model.CarInfo;
import com.unimi.mobidev.onderoad.model.RegionProvinceDict;
import com.unimi.mobidev.onderoad.other.LatLngManager;
import com.unimi.mobidev.onderoad.other.PlaceAutocompleteAdapter;
import com.unimi.mobidev.onderoad.other.StreetAutoCompleteTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final double TEN_KM_RADIUS = 7071.00;

    private StreetAutoCompleteTextView streetDepartureAutocompleteView;
    private Button datePickerButton;
    private Button timePickerButton;

    private Spinner regionDestinationSpinner;
    private Spinner provinceDestinationSpinner;

    private TextView priceTextView;
    private Spinner passeggersSpinner;
    private CheckBox outboundCheckBox;
    private Spinner surfboardNumberSpinner;
    private Spinner carSupportTypeSpinner;

    private CarInfo newCar;

    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter streetAdapter;

    private LatLngManager currentLocation;

    private LatLngBounds currentLocationBounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String todayText, nowText;
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, 0 /* clientId */, this).addApi(Places.GEO_DATA_API).build();

        setContentView(R.layout.activity_create);

        currentLocation = new LatLngManager(this.getApplicationContext());

        newCar = new CarInfo();

        Calendar todayDate = Calendar.getInstance();

        todayText = todayDate.get(Calendar.DAY_OF_MONTH) + " " + todayDate.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.ITALIAN);
        nowText = todayDate.get(Calendar.HOUR_OF_DAY) + ":" + todayDate.get(Calendar.MINUTE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.infoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_create);

        //Departure Info

        currentLocationBounds = currentLocation.getLatLngBounds(CreateActivity.TEN_KM_RADIUS);

        streetDepartureAutocompleteView = (StreetAutoCompleteTextView) findViewById(R.id.streetAutoCompleteTextField);
        streetDepartureAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        streetAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, currentLocationBounds,null);
        streetDepartureAutocompleteView.setAdapter(streetAdapter);

        datePickerButton = (Button) findViewById(R.id.dateButton);
        datePickerButton.setText(todayText);
        datePickerButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newCar.setDepartureDate(datePickerButton.getText().toString());
            }
        });

        timePickerButton = (Button) findViewById(R.id.timeButton);
        timePickerButton.setText(nowText);
        timePickerButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newCar.setDepartureTime(timePickerButton.getText().toString());
            }
        });

        //Destination Info

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, RegionProvinceDict.getKeys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regionDestinationSpinner = (Spinner) findViewById(R.id.destinationRegionSpinner);

        // TODO: Inserire regioni e province degli spot
        //adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, RegionProvinceDict.getKeys());
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionDestinationSpinner.setAdapter(adapter);

        provinceDestinationSpinner = (Spinner) findViewById(R.id.destinationProvinceSpinner);
        provinceDestinationSpinner.setEnabled(false);

        regionDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerItemSelected(provinceDestinationSpinner,parentView,selectedItemView,position,id);

                newCar.setRegionDestination(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        provinceDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setProvinceDestination(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Car Info

        priceTextView = (TextView) findViewById(R.id.priceTextView);
        priceTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newCar.setPriceCarInfo(Integer.valueOf(priceTextView.getText().toString()));
            }
        });

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList("1","2","3","4","5"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        passeggersSpinner = (Spinner) findViewById(R.id.passengersSpinner);
        passeggersSpinner.setAdapter(adapter);

        passeggersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setPassengersNumberCarInfo(Integer.valueOf(parentView.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        outboundCheckBox = (CheckBox) findViewById(R.id.outboundCheckBox);

        surfboardNumberSpinner = (Spinner) findViewById(R.id.surfboardNumberSpinner);
        surfboardNumberSpinner.setAdapter(adapter);

        surfboardNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setSurfboardNumberCarInfo(Integer.valueOf(parentView.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //TODO: Bisognerebbe modificare il numero di tavole trasportabili in base al tipo di supporto ed il numero di passeggeri
        ArrayAdapter<CharSequence> supportAdapter = ArrayAdapter.createFromResource(this,R.array.support_types,android.R.layout.simple_spinner_item);
        supportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        carSupportTypeSpinner = (Spinner) findViewById(R.id.carSupportTypeSpinner);
        carSupportTypeSpinner.setAdapter(supportAdapter);

        carSupportTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setSurfboardTypeCarInfo(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void datePickerListener(View v){
        DialogFragment newFragment = new DateFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void timePickerListener(View v){
        DialogFragment newFragment = new TimeFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void spinnerItemSelected(Spinner provinceSpinner, AdapterView<?> parentView, View selectedItemView, int position, long id){
        if (!provinceSpinner.isEnabled())
            provinceSpinner.setEnabled(true);

        ArrayAdapter<String> adapter = null;
        ArrayList<String> provinceList = RegionProvinceDict.getElemFromKey(parentView.getItemAtPosition(position).toString());

        if(provinceList != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinceList);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            provinceSpinner.setAdapter(adapter);
        }
    }

    public void saveCarListener(View w){
        newCar.setOutboundCarInfo(outboundCheckBox.isChecked());
        System.out.println(newCar.toString());
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            final AutocompletePrediction item = streetAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);

            String completeAddress = item.getPrimaryText(null).toString() + item.getSecondaryText(null).toString();
            Address selectedAddress = null;
            try {
                selectedAddress = currentLocation.getAddressInfo(completeAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Admin Area: " + selectedAddress.getSubAdminArea() +
                                "\nCountry Code: " + selectedAddress.getCountryCode() +
                                "\nCountry Name: " + selectedAddress.getCountryName() +
                                "\nFeature Name: " + selectedAddress.getFeatureName() +
                                "\nLocality: " + selectedAddress.getLocality() +
                                "\nPostal Code: " + selectedAddress.getPostalCode());

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            Toast.makeText(getApplicationContext(), "Clicked: " + primaryText, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                //Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            /*// Display the third party attributions if set.
            final CharSequence thirdPartyAttribution = places.getAttributions();
            if (thirdPartyAttribution == null) {
                mPlaceDetailsAttribution.setVisibility(View.GONE);
            } else {
                mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
                mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
            }

            //Log.i(TAG, "Place details received: " + place.getName());*/

            places.release();
        }
    };

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),Toast.LENGTH_SHORT).show();
    }
}
