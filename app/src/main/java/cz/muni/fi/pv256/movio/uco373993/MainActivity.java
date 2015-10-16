package cz.muni.fi.pv256.movio.uco373993;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Film> movies = new ArrayList<>(20);

        Film film1 = new Film(1, "http://image.tmdb.org/t/p/w185/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg", "title1");
        Film film2 = new Film(2, "http://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg", "title2");
        Film film3 = new Film(3, "http://image.tmdb.org/t/p/w185/kqjL17yufvn9OVLyXYpvtyrFfak.jpg", "title3");

        movies.add(film1);
        movies.add(film2);
        movies.add(film3);

//        R.layout.activity_main

        setContentView(R.layout.activity_main);

        FilmAdapter adapter = new FilmAdapter(this, R.layout.grid_view_row, movies);
        GridView gridView = (GridView) findViewById(R.id.movie_grid_view);
        gridView.setEmptyView(findViewById(R.id.empty));
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

      /*  Random random = new Random();

        List<Bar> data = new ArrayList<Bar>(20);
        for (int i = 0; i < 20; i++) {
            data.add(new Bar(random.nextInt()));
        }
        setListAdapter(new FooAdapter(this, data));*/


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    /*
    private static class FooAdapter extends BaseAdapter {

        private List<Bar> data;
        private Context context;

        public FooAdapter(Context context, List<Bar> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return data.get(position).i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            }

            TextView tv = (TextView) convertView;
            tv.setText(String.valueOf(data.get(position).i));

            return convertView;
        }
    }

    private static class Bar{

        public int i;

        public Bar(int i){
            this.i = i;
        }
    }

    */

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
