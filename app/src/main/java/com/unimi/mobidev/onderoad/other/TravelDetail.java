package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.TravelInfo;

/**
 * Created by Giuseppe Fabio Trentadue on 01/10/2016.
 */

public class TravelDetail extends LinearLayout {
    private TravelInfo currentTravel;
    private String travelKey;

    private View rootView;

    private ImageView passengerDriverImage;
    private TextView itineraryDepartureText;
    private TextView itineraryDestinationText;
    private TextView dateTimeText;
    private TextView priceText;

    private boolean isOwner;

    public TravelDetail(Context context, TravelInfo travelInfo, String travelKey) {
        super(context);
        init(context, travelInfo, travelKey);
    }

    public TravelDetail(Context context, AttributeSet attributeSet, TravelInfo travelInfo, String travelKey) {
        super(context, attributeSet);
        init(context, travelInfo, travelKey);
    }

    private void init(Context context, TravelInfo travelInfo, String travelKey) {
        String date, time, departure, destination;
        int price;

        this.rootView = inflate(context, R.layout.travel_detail_layout, this);

        this.isOwner = FirebaseAuth.getInstance().getCurrentUser().getUid().equals(travelInfo.getOwnerTravel().getIdUser());

        this.passengerDriverImage = (ImageView) rootView.findViewById(R.id.carDetailImage);

        if (this.isOwner) {
            this.passengerDriverImage.setImageResource(R.drawable.ic_action_driver);
            System.out.println("Is Owner!");
        }
        else {
            this.passengerDriverImage.setImageResource(R.drawable.ic_action_passenger);
            System.out.println("Is Passenger!");
        }

        this.dateTimeText = (TextView) rootView.findViewById(R.id.dateTimeTravel);
        this.itineraryDepartureText = (TextView) rootView.findViewById(R.id.itineraryTravelDeparture);
        this.itineraryDestinationText = (TextView) rootView.findViewById(R.id.itineraryTravelDestination);
        this.priceText = (TextView) rootView.findViewById(R.id.priceTravel);

        this.currentTravel = travelInfo;

        if (this.currentTravel != null) {
            date = this.currentTravel.formatDataDeparture();
            time = this.currentTravel.formatTimeDeparture();
            departure = this.currentTravel.getAddressDeparture().getProvinceInfo();
            destination = this.currentTravel.getSpotDestination().getTitle();
            price = this.currentTravel.getPriceTravel();

            this.dateTimeText.setText(date + " " + time);
            this.itineraryDepartureText.setText(departure);
            this.itineraryDestinationText.setText(destination);
            this.priceText.setText(price + "");
        }

        this.travelKey = travelKey;

        this.passengerDriverImage.postInvalidate();

    }

    public void setItineraryDepartureText(TextView itineraryDepartureText) {
        this.itineraryDepartureText = itineraryDepartureText;
    }

    public void setItineraryDestinationText(TextView itineraryDestinationText) {
        this.itineraryDestinationText = itineraryDestinationText;
    }

    public void setDateTimeText(TextView dateTimeText) {
        this.dateTimeText = dateTimeText;
    }

    public void setPriceText(TextView priceText) {
        this.priceText = priceText;
    }

    public String getItineraryDepartureText() {
        return itineraryDepartureText.getText().toString();
    }

    public void setItineraryDepartureText(String newText) {
        this.itineraryDepartureText.setText(newText);
    }

    public String getItineraryDestinationText() {
        return itineraryDestinationText.getText().toString();
    }

    public void setItineraryDestinationText(String newText) {
        this.itineraryDestinationText.setText(newText);
    }

    public String getDateTimeText() {
        return dateTimeText.getText().toString();
    }

    public void setDateTimeText(String newText) {
        this.dateTimeText.setText(newText);
    }

    public String getPriceText() {
        return priceText.getText().toString();
    }

    public void setPriceText(String newText) {
        this.priceText.setText(newText);
    }

    public TravelInfo getCurrentTravel() {
        return currentTravel;
    }

    public void setCurrentTravel(TravelInfo currentTravel) {
        this.currentTravel = currentTravel;
    }

    public String getTravelKey() {
        return travelKey;
    }

    public void setTravelKey(String travelKey) {
        this.travelKey = travelKey;
    }

    public String toString(){
        return "TravelDetail: " +
                "\nDate&Time : " + this.getDateTimeText() +
                "\nDeparture: " + this.getItineraryDepartureText() +
                "\nDestination: " + this.getItineraryDestinationText() +
                "\nPrice: " + this.getPriceText();
    }
}
