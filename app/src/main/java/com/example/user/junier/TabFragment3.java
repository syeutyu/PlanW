package com.example.user.junier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.user.junier.TabFragment2.version;

/**
 * Created by user on 2017-07-28.
 */

public class TabFragment3 extends Fragment {
    private String uname;
    private Datebase database;

    public TabFragment3(String uname) {
        this.uname = uname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_3, container, false);
        database = new Datebase(getActivity(), Datebase.Schema, null, version);
        database.drop();
        return v;
    }
}


