package edu.iupui.ericdock.farbucks;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import model.FarbucksBucket;
import model.Menuitem;

/**
 * Created by Eric on 2/27/2017.
 */

public class MenuFragment extends Fragment {
    private RecyclerView mMenuItemRecyclerView;
    private MenuItemAdapter mMenuItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mMenuItemRecyclerView = (RecyclerView) view.findViewById(R.id.menu_item_recycler_view);
        mMenuItemRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        updateUI();

        return view;
    }

    private void updateUI() {
        // QUERY FOR A LIST OF ALL MENU ITEMS FROM THE DATABASE
        FarbucksBucket farbucksBucket = FarbucksBucket.getInstance(getActivity().getApplication());
        List<Menuitem> allMenuitems = farbucksBucket.getDistinctMenuItems();

        // LOG OUTPUT THAT COUNTS THE NUMBER OF MENU ITEMS IN THE DATABASE
        Log.d("FARBUCKS", "There are " + String.valueOf(  allMenuitems.size()  ) + " items on the menu.");

        // ADD MENUITEM TO ADAPTER
        mMenuItemAdapter = new MenuItemAdapter(allMenuitems);
        mMenuItemRecyclerView.setAdapter(mMenuItemAdapter);
    }

    private class MenuItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private TextView mPriceTextView;
        private TextView mSizeTextView;
        private ImageView mImageView;

        private Menuitem mMenuitem;
        private List<Menuitem> mNameMatches;

        public MenuItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mNameTextView  = (TextView) itemView.findViewById(R.id.list_item_menuitem_name_textview);
            mPriceTextView = (TextView) itemView.findViewById(R.id.list_item_menuitem_prices_textview);
            mSizeTextView  = (TextView) itemView.findViewById(R.id.list_item_menuitem_sizes_textview);
            mImageView = (ImageView) itemView.findViewById(R.id.list_item_menuitem_imageview);
        }

        @Override
        public void onClick(View v) {
            Fragment menuitemDetailFragment = MenuitemDetailFragment.newInstance(mMenuitem.getId());

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_single_fragment, menuitemDetailFragment)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack("MenuDetail").commit();
        }

        public void bindMenuitem(Menuitem menuitem) {
            // set member variables
            mMenuitem = menuitem;
            mNameMatches = FarbucksBucket
                    .getInstance(getActivity()
                    .getApplication())
                    .getSizesAndPrices(mMenuitem.getName());

            // Add up all possible prices
            // Iterate all items with the same name to get all sizes and prices
            List<String> sizes = new ArrayList<String>();
            List<String> prices = new ArrayList<String>();

            // TODO THIS
            // pop them all on stacks, collapse down to string for output (with a comma for splitting)
            for (int i = 0; i < mNameMatches.size(); i++) {
                sizes.add(mNameMatches.get(i).getSize());
                prices.add(mNameMatches.get(i).getPrice());
            }

            if (prices.size() > 1) {
                double lowestPrice = Double.parseDouble(prices.get(0));
                double highestPrice = lowestPrice;

                for(int i = 0; i < prices.size(); i++) {
                    double price = Double.parseDouble(prices.get(i));
                    if(price < lowestPrice) lowestPrice = price;
                    if(price > highestPrice) highestPrice = price;
                }

                mPriceTextView.setText("$" + lowestPrice + " - " + highestPrice);
            }
            else {
                mPriceTextView.setText("$" + prices.get(0));
            }

            // TODO update and set after making layout
            mNameTextView.setText(mMenuitem.getName());
            mSizeTextView.setText(StringUtils.join(sizes.toArray(), ","));

            // Compile asset path to a URI for Glide
            // For some strange reason, cannot use a strings.xml reference in this
            Uri assetPath = Uri.parse("file:///android_asset/menuitems/" + mMenuitem.getMenuImage() + ".jpg");

            // show image

            Glide.with(getContext()).load(assetPath).into(mImageView);

        }
    }

    private class MenuItemAdapter extends RecyclerView.Adapter<MenuItemHolder> {
        private List<Menuitem> mMenuitemList;

        public MenuItemAdapter(List<Menuitem> allMenuitems) {
            mMenuitemList = allMenuitems;
        }

        @Override
        public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            // R.layout.list_item_menuitem
            View view = inflater.inflate(R.layout.list_item_menuitem, parent, false);

            return new MenuItemHolder(view);
        }

        @Override
        public void onBindViewHolder(MenuItemHolder holder, int position) {
            Menuitem thisMenuitem = mMenuitemList.get(position);
            holder.bindMenuitem(thisMenuitem);
        }

        @Override
        public int getItemCount() { return mMenuitemList.size(); };
    }
}