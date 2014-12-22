package com.chamas.luis.foodget;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
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
import com.parse.Parse;
import com.parse.ParseObject;


public class mapDisplay extends FragmentActivity implements LocationListener{
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_page);

       // Intent activityThatCalled = getIntent();
        Bundle b = this.getIntent().getExtras();
        String[] restaurants = b.getStringArray("rest");
        String[] restaurants2 = {"hello", restaurants[0]};

       // String userBudget = activityThatCalled.getExtras().getString("restaurants");

        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);
//
//        Parse.initialize(this, "37fmnVXPfeDxVktJ3jo6HhDT6uE6fz9EKXMXY7By", "2qeEUesyWBHUREh1FLqxwvUyL7hFUdndorxhCBVc");


        //map part
        setUpMapIfNeeded();

        //List part
       // String[] testRest = {userBudget};

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,restaurants);

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
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);
        mMap.setMyLocationEnabled(true);

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
