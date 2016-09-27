package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.unimi.mobidev.onderoad.R;

/**
 * Created by Giuseppe on 27/09/2016.
 */

public class LatLngManager implements LocationListener {

    private LocationManager locationManager;
    private Location currentLocation;
    private LatLng currentLatLng;

    public LatLngManager(Context context){
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
            if (locationManager != null) {
                currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (currentLocation != null) {
                    currentLatLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                }
            }
        }
        else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, this);

            if(locationManager != null){
                currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if(currentLocation != null){
                    currentLatLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                }
            }
        }
        else{
            Toast.makeText(context, R.string.no_provider_enabled,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
