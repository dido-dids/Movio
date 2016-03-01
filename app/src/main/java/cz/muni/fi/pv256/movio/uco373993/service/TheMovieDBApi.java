package cz.muni.fi.pv256.movio.uco373993.service;

import cz.muni.fi.pv256.movio.uco373993.model.Movie;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by David Boron on 29.2.2016.
 */
public interface TheMovieDBApi {

    public static final String URL = "http://api.themoviedb.org/";
    public static final String API_KEY = "c585ed6c438687d1597418a8a2c7a8dd";
    public static final String IMAGES_URL = "http://image.tmdb.org/t/p/original";
    public static final String QUERY_PARAM_API_KEY = "api_key";
    public static final String QUERY_PARAM_SORT_BY = "avg_rating.desc";


    @GET("/3/discover/movie")
    Call<Movie[]> loadInTheatreMovies(
            @Query(QUERY_PARAM_API_KEY) String apiKey,
            @Query(QUERY_PARAM_SORT_BY) String sortBy
    );
}
