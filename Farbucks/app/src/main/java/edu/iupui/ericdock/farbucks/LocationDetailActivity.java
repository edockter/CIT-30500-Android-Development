package edu.iupui.ericdock.farbucks;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;


/**
 * Created by ericd on 3/21/2017.
 */

public class LocationDetailActivity extends FragmentActivity {

    protected Fragment createFragment() {
        return new LocationDetailFragment();
    }
}
