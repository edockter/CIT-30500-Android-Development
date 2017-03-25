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
import model.Menuitem;

/**
 * Created by ericd on 3/21/2017.
 */

public class MenuitemDetailFragment extends Fragment {

    // Key to find our Menuitem ID
    private static final String ARG_FARBUCKS_MENUITEM_ID = "farbucks_menuitem_id";

    // the precious data
    private Menuitem mMenuitem;

    // form controls
    private ImageView mMenuimageImageview;
    private TextView  mNameTextView;
    private TextView  mPriceTextView;
    private TextView  mCategoryTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        Long locationId = args.getLong(ARG_FARBUCKS_MENUITEM_ID);
        mMenuitem = FarbucksBucket.getInstance(getActivity().getApplication()).getMenuitem(locationId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_detail, container, false);

        // Wire them up
        mMenuimageImageview = (ImageView) view.findViewById(R.id.menu_detail_menuitem_imageview);
        mNameTextView = (TextView) view.findViewById(R.id.menu_detail_name_textview);
        mPriceTextView = (TextView) view.findViewById(R.id.menu_detail_price_textview);
        mCategoryTextView = (TextView) view.findViewById(R.id.menu_detail_category_textview);

        // Set the texts
        mNameTextView.setText(mMenuitem.getName());
        mPriceTextView.setText("$" + mMenuitem.getPrice());
        //TODO finish this (after adding Category model)
        mCategoryTextView.setText("TOTO");

        // Compile asset path to a URI for Glide
        // For some strange reason, cannot use a strings.xml reference in this
        Uri assetPath = Uri.parse("file:///android_asset/menuitems/" + mMenuitem.getMenuImage() + ".jpg");

        // show image
        Glide.with(getContext()).load(assetPath).into(mMenuimageImageview);

        return view;
    }


    public static MenuitemDetailFragment newInstance(Long menuitemId) {
        Bundle args = new Bundle();
        args.putLong(ARG_FARBUCKS_MENUITEM_ID, menuitemId);

        MenuitemDetailFragment fragment = new MenuitemDetailFragment();
        fragment.setArguments(args);

        Log.d("FARBUCKS", "menuitemId is added to bundle.");
        Log.d("FARBUCKS", menuitemId.toString() );
        Log.d("FARBUCKS", "Get from args in newInstance() - " + args.getLong(ARG_FARBUCKS_MENUITEM_ID));

        return fragment;
    }
}
