package app.ericdock.iupui.edu.listenup;

import android.support.v4.app.Fragment;

/**
 * Created by Eric on 5/2/2017.
 */

public class SubscriptionsActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new SubscriptionsFragment();
    }
}