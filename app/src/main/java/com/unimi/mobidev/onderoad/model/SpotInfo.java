package com.unimi.mobidev.onderoad.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Pc-Utente on 30/09/2016.
 */

public class SpotInfo {

    private String nameSpot;
    private LatLng latLngSpot;
    private int ratingSpot;
    private String descriptionSpot;

    public SpotInfo(String nameSpot, LatLng latLngSpot, int ratingSpot, String descriptionSpot) {
        this.nameSpot = nameSpot;
        this.latLngSpot = latLngSpot;
        this.ratingSpot = ratingSpot;
        this.descriptionSpot = descriptionSpot;
    }

    public String getNameSpot() {
        return nameSpot;
    }

    public void setNameSpot(String nameSpot) {
        this.nameSpot = nameSpot;
    }

    public LatLng getLatLngSpot() {
        return latLngSpot;
    }

    public void setLatLngSpot(LatLng latLngSpot) {
        this.latLngSpot = latLngSpot;
    }

    public int getRatingSpot() {
        return ratingSpot;
    }

    public void setRatingSpot(int ratingSpot) {
        this.ratingSpot = ratingSpot;
    }

    public String getDescriptionSpot() {
        return descriptionSpot;
    }

    public void setDescriptionSpot(String descriptionSpot) {
        this.descriptionSpot = descriptionSpot;
    }
}
