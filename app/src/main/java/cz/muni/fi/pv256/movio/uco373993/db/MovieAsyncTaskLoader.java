package cz.muni.fi.pv256.movio.uco373993.db;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import cz.muni.fi.pv256.movio.uco373993.model.Movie;

public class MovieAsyncTaskLoader extends AsyncTaskLoader<List<Movie>> {

    private List<Movie> mData;

    public MovieAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public List<Movie> loadInBackground() {
        return new MovieDao(getContext()).fetchAll();
    }

    @Override
    public void deliverResult(List<Movie> data) {
        if (isReset()) return;
        mData = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) deliverResult(mData);
        if (takeContentChanged() || mData == null) forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
