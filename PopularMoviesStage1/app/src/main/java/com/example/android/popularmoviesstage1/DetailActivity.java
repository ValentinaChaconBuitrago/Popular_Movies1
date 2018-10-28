package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstage1.Model.Movie;
import com.squareup.picasso.Picasso;

/*
Class where details of a movie will show up
*/
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView titleTv;
    private TextView originalTitleTv;
    private TextView originalLanguageTv;
    private TextView popularityTv;
    private TextView votesTv;
    private TextView overviewTv;
    private TextView releaseDate;


    private static Movie movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        ImageView posterIv =  findViewById(R.id.posterImageView);
        titleTv =  findViewById(R.id.titleTextView);
        originalTitleTv = findViewById(R.id.originalTitleTextView);

        originalLanguageTv =  findViewById(R.id.originalLanguageTextView);
        popularityTv = findViewById(R.id.popularityTextView);
        votesTv =  findViewById(R.id.voteAverageTextView);
        overviewTv =  findViewById(R.id.overviewTextView);
        releaseDate = findViewById(R.id.releaseDateTextView);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        Movie[] movies = MovieAdapter.moviesData;
        movie = movies[position];

        populateUI();

        String initialPart = "http://image.tmdb.org/t/p/w185/";
        String path = initialPart + movie.getPosterPath();
        if(!movie.getPosterPath().equals("null")){
            Picasso.with(this).load(path).into(posterIv);
        }
        else if(movie.getPosterPath().equals("null")){
            Picasso.with(this).load(getString(R.string.no_image)).into(posterIv);
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

      titleTv.setText(movie.getTitle());
      originalTitleTv.setText(movie.getOriginalTitle());
      originalLanguageTv.setText(movie.getOriginalLanguage());
      String pop = ""+movie.getPopularity();
      popularityTv.setText(pop);
      String vote= ""+movie.getVoteAverage();
      votesTv.setText(vote);
      overviewTv.setText(movie.getOverview());
      releaseDate.setText(movie.getReleaseDate());
    }
}
