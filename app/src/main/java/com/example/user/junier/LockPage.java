package com.example.user.junier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.text.SimpleDateFormat;

import static com.example.user.junier.MainActivity.tag;
import static com.example.user.junier.TabFragment2.version;

public class LockPage extends AppCompatActivity {
    private TextView timeText;
    private LinearLayout linearLayout;
    private  Datebase database;
    private String uname;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock_page);
        database = new Datebase(getApplicationContext(), Datebase.Schema, null, version);
        set();
        jsonArray = database.getPlan(uname);
        Log.d(tag+"들어온 jsonarray.length()", String.valueOf(jsonArray.length()));


    }

    private void set() {
        timeText = (TextView) findViewById(R.id.timeText);
        linearLayout = (LinearLayout)findViewById(R.id.Linear);
        Intent intent = getIntent();
        uname = intent.getStringExtra("id");
        Log.d(tag,"들어온 uname 값 : " +uname);

        String date = makeclock();
        timeText.setText(date);
    }

    private String makeclock() {
        long l = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String date = simpleDateFormat.format(l);
        return date;
    }
}

