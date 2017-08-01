package com.example.user.junier;

import android.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        list_Item list_item = (list_Item) l.getItemAtPosition(position);
        Drawable drawable = list_item.getImage();
        String s = list_item.getText();
    }

    void addItem(Drawable icon, String title, String desc) {
        adapter.addItem(icon, title);
    }

}
