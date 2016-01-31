package cz.muni.fi.pv256.movio.uco373993;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by David Boron on 10.1.2016.
 */
public class MovieLoader {

    private static MovieLoader sInstance;
    private List<Movie> mMovies;
    private OkHttpClient mHttpClient;


    private MovieLoader() {
        mHttpClient = new OkHttpClient();
    }

    public static MovieLoader getInstance(){
        Log.d("MovieLoader", "getInstance() called");

        if (sInstance == null) {
            sInstance = new MovieLoader();
        }
        return sInstance;
    }

    public List<Movie> getMovies() {
        Log.d("MovieLoader", "getMovies() called");
        return mMovies;
    }

    public void addMovies(List<Movie> Movies) {
        Log.d("MovieLoader", "addMovies() called");

        if (null == mMovies) {
            mMovies = new ArrayList<>();
        }

        mMovies.addAll(Movies);
    }

    public boolean hasData() {
        Log.d("MovieLoader", "hasData() called");
        return null != mMovies;
    }

    public void addFilms(List<Movie> films) {
        Log.d("MovieLoader", "addFilms() called");
        if (null == mMovies) {
            mMovies = new ArrayList<>();
        }

        mMovies.addAll(films);
    }
    
    public Response loadMovies() {
        Log.d("MovieLoader", "loadMovies() called");

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("api.themoviedb.org")
                .addPathSegment("3")
                .addPathSegment("discover")
                .addPathSegment("movie")
                .setQueryParameter("api_key", "c585ed6c438687d1597418a8a2c7a8dd")
                .setQueryParameter("sort_by", "avg_rating.desc")
                .build();

        Log.d("url: ", httpUrl.toString());

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try {
            return mHttpClient.newCall(request).execute();
        } catch (IOException ex) {
            Log.e("MovieLoader", "Exception caught, " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }

}
