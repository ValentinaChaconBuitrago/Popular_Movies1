package com.example.android.popularmoviesstage1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.android.popularmoviesstage1.Model.Movie;
import com.example.android.popularmoviesstage1.Utils.JsonUtils;
import com.example.android.popularmoviesstage1.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class MainActivityFragment extends Fragment {

    private MovieAdapter movieAdapter;

    private GridView gridView;
    //private ListView listView;
    private TextView errorDisplayView;
    private ProgressBar loadingIndicator;
    private Snackbar mySnackbar;

    static Movie[] mov;

    public MainActivityFragment() {
    }

    //TODO falta lo del clickhandler para guardar la información en el detail activity


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_fragment, container, false);


        View contextView = rootView.findViewById(R.id.snackBarCoordinatorLayout);
        mySnackbar = Snackbar.make(contextView, R.string.error_message,Snackbar.LENGTH_LONG);


        //movieAdapter = new MovieAdapter(getActivity(), Arrays.asList(mov));


        //Get a reference to the GridView, and attach this adapter to it.
        gridView = (GridView) rootView.findViewById(R.id.grid_view);


        //listView = (ListView) rootView.findViewById(R.id.listview_flavor);


        loadingIndicator = (ProgressBar) rootView.findViewById(R.id.pb_loading_indicator);
        errorDisplayView = (TextView) rootView.findViewById(R.id.tv_error_message_display);

        //listView.setAdapter(movieAdapter);

        gridView.setAdapter(movieAdapter);
        loadMovieData();





        /**
         * Once all views are setup, the next step is to load the movies data
         */
        return rootView;
    }

    public  void loadMovieData() {
        showMovieDataView();
        String criteria = MainActivity.sortCriteria;
        new FetchMovieTask().equals(criteria);
    }

    /**
     * This Method will make the movie data visible and hide the error message.
     */
    private void showMovieDataView() {
        //Make sure the error is invisible
        //TODO remove error text view
        errorDisplayView.setVisibility(View.INVISIBLE);
        //Make sure the GridView is visible
        gridView.setVisibility(View.VISIBLE);
        //listView.setVisibility(View.VISIBLE);
    }

    /**
     * This Method will make the movie data invisible and show the error message.
     */
    private void showErrorMessage() {
        //Make sure the GridView is invisible
        gridView.setVisibility(View.INVISIBLE);
        //listView.setVisibility(View.INVISIBLE);
        //Make sure the error is visible
        //TODO Remove error text view
        errorDisplayView.setVisibility(View.VISIBLE);
        mySnackbar.show();
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            //Case 1: There's nothing to look up
            if (params.length == 0)
                return null;

            String sortingCriteria = params[0];
            String url = NetworkUtils.buildUrl(sortingCriteria);
            try {
               String data = NetworkUtils.getResponseFromHttpUrl(url);
                //TODO este si es el contexto??
                Movie[] movies = JsonUtils.parseMovieJson(getContext(), data);
                Log.v(TAG, "MOVIE TITLE IN FRAGMENT IS: " + movies[0].getTitle());
                Log.v(TAG, "MOVIE TITLE IN FRAGMENT IS: " + movies[1].getTitle());
                return movies;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            loadingIndicator.setVisibility(View.INVISIBLE);
            if (movies != null) {
                movieAdapter.setMovieData(movies);
                Log.v(TAG, "LONGITUD DE LAS PELICULAS ES " + movies.length);
                //movieAdapter = new MovieAdapter(getActivity(), Arrays.asList(movies));
            }
            else{
                showErrorMessage();
            }
        }
    }
}
