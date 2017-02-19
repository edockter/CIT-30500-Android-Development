package app.ericdock.iupui.edu.nflteams;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ericd on 2/6/2017.
 */

public class NFLTeamListFragment extends Fragment {

    // Member Variables
    private RecyclerView mNFLTeamRecyclerView;
    private NFLTeamAdapter mNFLTeamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle SavedInstanceState) {

        // wire up the view container + RecyclerView
        View view = inflater.inflate(R.layout.fragment_nflteam_list,
                container,
                false);

        mNFLTeamRecyclerView = (RecyclerView) view.findViewById(R.id.nflteam_recycler_view);

        mNFLTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        NFLTeamBucket teamBucket = NFLTeamBucket.get(getActivity());
        List<NFLTeam> allTeams = teamBucket.getNFLTeamList();

        if (mNFLTeamAdapter == null) {
            mNFLTeamAdapter = new NFLTeamAdapter(allTeams);
            mNFLTeamRecyclerView.setAdapter(mNFLTeamAdapter);
        }
        else
        {
            mNFLTeamAdapter.notifyDataSetChanged();
        }

    }

    private class NFLTeamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private NFLTeam mTeam;
        private TextView mNFLTeamNameTextView;
        private TextView mNFLTeamDivisionTextView;
        private ImageView mNFLTeamLogoImageView;

        public NFLTeamHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // wire up textviews
            mNFLTeamNameTextView = (TextView) itemView.findViewById(R.id.list_item_nflteam_title_text_view);
            mNFLTeamDivisionTextView = (TextView) itemView.findViewById(R.id.list_item_nflteam_division_text_view);

            // wire up imageview
            mNFLTeamLogoImageView = (ImageView) itemView.findViewById(R.id.list_item_logo_imageview);
        }

        @Override
        public void onClick(View v) {
            // INITIAL TOAST CODE
            // Toast.makeText(getActivity(), mNFLTeam.getTeamName() + " clicked!", Toast.LENGTH_SHORT).show();

            // BLANK INTENT -- no communication
            // Intent intent = new Intent(getActivity(), NFLTeamDetailActivity.class);

            Intent intent = NFLTeamPagerActivity.newIntent(getActivity(), mTeam.getId());
            startActivity(intent);
        }

        public void bindNFLTeam(NFLTeam nflTeam) {
            // get context and drawable from the filename we store in the Team Bucket
            Context context = mNFLTeamLogoImageView.getContext();
            int logoId = context.getResources().getIdentifier(nflTeam.getTeamShortName(), "drawable", context.getPackageName());

            // IN-CLASS METHOD for drawableID
            // String uri = "@drawable/" + nflTeam.getTeamShortName();
            // int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());

            // set member variables
            mTeam = nflTeam;
            mNFLTeamNameTextView.setText(nflTeam.getTeamName());
            mNFLTeamDivisionTextView.setText(nflTeam.getDivision());
            // mNFLTeamLogoImageView.setImageDrawable(drawable);
            Glide.with(getContext()).load(logoId).into(mNFLTeamLogoImageView);
        }
    }
    private class NFLTeamAdapter extends RecyclerView.Adapter<NFLTeamHolder> {

        private List<NFLTeam> mNFLTeamList;

        // constructor
        public NFLTeamAdapter(List<NFLTeam> NFLTeams) {
            mNFLTeamList = NFLTeams;
        }

        // Override 3 methods required by RecyclerView.Adapter
        @Override
        public NFLTeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_team,
                    parent,
                    false);

            return new NFLTeamHolder(view);
        }

        @Override
        public void onBindViewHolder(NFLTeamHolder holder, int position) {
            NFLTeam team = mNFLTeamList.get(position);
            holder.bindNFLTeam(team);
        }

        @Override
        public int getItemCount() {
            return mNFLTeamList.size();
        }
    }
}