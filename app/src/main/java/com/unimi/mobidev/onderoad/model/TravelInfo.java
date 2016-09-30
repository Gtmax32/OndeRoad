package com.unimi.mobidev.onderoad.model;

import java.util.ArrayList;

/**
 * Created by Pc-Utente on 30/09/2016.
 */

public class TravelInfo {

    private AddressInfo addressDeparture;
    private String dataDeparture;
    private String timeDeparture;
    private String regionDestination;
    private String provinceDestination;
    //TODO: Dopo aver inserito gli spot, sostituire la provincia con SpotInfo
    //private SpotInfo spotDestination;
    private int priceTravel;
    private CarInfo carTravel;
    private boolean isOutbound;
    private String noteTravel;
    private User ownerTravel;
    private ArrayList<User> passengersTravel;

    public TravelInfo() {}

    public AddressInfo getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(AddressInfo addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public String getDataDeparture() {
        return dataDeparture;
    }

    public void setDataDeparture(String dataDeparture) {
        this.dataDeparture = dataDeparture;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getRegionDestination() {
        return regionDestination;
    }

    public void setRegionDestination(String regionDestination) {
        this.regionDestination = regionDestination;
    }

    public String getProvinceDestination() {
        return provinceDestination;
    }

    public void setProvinceDestination(String provinceDestination) {
        this.provinceDestination = provinceDestination;
    }

    public int getPriceTravel() {
        return priceTravel;
    }

    public void setPriceTravel(int priceTravel) {
        this.priceTravel = priceTravel;
    }

    public CarInfo getCarTravel() {
        return carTravel;
    }

    public void setCarTravel(CarInfo carTravel) {
        this.carTravel = carTravel;
    }

    public boolean isOutbound() {
        return isOutbound;
    }

    public void setOutbound(boolean outbound) {
        isOutbound = outbound;
    }

    public String getNoteTravel() {
        return noteTravel;
    }

    public void setNoteTravel(String noteTravel) {
        this.noteTravel = noteTravel;
    }

    public User getOwnerTravel() {
        return ownerTravel;
    }

    public void setOwnerTravel(User ownerTravel) {
        this.ownerTravel = ownerTravel;
    }

    public ArrayList<User> getPassengersTravel() {
        return passengersTravel;
    }

    public void setPassengersTravel(ArrayList<User> passengersTravel) {
        this.passengersTravel = passengersTravel;
    }

    public String toString(){
        return "TravelInfo: " +
               "\nAddress info: " + this.addressDeparture.toString() +
               "\nData: " + this.dataDeparture +
               "\nTime: " + this.timeDeparture +
               "\nRegion: " + this.regionDestination +
               "\nProvince: " + this.provinceDestination +
               "\nPrice: " + this.priceTravel +
               "\nCar: " + this.carTravel.toString() +
               "\nOutbound: " + this.isOutbound +
               "\nNote: " + this.noteTravel +
               "\nUser: " + this.ownerTravel.toString();
    }
}
