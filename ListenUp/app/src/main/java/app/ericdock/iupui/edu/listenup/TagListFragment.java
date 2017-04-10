package app.ericdock.iupui.edu.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private TagAdapter mTagAdapter;
    private Tag[] mAPITags;
    private List<Tag> mTagList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        // wire up
        mTagListRecyclerView = (RecyclerView) view.findViewById(R.id.taglist_recycler_view);
        mTagListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Connect to API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        // Fetch a list of tags
        Call<Tag[]> call = client.getTopTags("100");

        // set title
        getActivity().setTitle("Tags");

        call.enqueue(new Callback<Tag[]>() {
            @Override
            public void onResponse(Call<Tag[]> call, Response<Tag[]> response) {
                mAPITags = response.body();
                updateUI(mAPITags);
                Log.d("LISTENUP", "Success!");
                Log.d("LISTENUP", String.valueOf(mAPITags.length));
            }

            @Override
            public void onFailure(Call<Tag[]> call, Throwable t) {
                Log.d("LISTENUP", "call = " + call.toString());
                Log.d("LISTENUP", t.toString());
                Log.d("LISTENUP", "Womp womp.");
            }
        });

        return view;
    }

    public void updateUI(Tag[] tags) {
        mTagList = Arrays.asList(mAPITags);
        mTagAdapter = new TagAdapter(mTagList);
        mTagListRecyclerView.setAdapter(mTagAdapter);
    }

    private class TagListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Tag mTag;
        private TextView mTagTextView;

        public TagListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTagTextView = (TextView) itemView;
        }

        public void bindTag(Tag tag) {
            mTag = tag;

            this.mTagTextView.setText(tag.getTag());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mTag.getTag() + " clicked.", Toast.LENGTH_SHORT).show();
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
