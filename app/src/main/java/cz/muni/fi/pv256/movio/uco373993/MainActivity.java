package cz.muni.fi.pv256.movio.uco373993;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FilmListFragment.Callback{

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail) != null) {
            mTwoPane = true;
//            onItemClick();
        } else {
            mTwoPane = false;
        }


    }

    @Override
    public void onItemClick(Film film) {
        if (mTwoPane) {
            FilmDetailFragment filmDetailFragment = new FilmDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Film", film);
            filmDetailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail, filmDetailFragment).commit();
        } else {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("Film", film);
            startActivity(intent);
        }
    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
