package com.unimi.mobidev.onderoad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.other.TravelDetail;

import java.util.ArrayList;

/**
 * Created by Pc-Utente on 01/10/2016.
 */

public class TravelInfoAdapter extends ArrayAdapter<TravelDetail> {
    private ArrayList<TravelDetail> listTravel;
    private int resourceLayoutTravel;
    private Context context;

    public TravelInfoAdapter(Context context, int resource, ArrayList<TravelDetail> list) {
        super(context, resource);
        this.context = context;
        this.listTravel = list;
        this.resourceLayoutTravel = resource;
    }

    public TravelInfoAdapter(Context context, int resource, int textViewResourceId, ArrayList<TravelDetail> list) {
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

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TravelDetail holder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            v = inflater.inflate(R.layout.travel_detail, null);

            holder = new TravelDetail(this.context);
        }

        return v;
    }
}
