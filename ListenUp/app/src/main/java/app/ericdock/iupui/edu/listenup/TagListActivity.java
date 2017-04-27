package app.ericdock.iupui.edu.listenup;

import android.support.v4.app.Fragment;

/**
 * Created by ericd on 4/3/2017.
 */

public class TagListActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {

        return new TagListFragment();
    }
}
