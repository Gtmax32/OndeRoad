package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;

/**
 * Created by Pc-Utente on 30/09/2016.
 */

public class AddressInfo implements Serializable{

    private String streetInfo;
    private String provinceInfo;
    private double latitudeInfo;
    private double longitudeInfo;

    public AddressInfo(String streetInfo, String provinceInfo, double latitudeInfo, double longitudeInfo) {
        this.streetInfo = streetInfo;
        this.provinceInfo = provinceInfo;
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

    public String getProvinceInfo() {
        return provinceInfo;
    }

    public void setProvinceInfo(String provinceInfo) {
        this.provinceInfo = provinceInfo;
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
                "\nProvince: " + this.provinceInfo +
                "\nLatitude: " + this.latitudeInfo +
                "\nLongitude: " + this.longitudeInfo;
    }
}
