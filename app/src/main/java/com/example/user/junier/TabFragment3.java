package com.example.user.junier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.user), "  User Info");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.logout), "  Log out");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.plock), "  Phone Lock");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.facebook), "  Facebook");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {

                    case 0: { //내가 가입한 정보보기

                        Intent intent = new Intent(getActivity(), UserInfo.class);
                        startActivity(intent);
                        break;

                    }

                    case 1: { //로그아웃페이지

                        Intent intent = new Intent(getActivity(), Signin.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;

                    }
                    case 2: { //폰잠금화면 알림설정 페이지

                        Intent intent = new Intent(getActivity(), Plock.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: { //페이스북 설정기능

                        Intent intent = new Intent(getActivity(), Facebook.class);
                        startActivity(intent);
                        break;

                    }

                }
            }


        });
        return v;
    }


}


