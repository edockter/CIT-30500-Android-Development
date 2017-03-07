package edu.iupui.ericdock.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.FarbucksBucket;
import model.Location;

/**
 * Created by Eric on 2/27/2017.
 */

public class LocationFragment extends Fragment {
    private TextView mLocationTitleTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        mLocationTitleTextView = (TextView) view.findViewById(R.id.location_title_textview);

        // QUERY FOR A LIST OF ALL LOCATIONS FROM THE DATABASE
        FarbucksBucket farbucksBucket = FarbucksBucket.getInstance(getActivity().getApplication());
        List<Location> allLocations = farbucksBucket.getLocations();

        // LOG OUTPUT THAT COUNTS THE NUMBER OF LOCATIONS IN THE DATABASE
        Log.d("FARBUCKS", "There are " + String.valueOf(  allLocations.size()  ) + " locations in the DB.");

        // WRITE LOCATION COUNT TO THE SCREEN
        mLocationTitleTextView.setText("There are " + String.valueOf(  allLocations.size()  ) + " locations in the DB.");

        return view;
    }
}