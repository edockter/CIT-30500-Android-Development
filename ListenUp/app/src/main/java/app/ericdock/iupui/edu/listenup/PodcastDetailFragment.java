package app.ericdock.iupui.edu.listenup;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import app.ericdock.iupui.edu.listenup.model.Podcast;

/**
 * Created by ericd on 4/10/2017.
 */

// use URL for data
public class PodcastDetailFragment extends Fragment {

    // Key to find our Podcast
    private static final String ARG_LISTENUP_TAG = "listenup_podcast_url";
    private static final String EXTRA_PODCAST_BUNDLE = "listenup_podcast_detail";

    private Podcast mPodcast;

    // form controls
    private ImageView mLogoImageView;
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPodcast = (Podcast) getActivity().getIntent().getSerializableExtra(PodcastDetailActivity.EXTRA_PODCAST_BUNDLE);

        View view = inflater.inflate(R.layout.fragment_podcastdetail, container, false);

        // Wire them up
        mLogoImageView = (ImageView) view.findViewById(R.id.podcast_detail_logo_imageview);
        mTitleTextView = (TextView) view.findViewById(R.id.podcast_detail_title_textview);
        mDescriptionTextView = (TextView) view.findViewById(R.id.podcast_detail_description_textview);

        // Set the texts
        mTitleTextView.setText(mPodcast.getTitle());
        mDescriptionTextView.setText(mPodcast.getDescription());

        // show image
        Glide.with(getContext()).load(mPodcast.getLogo_url()).into(mLogoImageView);

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
