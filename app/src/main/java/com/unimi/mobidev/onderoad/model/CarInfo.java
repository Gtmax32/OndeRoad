package com.unimi.mobidev.onderoad.model;

/**
 * Created by Giuseppe on 26/09/2016.
 */

public class CarInfo {

    private int passengersNumber;
    private int surfboardNumber;
    private String surfboardType;

    public CarInfo(int passengersNumber, int surfboardNumber, String surfboardType) {
        this.passengersNumber = passengersNumber;
        this.surfboardNumber = surfboardNumber;
        this.surfboardType = surfboardType;
    }

    public CarInfo(){}

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    public int getSurfboardNumber() {
        return surfboardNumber;
    }

    public void setSurfboardNumber(int surfboardNumber) {
        this.surfboardNumber = surfboardNumber;
    }

    public String getSurfboardType() {
        return surfboardType;
    }

    public void setSurfboardType(String surfboardType) {
        this.surfboardType = surfboardType;
    }

    public String toString(){
        return "CarInfo: " +
               "\nPassengers number: " + this.passengersNumber +
               "\nSurfboard number: " + this.surfboardNumber +
               "\nSurfboard type: " + this.surfboardType;
    }
}
