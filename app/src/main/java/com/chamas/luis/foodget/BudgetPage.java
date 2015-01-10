package com.chamas.luis.foodget;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;


public class BudgetPage extends Activity {
    private EditText budget;
    private Button searchButton;
    private String[] arrayofrest;
    private String[] arrayofgeopoints;
    private JSONArray arrayofmenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        budget = (EditText)findViewById(R.id.editText);
        searchButton = (Button)findViewById(R.id.SearchButton);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "37fmnVXPfeDxVktJ3jo6HhDT6uE6fz9EKXMXY7By", "2qeEUesyWBHUREh1FLqxwvUyL7hFUdndorxhCBVc");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("restaurant");
        query.whereNotEqualTo("resName", "");
        //  query.setLimit(1);
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
                        arrayofmenus.put(rest.getJSONArray("Menu"));
                    }
                    Log.d("rest", arrayofrest[0]);
                    Log.d("resgeopoint", arrayofgeopoints[0]);
                    Log.d("menu", String.valueOf(arrayofmenus));

                    //ParseObject rest = resList.get(0);

                    //String realRestName = rest.getString("resName");


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.budget_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            DialogFragment myFragment = new MyDialogFragment();
            myFragment.show(getFragmentManager(),"TheDialog");
            return true;
        }else if(id == R.id.exitApp){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void searchButtonClicked(View view) {

        Intent getMapIntent = new Intent(this, mapDisplay.class);
        Bundle b = new Bundle();
        b.putStringArray("rest", arrayofrest);

        final int result = 1;

        getMapIntent.putExtras(b);
      //  Intent getMenu = new Intent(this, )


        startActivity(getMapIntent);

//        String getBudget;
//        getBudget = String.valueOf(budget.getText());
//        if(getBudget.matches("")){
//            String noResponse = "You did not enter a budget";
//            Toast.makeText(this, noResponse, Toast.LENGTH_SHORT).show();
//        }else{
//            String response = "your budget is: $" + getBudget;
//            Toast.makeText(this, response,Toast.LENGTH_SHORT).show();
//        }
    }
}
