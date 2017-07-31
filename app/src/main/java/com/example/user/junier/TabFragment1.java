package com.example.user.junier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by user on 2017-07-28.
 */

public class TabFragment1 extends Fragment {
    String uname;

    public TabFragment1(String uname) {
        this.uname = uname;
    } // key값 얻어오기

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v =  inflater.inflate(R.layout.tab_fragment_1, container, false);

        RelativeLayout linearLayout = (RelativeLayout)v.findViewById(R.id.img);
        linearLayout.getBackground().setAlpha(145);

        return v;
        }
    }


