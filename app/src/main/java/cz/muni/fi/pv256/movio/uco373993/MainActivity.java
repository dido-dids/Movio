package cz.muni.fi.pv256.movio.uco373993;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Film> mMovies = new ArrayList<>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FilmAdapter adapter = new FilmAdapter(this, R.layout.grid_view_row, mMovies);
        GridView gridView = (GridView) findViewById(R.id.movie_grid_view);
        gridView.setEmptyView(findViewById(R.id.empty));
        TextView emptyText = (TextView) findViewById(R.id.empty_text);
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
                startActivity(new Intent(MainActivity.this, MovieDetailActivity.class));
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, (CharSequence) mMovies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //pre istotu
        adapter.notifyDataSetChanged();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
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
                "https://image.tmdb.org/t/p/w185/z3nGs7UED9XlqUkgWeT4jQ80m1N.jpg"
        };

        for (int i = 0; i < covers.length; i++) {
            mMovies.add(i, new Film(123, covers[i], "title" + i));
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
