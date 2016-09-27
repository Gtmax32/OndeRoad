package com.unimi.mobidev.onderoad.model;

/**
 * Created by Giuseppe on 26/09/2016.
 */

public class CarInfo {

    private String regionDeparture;
    private String provinceDeparture;
    private String meetingPoint;
    private String departureDate;
    private String departureTime;

    private String regionDestination;
    private String provinceDestination;

    private int priceCarInfo;
    private int passengersNumberCarInfo;
    private boolean outboundCarInfo;
    private int surfboardNumberCarInfo;
    private String surfboardTypeCarInfo;

    public CarInfo(){}

    public CarInfo(String regionDeparture, String provinceDeparture, String meetingPoint, String departureDate, String departureTime, String regionDestination, String provinceDestination, int priceCarInfo, int passengersNumberCarInfo, boolean outboundCarInfo, int surfboardNumberCarInfo, String surfboardTypeCarInfo) {
        this.regionDeparture = regionDeparture;
        this.provinceDeparture = provinceDeparture;
        this.meetingPoint = meetingPoint;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.regionDestination = regionDestination;
        this.provinceDestination = provinceDestination;
        this.priceCarInfo = priceCarInfo;
        this.passengersNumberCarInfo = passengersNumberCarInfo;
        this.outboundCarInfo = outboundCarInfo;
        this.surfboardNumberCarInfo = surfboardNumberCarInfo;
        this.surfboardTypeCarInfo = surfboardTypeCarInfo;
    }

    public String getRegionDeparture() {
        return regionDeparture;
    }

    public void setRegionDeparture(String regionDeparture) {
        this.regionDeparture = regionDeparture;
    }

    public String getProvinceDeparture() {
        return provinceDeparture;
    }

    public void setProvinceDeparture(String provinceDeparture) {
        this.provinceDeparture = provinceDeparture;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
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

    public int getPriceCarInfo() {
        return priceCarInfo;
    }

    public void setPriceCarInfo(int priceCarInfo) {
        this.priceCarInfo = priceCarInfo;
    }

    public int getPassengersNumberCarInfo() {
        return passengersNumberCarInfo;
    }

    public void setPassengersNumberCarInfo(int passengersNumberCarInfo) {
        this.passengersNumberCarInfo = passengersNumberCarInfo;
    }

    public boolean isOutboundCarInfo() {
        return outboundCarInfo;
    }

    public void setOutboundCarInfo(boolean outboundCarInfo) {
        this.outboundCarInfo = outboundCarInfo;
    }

    public int getSurfboardNumberCarInfo() {
        return surfboardNumberCarInfo;
    }

    public void setSurfboardNumberCarInfo(int surfboardNumberCarInfo) {
        this.surfboardNumberCarInfo = surfboardNumberCarInfo;
    }

    public String getSurfboardTypeCarInfo() {
        return surfboardTypeCarInfo;
    }

    public void setSurfboardTypeCarInfo(String surfboardTypeCarInfo) {
        this.surfboardTypeCarInfo = surfboardTypeCarInfo;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "regionDeparture='" + regionDeparture + '\'' +
                ", provinceDeparture='" + provinceDeparture + '\'' +
                ", meetingPoint='" + meetingPoint + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", regionDestination='" + regionDestination + '\'' +
                ", provinceDestination='" + provinceDestination + '\'' +
                ", priceCarInfo=" + priceCarInfo +
                ", passengersNumberCarInfo=" + passengersNumberCarInfo +
                ", outboundCarInfo=" + outboundCarInfo +
                ", surfboardNumberCarInfo=" + surfboardNumberCarInfo +
                ", surfboardTypeCarInfo='" + surfboardTypeCarInfo + '\'' +
                '}';
    }
}
