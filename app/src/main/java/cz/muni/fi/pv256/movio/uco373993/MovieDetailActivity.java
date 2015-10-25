package cz.muni.fi.pv256.movio.uco373993;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Film film = getIntent().getParcelableExtra("Film");
        FilmDetailFragment filmDetailFragment = new FilmDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Film", film);
        filmDetailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.container, filmDetailFragment).commit();
    }
}
