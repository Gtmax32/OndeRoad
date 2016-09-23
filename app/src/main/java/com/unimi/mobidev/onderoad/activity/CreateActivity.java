package com.unimi.mobidev.onderoad.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.fragment.DateFragment;
import com.unimi.mobidev.onderoad.fragment.TimeFragment;
import com.unimi.mobidev.onderoad.other.RegionProvinceDict;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {
    private Spinner regionDepartureSpinner;
    private Spinner provinceDepartureSpinner;
    private Spinner regionDestinationSpinner;
    private Spinner provinceDestinationSpinner;
    private Button datePickerButton;
    private Button timePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String todayText, nowText;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Calendar todayDate = Calendar.getInstance();

        todayText = todayDate.get(Calendar.DAY_OF_MONTH) + " " + todayDate.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.ITALIAN);
        nowText = todayDate.get(Calendar.HOUR_OF_DAY) + ":" + todayDate.get(Calendar.MINUTE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.infoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_create);

        //Departure Info

        regionDepartureSpinner = (Spinner) findViewById(R.id.departureRegionSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, RegionProvinceDict.getKeys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionDepartureSpinner.setAdapter(adapter);

        provinceDepartureSpinner = (Spinner) findViewById(R.id.departureProvinceSpinner);
        provinceDepartureSpinner.setEnabled(false);

        regionDepartureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerItemSelected(provinceDepartureSpinner,parentView,selectedItemView,position,id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        datePickerButton = (Button) findViewById(R.id.dateButton);
        datePickerButton.setText(todayText);

        timePickerButton = (Button) findViewById(R.id.timeButton);
        timePickerButton.setText(nowText);

        //Destination Info

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
}
