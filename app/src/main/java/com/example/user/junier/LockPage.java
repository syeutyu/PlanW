package com.example.user.junier;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import static com.example.user.junier.MainActivity.tag;
import static com.example.user.junier.TabFragment2.version;

public class LockPage extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureScanner;
    private TextView timeText;
    private LinearLayout linearLayout;
    private Datebase database;
    private String uname;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private String purpose, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gestureScanner = new GestureDetector(this);
        setContentView(R.layout.activity_lock_page);

        database = new Datebase(getApplicationContext(), Datebase.Schema, null, version);
        set();
        setTextPlan();

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

    private  void setTextPlan(){
        jsonArray = database.getPlan(uname);
        TextView[] textViews = new TextView[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                jsonObject = jsonArray.getJSONObject(i);
                purpose = jsonObject.getString("purpose");
                date = jsonObject.getString("date");
                Log.d(tag + "텍스트", purpose);
                textViews[i] = new TextView(getApplicationContext());
                textViews[i].setTextColor(Color.WHITE);
                textViews[i].setTextSize(20);
                textViews[i].setGravity(Gravity.CENTER);
                textViews[i].setText("수행 목표 : " + purpose + " \n 기간 : " + date);
                linearLayout.addView(textViews[i]);

                ViewGroup.MarginLayoutParams margin =
                        new ViewGroup.MarginLayoutParams(textViews[i].getLayoutParams());
                margin.setMargins(0, 24, 0, 70);
                textViews[i].setLayoutParams(new LinearLayout.LayoutParams(margin));


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }
    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(tag,"onFling");
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;

            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d(tag,"오른쪽");
                this.finish();
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d(tag,"왼쪽");
                this.finish();

            }
            // down to up swipe
            else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d(tag,"아래");
                this.finish();

            }
            // up to down swipe
            else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d(tag,"위에");
                this.finish();

            }
        } catch (Exception e) {

        }
        return true;
    }
}

