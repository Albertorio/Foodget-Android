package com.chamas.luis.foodget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Luis on 1/5/2015.
 */
public class resPage extends Activity{
    private TextView nameTextView;
    private TextView restName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respage);
        Intent activityThatCalled = getIntent();
        String resMenu = activityThatCalled.getExtras().getString("resMenu");
        String resName = activityThatCalled.getExtras().getString("resName");


        nameTextView = (TextView)findViewById(R.id.resPageTextView);
        restName = (TextView)findViewById(R.id.resNameTextView);
        nameTextView.setText(resMenu);
        restName.setText(resName);

        String[] resMenuItems;
        resMenu = resMenu.replaceFirst("\"","");
        resMenuItems = resMenu.split("\"");



        for(int i=0;i<resMenuItems.length;i++){
            Log.d("items",resMenuItems[i]);
        }

    }
}
