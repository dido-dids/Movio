package cz.muni.fi.pv256.movio.uco373993;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MovieListFragment.Callback {

    private boolean mTwoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail) != null) {
            mTwoPanel = true;
        } else {
            mTwoPanel = false;
        }
    }

    @Override
    public void onItemClick(Movie movie) {
        if (mTwoPanel) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Movie", movie);
            movieDetailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail, movieDetailFragment).commit();
        } else {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("Movie", movie);
            startActivity(intent);
        }
    }
}
