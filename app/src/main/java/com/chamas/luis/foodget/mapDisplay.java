package com.chamas.luis.foodget;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class mapDisplay extends FragmentActivity implements LocationListener{
    private GoogleMap mMap;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_page);

        Intent activityThatCalled = getIntent();

        String userBudget = activityThatCalled.getExtras().getString("budget");

        //Added to get current location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) this);



        //map part
        setUpMapIfNeeded();

        //List part
        String[] testRest = {"monkeys", "labecket", "something", "something else", userBudget};

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,testRest);

        ListView restView = (ListView)findViewById(R.id.restList);

        restView.setAdapter(theAdapter);

        restView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String restPicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(mapDisplay.this, restPicked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
//Methods added when location listener was implemented
    //OnLocationChanged
    //OnStatusChanged
    //OnProviderEnabled
    //OnProviderDisabled
    @Override
    public void onLocationChanged(Location location) {
        latitude = (double) (location.getLatitude());
        longitude = (double) (location.getLongitude());

        Log.i("Geo_Location", "Latitude: " + latitude + ", Longitude: " + longitude);
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
