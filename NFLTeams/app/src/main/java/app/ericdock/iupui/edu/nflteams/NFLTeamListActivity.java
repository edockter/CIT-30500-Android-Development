package app.ericdock.iupui.edu.nflteams;

import android.support.v4.app.Fragment;

/**
 * Created by ericd on 2/6/2017.
 */

public class NFLTeamListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NFLTeamListFragment();
    }
}
