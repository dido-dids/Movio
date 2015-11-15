package cz.muni.fi.pv256.movio.uco373993;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    public MovieDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        Movie movie = getArguments().getParcelable("Movie");
        TextView title = (TextView) view.findViewById(R.id.title);
        if (movie != null) {
            title.setText("" + movie.getTitle());

            ImageView cover = (ImageView) view.findViewById(R.id.cover);
            Picasso.with(getActivity()).load(movie.getCoverPath()).into(cover);
        }
        return view;
    }


}
