package com.chamas.luis.foodget;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;


public class BudgetPage extends Activity {
    private EditText budget;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        budget = (EditText)findViewById(R.id.editText);
        searchButton = (Button)findViewById(R.id.SearchButton);




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

          final int result = 1;
        String getBudget;
        getBudget = String.valueOf(budget.getText());


        getMapIntent.putExtra("budget", getBudget);

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
