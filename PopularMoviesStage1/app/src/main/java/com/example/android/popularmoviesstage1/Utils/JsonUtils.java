package com.example.android.popularmoviesstage1.Utils;


import android.content.Context;
import android.util.Log;

import com.example.android.popularmoviesstage1.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
Class where information from a Json will be used to build a Movie Object
 */
public final class JsonUtils {

    //·····························Constants························

    private static String INFORMATIVE_MESSAGE = "There is no information available";


    //·····························Methods···························

    /**
     * This method parses JSON from the response of the MovieDatabase API and returns
     * an array of movies.
     * @param Json JSON response from server
     * @return Array of movies
     * @throws JSONException If JSON data cannot be properly parsed
     */
    //Aqui debo mandar un arreglo de peliculas, entonces lo leo como si fuera a hacer el sadwich solo que debo
    //Tener en cuenta el arreglo que me entra entonces incorporo lo de JSON array de sunshine.
    public static Movie[] parseMovieJson(Context context, String Json) throws JSONException{

        //DESDE EL MAIN ACTIVITY LLAMAMOS ESTA CLASE Y POR PARAMETRO EL ENTRA EL CONTEXTO, QUE ES MAINACTIVITY.THIS, Y EL
        //STRING JSON QUE LE ENTRA VIENE DE PEDIRLE AL METODO NETWORK UTILS QUE NOS DE EL STRING RESULTADO DE LA QUERY


        JSONObject allInfo = new JSONObject(Json);
        //We do not have this entire amount because we have a single page and not all of them
        int amountResults = allInfo.getInt("total_results");


        JSONArray results = allInfo.getJSONArray("results");

        Movie[] movies = new Movie[results.length()];

        //There are reults available
        if(results.length()>= 1){
            for (int i = 0; i < results.length(); i++){
                JSONObject currentMovie = results.getJSONObject(i);

                int voteCount = currentMovie.getInt("vote_count");
                int id = currentMovie.getInt("id");
                boolean video = currentMovie.getBoolean("video");
                double voteAverage = currentMovie.getDouble("vote_average");
                String title = currentMovie.getString("title");

                Log.v(TAG, "MOVIE TITLE IS: " + title);

                double popularity = currentMovie.getDouble("popularity");
                String posterPath = currentMovie.getString("poster_path");
                String originalLanguage = currentMovie.getString("original_language");
                String originalTitle = currentMovie.getString("original_title");

                JSONArray genres = currentMovie.getJSONArray("genre_ids");
                List<Integer>  gen = new ArrayList<>();
                /*

                //TODO Why do I get an index out of range
                for (int j = 0; j < genres.length()-1;j++){
                    int temp = genres.getInt(i);
                    gen.add(temp);
                }
                */
                String backdropPath = currentMovie.getString("backdrop_path");
                boolean adult = currentMovie.getBoolean("adult");
                String overview = currentMovie.getString("overview");
                String releaseDate = currentMovie.getString("release_date");


                Movie thisMovie = new Movie(voteCount,id,video,voteAverage,title,popularity, posterPath, originalLanguage,
                        originalTitle, gen, backdropPath, adult, overview, releaseDate);

                movies[i] = thisMovie;
            }
        }
        return movies;
    }
}
