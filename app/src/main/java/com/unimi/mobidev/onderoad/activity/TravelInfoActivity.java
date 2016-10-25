package com.unimi.mobidev.onderoad.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.TravelInfo;

public class TravelInfoActivity extends AppCompatActivity {

    private TravelInfo travelDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.createInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_travel);

        travelDisplayed = (TravelInfo) this.getIntent().getSerializableExtra("TravelInfo");
    }
}
