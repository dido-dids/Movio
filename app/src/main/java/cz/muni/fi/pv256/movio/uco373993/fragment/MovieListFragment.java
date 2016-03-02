package cz.muni.fi.pv256.movio.uco373993.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco373993.MovieAdapter;
import cz.muni.fi.pv256.movio.uco373993.MovieLoader;
import cz.muni.fi.pv256.movio.uco373993.R;
import cz.muni.fi.pv256.movio.uco373993.db.MovieAsyncTaskLoader;
import cz.muni.fi.pv256.movio.uco373993.model.Movie;
import cz.muni.fi.pv256.movio.uco373993.service.MovieDownloadService;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,
        LoaderManager.LoaderCallbacks<List<Movie>> {

    public List<Movie> mMovies = new ArrayList<>(20);
    private StickyGridHeadersGridView mGridView;
    private BroadcastReceiver mBroadcastReceiver;

    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MovieListFragment", "onCreateView() called, inflater: " + inflater + ", container: " + container + ", savedInstanceState: " + savedInstanceState);

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

        mBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                mGridView.setAdapter(new MovieAdapter(getActivity(), R.layout.grid_view_row, MovieLoader.getInstance().getMovies()));
            }

        };

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver, new IntentFilter(MovieDownloadService.BROADCAST_INTENT));

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getLoaderManager().getLoader(0) == null) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    @Override
    public void onStart() {
        Log.d("MovieListFragment", "onStart() called");
        super.onStart();

        if (MovieLoader.getInstance().hasData()) {
            mGridView.setAdapter(new MovieAdapter(getActivity(), R.layout.grid_view_row, MovieLoader.getInstance().getMovies()));
        } else {
            Intent intent = new Intent(getActivity(), MovieDownloadService.class);
            intent.putExtra(MovieDownloadService.DOWNLOAD_KEY, MovieDownloadService.RESULT_KEY);
            getActivity().startService(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.favoriteSwitcher);
        SwitchCompat switchCompat = (SwitchCompat) MenuItemCompat.getActionView(item);
        switchCompat.setOnCheckedChangeListener(this);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mGridView.setAdapter(new MovieAdapter(getActivity(), R.layout.grid_view_row, mMovies));
        } else {
            mGridView.setAdapter(new MovieAdapter(getActivity(), R.layout.grid_view_row, MovieLoader.getInstance().getMovies()));
        }
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieAsyncTaskLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        mMovies.clear();
        mMovies = data;
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }

    public interface Callback {
        void onItemClick(Movie movie);
    }

}
