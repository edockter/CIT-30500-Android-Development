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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericd on 4/3/2017.
 */

public class PodcastListFragment extends Fragment {
    // Key to find our Podcast tag
    private static final String ARG_LISTENUP_TAG = "listenup_podcast_tag";
    private static final String EXTRA_PODCAST_BUNDLE = "listenup_podcast_detail";

    private RecyclerView mPodcastListRecyclerView;
    private PodcastAdapter mPodcastAdapter;
    private ProgressBar mProgressBar;
    private List<Podcast> mPodcastList;
    private String mTag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        String tag = args.getString(ARG_LISTENUP_TAG);
        mTag = tag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_podcastlist, container, false);

        // wire up
        mPodcastListRecyclerView = (RecyclerView) view.findViewById(R.id.podcastlist_recycler_view);
        mPodcastListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressBar = (ProgressBar) view.findViewById(R.id.podcast_progress);

        // Hide RecyclerView
        mPodcastListRecyclerView.setVisibility(View.GONE);

        // Connect to API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        // Fetch a list of podcasts with the tag we got from out bundle
        Call<Podcast[]> call = client.getTaggedPodcasts("100", mTag);

        // set title
        getActivity().setTitle("\"" + mTag + "\"".concat(" Podcasts"));

        //
        call.enqueue(new Callback<Podcast[]>() {
            @Override
            public void onResponse(Call<Podcast[]> call, Response<Podcast[]> response) {
                Podcast[] podcasts = response.body();
                updateUI(podcasts);
                Log.d("LISTENUP", "Success!");
                Log.d("LISTENUP", String.valueOf(podcasts.length));

                // Hide progress bar and show recyclerview
                mProgressBar.setVisibility(View.GONE);
                mPodcastListRecyclerView.setVisibility(View.VISIBLE);
                mPodcastAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Podcast[]> call, Throwable t) {
                Log.d("LISTENUP", "call = " + call.toString());
                Log.d("LISTENUP", t.toString());
                Log.d("LISTENUP", "Womp womp.");

                // Hide progress bar and show recyclerview
                mProgressBar.setVisibility(View.GONE);
                mPodcastListRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    public void updateUI(Podcast[] podcasts) {
        mPodcastList = Arrays.asList(podcasts);
        mPodcastAdapter = new PodcastAdapter(mPodcastList);
        mPodcastListRecyclerView.setAdapter(mPodcastAdapter);
    }

    private class PodcastListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Podcast mPodcast;
        private TextView mPodcastTitleTextView;
        private TextView mPodcastSubscribersTextView;   // Something interesting to show, why not
        private ImageView mLogoImageView;

        public PodcastListHolder(View itemView) {
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

    private class PodcastAdapter extends RecyclerView.Adapter<PodcastListHolder> {
        private List<Podcast> mPodcastList;

        public PodcastAdapter(List<Podcast> allPodcasts) {
            mPodcastList = allPodcasts;
        }

        @Override
        public PodcastListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View view = inflater.inflate(R.layout.list_item_podcast, parent, false);

            return new PodcastListHolder(view);
        }

        @Override
        public void onBindViewHolder(PodcastListHolder holder, int position) {
            Podcast thisPodcast = mPodcastList.get(position);

            holder.bindPodcast(thisPodcast);
        }

        @Override
        public int getItemCount() {
            return mPodcastList.size();
        }
    }

    public static PodcastListFragment newInstance(String tag) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LISTENUP_TAG, tag);

        PodcastListFragment fragment = new PodcastListFragment();
        fragment.setArguments(args);

        return fragment;
    }
}