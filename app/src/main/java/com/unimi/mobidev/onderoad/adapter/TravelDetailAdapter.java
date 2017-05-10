package com.unimi.mobidev.onderoad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

/**
 * Created by Giuseppe Fabio Trentadue on 01/10/2016.
 */

public class TravelDetailAdapter extends ArrayAdapter<TravelDetail> {
    private ArrayList<TravelDetail> listTravel;
    private int resourceLayoutTravel;
    private Context context;

    public TravelDetailAdapter(Context context, int resource, ArrayList<TravelDetail> list) {
        super(context, resource);
        this.context = context;
        this.listTravel = list;
        this.resourceLayoutTravel = resource;
    }

    public TravelDetailAdapter(Context context, int resource, int textViewResourceId, ArrayList<TravelDetail> list) {
        super(context, resource, textViewResourceId);
        this.context = context;
        this.listTravel = list;
        this.resourceLayoutTravel = resource;
    }

    @Override
    public int getCount() {
        return listTravel.size();
    }

    @Override
    public TravelDetail getItem(int position) {
        return listTravel.get(position);
    }

    public void addItem(TravelDetail travel) {
        if (travel != null)
            listTravel.add(travel);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TravelDetail holder;
        String dateTime, departure, destination;
        int price;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            v = inflater.inflate(R.layout.travel_detail_layout, null);

            /*holder = new TravelDetail(this.context);
            holder.setItineraryDepartureText((TextView) v.findViewById(R.id.itineraryTravelDeparture));
            holder.setItineraryDestinationText((TextView) v.findViewById(R.id.itineraryTravelDestination));
            holder.setDateTimeText((TextView) v.findViewById(R.id.dateTimeTravel));
            holder.setPriceText((TextView) v.findViewById(R.id.priceTravel));
            holder.setTag(holder);*/
        }

        holder = this.getItem(position);

        TextView dateTimeText = (TextView) v.findViewById(R.id.dateTimeTravel);
        TextView itineraryDepartureText = (TextView) v.findViewById(R.id.itineraryTravelDeparture);
        TextView itineraryDestinationText = (TextView) v.findViewById(R.id.itineraryTravelDestination);
        TextView priceText = (TextView) v.findViewById(R.id.priceTravel);

        dateTime = holder.getDateTimeText();
        departure = holder.getItineraryDepartureText();
        destination = holder.getItineraryDestinationText();
        price = Integer.parseInt(holder.getPriceText());

        dateTimeText.setText(dateTime);
        itineraryDepartureText.setText(departure);
        itineraryDestinationText.setText(destination);
        priceText.setText(price + "");

        return v;
    }


}
