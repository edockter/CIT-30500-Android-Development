package app.ericdock.iupui.edu.listenup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

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
    private String mDeviceKey;                  // pre-compiled device key

    private final String USERNAME_KEY = "gpodder_username";
    private final String PASSWORD_KEY = "gpodder_password";
    private final String BOOLEAN_KEY = "gpodder_login_status";
    private final String DEVICE_KEY = "gpodder_device_id";

    private SharedPreferences mSavedUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // wire up
        mUsernameEditText = (EditText) view.findViewById(R.id.login_username_edittext);
        mPasswordEditText = (EditText) view.findViewById(R.id.login_password_edittext);
        mLoginButton = (Button) view.findViewById(R.id.login_button);

        // see if we have a saved user
        mSavedUser =  getActivity().getPreferences(Context.MODE_PRIVATE);

        // recall previous items if they exist, if not get null
        mUsername = mSavedUser.getString(USERNAME_KEY, null);
        mPassword = mSavedUser.getString(PASSWORD_KEY, null);


        if (mUsername != null && mPassword != null) {
            mUsernameEditText.setText(mUsername);
            mPasswordEditText.setText(mPassword);
        }

        // If we are logged in, show different text
        if (mSavedUser.getBoolean(BOOLEAN_KEY, false)) {
            mLoginButton.setText(getResources().getString(R.string.login_button_loggedin));
        }

        // Compile device key now that our Resources() are available
        mDeviceKey = String.format("%s-%s", android.os.Build.MODEL, getResources().getString(R.string.app_name));
        // Get rid of spaces as they seem to confuse gPodder
        mDeviceKey = mDeviceKey.replace(' ', '-');

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Get our values
                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();

                // Only do call if the user has entered something
                if (mUsername.length() > 0 && mPassword.length() > 0) {
                    // Define the call
                    GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, mUsername, mPassword);
                    Call call = client.login(mUsername);

                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, retrofit2.Response response) {
                            Log.d("LISTENUP", String.format("Login response received, code %s", response.code()));

                            if (response.isSuccessful()) {
                                Log.d("LISTENUP", String.format("Login success!  Login status: %s", response.code()));
                                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                                // Success! Let's save these good credentials
                                mLoginSuccessful = true;
                                updateUI();
                                registerDevice();
                            }
                            else if (response.code() >= 400 && response.code() < 500) {
                                Log.d("LISTENUP", String.format("Bad login request!  Login status: %s", response.code()));
                                Log.d("LISTENUP", String.format("Login response: {0}", response.body()));
                                Toast.makeText(getContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                            }
                            else if (response.code() >= 500 && response.code() < 600) {
                                Log.d("LISTENUP", String.format("Login server error!  Login status: %s", response.code()));
                                Log.d("LISTENUP", String.format("Login response: {0}", response.body()));
                                Toast.makeText(getContext(), "Server error has occurred. Try again later.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Log.d("LISTENUP", String.format("Unexpected login response.  Status: %s Response: %s", response.code(), response.body()));
                            }
                        }


                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("LISTENUP", "Error calling Login method of API.");
                            Log.d("LISTENUP", t.toString());
                            Toast.makeText(getContext(), "Error calling Login method of API. It has probably timed out.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Please enter a Username and Password to login.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void updateUI() {
        if (mLoginSuccessful) {
            // Write credentials to sharedpreferences
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(USERNAME_KEY, mUsername);
            editor.putString(PASSWORD_KEY, mPassword);
            editor.putBoolean(BOOLEAN_KEY, mLoginSuccessful);
            editor.putString(DEVICE_KEY, mDeviceKey);
            editor.commit();

            // change login button
            // TODO move to strings
            mLoginButton.setText(getResources().getString(R.string.login_button_loggedin));

            // compile body to match API docs -- "caption" and "type"
            //      Type is always mobile
//                        JsonObject deviceBody = new JsonObject();
//
//                        deviceBody.addProperty("caption", String.format("ListenUp Device - {0}", android.os.Build.MODEL));
//                        deviceBody.addProperty("type", "mobile");

        } else {
            Toast.makeText(getContext(), "Login unsuccessful.", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerDevice() {
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, mUsername, mPassword);

        JsonObject bodyContents = new JsonObject();
        bodyContents.addProperty("caption", mDeviceKey);
        bodyContents.addProperty("type", "mobile");

        Call deviceCall = client.addDevice(mUsername, mDeviceKey, bodyContents);

        deviceCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("LISTENUP", "Error calling Update Device method of API.");
                Log.d("LISTENUP", t.toString());
            }

            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                Log.d("LISTENUP", "DeviceAdd response received.");

                if (response.isSuccessful()) {
                    Log.d("LISTENUP", String.format("Device Add success!  Status: %s", response.code()));
                    Toast.makeText(getContext(), "Device registered as a podcast client.", Toast.LENGTH_SHORT).show();
                }
                else if (response.code() >= 400 && response.code() < 500) {
                    Log.d("LISTENUP", String.format("Bad Device Add request!  Device call status: %s", response.code()));
                    Toast.makeText(getContext(), "Error adding device. Please login again.", Toast.LENGTH_SHORT).show();
                }
                else if (response.code() >= 500 && response.code() < 600) {
                    Log.d("LISTENUP", String.format("Device server error!  Device call status: %s", response.code()));
                    Toast.makeText(getContext(), "Error adding device. Please login again.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("LISTENUP", String.format("Unexpected device add response.  Status: %s", response.code()));
                }
            }
        });
    }
}
