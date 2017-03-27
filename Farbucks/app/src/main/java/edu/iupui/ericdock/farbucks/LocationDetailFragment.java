package edu.iupui.ericdock.farbucks;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import model.FarbucksBucket;
import model.Location;

/**
 * Created by ericd on 3/21/2017.
 */

public class LocationDetailFragment extends Fragment implements OnMapReadyCallback {

    // Key to find our Location ID
    private static final String ARG_FARBUCKS_LOCATION_ID = "farbucks_location_id";

    // the precious data
    private Location  mLocation;

    // form controls
    private ImageView mStoreImageView;
    private MapView   mDetailMapView;
    private TextView  mNameTextView;
    private TextView  mCityTextView;
    private TextView  mStateTextView;
    private TextView  mZipcodeTextView;

    // Map that lives inside the Map View
    private GoogleMap mGoogleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        Long locationId = args.getLong(ARG_FARBUCKS_LOCATION_ID);
        mLocation = FarbucksBucket.getInstance(getActivity().getApplication()).getLocation(locationId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_detail, container, false);

        // Wire them up
        mStoreImageView = (ImageView) view.findViewById(R.id.location_detail_store_imageview);
        mDetailMapView = (MapView) view.findViewById(R.id.location_detail_mapview);
        mNameTextView = (TextView) view.findViewById(R.id.location_detail_name_textview);
        mCityTextView = (TextView) view.findViewById(R.id.location_detail_city_textview);
        mStateTextView = (TextView) view.findViewById(R.id.location_detail_state_textview);
        mZipcodeTextView = (TextView) view.findViewById(R.id.location_detail_zipcode_textview);

        // Set the texts
        mNameTextView.setText(mLocation.getName());
        mCityTextView.setText(mLocation.getCity());
        mStateTextView.setText(mLocation.getState());
        mZipcodeTextView.setText(mLocation.getZipcode());
        getActivity().setTitle(mLocation.getName());

        // Compile asset path to a URI for Glide
        // For some strange reason, cannot use a strings.xml reference in this
        Uri assetPath = Uri.parse("file:///android_asset/locations/" + mLocation.getStoreImage() + ".jpg");

        // show image
        Glide.with(getContext()).load(assetPath).into(mStoreImageView);

        // Fire up the map
        mDetailMapView.onCreate(savedInstanceState);
        mDetailMapView.getMapAsync(this);

        return view;
    }


    public static LocationDetailFragment newInstance(Long locationId) {
        Bundle args = new Bundle();
        args.putLong(ARG_FARBUCKS_LOCATION_ID, locationId);

        LocationDetailFragment fragment = new LocationDetailFragment();
        fragment.setArguments(args);

        Log.d("FARBUCKS", "locationId is added to bundle.");
        Log.d("FARBUCKS", locationId.toString() );
        Log.d("FARBUCKS", "Get from args in newInstance() - " + args.getLong(ARG_FARBUCKS_LOCATION_ID));
        return fragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("FARBUCKS", "Map ready triggered.");

        mGoogleMap = googleMap;
        LatLng locationCoords = new LatLng(
                Double.parseDouble(mLocation.getLatitude()),
                Double.parseDouble(mLocation.getLongitude()));

        // Allow zoomingzsss
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        // TODO: Change these values (what's on the marker)
        // Set all options for marker -- can be done inline
        MarkerOptions marker = new MarkerOptions().position(locationCoords).title(mLocation.getName());
        mGoogleMap.addMarker(marker);

        // Move maps to our store + update map view
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCoords, 10));
        mDetailMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDetailMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mDetailMapView.onDestroy();
    }
}
