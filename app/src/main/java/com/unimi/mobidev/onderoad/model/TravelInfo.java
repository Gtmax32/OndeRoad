package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Giuseppe Fabio Trentadue on 30/09/2016.
 */

public class TravelInfo extends Object implements Serializable {

    private AddressInfo addressDeparture;
    private Long dateTimeDeparture;
    private SpotInfo spotDestination;
    private int priceTravel;
    private CarInfo carTravel;
    private boolean isOutbound;
    private String noteTravel;
    private User ownerTravel;
    private ArrayList<User> passengersTravel;

    public TravelInfo() {
    }

    /*public TravelInfo(ArrayList<User> list) {
        AddressInfo exampleAddress = new AddressInfo("Via Ugo Betti Milano, MI, Italia", "Milano", 45.492888, 9.1102608);
        SpotInfo exampleSpot = new SpotInfo("Toscana", "Ms", "Cinquale", "Il trabucco", 43.9828071, 10.153797199999985, 0, "AFFIANCO AL RISTORANTE TRABUCCO. LO SPOT PRINCIPALE E' ACCANTO AL MOLETTO DI MASSI", new SpotInfoTable("DX", "S-O (MODERATO)", "SO", "SABBIA"));
        CarInfo exampleCar = new CarInfo(4, 3, "Dentro l'auto");
        User exampleUser = new User("AaCVWDmIx8NQnPV4KLP7LFYwEbM2","Giuseppe Fabio Trentadue", "gtmax_32@hotmail.it");
        this.addressDeparture = exampleAddress;
        this.dateTimeDeparture = new GregorianCalendar(2017,5,4,10,0);
        this.spotDestination = exampleSpot;
        this.priceTravel = 40;
        this.carTravel = exampleCar;
        this.isOutbound = false;
        this.noteTravel = "fuffa";
        this.ownerTravel = exampleUser;
        this.passengersTravel = list;
    }*/

    public AddressInfo getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(AddressInfo addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public Long getDateTimeDeparture() {
        return dateTimeDeparture;
    }

    public void setDateTimeDeparture(Long dateTimeDeparture) {
        this.dateTimeDeparture = dateTimeDeparture;
    }

    private GregorianCalendar timestampToCalendar(){
        Date d = new Date(this.dateTimeDeparture);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);

        return calendar;
    }

    private void calendarToTimestamp(GregorianCalendar calendar){
        this.dateTimeDeparture = calendar.getTimeInMillis();
    }

    public String formatDataDeparture() {
        SimpleDateFormat outputTime = new SimpleDateFormat("dd MMMM",Locale.ITALIAN);
        return outputTime.format(this.timestampToCalendar().getTime());
    }

    public void updateDateTimeDeparture(String date, String time) {
        Calendar c = Calendar.getInstance();
        String splitDate[] = date.split(" ");
        int day = Integer.parseInt(splitDate[0]);
        int year = c.get(Calendar.YEAR);

        int currentMonth = c.get(Calendar.MONTH);

        //Se ci troviamo a dicembre, ma la data del viaggio corrisponde a gennaio,
        //vuol dire che l'anno del viaggio è successivo all'anno corrente
        if (currentMonth == Calendar.DECEMBER && splitDate[1].equals("gennaio")) {
            year++;
        }

        try {
            c.setTime(new SimpleDateFormat("MMMM", Locale.ITALIAN).parse(splitDate[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month = c.get(Calendar.MONTH);

        System.out.println("Selected date:" + day + "-" + month + "-" + year);

        String splitTime[] = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        GregorianCalendar calendar = new GregorianCalendar(year,month,day,hour,minute);

        calendarToTimestamp(calendar);
    }

    /*public void updateDataDeparture(String s) {
        Calendar c = Calendar.getInstance();
        String splitDate[] = s.split(" ");
        int day = Integer.parseInt(splitDate[0]);
        int year = c.get(Calendar.YEAR);

        int currentMonth = c.get(Calendar.MONTH);

        //Se ci troviamo a dicembre, ma la data del viaggio corrisponde a gennaio,
        //vuol dire che l'anno del viaggio è successivo all'anno corrente
        if (currentMonth == Calendar.DECEMBER && splitDate[1].equals("gennaio")) {
            year++;
        }

        try {
            c.setTime(new SimpleDateFormat("MMMM", Locale.ITALIAN).parse(splitDate[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month = c.get(Calendar.MONTH);
        this.setTravelDate(day, month, year);
        System.out.println("Selected date:" + day + "-" + month + "-" + year);
    }

    public void updateTimeDeparture(String s) {
        String splitTime[] = s.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        this.setTravelTime(hour, minute);
    }*/

    public String formatTimeDeparture() {
        SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm",Locale.ITALIAN);
        return outputTime.format(this.timestampToCalendar().getTime());
    }

    /*private void setTravelDate(int day, int month, int year) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);

        this.dateTimeDeparture = calendar.getTimeInMillis();
    }

    private void setTravelTime(int hour, int minute) {
        this.dateTimeDeparture.set(Calendar.HOUR_OF_DAY, hour);
        this.dateTimeDeparture.set(Calendar.MINUTE, minute);
    }*/

    /*public String getRegionDestination() {
        return regionDestination;
    }

    public void setRegionDestination(String regionDestination) {
        this.regionDestination = regionDestination;
    }*/

    public SpotInfo getSpotDestination() {
        return spotDestination;
    }

    public void setSpotDestination(SpotInfo spotDestination) {
        this.spotDestination = spotDestination;
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

    public boolean isPassenger(String Key) {
        if (this.passengersTravel != null) {
            for (User u : this.passengersTravel){
                if (u.getIdUser().equals(Key))
                    return true;
            }
        }

        return false;
    }

    public boolean isFull(){
        return (this.getPassengersTravel() != null && this.getPassengersTravel().size() == this.getCarTravel().getPassengersNumber());
    }

    public String toString() {
        return "TravelInfo: " +
                "\n" + this.addressDeparture.toString() +
                "\nDate: " + timestampToCalendar().get(Calendar.DAY_OF_MONTH) + " " + timestampToCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ITALIAN) +
                "\nTime: " + timestampToCalendar().get(Calendar.HOUR_OF_DAY) + ":" + timestampToCalendar().get(Calendar.MINUTE) +
                "\n" + this.spotDestination.toString() +
                "\nPrice: " + this.priceTravel +
                "\n" + this.carTravel.toString() +
                "\nOutbound: " + this.isOutbound +
                "\nNote: " + this.noteTravel +
                "\n" + this.ownerTravel.toString();
    }
}
