package com.example.user.junier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Week extends AppCompatActivity {

    String daydata;
    public static String num;
    public ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);


        final Context context = this;
        Button btn = (Button) findViewById(R.id.save);
        CalendarView cal = (CalendarView) findViewById(R.id.cal);
        final TextView day = (TextView) findViewById(R.id.datebelow);


        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {


                String numdate = compareDate(year, month, dayOfMonth);

                int nowday = getNowDate();
                int numday = Integer.parseInt(numdate);
                num = year + "," + (month + 1) + "," + dayOfMonth;

                addAndDeleteDate(context, day, nowday, numday,num);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    public int getNowDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Log.d("날자", String.valueOf(sdf));
        String getTime = sdf.format(date);

        int time = Integer.parseInt(getTime);

        Log.d("현재 날자", String.valueOf(time));
        return time;
    }

    public String compareDate(int year, int month, int dayOfMonth) {
        if ((month + 1) < 10) {
            return year + "" + "0" + (month + 1) + dayOfMonth;
        } else {
            return year + "" + (month + 1) + dayOfMonth;
        }
    }

    public void addAndDeleteDate(Context context, TextView day, int nowday, int numday,String num) {
        String data = null;
        daydata = null;
        Boolean check = true;
        if (nowday <= numday) {
            try {
                for (int i = 0; i < arr.size(); i++) {

                    if (num.equals(arr.get(i))) {

                        check = false;
                        break;
                    } else {
                        check = true;
                    }

                }

                if (arr.size() < 7) {

                    Log.v("check 값 : ", String.valueOf(check));

                    if (check == true) {

                        arr.add(num);
                        Log.v("data 날짜", String.valueOf(arr.size()));

                        Toast.makeText(this, num + "이 저장되었습니다. ", Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < arr.size(); i++) {

                            for (int j = 0; j <= i; j++) {
                                if (arr.get(j) != null)
                                    data += arr.get(j) + " ";
                            }
                            data += "&";
                        }
                        Log.v("data 값 : ", data);

                        String[] ch = data.split("&");

                        for (int i = 1; i < (ch.length + 1); i++) {

                            if (ch[i - 1].contains("null")) {

                                String[] ch2;
                                ch2 = ch[i - 1].split("null");
                                daydata = ch2[1];

                            } else {
                                if (i == 7) {
                                    Log.v("오류 ", ch[i - 1]);
                                }
                                daydata = ch[i - 1];
                            }
                        }


                        day.setText(TextViewHelper.shrinkWithWordUnit(day, daydata, 100));

                    }
                } else {

                    Toast.makeText(getApplicationContext(), "입력가능한 일수를 초과했습니다.", Toast.LENGTH_SHORT).show();

                }

                if (check == false) {

                    Toast.makeText(getApplicationContext(), "해당일을 취소합니다.", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < arr.size(); i++) {
                        if (num.equals(arr.get(i))) {
                            Log.v("해당 값을 제거 합니다.", String.valueOf(arr.get(i)));
                            arr.remove(i);
                        }

                    }
                    for (int i = 0; i < arr.size(); i++) {

                        for (int j = 0; j <= i; j++) {
                            if (arr.get(j) != null)
                                data += arr.get(j) + " ";
                        }
                        data += "&";
                    }
                    if (data != null) {
                        Log.v("data 값 : ", data);

                        String[] ch = data.split("&");

                        for (int i = 1; i < (ch.length + 1); i++) {

                            if (ch[i - 1].contains("null")) {

                                String[] ch2;
                                ch2 = ch[i - 1].split("null");
                                daydata = ch2[1];

                            } else {
                                if (i == 7) {
                                    Log.v("오류 ", ch[i - 1]);
                                }
                                daydata = ch[i - 1];
                            }
                        }
                        day.setText(TextViewHelper.shrinkWithWordUnit(day, daydata, 100));

                    } else {
                        Toast.makeText(getApplicationContext(), "초기화완료", Toast.LENGTH_SHORT).show();
                        day.setText("");
                    }

                }

            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            Toast.makeText(context, "지난 일정은 선택하실수가없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
