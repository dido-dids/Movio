package cz.muni.fi.pv256.movio.uco373993;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    List<Movie> mMovies = new ArrayList<>(20);
    private Callback mCallback;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        MovieAdapter adapter = new MovieAdapter(getActivity(), R.layout.grid_view_row, mMovies);
        StickyGridHeadersGridView gridView = (StickyGridHeadersGridView) view.findViewById(R.id.movie_grid_view);
        gridView.setEmptyView(view.findViewById(R.id.empty));

        TextView emptyText = (TextView) view.findViewById(R.id.empty_text);
        gridView.setEmptyView(emptyText);

        if (isNetworkAvailable()) {
            initMovies();
            if (mMovies.isEmpty()) {
                emptyText.setText("žádná data");
            }
        } else {
            emptyText.setText("žádné připojení");
        }



        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Callback) getActivity()).onItemClick(mMovies.get(position));
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), mMovies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

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
            mMovies.add(i, new Movie(123, covers[i], "title" + i));
        }
    }

    public interface Callback {
        void onItemClick(Movie movie);
    }

}
