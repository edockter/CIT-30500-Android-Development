package app.ericdock.iupui.edu.nflteams;

import android.content.Context;
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

    private void updateUI() {
        NFLTeamBucket teamBucket = NFLTeamBucket.get(getActivity());
        List<NFLTeam> allTeams = teamBucket.getNFLTeamList();

        mNFLTeamAdapter = new NFLTeamAdapter(allTeams);
        mNFLTeamRecyclerView.setAdapter(mNFLTeamAdapter);
    }

    private class NFLTeamHolder extends RecyclerView.ViewHolder {
        public NFLTeam mTeam;
        public TextView mNFLTeamNameTextView;
        public TextView mNFLTeamDivisionTextView;
        public ImageView mNFLTeamLogoImageView;

        public NFLTeamHolder(View itemView) {
            super(itemView);

            // wire up textviews
            mNFLTeamNameTextView = (TextView) itemView.findViewById(R.id.list_item_nflteam_title_text_view);
            mNFLTeamDivisionTextView = (TextView) itemView.findViewById(R.id.list_item_nflteam_division_text_view);

            // wire up imageview
            mNFLTeamLogoImageView = (ImageView) itemView.findViewById(R.id.list_item_logo_imageview);
        }

        public void bindNFLTeam(NFLTeam nflTeam) {
            // get context and drawable from the filename we store in the Team Bucket
            Context context = mNFLTeamLogoImageView.getContext();
            int logoId = context.getResources().getIdentifier(nflTeam.getTeamShortName(), "drawable", context.getPackageName());
            Drawable drawable = getResources().getDrawable(logoId);

            // set member variables
            mTeam = nflTeam;
            mNFLTeamNameTextView.setText(nflTeam.getTeamName());
            mNFLTeamDivisionTextView.setText(nflTeam.getDivision());
            mNFLTeamLogoImageView.setImageDrawable(drawable);
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