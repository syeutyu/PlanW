package com.example.user.junier;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.user.junier.TabFragment2.version;

/**
 * Created by user on 2017-07-28.
 */

public class TabFragment3 extends Fragment {
    private String uname;
    private ListView listView;
    private ArrayList<list_Item> list;
    private ListViewAdapter adapter;
    private Datebase database;

    public TabFragment3(String uname) {
        this.uname = uname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_3, container, false);
        listView = v.findViewById(R.id.list_view);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.logout), "  Log out");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.plock), "  Phone Lock");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.facebook), "  Facebook");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.delete), "  Data Reset");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {


                    case 0: { //로그아웃페이지

                        Intent intent = new Intent(getActivity(), Signin.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;

                    }
                    case 1: { //폰잠금화면 알림설정 페이지

                        Intent intent = new Intent(getActivity(), Plock.class);
                        Log.d("first",uname);
                        intent.putExtra("id",uname);
                        startActivity(intent);
                        break;
                    }
                    case 2: { //페이스북 설정기능

                        Intent intent = new Intent(getActivity(), Facebook.class);
                        startActivity(intent);
                        break;

                    }

                    case 3 : {
                        database = new Datebase(getActivity(),Datebase.Schema,null,version);
                        database.delete();
                        Snackbar.make(view,"데이터 초기화 완료 ",Snackbar.LENGTH_SHORT).show();
                        break;
                    }

                }
            }


        });
        return v;
    }


}


