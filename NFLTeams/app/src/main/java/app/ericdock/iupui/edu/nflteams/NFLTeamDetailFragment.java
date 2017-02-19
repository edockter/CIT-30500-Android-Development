package app.ericdock.iupui.edu.nflteams;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * Created by ericd on 2/1/2017.
 */

public class NFLTeamDetailFragment extends Fragment {

    private static final String ARG_NFLTEAM_ID = "nfl_team_id";

    private TextView mNFLTeamNameTextView;
    private TextView mDivisionTextView;
    private TextView mStadiumTextView;
    private ImageView mNFLTeamLogoImageView;

    private NFLTeam mNFLTeam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID nflTeamId = (UUID) getArguments().getSerializable(ARG_NFLTEAM_ID);

        mNFLTeam = NFLTeamBucket.get(getActivity()).getNFLTeam(nflTeamId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.fragment_nflteam_detail, container, false);

        // Widgets are wired in the onCreateView method for Fragments,
        //      not onCreate() like Activity
        mNFLTeamNameTextView = (TextView) v.findViewById(R.id.team_name_textview);
        mDivisionTextView = (TextView) v.findViewById(R.id.division_textview);
        mStadiumTextView = (TextView) v.findViewById(R.id.stadium_textview);
        mNFLTeamLogoImageView = (ImageView) v.findViewById(R.id.logo_imageview);

        mNFLTeamNameTextView.setText(mNFLTeam.getTeamName());
        mDivisionTextView.setText(mNFLTeam.getDivision());
        mStadiumTextView.setText(mNFLTeam.getStadium());

        // get context and drawable from the filename we store in the Team Bucket
        Context context = mNFLTeamLogoImageView.getContext();
        int logoId = context.getResources().getIdentifier(mNFLTeam.getTeamShortName(), "drawable", context.getPackageName());

        // show image
        Glide.with(getContext()).load(logoId).into(mNFLTeamLogoImageView);

        return v;
    }

    public static NFLTeamDetailFragment newInstance(UUID nflTeamId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NFLTEAM_ID, nflTeamId);

        NFLTeamDetailFragment fragment = new NFLTeamDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
