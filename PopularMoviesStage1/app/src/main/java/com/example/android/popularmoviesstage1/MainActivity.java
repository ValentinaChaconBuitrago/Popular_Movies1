package com.example.android.popularmoviesstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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
            //MainActivityFragment.loadMovieData();
            return true;

        }
        else if (id == R.id.sortbyVotes_settings){
            sortCriteria = "vote_average.desc";
            //TODO AQUI TENGO QUE LLAMAR AL METODO loadMovieData()?????
            //MainActivityFragment.loadMovieData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
