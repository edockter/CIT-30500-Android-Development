package app.ericdock.iupui.edu.nflteams;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public TextView mNFLTeamNameTextView;

        public NFLTeamHolder(View itemView) {
            super(itemView);

            // wire up the built-in textview fo the itemView
            mNFLTeamNameTextView = (TextView) itemView;
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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,
                    parent,
                    false);

            return new NFLTeamHolder(view);
        }

        @Override
        public void onBindViewHolder(NFLTeamHolder holder, int position) {
            NFLTeam team = mNFLTeamList.get(position);
            holder.mNFLTeamNameTextView.setText(team.getTeamName());
        }

        @Override
        public int getItemCount() {
            return mNFLTeamList.size();
        }
    }
}
