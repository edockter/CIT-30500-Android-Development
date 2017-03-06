package model;

import android.app.Application;

import java.util.List;

import edu.iupui.ericdock.farbucks.FarbucksApp;

/**
 * Created by ericd on 3/6/2017.
 */

public class FarbucksBucket {
    // CREATE A STATIC VARIABLE THAT HOLDS A REFERENCE TO THE ONE AND ONLY OBJECT OF THIS CLASS
    private static FarbucksBucket sFarbucksBucket;

    private LocationDao mLocationDao;

    private DaoSession mDaoSession;

    // CONSTRUCTOR
    private FarbucksBucket(Application context) {
        mDaoSession = ((FarbucksApp) context).getDaoSession();
        mLocationDao = mDaoSession.getLocationDao();
    }

    // RETRIEVE THE SINGLE INSTANCE OF FarbucksBucket
    public static FarbucksBucket getInstance(Application context) {
        if (sFarbucksBucket == null) {
            sFarbucksBucket = new FarbucksBucket(context);
        }
        return sFarbucksBucket;
    }

    // RETURN A LIST OF ALL LOCATIONS IN THE DATABASE
    public List<Location> getLocations() {
        List<Location> allLocations = mLocationDao.loadAll();
        return allLocations;
    }
}
