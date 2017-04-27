package app.ericdock.iupui.edu.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by ericd on 4/24/2017.
 */

public class DeviceListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DeviceListFragment().newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, DeviceListActivity.class);

        return intent;
    }
}