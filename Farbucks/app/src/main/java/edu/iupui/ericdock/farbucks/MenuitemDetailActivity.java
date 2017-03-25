package edu.iupui.ericdock.farbucks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


/**
 * Created by ericd on 3/21/2017.
 */

public class MenuitemDetailActivity extends FragmentActivity {

    protected Fragment createFragment() {
        return new MenuitemDetailFragment();
    }
}
