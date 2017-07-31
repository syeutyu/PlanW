package com.example.user.junier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by user on 2017-07-26.
 */

public class SplashActivity extends Activity {
    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),Signin.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // 3초 후 이미지를 닫습니다

    }
}
