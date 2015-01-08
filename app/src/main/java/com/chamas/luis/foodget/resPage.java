package com.chamas.luis.foodget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Luis on 1/5/2015.
 */
public class resPage extends Activity{
    private TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respage);
        Intent activityThatCalled = getIntent();
        String resName = activityThatCalled.getExtras().getString("resName");
        nameTextView = (TextView)findViewById(R.id.resPageTextView);
        nameTextView.setText(resName);
    }
}
