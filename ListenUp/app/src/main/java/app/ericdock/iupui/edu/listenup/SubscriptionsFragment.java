package app.ericdock.iupui.edu.listenup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import app.ericdock.iupui.edu.listenup.model.Podcast;
import app.ericdock.iupui.edu.listenup.model.SavedPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.ericdock.iupui.edu.listenup.PodcastDetailActivity.EXTRA_PODCAST_BUNDLE;

/**
 * Created by Eric on 5/2/2017.
 */

public class SubscriptionsFragment extends Fragment {
    private RecyclerView mSubscriptionsRecyclerView;
    private ProgressBar mProgressBar;
    private SubscriptionAdapter mSubscriptionAdapter;
    private List<Podcast> mSubscriptionList;
    private SavedPreferences mSavedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_subscriptions, container, false);

        // wire up
        mSubscriptionsRecyclerView = (RecyclerView) view.findViewById(R.id.subscriptions_recycler_view);
        mSubscriptionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressBar = (ProgressBar) view.findViewById(R.id.subscriptions_progress);

        // set title
        getActivity().setTitle("Subscriptions");

        // Get saved user
        mSavedPreferences = mSavedPreferences.get(getActivity());

        // Connect to API
        if (mSavedPreferences.isUserLoggedIn()) {
            GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, mSavedPreferences.getUsername(), mSavedPreferences.getPassword());
            Call<Podcast[]> call = client.getSubscriptions(mSavedPreferences.getUsername());

            // TODO Get subscriptions here
            call.enqueue(new Callback<Podcast[]>() {
                @Override
                public void onResponse(Call<Podcast[]> call, Response<Podcast[]> response) {
                    Podcast[] podcasts = response.body();
                    updateUI(podcasts);
                    Log.d("LISTENUP", "Successful subscription get!");
                    Log.d("LISTENUP", String.valueOf(podcasts.length));

                    // Hide progress bar and show recyclerview
                    mProgressBar.setVisibility(View.GONE);
                    mSubscriptionsRecyclerView.setVisibility(View.VISIBLE);
                    mSubscriptionAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Podcast[]> call, Throwable t) {
                    Log.d("LISTENUP", "call = " + call.toString());
                    Log.d("LISTENUP", t.toString());
                    Log.d("LISTENUP", "Womp womp. Subscriptions failed.");


                    // Hide progress bar and show recyclerview
                    mProgressBar.setVisibility(View.GONE);
                    mSubscriptionsRecyclerView.setVisibility(View.VISIBLE);

                }
            });
        }
        else {

        }

        return view;
    }

    public void updateUI(Podcast[] podcasts) {
        mSubscriptionList = Arrays.asList(podcasts);
        mSubscriptionAdapter = new SubscriptionAdapter(mSubscriptionList);
        mSubscriptionsRecyclerView.setAdapter(mSubscriptionAdapter);
    }

    private class SubscriptionListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Podcast mPodcast;
        private TextView mPodcastTitleTextView;
        private TextView mPodcastSubscribersTextView;   // Something interesting to show, why not
        private ImageView mLogoImageView;

        public SubscriptionListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mPodcastTitleTextView = (TextView) itemView.findViewById(R.id.list_item_podcast_title_textview);
            mPodcastSubscribersTextView = (TextView) itemView.findViewById(R.id.list_item_podcast_subscribers_textview);
            mLogoImageView = (ImageView) itemView.findViewById(R.id.list_item_podcast_logo_imageview);
        }

        public void bindPodcast(Podcast podcast) {
            mPodcast = podcast;

            this.mPodcastTitleTextView.setText(podcast.getTitle());
            this.mPodcastSubscribersTextView.setText(podcast.getSubscribers().concat(" Subscribers"));

            Glide.with(getContext()).load(mPodcast.getScaled_logo_url()).into(mLogoImageView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = PodcastDetailActivity.newIntent(getActivity(), mPodcast);
            intent.putExtra(EXTRA_PODCAST_BUNDLE, mPodcast);
            getActivity().startActivity(intent);
        }
    }

    private class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionListHolder> {
        private List<Podcast> mPodcastList;

        public SubscriptionAdapter(List<Podcast> allPodcasts) {
            mPodcastList = allPodcasts;
        }

        @Override
        public SubscriptionListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View view = inflater.inflate(R.layout.list_item_podcast, parent, false);

            return new SubscriptionListHolder(view);
        }

        @Override
        public void onBindViewHolder(SubscriptionListHolder holder, int position) {
            Podcast thisPodcast = mPodcastList.get(position);

            holder.bindPodcast(thisPodcast);
        }

        @Override
        public int getItemCount() {
            return mPodcastList.size();
        }
    }

    public static SubscriptionsFragment newInstance(String tag) {
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_LISTENUP_TAG, tag);

        SubscriptionsFragment fragment = new SubscriptionsFragment();
//        fragment.setArguments(args);

        return fragment;
    }
}
