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

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.user.junier.TabFragment2.Result_Code;
import static com.example.user.junier.TabFragment2.tag;

public class Week extends AppCompatActivity {

    public Boolean Datecheck = true;
    public static String daydata;
    private int count = 0;
    public static String num;
    public ArrayList<String> arr = new ArrayList<>();
    private String sday1,sday2;

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
                Datecheck = getNowDate(year, month, dayOfMonth);
        if(count ==0) {
            num = (month + 1) + "," + dayOfMonth + "  ";
            sday1 = num;
            count++;
        } else if(count == 1){
            num = (month + 1) + "," + dayOfMonth + "  ";
            sday2 = num;
        }
                addAndDeleteDate(context, day, sday1,sday2);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("Date", daydata);
                setResult(Result_Code, intent);
                finish();

            }
        });
    }

    public Boolean getNowDate(int year, int month, int dayOfMonth) {
        Calendar aDate = Calendar.getInstance();
        Calendar bDate = Calendar.getInstance();
            aDate.set(year, month, dayOfMonth);
            aDate.set(Calendar.HOUR_OF_DAY, 0);
            aDate.set(Calendar.MINUTE, 0);
            aDate.set(Calendar.SECOND, 0);
            aDate.set(Calendar.MILLISECOND, 0);

            bDate.set(Calendar.HOUR_OF_DAY, 0);
            bDate.set(Calendar.MINUTE, 0);
            bDate.set(Calendar.SECOND, 0);
            bDate.set(Calendar.MILLISECOND, 0);


            if (aDate.after(bDate)) {
                Log.v(tag, "일수가 더 큼");
                return true;
            } else if (aDate.before(bDate)) {
                Log.v(tag, "일수가 더 작음");
                return false;
            } else {
                Log.v(tag, "일수가 같음");
                return true;
            }

    }

    public void addAndDeleteDate(Context context, TextView day, String sday1,String sday2) {
        Boolean check = true;
        String data = null;
        daydata = null;

        if (Datecheck == true) {
            try {
                for (int i = 0; i < arr.size(); i++) {

                    if (sday1.equals(arr.get(i))) {
                        Log.d("일치하는 데이터 찾음", arr.get(i));
                        check = false;
                        break;
                    } else if(sday2.equals(arr.get(i)))
                    {
                        check = false;
                    } else{
                        check = true;
                    }

                }
                if (check == true) {

                    if (arr.size() < 2) {

                        Log.v("check 값 : ", String.valueOf(check));


                        arr.add(num);
                        Log.v("data 날짜", String.valueOf(arr.size()));

                        Toast.makeText(getApplicationContext(), num + "이 저장되었습니다. ", Toast.LENGTH_SHORT).show();

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
                                if (i == 2) {
                                    Log.v("오류 ", ch[i - 1]);
                                }
                                daydata = ch[i - 1];
                            }
                        }


                        day.setText(TextViewHelper.shrinkWithWordUnit(day, daydata, 100));

                    } else{
                        Toast.makeText(getApplicationContext(), "2일이후는 선택하실수 없습니다",Toast.LENGTH_SHORT).show();
                    }
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