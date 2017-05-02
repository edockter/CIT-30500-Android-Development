package model;

import android.app.Application;

import org.greenrobot.greendao.query.WhereCondition;

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
    private MenucategoryDao mMenuCategoryDao;

    private DaoSession mDaoSession;

    // CONSTRUCTOR
    private FarbucksBucket(Application context) {
        mDaoSession = ((FarbucksApp) context).getDaoSession();
        mLocationDao = mDaoSession.getLocationDao();
        mMenuItemDao = mDaoSession.getMenuitemDao();
        mMenuCategoryDao = mDaoSession.getMenucategoryDao();
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

    public List<Menuitem> getDistinctMenuItems() {
        List<Menuitem> distinctMenuItems = mMenuItemDao.queryBuilder()
                .where(new WhereCondition.StringCondition("1 GROUP BY NAME")).list();

        return distinctMenuItems;
    }

    // getSizesAndPrices

    // Search by Name to process Sizes & prices
    public List<Menuitem> getSizesAndPrices(String name) {
        List<Menuitem> menuItems = mMenuItemDao.queryBuilder()
                .where(MenuitemDao.Properties.Name.eq(name))
                .orderAsc(MenuitemDao.Properties.Name).list();

        return menuItems;
    }

    // RETURN A SINGLE MENU ITEM GIVEN AN ID
    public Menuitem getMenuitem(Long menuitemId) {
        Menuitem menuitem = (Menuitem) mMenuItemDao.load(menuitemId);
        return menuitem;
    }

    // RETURN A CATEGORY NAME GIVEN AN ID
    public String getCategoryName(Long menuCategoryId) {
        Menucategory category = mMenuCategoryDao.load(menuCategoryId);
        return category.getName();
    }


    // RETURN A CATEGORY NAME GIVEN AN ID (With string conversion)
    public String getCategoryName(String menuCategoryId) {
        Long categoryId = Long.valueOf(menuCategoryId);
        Menucategory category = mMenuCategoryDao.load(categoryId);
        return category.getName();
    }
}