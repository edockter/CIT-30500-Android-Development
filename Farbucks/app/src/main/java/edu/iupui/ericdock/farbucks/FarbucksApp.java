package edu.iupui.ericdock.farbucks;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.DaoMaster;
import model.DaoSession;

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
        checkFirstRun(getApplicationContext());

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DATABASE_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();

        Iconify.with(new FontAwesomeModule());
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    // METHOD THAT CHECKS TO SEE IF THIS IS THE FIRST TIME THE APP IS RUN ON THIS DEVICE
    private void checkFirstRun(Context context) {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;


        // Get current version code
        int currentVersionCode = 0;
        try {
            currentVersionCode = (context).getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            // handle exception
            e.printStackTrace();
            return;
        }

        // Get saved version code
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            // ON FIRST RUN, COPY THE PACKAGED DATABASE FROM ASSETS
            copyDatabaseFromAssets(context, "farbucks_db", false);

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade

        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).commit();
    }

    // THIS METHOD COPIES THE DATABASE FROM THE ASSETS FOLDER AND USES IT AS THE DATA SOURCE FOR THE APPLICATION
    private boolean copyDatabaseFromAssets(Context context, String databaseName , boolean overwrite)  {

        Log.d("FARBUCKS", "In copyDatabaseFromAssets");

        File outputFile = context.getDatabasePath(databaseName);

        Log.d("FARBUCKS", "outputFile: " + outputFile);

        if (outputFile.exists() && !overwrite) {
            return true;
        }

        outputFile = context.getDatabasePath(databaseName + ".temp");

        Log.d("FARBUCKS", "outputFile Temp: " + outputFile);

        outputFile.getParentFile().mkdirs();

        try {
            InputStream inputStream = context.getAssets().open(databaseName);
            OutputStream outputStream = new FileOutputStream(outputFile);


            // transfer bytes from the input stream into the output stream
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the streams
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            outputFile.renameTo(context.getDatabasePath(databaseName));

        } catch (IOException e) {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            return false;
        }

        return true;
    }
}
