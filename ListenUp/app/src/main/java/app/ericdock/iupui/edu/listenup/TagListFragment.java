package app.ericdock.iupui.edu.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.ericdock.iupui.edu.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericd on 4/3/2017.
 */

public class TagListFragment extends Fragment {

    private RecyclerView mTagListRecyclerView;
    private ProgressBar mProgressBar;
    private TagAdapter mTagAdapter;
    private List<Tag> mTagList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        // Wire up recyclerview
        mTagListRecyclerView = (RecyclerView) view.findViewById(R.id.taglist_recycler_view);
        mTagListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Hide RecyclerView
        mTagListRecyclerView.setVisibility(View.GONE);

        // Wire up progress indicator
        mProgressBar = (ProgressBar) view.findViewById(R.id.taglist_progress);

        // Connect to API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        // Fetch a list of tags
        Call<Tag[]> call = client.getTopTags("100");

        //
        call.enqueue(new Callback<Tag[]>() {
            @Override
            public void onResponse(Call<Tag[]> call, Response<Tag[]> response) {
                Tag[] tags = response.body();
                updateUI(tags);
                Log.d("LISTENUP", "Success!");
                Log.d("LISTENUP", response.body().toString());

                // Hide progress bar and show recyclerview
                mProgressBar.setVisibility(View.GONE);
                mTagListRecyclerView.setVisibility(View.VISIBLE);
                mTagAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Tag[]> call, Throwable t) {
                Log.d("LISTENUP", "call = " + call.toString());
                Log.d("LISTENUP", t.toString());
                Log.d("LISTENUP", "Womp womp.");

                // Report error to user
                Tag errorTag = new Tag();
                errorTag.setTitle("Error.");
                errorTag.setTag("Error contacting API. Try again later.");
                Tag[] errorArray = { errorTag };

                updateUI(errorArray);
            }
        });


        // set title
        getActivity().setTitle("Tags");

        // Pass an empty array on first run so we get the Adapter attached
        Tag[] emptyTags = null;
        updateUI(emptyTags);
        return view;
    }

    public void updateUI(Tag[] tags) {

        // Attach a blank list on first run so we successfully attach before running the query
        if (tags == null) {
            Tag loadingTag = new Tag();
            loadingTag.setTitle("Loading...");
            loadingTag.setTag("Loadin' Up, Hold Tight Buddy!");
            mTagList = new ArrayList<Tag>();
            mTagList.add(loadingTag);
        }
        else {
            mTagList = Arrays.asList(tags);
        }

        mTagAdapter = new TagAdapter(mTagList);
        mTagListRecyclerView.setAdapter(mTagAdapter);
    }

    private class TagListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Tag mTag;
        private TextView mTagTextView;

        public TagListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTagTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void bindTag(Tag tag) {

            mTag = tag;

            this.mTagTextView.setText(tag.getTag());
        }

        @Override
        public void onClick(View v) {
            Fragment podcastListFragment = PodcastListFragment.newInstance(mTag.getTag());

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, podcastListFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack("Podcasts").commit();
        }
    }

    private class TagAdapter extends RecyclerView.Adapter<TagListHolder> {
        private List<Tag> mTagList;

        public TagAdapter(List<Tag> allTags) {
            mTagList = allTags;
        }

        @Override
        public TagListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new TagListHolder(view);
        }

        @Override
        public void onBindViewHolder(TagListHolder holder, int position) {
            Tag thisTag = mTagList.get(position);

            holder.bindTag(thisTag);
        }

        @Override
        public int getItemCount() {
            return mTagList.size();
        }
    }
}
