package com.unimi.mobidev.onderoad.other;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;
import com.unimi.mobidev.onderoad.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Giuseppe on 27/09/2016.
 */

public class LatLngManager implements LocationListener {

    private Context activityContext;
    private LocationManager locationManager;
    private Location currentLocation;
    private LatLng currentLatLng;

    public LatLngManager(Context context){

        this.activityContext = context;

        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }

    public LatLngBounds getLatLngBounds(double radius){
        if(this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
            if (this.locationManager != null) {
                this.currentLocation = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (this.currentLocation != null) {
                    this.currentLatLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                }
            }
        }
        else if (this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, this);

            if(this.locationManager != null){
                this.currentLocation = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if(this.currentLocation != null){
                    this.currentLatLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                }
            }
        }
        else{
            Toast.makeText(this.activityContext, R.string.no_provider_enabled,Toast.LENGTH_LONG).show();
        }

        if(currentLatLng != null){
            System.out.println("Current latitude: " + currentLatLng.latitude + "\nCurrent longitude: " + currentLatLng.longitude);
            LatLng southwest = SphericalUtil.computeOffset(currentLatLng, radius * Math.sqrt(2.0), 225);
            LatLng northeast = SphericalUtil.computeOffset(currentLatLng, radius * Math.sqrt(2.0), 45);
            System.out.println("Southwest latitude" + southwest.latitude + "\nSouthwest longitude" + southwest.longitude);
            System.out.println("Northeast latitude" + northeast.latitude + "\nNortheast longitude" + northeast.longitude);
            return new LatLngBounds(southwest, northeast);
        }
        else
            return null;
    }

    public Address getAddressInfo(String address) throws IOException {
        Geocoder coder =  null;
        List<Address> addresses = null;

        if(currentLatLng != null){
            coder = new Geocoder(activityContext, Locale.getDefault());
            addresses = coder.getFromLocationName(address,1);

            if(addresses.size() > 0)
                return addresses.get(0);
        }

        return null;
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
