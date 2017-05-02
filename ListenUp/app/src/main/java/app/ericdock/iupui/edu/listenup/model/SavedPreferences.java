package app.ericdock.iupui.edu.listenup.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import app.ericdock.iupui.edu.listenup.R;

/**
 * Created by Eric on 5/2/2017.
 */

// Singletons
public class SavedPreferences {
    // this singleton
    private static SavedPreferences sSavedPreferences;

    // vars
    private static SharedPreferences mSavedUser;
    private boolean mUserLoggedIn;
    private String mPassword;
    private String mUsername;
    private String mDeviceId;

    private SavedPreferences(Activity activity) {
        mSavedUser =  activity.getPreferences(Context.MODE_PRIVATE);

        // get login info
        mUserLoggedIn = mSavedUser.getBoolean(activity.getResources().getString(R.string.BOOLEAN_KEY), false);
        mUsername = mSavedUser.getString(activity.getResources().getString(R.string.USERNAME_KEY), null);
        mPassword = mSavedUser.getString(activity.getResources().getString(R.string.PASSWORD_KEY), null);
        mDeviceId = mSavedUser.getString(activity.getResources().getString(R.string.DEVICE_KEY), null);
    }

    // retrieve singleton
    public static SavedPreferences get(Activity activity) {

        if (sSavedPreferences == null) {
            sSavedPreferences = new SavedPreferences(activity);
        }

        return sSavedPreferences;
    }

    public static SavedPreferences getSavedPreferences() {
        return sSavedPreferences;
    }

    public static void setSavedPreferences(SavedPreferences savedPreferences) {
        sSavedPreferences = savedPreferences;
    }

    public static SharedPreferences getmSavedUser() {
        return mSavedUser;
    }

    public static void setmSavedUser(SharedPreferences mSavedUser) {
        SavedPreferences.mSavedUser = mSavedUser;
    }

    public boolean isUserLoggedIn() {
        return mUserLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        mUserLoggedIn = userLoggedIn;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        mDeviceId = deviceId;
    }
}
