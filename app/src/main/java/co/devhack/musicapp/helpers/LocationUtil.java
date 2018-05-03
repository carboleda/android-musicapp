package co.devhack.musicapp.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;


/**
 * Created by krlosf on 30/04/18.
 */

public class LocationUtil implements LocationListener {

    private static LocationUtil instance;
    private long minTime = 1000 * 30;
    private float minDistance = 2;

    public boolean init(Context context) {
        int permissionResult = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (PackageManager.PERMISSION_GRANTED == permissionResult) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);
            LocationUtil.instance = this;

            return true;
        }

        return false;
    }

    public static LocationUtil getInstance() {
        return instance;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("LocationUtil", "latitude, longitude: "+location.getLatitude()+", "+location.getLongitude());
        //new Geocoder(context).getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(.getCountryName());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
