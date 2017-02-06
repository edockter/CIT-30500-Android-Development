package app.ericdock.iupui.edu.nflteams;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ericd on 2/1/2017.
 */

public class NFLTeamDetailFragment extends Fragment {

    private TextView mNFLTeamNameTextView;
    private TextView mDivisionTextView;
    private TextView mStadiumTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.fragment_nflteam_detail, container, false);

        // Widgets are wired in the onCreateView method for Fragments,
        //      not onCreate() like Activity
        mNFLTeamNameTextView = (TextView) v.findViewById(R.id.team_name_textview);
        mDivisionTextView = (TextView) v.findViewById(R.id.division_textview);
        mStadiumTextView = (TextView) v.findViewById(R.id.stadium_textview);

        return v;
    }
}
