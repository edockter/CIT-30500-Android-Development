package model;

import android.app.Application;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import edu.iupui.ericdock.farbucks.FarbucksApp;

/**
 * Created by ericd on 3/6/2017.
 */

public class FarbucksBucket {
    // CREATE A STATIC VARIABLE THAT HOLDS A REFERENCE TO THE ONE AND ONLY OBJECT OF THIS CLASS
    private static FarbucksBucket sFarbucksBucket;

    private LocationDao mLocationDao;
    private MenuitemDao mMenuItemDao;

    private DaoSession mDaoSession;

    // CONSTRUCTOR
    private FarbucksBucket(Application context) {
        mDaoSession = ((FarbucksApp) context).getDaoSession();
        mLocationDao = mDaoSession.getLocationDao();
        mMenuItemDao = mDaoSession.getMenuitemDao();
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


    // RETURN A SINGLE MENU ITEM GIVEN AN ID
    public Location getLocation(Long locationId) {
        Location location = (Location) mLocationDao.load(locationId);
        return location;
    }

    // RETURN A LIST OF MENU ITEMS IN THE DATABASE
    public List<Menuitem> getMenuItems() {
        List<Menuitem> allMenuitems = mMenuItemDao.loadAll();
        return allMenuitems;
    }

    // RETURN A SINGLE MENU ITEM GIVEN AN ID
    public Menuitem getMenuitem(Long menuitemId) {
        Menuitem menuitem = (Menuitem) mMenuItemDao.load(menuitemId);
        return menuitem;
    }
}
