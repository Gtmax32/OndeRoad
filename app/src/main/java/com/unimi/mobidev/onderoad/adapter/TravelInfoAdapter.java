package com.unimi.mobidev.onderoad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

/**
 * Created by Giuseppe Fabio Trentadue on 01/10/2016.
 */

public class TravelInfoAdapter extends ArrayAdapter<TravelInfo> {
    private ArrayList<TravelInfo> listTravel;
    private int resourceLayoutTravel;
    private Context context;

    public TravelInfoAdapter(Context context, int resource, ArrayList<TravelInfo> list) {
        super(context, resource);
        this.context = context;
        this.listTravel = list;
        this.resourceLayoutTravel = resource;
    }

    public TravelInfoAdapter(Context context, int resource, int textViewResourceId, ArrayList<TravelInfo> list) {
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
    public TravelInfo getItem(int position) {
        return listTravel.get(position);
    }

    public void addItem(TravelInfo travel){
        if(travel != null)
            listTravel.add(travel);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TravelDetail holder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            v = inflater.inflate(R.layout.travel_detail, null);

            holder = new TravelDetail(this.context);
            holder.setItineraryText((TextView) v.findViewById(R.id.itineraryTravel));
            holder.setDateTimeText((TextView) v.findViewById(R.id.dateTimeTravel));
            holder.setPriceText((TextView) v.findViewById(R.id.priceTravel));
            holder.setTag(holder);
        }
        else{
            holder = (TravelDetail) v.getTag();
        }

        return v;
    }
}
