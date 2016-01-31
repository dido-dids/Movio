package cz.muni.fi.pv256.movio.uco373993;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    public List<Movie> mMovies = new ArrayList<>(20);
    private Callback mCallback;
    private StickyGridHeadersGridView mGridView;
    private AsyncDownloadTask mAsyncTask;

    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mGridView = (StickyGridHeadersGridView) view.findViewById(R.id.movie_grid_view);

        ViewStub emptyView = (ViewStub) view.findViewById(R.id.empty);
        mGridView.setEmptyView(emptyView);

        TextView emptyText = (TextView) view.findViewById(R.id.empty_text);

        if (isNetworkAvailable()) {
//            initMovies();
            if (mMovies.isEmpty()) {
                emptyText.setText(R.string.no_data);
            }
        } else {
            emptyText.setText(R.string.no_connection);
        }

        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), R.layout.grid_view_row, mMovies);
        mGridView.setAdapter(movieAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Callback) getActivity()).onItemClick((Movie) mGridView.getItemAtPosition(position));
            }
        });

        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                String title = ((Movie) mGridView.getItemAtPosition(position)).getTitle();
                Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        if (MovieLoader.getInstance().hasData()) {
            mGridView.setAdapter(new MovieAdapter(getActivity(), R.layout.grid_view_row, MovieLoader.getInstance().getMovies()));
        } else {
            if (mAsyncTask != null) {
                mAsyncTask.cancel(true);
            }
            mAsyncTask = new AsyncDownloadTask();
            mAsyncTask.execute();
        }
    }

    @Override
    public void onStop() {

        super.onStop();

        if (mAsyncTask != null) {
            mAsyncTask.cancel(true);
            mAsyncTask = null;
        }
    }

    private class AsyncDownloadTask extends AsyncTask<Void, Void, Boolean> {

        private Gson mGson = new Gson();

        @Override
        protected Boolean doInBackground(Void... params) {

            final MovieLoader movieLoader = MovieLoader.getInstance();
            Response response = movieLoader.loadMovies();

            if (response != null) {
                if (response.isSuccessful()) {

                    try {
                        final MovieDataObject MovieDataObject = mGson.fromJson(response.body().charStream(), MovieDataObject.class);
                        response.body().close();

                        MovieLoader.getInstance().addFilms(Arrays.asList(MovieDataObject.getResults()));
                        return Boolean.TRUE;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            return Boolean.FALSE;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (null != result && result) {
                mGridView.setAdapter(new MovieAdapter(getActivity(), R.layout.grid_view_row, MovieLoader.getInstance().getMovies()));
//                mGvwMovies.setAdapter(new FilmAdapter(getActivity(), DataLoader.getInstance().getFilms(), R.layout.list_item_film_header, R.layout.item_film));
            }
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*
    public void initMovies() {

        String[] covers = {
                "http://image.tmdb.org/t/p/w185/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg",
                "http://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg",
                "http://image.tmdb.org/t/p/w185/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                "https://image.tmdb.org/t/p/w185/aAmfIX3TT40zUHGcCKrlOZRKC7u.jpg",
                "https://image.tmdb.org/t/p/w185/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg",
                "https://image.tmdb.org/t/p/w185/q0R4crx2SehcEEQEkYObktdeFy.jpg",
                "https://image.tmdb.org/t/p/w185/qey0tdcOp9kCDdEZuJ87yE3crSe.jpg",
                "https://image.tmdb.org/t/p/w185/ktyVmIqfoaJ8w0gDSZyjhhOPpD6.jpg",
                "https://image.tmdb.org/t/p/w185/b0f5Thd0IVYVUcteQAtcnwdta0c.jpg",
                "https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                "https://image.tmdb.org/t/p/w185/t4PLWexbe4wbNydCOBv18cYexup.jpg",
                "https://image.tmdb.org/t/p/w185/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg",
                "https://image.tmdb.org/t/p/w185/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg",
                "https://image.tmdb.org/t/p/w185/vlTPQANjLYTebzFJM1G4KeON0cb.jpg",
                "https://image.tmdb.org/t/p/w185/z3nGs7UED9XlqUkgWeT4jQ80m1N.jpg",
                "http://image.tmdb.org/t/p/w185/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg",
                "http://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg",
                "http://image.tmdb.org/t/p/w185/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                "https://image.tmdb.org/t/p/w185/aAmfIX3TT40zUHGcCKrlOZRKC7u.jpg",
                "https://image.tmdb.org/t/p/w185/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg",
                "https://image.tmdb.org/t/p/w185/q0R4crx2SehcEEQEkYObktdeFy.jpg",
                "https://image.tmdb.org/t/p/w185/qey0tdcOp9kCDdEZuJ87yE3crSe.jpg",
                "https://image.tmdb.org/t/p/w185/ktyVmIqfoaJ8w0gDSZyjhhOPpD6.jpg",
                "https://image.tmdb.org/t/p/w185/b0f5Thd0IVYVUcteQAtcnwdta0c.jpg",
                "https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                "https://image.tmdb.org/t/p/w185/t4PLWexbe4wbNydCOBv18cYexup.jpg",
                "https://image.tmdb.org/t/p/w185/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg",
                "https://image.tmdb.org/t/p/w185/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg",
                "https://image.tmdb.org/t/p/w185/vlTPQANjLYTebzFJM1G4KeON0cb.jpg",
        };

        for (int i = 0; i < covers.length; i++) {
//            mMovies.add(i, new Movie(123, covers[i], "title" + i));
            mMovies.add(i, new Movie());
        }
    }
    */

    public interface Callback {
        void onItemClick(Movie movie);
    }

}
