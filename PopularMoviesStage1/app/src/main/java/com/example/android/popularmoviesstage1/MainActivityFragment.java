package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import com.example.android.popularmoviesstage1.Model.Movie;
import com.example.android.popularmoviesstage1.Utils.JsonUtils;
import com.example.android.popularmoviesstage1.Utils.NetworkUtils;
import org.json.JSONException;
import java.io.IOException;


import static android.content.ContentValues.TAG;

public class MainActivityFragment extends Fragment {

    private MovieAdapter movieAdapter;

    private GridView gridView;
    private ProgressBar loadingIndicator;
    private Snackbar mySnackbar;
    private String sortCriteria;


    public MainActivityFragment() {
    }

    public void setSortCriteria(String ordenar){
        sortCriteria = ordenar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_fragment, container, false);


        View contextView = rootView.findViewById(R.id.snackBarCoordinatorLayout);
        mySnackbar = Snackbar.make(contextView, R.string.error_message,Snackbar.LENGTH_LONG);


        movieAdapter = new MovieAdapter(getActivity());

        //Get a reference to the GridView, and attach this adapter to it.
        gridView = rootView.findViewById(R.id.grid_view);

        loadingIndicator =  rootView.findViewById(R.id.pb_loading_indicator);

        gridView.setAdapter(movieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                launchDetailActivity(i);
            }
        });
        loadMovieData();


        /*
         * Once all views are setup, the next step is to load the movies data
         */
        return rootView;
    }
    private void launchDetailActivity(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    private void loadMovieData() {
        showMovieDataView();
        sortCriteria = MainActivity.sortCriteria;
        new FetchMovieTask().execute(sortCriteria);
    }

    /**
     * This Method will make the movie data visible and hide the error message.
     */
    private void showMovieDataView() {
        //Make sure the GridView is visible
        gridView.setVisibility(View.VISIBLE);
    }

    /**
     * This Method will make the movie data invisible and show the error message.
     */
    private void showErrorMessage() {
        //Make sure the GridView is invisible
        gridView.setVisibility(View.INVISIBLE);

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
                Movie[] movies = JsonUtils.parseMovieJson(getContext(), data);
                Log.v(TAG, "MOVIE TITLE IN FRAGMENT IS: " + movies[0].getTitle());
                Log.v(TAG, "MOVIE TITLE IN FRAGMENT IS: " + movies[1].getTitle());
                return movies;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            loadingIndicator.setVisibility(View.INVISIBLE);
            if (movies != null) {
                movieAdapter.setMovieData(movies);
                Log.v(TAG, "LONGITUD DE LAS PELICULAS ES " + movies.length);
            }
            else{
                showErrorMessage();
            }
        }
    }
}
