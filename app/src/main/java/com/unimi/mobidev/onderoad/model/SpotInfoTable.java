package com.unimi.mobidev.onderoad.model;

import java.io.Serializable;

/**
 * Created by Giuseppe Fabio Trentadue on 03/05/2017.
 */

public class SpotInfoTable implements Serializable{
    private String waveSpot;
    private String windSpot;
    private String swellSpot;
    private String seabedSpot;

    public SpotInfoTable(){}

    public SpotInfoTable(String onda, String vento, String mareggiata, String fondale) {
        this.waveSpot = onda;
        this.windSpot = vento;
        this.swellSpot = mareggiata;
        this.seabedSpot = fondale;
    }

    public String getWaveSpot() {
        return waveSpot;
    }

    public void setWaveSpot(String waveSpot) {
        this.waveSpot = waveSpot;
    }

    public String getWindSpot() {
        return windSpot;
    }

    public void setWindSpot(String windSpot) {
        this.windSpot = windSpot;
    }

    public String getSwellSpot() {
        return swellSpot;
    }

    public void setSwellSpot(String swellSpot) {
        this.swellSpot = swellSpot;
    }

    public String getSeabedSpot() {
        return seabedSpot;
    }

    public void setSeabedSpot(String seabedSpot) {
        this.seabedSpot = seabedSpot;
    }

    public String toString(){
        return "SpotInfoTable:\n" +
                "Spot wave: " + this.waveSpot +
                "\nSpot wind: " + this.windSpot +
                "\nSpot swell: " + this.swellSpot +
                "\nSpot seabed: " + this.seabedSpot;
    }
}
