package app.ericdock.iupui.edu.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.ericdock.iupui.edu.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericd on 4/3/2017.
 */

public class TagListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = null;

        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        // Fetch a list of tags
        Call<Tag[]> call = client.getTopTags("20");

        call.enqueue(new Callback<Tag[]>() {
            @Override
            public void onResponse(Call<Tag[]> call, Response<Tag[]> response) {
                Log.d("LISTENUP", "Success!");
                Tag[] tags = response.body();
                Log.d("LISTENUP", String.valueOf(tags.length));
            }

            @Override
            public void onFailure(Call<Tag[]> call, Throwable t) {
                Log.d("LISTENUP", "call = " + call.toString());
                Log.d("LISTENUP", t.toString());
                Log.d("LISTENUP", "Womp womp.");
            }
        });

        return v;
    }
}
