package app.ericdock.iupui.edu.listenup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by ericd on 4/26/2017.
 */

public class LoginFragment extends Fragment {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private String mUsername;
    private String mPassword;
    private boolean mLoginSuccessful = false;   // Start with false, true if login works

    private final String USERNAME_KEY = "gpodder_username";
    private final String PASSWORD_KEY = "gpodder_password";
    private final String BOOLEAN_KEY = "gpodder_login_status";
    private final String DEVICE_KEY = "gpodder_device_id";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        // wire up
        mUsernameEditText = (EditText) view.findViewById(R.id.login_username_edittext);
        mPasswordEditText = (EditText) view.findViewById(R.id.login_password_edittext);
        mLoginButton = (Button) view.findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();

                GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, mUsername, mPassword);
                Call<ResponseBody> call = client.login(mUsername);


                if (mLoginSuccessful) {
                    // Write credentials to sharedpreferences
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(USERNAME_KEY, mUsername);
                    editor.putString(PASSWORD_KEY, mPassword);
                    editor.putBoolean(BOOLEAN_KEY, true);
                    editor.putString(DEVICE_KEY, "device-id");
                    editor.commit();
                }
                else {
                    Toast.makeText(getContext(), "Login unsuccessful.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
