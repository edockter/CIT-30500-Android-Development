package edu.iupui.ericdock.farbucks;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new HomeFragment();
    }
}
