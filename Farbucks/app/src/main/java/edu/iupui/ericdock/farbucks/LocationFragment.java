package edu.iupui.ericdock.farbucks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.FarbucksBucket;
import model.Location;

/**
 * Created by Eric on 2/27/2017.
 */

public class LocationFragment extends Fragment {
    private RecyclerView mLocationRecyclerView;
    private LocationAdapter mLocationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        mLocationRecyclerView = (RecyclerView) view.findViewById(R.id.location_recycler_view);
        mLocationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        // QUERY FOR A LIST OF ALL LOCATIONS FROM THE DATABASE
        FarbucksBucket farbucksBucket = FarbucksBucket.getInstance(getActivity().getApplication());
        List<Location> allLocations = farbucksBucket.getLocations();

        // LOG OUTPUT THAT COUNTS THE NUMBER OF LOCATIONS IN THE DATABASE
        Log.d("FARBUCKS", "There are " + String.valueOf(  allLocations.size()  ) + " locations in the DB.");

        // WRITE LOCATION COUNT TO THE SCREEN
        //mLocationTitleTextView.setText("There are " + String.valueOf(  allLocations.size()  ) + " locations in the DB.");

        // ADD LOCATIONS TO ADAPTER
        mLocationAdapter = new LocationAdapter(allLocations);
        mLocationRecyclerView.setAdapter(mLocationAdapter);

    }

    private class LocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Location mLocation;
        private TextView mNameTextView;
        private TextView mIdTextView;
        private TextView mCityTextView;
        private TextView mStateTextView;
        private TextView mZipcodeTextView;

        public LocationHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            // textvewz
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_location_name_textview);
            mIdTextView = (TextView) itemView.findViewById(R.id.list_item_location_id_textview);
            mCityTextView  = (TextView) itemView.findViewById(R.id.list_item_location_city_textview);
            mStateTextView = (TextView) itemView.findViewById(R.id.list_item_location_state_textview);
            mZipcodeTextView = (TextView) itemView.findViewById(R.id.list_item_location_zipcode_textview);
        }

        @Override
        public void onClick(View v) {
            // INITIAL TOAST CODE
            // Toast.makeText(getActivity(), mNFLTeam.getTeamName() + " clicked!", Toast.LENGTH_SHORT).show();

            // BLANK INTENT -- no communication
            // Intent intent = new Intent(getActivity(), NFLTeamDetailActivity.class);

            Intent intent = LocationDetailActivity.newIntent(getActivity(), mLocation.getId());
            startActivity(intent);
        }

        public void bindLocation(Location location) {
            // set member variables
            mLocation = location;
            mNameTextView.setText(mLocation.getName());
            mIdTextView.setText("Store #" + mLocation.getId().toString());
            mCityTextView.setText(mLocation.getCity());
            mStateTextView.setText(mLocation.getState());
            mZipcodeTextView.setText(mLocation.getZipcode());
        }
    }
    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {
        private List<Location> mLocationList;

        public LocationAdapter(List<Location> allLocations) {
            mLocationList = allLocations;
        }

        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View view = inflater.inflate(R.layout.list_item_location_relative, parent, false);

            return new LocationHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location thisLocation = mLocationList.get(position);
            holder.bindLocation(thisLocation);
        }

        @Override
        public int getItemCount() {
            return mLocationList.size();
        }
    }
}