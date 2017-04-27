package app.ericdock.iupui.edu.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

/**
 * Created by ericd on 4/26/2017.
 */

public class LoginFragment extends Fragment {
    private TextView mUsernameTextview;
    private TextView mPasswordTextview;
    private Button mLoginButton;
    private String mUsername;
    private String mPassword;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        // wire up
        mUsernameTextview = (TextView) view.findViewById(R.id.login_username_edittext);
        mPasswordTextview = (TextView) view.findViewById(R.id.login_password_edittext);

        return view;
    }

}
