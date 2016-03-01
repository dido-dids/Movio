package cz.muni.fi.pv256.movio.uco373993;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cz.muni.fi.pv256.movio.uco373993.model.Movie;
import cz.muni.fi.pv256.movio.uco373993.service.TheMovieDBApi;

/**
 * Created by David Boron on 16.10.2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private int mLayoutResourceid;

    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutResourceid = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = ((Activity) mContext).getLayoutInflater().inflate(mLayoutResourceid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.row);
            convertView.setTag(viewHolder);
            Log.i("adapter", "inflate radku " + position);
            Log.i("movie","movie: "+ movie.getTitle());
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.i("adapter","recyklace radku "+ position);
        }

        Picasso.with(mContext).load(TheMovieDBApi.IMAGES_URL + movie.getCoverPath())
                .fit()
                .centerCrop()
                .into(viewHolder.mImageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageView;
    }


}
