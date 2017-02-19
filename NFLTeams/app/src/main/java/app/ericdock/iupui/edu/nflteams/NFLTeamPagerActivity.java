package app.ericdock.iupui.edu.nflteams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by ericd on 2/17/2017.
 */

public class NFLTeamPagerActivity extends FragmentActivity {
    private static final String EXTRA_NFLTEAM_ID =
            "app.ericdock.iupui.edu.nflteams.nflteam_id";

    private ViewPager mViewPager;
    private List<NFLTeam> mNFLTeams;

    public static Intent newIntent(Context packageContext, UUID nflTeamId) {
        Intent intent = new Intent(packageContext, NFLTeamPagerActivity.class);
        intent.putExtra(EXTRA_NFLTEAM_ID, nflTeamId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nflteam_pager);

        UUID nflTeamId = (UUID) getIntent().getSerializableExtra(EXTRA_NFLTEAM_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_nflteam_pager_view_pager);

        mNFLTeams = NFLTeamBucket.get(this).getNFLTeamList();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                NFLTeam team = mNFLTeams.get(position);
                return NFLTeamDetailFragment.newInstance(team.getId());
            }

            @Override
            public int getCount() {
                return mNFLTeams.size();
            }
        });

        for (int i = 0; i < mNFLTeams.size(); i++) {
            if (mNFLTeams.get(i).getId().equals(nflTeamId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
