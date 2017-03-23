package edu.iupui.ericdock.farbucks;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * Created by ericd on 3/21/2017.
 */

public class LocationDetailActivity extends SingleFragmentActivity {
    private static final String EXTRA_LOCATION_ID = "app.ericdock.iupui.edu.farbucks.location_id";

    public static Intent newIntent(Context packageContext, Long locationId) {
        Intent intent = new Intent(packageContext, LocationDetailActivity.class);
        intent.putExtra(EXTRA_LOCATION_ID, locationId);
        return intent;
    }

    protected Fragment createFragment() {
        return new LocationDetailFragment();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_location_detail);

        Long locationId = (Long) getIntent().getSerializableExtra(EXTRA_LOCATION_ID);

        //FragmentManager fragmentManager = getSupportFragmentManager();

        // Grab the content fragment so we can show it
        FragmentManager fm = getSupportFragmentManager();
        //Fragment fragment = fm.findFragmentById(R.id.fragment_location_detail);

        LocationDetailFragment detailFragment = LocationDetailFragment.newInstance(locationId);
        fm.beginTransaction().add(R.id.content_single_fragment, detailFragment).commit();
    }
}
