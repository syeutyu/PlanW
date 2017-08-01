package com.example.user.junier;

import android.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2017-08-01.
 */

public class CustomListFragment extends ListFragment {
    ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new ListViewAdapter();
        setListAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.user), "User");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.logout), "Logout");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.plock), "Phone Lock");

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    public void addItem(Drawable icon, String title, String desc) {
        adapter.addItem(icon, title);
    }

}
