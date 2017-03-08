package edu.iupui.ericdock.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private class LocationHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;

        public LocationHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView;
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

            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new LocationHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location thisLocation = mLocationList.get(position);
            holder.mTitleTextView.setText(thisLocation.getName());
        }

        @Override
        public int getItemCount() {
            return mLocationList.size();
        }
    }
}