package edu.iupui.ericdock.farbucks;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Eric on 2/27/2017.
 */

public class OrderActivity extends AppCompatActivity {
    protected Fragment createFragment() {
        return new OrderFragment();
    }
}
