package cz.muni.fi.pv256.movio.uco373993.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco373993.MovieAdapter;
import cz.muni.fi.pv256.movio.uco373993.MovieLoader;
import cz.muni.fi.pv256.movio.uco373993.R;
import cz.muni.fi.pv256.movio.uco373993.model.Movie;
import cz.muni.fi.pv256.movio.uco373993.service.MovieDownloadService;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

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

        return view;
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public interface Callback {
        void onItemClick(Movie movie);
    }

}
