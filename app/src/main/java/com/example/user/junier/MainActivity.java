package com.example.user.junier;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.junier.TabFragment2.version;

public class MainActivity extends AppCompatActivity {
    public static final String tag = "로그";
    public Context context = this;
    public static final int Result_Code = 1001;
    public static String purpose;
    private TextInputEditText text;
    private TextView daytext;
    private Button In;
    Datebase helper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_main);
        helper = new Datebase(context,Datebase.Schema,null,version);
        database = helper.getWritableDatabase();


        In = (Button)findViewById(R.id.Invite);
        In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Facebook Friend ","Invite");
                Intent intent1 = new Intent(context,Signup.class);
                startActivity(intent1);
            }
        });
        text = (TextInputEditText) findViewById(R.id.purpose);
        daytext = (TextView)findViewById(R.id.select_day);

    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.one: {
                purpose = text.getText().toString();
                Log.d("purpose 값 ", purpose);

                Toast.makeText(getApplicationContext(), "Day화면 출력", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Day.class);
                startActivityForResult(intent, Result_Code);

                break;
            }
            case R.id.two: {
                Toast.makeText(getApplicationContext(), "Week화면 출력", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Week.class);
                startActivity(intent);
                break;
            }
            case R.id.three: {
                Toast.makeText(getApplicationContext(), "three 출력", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(tag, "onActivityResult");
        try{
            if (requestCode == Result_Code) {
                String Date = data.getStringExtra("Date");
                Log.d("Date",Date);
                daytext.setText(Date);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }


}