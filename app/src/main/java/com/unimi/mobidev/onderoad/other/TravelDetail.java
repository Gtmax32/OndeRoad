package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unimi.mobidev.onderoad.R;

/**
 * Created by Pc-Utente on 01/10/2016.
 */

public class TravelDetail extends LinearLayout {
    private View rootView;

    private TextView itineraryText;
    private TextView dateTimeText;
    private TextView priceText;

    public TravelDetail(Context context){
        super(context);
        init(context);
    }

    public TravelDetail(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.travel_detail,this);

        itineraryText = (TextView) findViewById(R.id.itineraryTravel);
        dateTimeText = (TextView) findViewById(R.id.dateTimeTravel);
        priceText = (TextView) findViewById(R.id.priceTravel);
    }

    private void setItineraryText(String itinerary){
        itineraryText.setText(itinerary);
    }

    private void setdateTimeText(String dateTime){
        dateTimeText.setText(dateTime);
    }

    private void setpriceText(String price){
        priceText.setText(price);
    }
}
