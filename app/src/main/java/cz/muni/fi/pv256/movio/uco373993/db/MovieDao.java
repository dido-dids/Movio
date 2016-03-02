package cz.muni.fi.pv256.movio.uco373993.db;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco373993.model.Movie;

public class MovieDao {

    private static final String TAG = MovieDao.class.getSimpleName();
    private Context mContext;
    private Dao<Movie, Long> mDao;

    public MovieDao(Context context) {
        mContext = context;
    }

    private Dao<Movie, Long> getDao() throws SQLException {
        if(mDao == null) {
            DbHelper helper = OpenHelperManager.getHelper(mContext, DbHelper.class);
            mDao = helper.getDao(Movie.class);
        }

        return mDao;
    }

    public ArrayList<Movie> fetchAll(){
        ArrayList<Movie> list = new ArrayList<>();

        try{
            Dao<Movie, Long> MovieDao = getDao();
            List<Movie> items = MovieDao.queryForAll();

            list.addAll(items);
        }catch (SQLException e){
            Log.e(TAG, "fetching app alarms failed", e);
        }

        return list;
    }

    public Movie fetch(Long id){
        ArrayList<Movie> items = fetchAll();

        for(Movie item : items) {
            if(item.getId().equals(id))
                return item;
        }

        return null;
    }
    
    public boolean createOrUpdate(Movie item){
        try{
            Dao<Movie, Long> dao = getDao();
            dao.createOrUpdate(item);

            return true;
        } catch (SQLException e) {
            Log.e(TAG, "updating/creating audio items failed", e);
            return false;
        }
    }

    public boolean delete(Movie item){
        try{
            Dao<Movie, Long> dao = getDao();
            dao.delete(item);

            return true;
        } catch (SQLException e) {
            Log.e(TAG, "deleting audio items failed", e);
            return false;
        }
    }
}
