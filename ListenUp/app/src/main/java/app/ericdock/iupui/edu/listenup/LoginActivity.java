package app.ericdock.iupui.edu.listenup;

import android.support.v4.app.Fragment;

/**
 * Created by ericd on 4/26/2017.
 */

public class LoginActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new LoginFragment();
    }
}
