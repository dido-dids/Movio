package cz.muni.fi.pv256.movio.uco373993.db;

import com.tojc.ormlite.android.OrmLiteSimpleContentProvider;

public class Provider extends OrmLiteSimpleContentProvider<DbHelper> {
    @Override
    protected Class<DbHelper> getHelperClass() {
        return DbHelper.class;
    }

    @Override
    public boolean onCreate() {
        return false;
    }
}
