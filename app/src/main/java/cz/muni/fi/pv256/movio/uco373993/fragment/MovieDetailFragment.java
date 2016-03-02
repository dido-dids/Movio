package cz.muni.fi.pv256.movio.uco373993.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio.uco373993.R;
import cz.muni.fi.pv256.movio.uco373993.db.MovieDao;
import cz.muni.fi.pv256.movio.uco373993.model.Movie;
import cz.muni.fi.pv256.movio.uco373993.service.TheMovieDBApi;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        final Movie movie = getArguments().getParcelable("Movie");
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView date = (TextView) view.findViewById(R.id.release_date);

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        MovieDao dao = new MovieDao(getActivity());
        if (movie != null) {
            if (dao.fetch(movie.getId()) != null) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_minus));
            } else {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_add));
            }
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDao dao = new MovieDao(getActivity());
                if (movie != null) {
                    if (dao.fetch(movie.getId()) == null) {
                        dao.createOrUpdate(movie);
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_minus));
                    } else {
                        dao.delete(movie);
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_add));
                    }
                }
            }
        });

        if (movie != null) {
            title.setText("" + movie.getTitle());
            date.setText("" + movie.getReleaseDate());

            ImageView cover = (ImageView) view.findViewById(R.id.cover);
            ImageView backdrop = (ImageView) view.findViewById(R.id.backgroundImage);

            Picasso.with(getActivity())
                    .load(TheMovieDBApi.IMAGES_URL + movie.getCoverPath())
                    .fit()
                    .centerCrop()
                    .into(cover);

            Picasso.with(getActivity())
                    .load(TheMovieDBApi.IMAGES_URL + movie.getBackdropPath())
                    .fit()
                    .centerCrop()
                    .into(backdrop);
        }
        return view;
    }


}
