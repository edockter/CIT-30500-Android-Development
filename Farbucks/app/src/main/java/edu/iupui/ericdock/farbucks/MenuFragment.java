package edu.iupui.ericdock.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
        mMenuItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        // QUERY FOR A LIST OF ALL MENU ITEMS FROM THE DATABASE
        FarbucksBucket farbucksBucket = FarbucksBucket.getInstance(getActivity().getApplication());
        List<Menuitem> allMenuitems = farbucksBucket.getMenuItems();

        // LOG OUTPUT THAT COUNTS THE NUMBER OF MENU ITEMS IN THE DATABASE
        Log.d("FARBUCKS", "There are " + String.valueOf(  allMenuitems.size()  ) + " items on the menu.");

        // ADD MENUITEM TO ADAPTER
        mMenuItemAdapter = new MenuItemAdapter(allMenuitems);
        mMenuItemRecyclerView.setAdapter(mMenuItemAdapter);
    }

    private class MenuItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Menuitem mMenuitem;

        public MenuItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView;
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

            mTitleTextView.setText(mMenuitem.getName());

            // TODO update and set after making layout
            /* mNameTextView.setText(mLocation.getName());
            mIdTextView.setText("Store #" + mLocation.getId().toString());
            mCityTextView.setText(mLocation.getCity());
            mStateTextView.setText(mLocation.getState());
            mZipcodeTextView.setText(mLocation.getZipcode());
            */
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

            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

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