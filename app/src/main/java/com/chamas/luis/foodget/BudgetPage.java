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
import org.json.JSONException;
import org.json.JSONObject;

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
    private String menus;
    private String[] retval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        budget = (EditText)findViewById(R.id.editText);
        searchButton = (Button)findViewById(R.id.SearchButton);

        Bundle b = this.getIntent().getExtras();
        arrayofrest = b.getStringArray("restaurants");
        arrayofgeopoints = b.getStringArray("coordinates");
        menus = b.getString("menu");
        Log.d("rest 1 name", arrayofrest[0]);
        Log.d("geo 1 ", arrayofgeopoints[0]);
        Log.d("menus", menus);
        for(int i=0;i<arrayofgeopoints.length;i++){
            arrayofgeopoints[i] = arrayofgeopoints[i].replace("ParseGeoPoint[", "");
            arrayofgeopoints[i] = arrayofgeopoints[i].replace("]", "");
        }
        Log.d("geo 1 after ", arrayofgeopoints[0]);
        menus = menus.replace("[", "");
        Log.d("menus after", menus);
        retval = menus.split("]");
        Log.d("new retval",Arrays.toString(retval));
        for(int i=1;i<retval.length;i++){
            retval[i] = retval[i].replaceFirst(",", "");
        }
        for(int i=0;i<retval.length;i++){
            Log.d("qwerty", retval[i]);
        }
//        for(int i =0; i< retval.length;i++){
//            retval[i] = retval[i].replace(",", "");
//            retval[i] = retval[i].replaceAll("\\n", "");
//
//        }
//        Log.d("new retval after",Arrays.toString(retval));
//        Log.d("retval 0 after", retval[0]);
//        Log.d("retval 1 after", retval[1]);
//        Log.d("retval 2 after", retval[2]);
//        Log.d("retval 3 after", retval[3]);
//        Log.d("retval 4 after", retval[4]);

//        for(String retval : menus.split("]")) {
//            Log.d("retval", retval);
//        }

       // Log.d("retval alone", retval);
        //String [] spl = menus.split("]",5);
        //Log.d("split", Arrays.toString(spl));

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
        b.putStringArray("menu", retval);
        b.putStringArray("geo", arrayofgeopoints);

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
