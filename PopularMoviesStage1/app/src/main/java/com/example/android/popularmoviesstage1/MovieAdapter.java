package com.example.android.popularmoviesstage1;
import android.app.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.popularmoviesstage1.Model.Movie;
import com.squareup.picasso.Picasso;


import static android.content.ContentValues.TAG;

public class MovieAdapter extends ArrayAdapter<Movie> {

    static Movie[] moviesData;

    public MovieAdapter(Activity context){
        super(context,0);
    }

    /**
     * Provides a view for an AdapterView. In this case a grid View.
     * @param position The AdapterView position that is requesting a view.
     * @param convertView The recycled view to populate.
     * @param parent The parent view group that is used for inflation.
     * @return The View for the position in the AdapterView
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        //Gets the movie object from the array adapter at the appropriate position
        Movie movie = getItem(position);

        /*
         *Adapter recycles views to AdapterViews.
         * Case 1: This is a new View  object, so it is necessary to inflate the layout.
         * Case 2: This View already has the layout inflated from a previous call to getView().
         *         Because of this, it is only necessary to modify the View widget as usual.
         */
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie, parent, false);
        }


        ImageView thumbnail =  convertView.findViewById(R.id.grid_item_poster);
        if(movie.getPosterPath()!= null){
        String initialPart = getContext().getString(R.string.initial_path);

        String path = initialPart + movie.getPosterPath();
        Log.v(TAG, "MOVIE PATH IN ADAPTER IS: " + path);
        if (!movie.getPosterPath().equals("null")) {
            Picasso.with(getContext()).load(path).into(thumbnail);
        } else if (movie.getPosterPath().equals("null")) {
            Picasso.with(getContext()).load(getContext().getString(R.string.no_image)).into(thumbnail);
        }
        }
        Log.v(TAG, "MOVIE TITLE IN ADAPTER IS: " + movie.getTitle());
        TextView title =  convertView.findViewById(R.id.title_textView);
        title.setText(movie.getTitle());

        return convertView;
    }

    public void setMovieData(Movie[] movies){
        clear();
        addAll(movies);
        moviesData  = movies;
        notifyDataSetChanged();
        Log.v(TAG, "SET MOVIE DATA TITLE IS: " + movies[1].getTitle());
    }
}
