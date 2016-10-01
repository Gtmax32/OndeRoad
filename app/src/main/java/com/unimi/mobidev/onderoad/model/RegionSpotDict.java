package com.unimi.mobidev.onderoad.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.unimi.mobidev.onderoad.model.SpotInfo;
/**
 * Created by Pc-Utente on 01/10/2016.
 */

public class RegionSpotDict {

    private static final TreeMap<String, List<SpotInfo>> DICT;
    static{
        DICT = new TreeMap<>();
        DICT.put("Liguria", Arrays.asList(new SpotInfo("Levanto",new LatLng(45.000,9.000),0,null)));

    }

}
