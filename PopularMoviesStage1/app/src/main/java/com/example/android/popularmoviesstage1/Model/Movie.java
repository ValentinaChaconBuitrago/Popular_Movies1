package com.example.android.popularmoviesstage1.Model;

import java.util.List;

public class Movie {
    //·····························Attributes························

    private int voteCount;
    private int id;
    private boolean video;
    private double voteAverage;
    private String title;
    private double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private List<Integer> genreIds;
    private String backdropPath;
    private boolean adult;
    private String overview;
    private String releaseDate;


    //·····························Methods···························

    /**
     * Empty constructor used for serialization
     */
    public Movie(){

    }

    /**
     * Movie constructor with parameters
     */
    public Movie(int pVoteCount, int pId, boolean pVideo, double pVoteAverage, String pTitle, double pPopularity, String pPosterPath,
                 String pOriginalLanguage, String pOriginalTitle, List<Integer> pGenreIds, String pBackdrop, boolean pAdult, String pOverview,
                 String pRealeaseDate){
        voteCount = pVoteCount;
        id = pId;
        video = pVideo;
        voteAverage = pVoteAverage;
        title = pTitle;
        popularity = pPopularity;
        posterPath = pPosterPath;
        originalLanguage = pOriginalLanguage;
        originalTitle = pOriginalTitle;
        genreIds = pGenreIds;
        backdropPath = pBackdrop;
        adult = pAdult;
        overview = pOverview;
        releaseDate = pRealeaseDate;
    }

    public int getVoteCount(){return voteCount;}
    public void setVoteCount (int voteCount){this.voteCount = voteCount;}

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public boolean getVideo(){return video;}
    public void setVideo(boolean video){this.video = video;}

    public double getVoteAverage(){return voteAverage;}
    public void setVoteAverage(double voteAverage){this.voteAverage = voteAverage;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public double getPopularity(){return popularity;}
    public void setPopularity(double popularity){this.popularity = popularity;}

    public String getPosterPath(){return posterPath;}
    public void setPosterPath(String posterPath){this.posterPath = posterPath;}

    public String getOriginalLanguage(){return originalLanguage;}
    public void setOriginalLanguage(String originalLanguage){this.originalLanguage = originalLanguage;}

    public String getOriginalTitle(){return originalTitle;}
    public void setOriginalTitle(String originalTitle){this.originalTitle = originalTitle;}

    public List<Integer> getGenreIds(){return genreIds;}
    public void setGenreIds(List<Integer> genreIds){this.genreIds = genreIds;}

    public String getBackdropPath(){return  backdropPath;}
    public void setBackdropPath(String backdropPath){this.backdropPath = backdropPath;}

    public boolean getAdult(){return adult;}
    public void setAdult(boolean adult){this.adult = adult;}

    public String getOverview(){return overview;}
    public void setOverview(String overview){this.overview = overview;}

    public String getReleaseDate(){return releaseDate;}
    public void setReleaseDate(String releaseDate){this.releaseDate = releaseDate;}
}
