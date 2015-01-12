package com.chamas.luis.foodget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Luis on 1/9/2015.
 */
public class splashClass extends Activity{
    private String[] arrayofrest;
    private String[] arrayofgeopoints;
    private JSONArray arrayofmenus;
    private String[] array;
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "37fmnVXPfeDxVktJ3jo6HhDT6uE6fz9EKXMXY7By", "2qeEUesyWBHUREh1FLqxwvUyL7hFUdndorxhCBVc");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("restaurant");
        query.whereNotEqualTo("resName", "");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> resList, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("resName", "Retrieved " + resList.size() + " restaurant");
                    //testParse.setText(resList.toString());
                    int size = resList.size();
                    arrayofrest = new String[size];
                    arrayofgeopoints = new String[size];
                    arrayofmenus = new JSONArray();
                    for(int i=0;i<size;i++){
                        ParseObject rest = resList.get(i);
                        arrayofrest[i] = rest.getString("resName");
                        arrayofgeopoints[i] = String.valueOf(rest.getParseGeoPoint("coordinates"));
                       // array[i] = String.valueOf(rest.getString(""));
                        arrayofmenus.put(rest.getJSONArray("Menu"));
                    }
                    Log.d("rest", arrayofrest[0]);
                    Log.d("resgeopoint", arrayofgeopoints[0]);
                    Log.d("menu splash", arrayofmenus.toString());

                } else {
                    Log.d("rest", "Error: " + e.getMessage());
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(splashClass.this, BudgetPage.class);
                Bundle b = new Bundle();
                b.putStringArray("restaurants", arrayofrest);
                b.putStringArray("coordinates", arrayofgeopoints);
                b.putString("menu", arrayofmenus.toString());
                mainIntent.putExtras(b);
                splashClass.this.startActivity(mainIntent);
                splashClass.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);

    }
}
