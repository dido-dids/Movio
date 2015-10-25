package cz.muni.fi.pv256.movio.uco373993;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmDetailFragment extends Fragment {


    public FilmDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film_detail, container, false);
        Film film = getArguments().getParcelable("Film");
        TextView title = (TextView) view.findViewById(R.id.title);
        if (film!=null) title.setText(""+film.getTitle());
        return view;
    }


}
