package cz.muni.fi.pv256.movio.uco373993;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by David Boron on 30.1.2016.
 */
public class MovieDataObject {

    @SerializedName("results")
    private Movie[] mResults;

    public MovieDataObject() {
    }

    public Movie[] getResults() {
        return mResults;
    }

    @SuppressWarnings("unused")
    public void setResults(Movie[] results) {
        mResults = results;
    }
}
