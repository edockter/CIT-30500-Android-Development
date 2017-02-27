package edu.iupui.ericdock.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Eric on 2/27/2017.
 */

public class MenuFragment extends Fragment {
    private TextView mMenuTitleTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mMenuTitleTextView = (TextView) view.findViewById(R.id.menu_title_textview);

        return view;
    }
}