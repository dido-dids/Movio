package cz.muni.fi.pv256.movio.uco373993.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;

import cz.muni.fi.pv256.movio.uco373993.MovieLoader;
import cz.muni.fi.pv256.movio.uco373993.model.Movie;
import cz.muni.fi.pv256.movio.uco373993.model.MovieDataObject;
import retrofit.Call;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static cz.muni.fi.pv256.movio.uco373993.R.string.notification_error_text;
import static cz.muni.fi.pv256.movio.uco373993.R.string.notification_error_title;

public class MovieDownloadService extends IntentService {

    public static final String BROADCAST_INTENT = "broadcastIntent";
    public static final String DOWNLOAD_KEY = "download";
    public static final String RESULT_KEY = "data_result";

    private Retrofit mRetrofit;
    private TheMovieDBApi mMDBApi;
    private Gson mGson;

    public MovieDownloadService() {
        this("MovieDownloadService");
    }

    public MovieDownloadService(String name) {
        super(name);
        mGson = new Gson();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        initRetrofit();
        Call<Movie[]> MoviesCall = mMDBApi.loadInTheatreMovies(TheMovieDBApi.API_KEY, TheMovieDBApi.QUERY_PARAM_SORT_BY);

        try {
            Response response = MoviesCall.execute();

            if (response  != null) {
                if (response.isSuccess()) {
                    Movie[] Movies = (Movie[]) response.body();
                    MovieLoader.getInstance().addMovies(Arrays.asList(Movies));
                } else {
                    errorNotification();
                    return;
                }
            } else {
                errorNotification();
                return;
            }
        } catch (IOException ex) {
            errorNotification();
            return;
        }

        Intent broadcastIntent = new Intent(BROADCAST_INTENT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }

    private void errorNotification() {
        Notification notif = new NotificationCompat.Builder(this)
                .setContentTitle(getString(notification_error_title))
                .setContentText(getString(notification_error_text))
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notif);
    }

    private void initRetrofit() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDBApi.URL)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
                        return new Converter<ResponseBody, Movie[]>() {

                            @Override
                            public Movie[] convert(ResponseBody value) throws IOException {
                                MovieDataObject resultWrapper = mGson.fromJson(value.charStream(), MovieDataObject.class);
                                value.close();
                                return resultWrapper.getResults();
                            }

                        };
                    }
                })
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMDBApi = mRetrofit.create(TheMovieDBApi.class);
    }
}
