package cz.muni.fi.pv256.movio.uco373993;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Film> mMovies = new ArrayList<>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        R.layout.activity_main

        setContentView(R.layout.activity_main);

        FilmAdapter adapter = new FilmAdapter(this, R.layout.grid_view_row, mMovies);
        GridView gridView = (GridView) findViewById(R.id.movie_grid_view);
        gridView.setEmptyView(findViewById(R.id.empty));
        TextView emptyText = (TextView) findViewById(R.id.empty_text);
        gridView.setEmptyView(emptyText);

        if (hasConnection()) {
            initMovies();
        }
        else{
            emptyText.setText("zadne data/pripojeni");
        }

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, MovieDetailActivity.class));
            }
        });
        //pre istotu :D
        adapter.notifyDataSetChanged();
        Log.v("image", adapter.getItem(0).getCoverPath());

    }

    private boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        }
        return ni.isConnected();
    }

    public void initMovies(){
        Film film1 = new Film(1, "http://image.tmdb.org/t/p/w185/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg", "title1");
        Film film2 = new Film(2, "http://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg", "title2");
        Film film3 = new Film(3, "http://image.tmdb.org/t/p/w185/kqjL17yufvn9OVLyXYpvtyrFfak.jpg", "title3");

        mMovies.add(film1);
        mMovies.add(film2);
        mMovies.add(film3);
    }

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
    }
}
