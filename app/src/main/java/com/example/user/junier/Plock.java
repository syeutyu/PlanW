package com.example.user.junier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Plock extends AppCompatActivity {
    private Button btn1,btn2;
    private Context context = this;
    private String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plock);

        setting();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"이후에 잠금화면이 표시됩니다",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ScereenService.class);
                intent.putExtra("id",uname);
                Log.d("click ",uname);
                startService(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"이후에 잠금화면이 표시 안됩니다",Snackbar.LENGTH_SHORT).show();
                stopService(new Intent(getApplicationContext(),ScereenService.class));
            }
        });

    }
    private  void setting(){
        btn1 = (Button)findViewById(R.id.lockbtn1);
        btn2 = (Button)findViewById(R.id.lockbtn2);
        Intent intent = getIntent();
        uname = intent.getStringExtra("id");
    }
}
