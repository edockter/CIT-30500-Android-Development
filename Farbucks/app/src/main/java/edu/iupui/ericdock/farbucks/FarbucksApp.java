package edu.iupui.ericdock.farbucks;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import edu.iupui.ericdock.farbucks.model.DaoMaster;
import edu.iupui.ericdock.farbucks.model.DaoSession;

/**
 * Created by ericd on 3/6/2017.
 */

public class FarbucksApp extends Application {

    private static final String DATABASE_NAME = "farbucks_db";

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // get to this shortly
        // checkFirstRun(getApplicationContext());

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DATABASE_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


}
