package edu.iupui.ericdock.farbucks;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
    private String mMenucategory;
    private List<Menuitem> mNameMatches;

    // form controls
    private ImageView mMenuimageImageview;
    private TextView  mNameTextView;
    private TextView  mPriceTextView;
    private TextView  mCategoryTextView;
    private TextView  mPrice2TextView;
    private TextView  mPrice3TextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        Long locationId = args.getLong(ARG_FARBUCKS_MENUITEM_ID);

        FarbucksBucket bucket = FarbucksBucket.getInstance(getActivity().getApplication());
        mMenuitem = bucket.getMenuitem(locationId);
        mMenucategory = bucket.getCategoryName(mMenuitem.getCategoryId());
        mNameMatches = bucket.getSizesAndPrices(mMenuitem.getName());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_detail, container, false);

        // Concatenate string if Size is not empty. Otherwise don't
        String nameOutput = new String();

        if (!mMenuitem.getSize().isEmpty()) {
            nameOutput = mMenuitem.getSize() + " ";
        }

        nameOutput += mMenuitem.getName();

        // Wire them up
        mMenuimageImageview = (ImageView) view.findViewById(R.id.menu_detail_menuitem_imageview);
        mNameTextView = (TextView) view.findViewById(R.id.menu_detail_name_textview);
        mPriceTextView = (TextView) view.findViewById(R.id.menu_detail_price_textview);
        mPrice2TextView = (TextView) view.findViewById(R.id.menu_detail_price2_textview);
        mPrice3TextView = (TextView) view.findViewById(R.id.menu_detail_price3_textview);
        mCategoryTextView = (TextView) view.findViewById(R.id.menu_detail_category_textview);

        // Iterate all items with the same name to get all sizes and prices
        List<String> sizes = new ArrayList<String>();
        List<String> prices = new ArrayList<String>();

        // pop them all on stacks, collapse down to string for output (with a comma for splitting)
        for (Menuitem item : mNameMatches) {
            sizes.add(item.getSize());
            prices.add("$" + item.getPrice());
        }

        // Set the texts
        mNameTextView.setText(nameOutput);
        mPriceTextView.setText(StringUtils.join(sizes.toArray(), ",") + StringUtils.join(prices.toArray(), ","));
        getActivity().setTitle(mMenuitem.getName());

        // Base on # of results in mNameMatches. if 1, use the single-box. else, set the multiple ones;

        if (sizes.size() == 1) {
            mPriceTextView.setText(StringUtils.join(prices.toArray()));
        }
        else {
            mPriceTextView.setText(sizes.get(0) + " - " + prices.get(0));
            mPrice2TextView.setText(sizes.get(1) + " - " + prices.get(1));
            mPrice3TextView.setText(sizes.get(2) + " - " + prices.get(2));
        }

        mCategoryTextView.setText(mMenucategory);

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
