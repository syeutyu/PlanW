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
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.user.junier.MainActivity.tag;
import static com.example.user.junier.TabFragment2.version;

/**
 * Created by user on 2017-07-28.
 */

public class TabFragment1 extends Fragment {
    private static String uname;
    private RelativeLayout linearLayout;
    private TextView count, fini, pur1, pur2, pur3, pur4,datet1,datet2,datet3,datet4;
    private ImageButton imageButton;
    private Datebase database;
    private String date, purpose;
    private JSONArray jsonArray;
    private ArrayList<String> list = new ArrayList<>();

    public TabFragment1(String uname) {
        Log.d(tag, uname);
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
                Log.d("json Ojbect get plan :", String.valueOf(jsonArray));
                setText(jsonArray);

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
        datet1 = v.findViewById(R.id.datet1);
        datet2 = v.findViewById(R.id.datet2);
        datet3 = v.findViewById(R.id.datet3);
        datet4 = v.findViewById(R.id.datet4);
        linearLayout = v.findViewById(R.id.img);
        linearLayout.getBackground().setAlpha(120);
    }

    private void setText(JSONArray jsonArray) {

        try {

            for (int i = 1; i < jsonArray.length(); i++) {

                JSONObject JsonData = jsonArray.getJSONObject(i);
                Log.d(tag, String.valueOf(JsonData));
                purpose = JsonData.getString("purpose");
                date = JsonData.getString("date");
                if (i == 1) {
                    datet1.setText("Plan Date is :"+date);
                    pur1.setText("Plan Purpose is : "+purpose);
                } else if (i == 2) {
                    datet2.setText("Plan Date is :"+date);
                    pur2.setText("Plan Purpose is : "+purpose);

                } else if (i == 3) {
                    datet3.setText("Plan Date is :"+date);
                    pur3.setText("Plan Purpose is : "+purpose);

                } else if (i == 4) {
                    datet4.setText("Plan Date is :"+date);
                    pur4.setText("Plan Purpose is : "+purpose);
                }

            }

            count.setText(getTime());
            fini.setText(String.valueOf(jsonArray.length()-1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getTime(){
        long time  = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = simpleDateFormat.format(new Date(time));
        Log.d("오늘 날짜",day);

        return day;
    }
}


