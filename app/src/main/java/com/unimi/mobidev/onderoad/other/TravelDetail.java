package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unimi.mobidev.onderoad.R;

/**
 * Created by Giuseppe Fabio Trentadue on 01/10/2016.
 */

public class TravelDetail extends LinearLayout {
    private View rootView;

    private TextView itineraryText;
    private TextView dateTimeText;
    private TextView priceText;

    public TravelDetail(Context context) {
        super(context);
        init(context);
    }

    public TravelDetail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.travel_detail, this);

        itineraryText = (TextView) findViewById(R.id.itineraryTravel);
        dateTimeText = (TextView) findViewById(R.id.dateTimeTravel);
        priceText = (TextView) findViewById(R.id.priceTravel);
    }

    public TextView getItineraryText() {
        return itineraryText;
    }

    public void setItineraryText(TextView itineraryText) {
        this.itineraryText = itineraryText;
    }

    public TextView getDateTimeText() {
        return dateTimeText;
    }

    public void setDateTimeText(TextView dateTimeText) {
        this.dateTimeText = dateTimeText;
    }

    public TextView getPriceText() {
        return priceText;
    }

    public void setPriceText(TextView priceText) {
        this.priceText = priceText;
    }

    private void setItineraryText(String itinerary) {
        itineraryText.setText(itinerary);
    }

    private void setdateTimeText(String dateTime) {
        dateTimeText.setText(dateTime);
    }

    private void setpriceText(String price) {
        priceText.setText(price);
    }
}
