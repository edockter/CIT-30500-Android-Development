package app.ericdock.iupui.edu.listenup;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import app.ericdock.iupui.edu.listenup.model.Podcast;
import app.ericdock.iupui.edu.listenup.model.SavedPreferences;
import retrofit2.Call;
import retrofit2.Callback;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by ericd on 4/10/2017.
 */

public class PodcastDetailFragment extends Fragment {

    // Key to find our Podcast
    private static final String ARG_LISTENUP_TAG = "listenup_podcast_url";
    private static final String EXTRA_PODCAST_BUNDLE = "listenup_podcast_detail";

    // Saved preferences
    private SavedPreferences mSavedPreferences;

    // POD CAST DA TA
    private Podcast mPodcast;

    // form controls
    private ImageView mLogoImageView;
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private FloatingActionButton mAddPodcastButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // see if we have a saved user
        mSavedPreferences  =  mSavedPreferences.get(getActivity());

        // show button if they are
        if (mSavedPreferences.isUserLoggedIn()) {
            mAddPodcastButton.setVisibility(VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPodcast = (Podcast) getActivity().getIntent().getSerializableExtra(PodcastDetailActivity.EXTRA_PODCAST_BUNDLE);

        View view = inflater.inflate(R.layout.fragment_podcastdetail, container, false);

        // Wire them up
        mLogoImageView = (ImageView) view.findViewById(R.id.podcast_detail_logo_imageview);
        mTitleTextView = (TextView) view.findViewById(R.id.podcast_detail_title_textview);
        mDescriptionTextView = (TextView) view.findViewById(R.id.podcast_detail_description_textview);
        mAddPodcastButton = (FloatingActionButton) view.findViewById(R.id.add_podcast_fab);

        // Set the texts
        mTitleTextView.setText(mPodcast.getTitle());
        mDescriptionTextView.setText(mPodcast.getDescription());

        // show image
        Glide.with(getContext()).load(mPodcast.getLogo_url()).into(mLogoImageView);

        // set button listener
        mAddPodcastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Define the call
                GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, mSavedPreferences.getUsername(), mSavedPreferences.getPassword());

                // Get the beach body we need (in 90 days or less!)
                JsonObject bodyContents = new JsonObject();

                JsonArray array = new JsonArray();
                array.add(mPodcast.getUrl());

                bodyContents.add("add", array);

                Call call = client.uploadSubscriptionChanges(bodyContents, mSavedPreferences.getUsername(), mSavedPreferences.getPassword());

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, retrofit2.Response response) {
                        Log.d("LISTENUP", String.format("Login response received, code %s", response.code()));

                        if (response.isSuccessful()) {
                            Log.d("LISTENUP", String.format("Podcast Subscription Success!  Status: %s", response.code()));
                            Toast.makeText(getContext(), "Subscription saved!", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.code() >= 400 && response.code() < 500) {
                            Log.d("LISTENUP", String.format("Bad subscribe request!  Status: %s", response.code()));
                            Toast.makeText(getContext(), "Bad login. Subscription not saved..", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.code() >= 500 && response.code() < 600) {
                            Log.d("LISTENUP", String.format("Subscribe server error!  Status: %s", response.code()));
                            Toast.makeText(getContext(), "Server error has occurred. Try again later.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("LISTENUP", String.format("Unexpected login response.  Status: %s Response: %s", response.code(), response.body()));
                        }
                    }


                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.d("LISTENUP", "Error calling Add Subscription method of API.");
                        Log.d("LISTENUP", t.toString());
                        Toast.makeText(getContext(), "Error adding Subscription. API has probably timed out.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    public static PodcastDetailFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LISTENUP_TAG, url);

        PodcastDetailFragment fragment = new PodcastDetailFragment();
        fragment.setArguments(args);

        Log.d("FARBUCKS", "url is added to bundle.");
        Log.d("FARBUCKS", url);

        return fragment;
    }
}
