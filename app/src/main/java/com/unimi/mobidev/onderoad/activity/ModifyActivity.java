package com.unimi.mobidev.onderoad.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.unimi.mobidev.onderoad.adapter.PlaceAutocompleteAdapter;
import com.unimi.mobidev.onderoad.fragment.DatePickerModification;
import com.unimi.mobidev.onderoad.fragment.TimePickerModification;
import com.unimi.mobidev.onderoad.model.AddressInfo;
import com.unimi.mobidev.onderoad.model.RawProvinceDict;
import com.unimi.mobidev.onderoad.model.RegionSpotDict;
import com.unimi.mobidev.onderoad.model.SpotInfo;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.LatLngManager;
import com.unimi.mobidev.onderoad.other.StreetAutoCompleteTextView;

import java.io.IOException;
import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final double TEN_KM_RADIUS = 7071.00;

    private StreetAutoCompleteTextView streetDepartureAutocompleteView;
    private Button datePickerButton;
    private Button timePickerButton;

    private Spinner regionDestinationSpinner;
    private Spinner spotDestinationSpinner;

    private EditText noteText;

    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter streetAdapter;

    private LatLngManager currentLocation;

    private LatLngBounds currentLocationBounds;

    private TravelInfo toModifyTravel;

    private AddressInfo meetingPoint;

    private ArrayList<SpotInfo> selectedRegionSpot;

    private String timeTravel = " ", dateTravel = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.modifyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.modify_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toModifyTravel = (TravelInfo) this.getIntent().getSerializableExtra("TravelInfo");

        System.out.println("In ModifyActivity: " + toModifyTravel.toString());

        meetingPoint = new AddressInfo();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, 0, this).addApi(Places.GEO_DATA_API).build();

        currentLocation = new LatLngManager(this.getApplicationContext());

        currentLocationBounds = currentLocation.getLatLngBounds(ModifyActivity.TEN_KM_RADIUS);

        streetDepartureAutocompleteView = (StreetAutoCompleteTextView) findViewById(R.id.modifyStreetAutoCompleteTextField);
        streetDepartureAutocompleteView.setText(toModifyTravel.getAddressDeparture().getStreetInfo());
        streetDepartureAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        streetAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, currentLocationBounds, null);
        streetDepartureAutocompleteView.setAdapter(streetAdapter);

        datePickerButton = (Button) findViewById(R.id.modifyDateButton);
        datePickerButton.setText(toModifyTravel.formatDataDeparture());
        datePickerButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //toModifyTravel.updateDataDeparture(datePickerButton.getText().toString());
                dateTravel = datePickerButton.getText().toString();
            }
        });

        timePickerButton = (Button) findViewById(R.id.modifyTimeButton);
        timePickerButton.setText(toModifyTravel.formatTimeDeparture());
        timePickerButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //toModifyTravel.updateTimeDeparture(timePickerButton.getText().toString());
                timeTravel = timePickerButton.getText().toString();
            }
        });


        selectedRegionSpot = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, RegionSpotDict.getKeys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regionDestinationSpinner = (Spinner) findViewById(R.id.modifyDestinationRegionSpinner);
        regionDestinationSpinner.setAdapter(adapter);
        int position = adapter.getPosition(toModifyTravel.getSpotDestination().getRegionSpot());
        regionDestinationSpinner.setSelection(position);

        spotDestinationSpinner = (Spinner) findViewById(R.id.modifyDestinationProvinceSpinner);
        spotDestinationSpinner.setEnabled(false);

        regionDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerSpotItemSelected(spotDestinationSpinner, parentView, selectedItemView, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spotDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (selectedRegionSpot != null && selectedRegionSpot.size() > 0)
                    toModifyTravel.setSpotDestination(selectedRegionSpot.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        noteText = (EditText) findViewById(R.id.modifyNoteTextField);
        noteText.setText(toModifyTravel.getNoteTravel());

        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                toModifyTravel.setNoteTravel(noteText.getText().toString());
            }
        });
    }

    public void datePickerListener(View v) {
        DialogFragment newFragment = new DatePickerModification();
        newFragment.show(getSupportFragmentManager(), "datePickerModification");
    }

    public void timePickerListener(View v) {
        DialogFragment newFragment = new TimePickerModification();
        newFragment.show(getSupportFragmentManager(), "timePickerModification");
    }

    private void spinnerSpotItemSelected(Spinner spotSpinner, AdapterView<?> parentView, View selectedItemView, int position, long id) {
        if (!spotSpinner.isEnabled())
            spotSpinner.setEnabled(true);

        ArrayAdapter<String> adapter = null;
        selectedRegionSpot = RegionSpotDict.getElemFromKey(parentView.getItemAtPosition(position).toString());
        ArrayList<String> spotNameList = RegionSpotDict.getNameListFromKey(parentView.getItemAtPosition(position).toString());

        if (spotNameList != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spotNameList);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spotSpinner.setAdapter(adapter);

            int spotPosition = adapter.getPosition(toModifyTravel.getSpotDestination().getTitle());
            spotSpinner.setSelection(spotPosition);
        }
    }

    public void modifyCarListener(View w) {
        if (!dateTravel.equals(" ") && timeTravel.equals(" ")){

        }
        System.out.println("I'm editing the travel..." + toModifyTravel.toString());

        Intent intent = this.getIntent();
        intent.putExtra("TravelInfo", toModifyTravel);

        this.setResult(RESULT_OK, intent);
        Toast.makeText(this.getApplicationContext(), "Travel modified!", Toast.LENGTH_SHORT).show();
        finish();
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

            String completeAddress = item.getPrimaryText(null).toString() + " " + item.getSecondaryText(null).toString();
            Address selectedAddress = null;
            try {
                selectedAddress = currentLocation.getAddressInfo(completeAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (selectedAddress != null) {
                meetingPoint.setStreetInfo(completeAddress);
                meetingPoint.setProvinceInfo(RawProvinceDict.getValue(selectedAddress.getSubAdminArea()));
                meetingPoint.setLatitudeInfo(selectedAddress.getLatitude());
                meetingPoint.setLongitudeInfo(selectedAddress.getLongitude());
                toModifyTravel.setAddressDeparture(meetingPoint);
            } else {
                //TODO: Parametrizzare messaggi, d'errore e non.
                Toast.makeText(getApplicationContext(), "Connection problem!\nCheck you internet connection.", Toast.LENGTH_SHORT).show();
            }

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            //Toast.makeText(getApplicationContext(), "Clicked: " + primaryText, Toast.LENGTH_SHORT).show();
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.incomplete_modify_alert_message)
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyActivity.this.finish();
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
}
