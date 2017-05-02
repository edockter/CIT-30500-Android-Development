package app.ericdock.iupui.edu.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericd on 4/24/2017.
 */

public class DeviceListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Connect to API, uses username and password for GPodder
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, "username", "password");

        // Fetch a list of devices for a given username
        Call<String> call = client.getDevices("username");
        Log.d("LISTENUP", "Making devices call to API...");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseBody = response.body();
                Log.d("LISTENUP", "Success!");
                Log.d("LISTENUP", String.valueOf(response.code()) + String.valueOf(response.isSuccessful()));
                Log.d("LISTENUP", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("LISTENUP", "Womp womp.");
            }
        });

        return null;
    }

    public static DeviceListFragment newInstance() {
        DeviceListFragment fragment = new DeviceListFragment();
        return fragment;
    }
}
