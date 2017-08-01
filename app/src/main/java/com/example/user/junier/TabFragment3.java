package com.example.user.junier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2017-07-28.
 */

public class TabFragment3 extends Fragment {
    private String uname;
    private Datebase database;
    private ListView listView;
    ArrayList<list_Item> list;
    ListViewAdapter adapter;

    public TabFragment3(String uname) {
        this.uname = uname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_3, container, false);
        listView = v.findViewById(R.id.list_view);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.user),"  User Info");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.logout),"  Log out");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.plock),"  Phone Lock");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.plock),"  Phone Lock");

        return v;
    }





}


