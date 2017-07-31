package com.example.user.junier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import static com.example.user.junier.MainActivity.tag;
import static com.example.user.junier.TabFragment2.version;

/**
 * Created by user on 2017-07-28.
 */

public class TabFragment1 extends Fragment {
    private static String uname;
    private RelativeLayout linearLayout;
    private TextView count, fini,pur1,pur2,pur3,pur4;
    private ImageButton imageButton;
    private JSONArray jsonArray;
    private Datebase database;
    private ArrayList<String> list = new ArrayList<>();

    public TabFragment1(String uname) {
        Log.d(tag,uname);
        this.uname = uname;
    } // key값 얻어오기

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        getLayout(v);
        database = new Datebase(getActivity(), Datebase.Schema, null, version);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                jsonArray = database.getPlan(uname);
                try{
                    Log.d("jsonArray 넘겨져 온 값 : ",String.valueOf(jsonArray));
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

    private void getLayout(View v) {
        imageButton = v.findViewById(R.id.re);
        count = v.findViewById(R.id.count);
        fini = v.findViewById(R.id.fini);
        pur1 = v.findViewById(R.id.pur1);
        pur2 = v.findViewById(R.id.pur2);
        pur3 = v.findViewById(R.id.pur3);
        pur4 = v.findViewById(R.id.pur4);
        linearLayout = v.findViewById(R.id.img);
        linearLayout.getBackground().setAlpha(120);
    }
}


