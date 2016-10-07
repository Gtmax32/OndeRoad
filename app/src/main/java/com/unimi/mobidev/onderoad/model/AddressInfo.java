package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;

/**
 * Created by Pc-Utente on 30/09/2016.
 */

public class AddressInfo implements Serializable{

    private String streetInfo;
    private double latitudeInfo;
    private double longitudeInfo;

    public AddressInfo(String streetInfo, double latitudeInfo, double longitudeInfo) {
        this.streetInfo = streetInfo;
        this.latitudeInfo = latitudeInfo;
        this.longitudeInfo = longitudeInfo;
    }

    public AddressInfo() {
    }

    public String getStreetInfo() {
        return streetInfo;
    }

    public void setStreetInfo(String streetInfo) {
        this.streetInfo = streetInfo;
    }

    public double getLatitudeInfo() {
        return latitudeInfo;
    }

    public void setLatitudeInfo(double latitudeInfo) {
        this.latitudeInfo = latitudeInfo;
    }

    public double getLongitudeInfo() {
        return longitudeInfo;
    }

    public void setLongitudeInfo(double longitudeInfo) {
        this.longitudeInfo = longitudeInfo;
    }

    public String toString(){
        return "AddressInfo:" +
                "\nStreet: " + this.streetInfo +
                "\nLatitude: " + this.latitudeInfo +
                "\nLongitude: " + this.longitudeInfo;
    }
}
