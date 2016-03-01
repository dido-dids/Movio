package cz.muni.fi.pv256.movio.uco373993;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco373993.model.Movie;

/**
 * Created by David Boron on 10.1.2016.
 */
public class MovieLoader {

    private static MovieLoader sInstance;
    private List<Movie> mMovies;

    private MovieLoader() {
    }

    public static MovieLoader getInstance(){

        if (sInstance == null) {
            sInstance = new MovieLoader();
        }
        return sInstance;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void addMovies(List<Movie> Movies) {

        if (null == mMovies) {
            mMovies = new ArrayList<>();
        }

        mMovies.addAll(Movies);
    }

    public boolean hasData() {
        return null != mMovies;
    }
}
