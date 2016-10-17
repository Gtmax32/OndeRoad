package com.unimi.mobidev.onderoad.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Pc-Utente on 30/09/2016.
 */

public class SpotInfo implements Serializable {

    private String nameSpot;
    private double latitudeSpot;
    private double longitudeSpot;
    private int ratingSpot;
    private String descriptionSpot;

    public SpotInfo(String nameSpot, double latitudeSpot, double longitudeSpot, int ratingSpot, String descriptionSpot) {
        this.nameSpot = nameSpot;
        this.latitudeSpot = latitudeSpot;
        this.longitudeSpot = longitudeSpot;
        this.ratingSpot = ratingSpot;
        this.descriptionSpot = descriptionSpot;
    }

    public String getNameSpot() {
        return nameSpot;
    }

    public void setNameSpot(String nameSpot) {
        this.nameSpot = nameSpot;
    }

    public LatLng getLatitudeSpot() {
        return new LatLng(this.latitudeSpot,this.longitudeSpot);
    }

    public void setLatitudeSpot(double latitudeSpot) {
        this.latitudeSpot = latitudeSpot;
    }

    public double getLongitudeSpot() {
        return longitudeSpot;
    }

    public void setLongitudeSpot(double longitudeSpot) {
        this.longitudeSpot = longitudeSpot;
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
