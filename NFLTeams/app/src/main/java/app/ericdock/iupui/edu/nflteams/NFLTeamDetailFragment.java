package app.ericdock.iupui.edu.nflteams;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ericd on 2/1/2017.
 */

public class NFLTeamDetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.fragment_nflteam_detail, container, false);

        // Widgets are wired in the onCreateView method for Fragments,
        //      not onCreate() like Activity
        TextView mNFLTeamNameTextView;

        mNFLTeamNameTextView = (TextView) v.findViewById(R.id.nflteam_name_text);

        return v;
    }
}
