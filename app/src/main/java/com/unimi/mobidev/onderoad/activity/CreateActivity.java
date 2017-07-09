package com.unimi.mobidev.onderoad.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.firebase.auth.FirebaseUser;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.adapter.PlaceAutocompleteAdapter;
import com.unimi.mobidev.onderoad.firebase.FirebaseUtils;
import com.unimi.mobidev.onderoad.fragment.DatePickerCreation;
import com.unimi.mobidev.onderoad.fragment.TimePickerCreation;
import com.unimi.mobidev.onderoad.model.AddressInfo;
import com.unimi.mobidev.onderoad.model.CarInfo;
import com.unimi.mobidev.onderoad.model.RawProvinceDict;
import com.unimi.mobidev.onderoad.model.RegionSpotDict;
import com.unimi.mobidev.onderoad.model.SpotInfo;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.model.User;
import com.unimi.mobidev.onderoad.other.LatLngManager;
import com.unimi.mobidev.onderoad.other.StreetAutoCompleteTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final double TEN_KM_RADIUS = 7071.00;

    private StreetAutoCompleteTextView streetDepartureAutocompleteView;
    private Button datePickerButton;
    private Button timePickerButton;

    private Spinner regionDestinationSpinner;
    private Spinner spotDestinationSpinner;

    private TextView priceLabelInfo;
    private TextView priceTextView;
    private RadioGroup passengersRadioGroup;
    private CheckBox outboundCheckBox;
    private RadioGroup surfboardRadioGroup;
    private Spinner carSupportTypeSpinner;
    private EditText noteText;

    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter streetAdapter;

    private LatLngManager currentLocation;

    private LatLngBounds currentLocationBounds;

    private TravelInfo newTravel;

    private GregorianCalendar todayDate;

    private CarInfo newCar;

    private AddressInfo meetingPoint;

    private User ownerTravel;

    private ArrayList<SpotInfo> selectedRegionSpot;

    private SpotInfo spotDetail;

    private int checkMod = 0;

    private String timeTravel = " ", dateTravel = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.travelInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_create);

        //Serve per inserire la freccia per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spotDetail = (SpotInfo) this.getIntent().getSerializableExtra("SpotData");

        /*String first_name = "", middle_name = "", last_name = "";

        appData = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        ownerTravel = new User(appData.getString("First name", first_name) + " " + appData.getString("Middle name", middle_name),appData.getString("Last name", last_name),appData.getString("ID", ID),appData.getString("Email", email));
        System.out.println(ownerTravel.toString());*/

        FirebaseUser currentUser = FirebaseUtils.getCurrentUser();

        ownerTravel = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(),FirebaseUtils.getFirebaseToken());

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, 0, this).addApi(Places.GEO_DATA_API).build();

        currentLocation = new LatLngManager(this.getApplicationContext());

        newTravel = new TravelInfo();
        newTravel.setOwnerTravel(ownerTravel);

        newCar = new CarInfo();

        meetingPoint = new AddressInfo();

        selectedRegionSpot = new ArrayList<>();

        todayDate = new GregorianCalendar();

        newTravel.setDateTimeDeparture(todayDate.getTimeInMillis());

        dateTravel = String.format(Locale.ITALIAN, "%02d", todayDate.get(Calendar.DAY_OF_MONTH) + 1) + " " + todayDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ITALIAN);

        timeTravel = String.format(Locale.ITALIAN, "%02d", todayDate.get(Calendar.HOUR_OF_DAY)) + ":" + String.format(Locale.ITALIAN, "%02d", todayDate.get(Calendar.MINUTE));

        //Departure Info

        currentLocationBounds = currentLocation.getLatLngBounds(CreateActivity.TEN_KM_RADIUS);

        streetDepartureAutocompleteView = (StreetAutoCompleteTextView) findViewById(R.id.streetAutoCompleteTextField);
        streetDepartureAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        streetAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, currentLocationBounds, null);
        streetDepartureAutocompleteView.setAdapter(streetAdapter);

        datePickerButton = (Button) findViewById(R.id.dateButton);
        datePickerButton.setText(dateTravel);
        datePickerButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //newTravel.updateDataDeparture(datePickerButton.getText().toString());
                dateTravel = datePickerButton.getText().toString();
            }
        });

        timePickerButton = (Button) findViewById(R.id.timeButton);
        timePickerButton.setText(timeTravel);
        timePickerButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //newTravel.updateTimeDeparture(timePickerButton.getText().toString());
                timeTravel = timePickerButton.getText().toString();
            }
        });

        //Destination Info

        int position = 0;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, RegionSpotDict.getKeys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regionDestinationSpinner = (Spinner) findViewById(R.id.destinationRegionSpinner);
        regionDestinationSpinner.setAdapter(adapter);

        if (spotDetail != null) {
            position = adapter.getPosition(spotDetail.getRegionSpot());
            regionDestinationSpinner.setSelection(position);
        }

        spotDestinationSpinner = (Spinner) findViewById(R.id.destinationSpotSpinner);
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
                    newTravel.setSpotDestination(selectedRegionSpot.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Car Info

        priceLabelInfo = (TextView) findViewById(R.id.priceLabel);
        priceLabelInfo.setOnTouchListener(touchForInfo);

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
                newTravel.setPriceTravel(Integer.valueOf(priceTextView.getText().toString()));
            }
        });

        passengersRadioGroup = (RadioGroup) findViewById(R.id.passengersRadioButtons);
        newCar.setPassengersNumber(4);

        outboundCheckBox = (CheckBox) findViewById(R.id.outboundCheckBox);

        surfboardRadioGroup = (RadioGroup) findViewById(R.id.surfboardRadioButtons);
        newCar.setSurfboardNumber(4);

        /*adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList("1","2","3","4","5"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        passeggersSpinner = (Spinner) findViewById(R.id.passengersSpinner);
        passeggersSpinner.setAdapter(adapter);

        passeggersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setPassengersNumber(Integer.valueOf(parentView.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        surfboardNumberSpinner = (Spinner) findViewById(R.id.surfboardNumberSpinner);
        surfboardNumberSpinner.setAdapter(adapter);

        surfboardNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setSurfboardNumber(Integer.valueOf(parentView.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        //TODO: Bisognerebbe modificare il numero di tavole trasportabili in base al tipo di supporto ed il numero di passeggeri
        ArrayAdapter<CharSequence> supportAdapter = ArrayAdapter.createFromResource(this, R.array.support_types, android.R.layout.simple_spinner_item);
        supportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        carSupportTypeSpinner = (Spinner) findViewById(R.id.carSupportTypeSpinner);
        carSupportTypeSpinner.setAdapter(supportAdapter);

        carSupportTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newCar.setSurfboardType(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        noteText = (EditText) findViewById(R.id.noteTextField);
        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newTravel.setNoteTravel(noteText.getText().toString());
            }
        });

        int a = 0;

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                Toast.makeText(this.getApplicationContext(), "I permessi sono necessari per migliorare\nl'efficienza dell'applicazione!", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    streetDepartureAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
                } else {
                    Toast.makeText(this, "I permessi sono necessari per migliorare\nl'efficienza dell'applicazione!", Toast.LENGTH_LONG).show();
                    streetDepartureAutocompleteView.setOnItemClickListener(null);
                }
            }
        }
    }

    public void datePickerListener(View v) {
        DialogFragment newFragment = new DatePickerCreation();
        newFragment.show(getSupportFragmentManager(), "datePickerCreation");
    }

    public void timePickerListener(View v) {
        DialogFragment newFragment = new TimePickerCreation();
        newFragment.show(getSupportFragmentManager(), "timePickerCreation");
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

            if (spotDetail != null) {
                int pos = adapter.getPosition(spotDetail.getTitle());
                spotSpinner.setSelection(pos);
            }
        }
    }

    private View.OnTouchListener touchForInfo = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                Toast.makeText(CreateActivity.this.getApplicationContext(),"Prova",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.passengersRadio1:
                if (checked)
                    newCar.setPassengersNumber(1);
                break;
            case R.id.passengersRadio2:
                if (checked)
                    newCar.setPassengersNumber(2);
                break;
            case R.id.passengersRadio3:
                if (checked)
                    newCar.setPassengersNumber(3);
                break;
            case R.id.passengersRadio4:
                if (checked)
                    newCar.setPassengersNumber(4);
                break;
            case R.id.boardRadio1:
                if (checked)
                    newCar.setSurfboardNumber(1);
                break;
            case R.id.boardRadio2:
                if (checked)
                    newCar.setSurfboardNumber(2);
                break;
            case R.id.boardRadio3:
                if (checked)
                    newCar.setSurfboardNumber(3);
                break;
            case R.id.boardRadio4:
                if (checked)
                    newCar.setSurfboardNumber(4);
                break;

        }
    }

    public void saveCarListener(View w) {
        int price = Integer.valueOf(priceTextView.getText().toString());

        if (price == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Attenzione!").setMessage(R.string.price_not_set_alert_message)
                    .setCancelable(false)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            if (checkMod > 0) {
                //newTravel.setPassengersTravel(carAvailablePlace);
                newTravel.setPassengersTravel(null);
                newTravel.setAddressDeparture(meetingPoint);
                newTravel.setPriceTravel(price);
                newTravel.setOutbound(outboundCheckBox.isChecked());
                newTravel.setCarTravel(newCar);
                newTravel.setNoteTravel(noteText.getText().toString());
                newTravel.updateDateTimeDeparture(dateTravel, timeTravel);

                String completeCreation = "Confermi la creazione del viaggio in partenza il giorno " + dateTravel + " alle ore " + timeTravel +
                        " da " + meetingPoint.getStreetInfo() + " e arrivo allo spot " + newTravel.getSpotDestination().getTitle() + " in " + newTravel.getSpotDestination().getRegionSpot() + "?";
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Terminata Creazione").setMessage(completeCreation)
                        .setCancelable(false)
                        .setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(CreateActivity.this.getApplicationContext(), R.string.toast_travel_creation, Toast.LENGTH_SHORT).show();

                                FirebaseUtils.getDatabaseReference("travels").push().setValue(newTravel);

                                finish();
                            }
                        })
                        .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Attenzione!").setMessage(R.string.not_complete_travel_alert_message)
                        .setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
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

            if (currentLocationBounds == null) {
                currentLocationBounds = currentLocation.getLatLngBounds(CreateActivity.TEN_KM_RADIUS);
            }

            try {
                selectedAddress = currentLocation.getAddressInfo(completeAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (selectedAddress != null) {

                if (selectedAddress.getFeatureName().equals(selectedAddress.getLocality())) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
                    builder.setMessage(selectedAddress.getLocality() + " " + getString(R.string.city_selection_alert_message))
                            .setCancelable(false)
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    streetDepartureAutocompleteView.setText("");
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    meetingPoint.setStreetInfo(completeAddress);
                    meetingPoint.setProvinceInfo(RawProvinceDict.getValue(selectedAddress.getSubAdminArea()));
                    meetingPoint.setLatitudeInfo(selectedAddress.getLatitude());
                    meetingPoint.setLongitudeInfo(selectedAddress.getLongitude());

                    System.out.println("Selected address: " + selectedAddress.toString());

                    checkMod++;
                }
            } else {
                Toast.makeText(getApplicationContext(), R.string.position_connection_error, Toast.LENGTH_SHORT).show();
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
    public void onConnectionFailed(ConnectionResult connectionResult) {
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
        builder.setMessage(R.string.creation_not_complete_alert_message)
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CreateActivity.this.finish();
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
