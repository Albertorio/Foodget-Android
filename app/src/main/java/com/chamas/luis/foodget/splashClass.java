package com.chamas.luis.foodget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Luis on 1/9/2015.
 */
public class splashClass extends Activity{
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(splashClass.this, BudgetPage.class);
                splashClass.this.startActivity(mainIntent);
                splashClass.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);

    }
}
