package com.example.android.popularmoviesstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    static String sortCriteria = "popularity.desc";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sortbyPop_settings){
            sortCriteria = "popularity.desc";
            getSupportFragmentManager().beginTransaction().replace((R.id.activityFragment), new MainActivityFragment()).commit();
            return true;

        }
        else if (id == R.id.sortbyRate_settings){
            sortCriteria = "vote_average.desc";
            getSupportFragmentManager().beginTransaction().replace((R.id.activityFragment), new MainActivityFragment()).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
