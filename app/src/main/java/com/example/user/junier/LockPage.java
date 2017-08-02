package com.example.user.junier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import static com.example.user.junier.MainActivity.tag;
import static com.example.user.junier.TabFragment2.version;

public class LockPage extends AppCompatActivity {
    private TextView timeText;
    private LinearLayout linearLayout;
    private Datebase database;
    private String uname;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private String purpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lock_page);

        database = new Datebase(getApplicationContext(), Datebase.Schema, null, version);
        set();
        jsonArray = database.getPlan(uname);

        TextView[] textViews = new TextView[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                purpose = jsonObject.getString("purpose");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //이부분 테스트해야 되네
            textViews[i] = new TextView(this);
            textViews[i].setLayoutParams(linearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews[i].setTextSize(23);
            textViews[i].setGravity(Gravity.CENTER);
            textViews[i].setText(purpose);
            linearLayout.addView(textViews[i]);

        }

    }

    private void set() {
        timeText = (TextView) findViewById(R.id.timeText);
        linearLayout = (LinearLayout) findViewById(R.id.Linear);
        Intent intent = getIntent();
        uname = intent.getStringExtra("id");
        Log.d(tag, "들어온 uname 값 : " + uname);

        String date = makeclock();
        timeText.setText(date);
    }

    private String makeclock() {
        long l = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String date = simpleDateFormat.format(l);
        return date;
    }
}

