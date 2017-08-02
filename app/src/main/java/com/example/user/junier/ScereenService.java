package com.example.user.junier;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by user on 2017-08-02.
 */

public class ScereenService extends Service {
    ScreenReceiver receiver = null;
    String uname;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        receiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, filter);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        uname = intent.getStringExtra("id");
        Log.d("생성자",uname);
        if (intent != null) {
            Log.d("생성자","1");
            if (intent.getAction() == null) {
                    receiver = new ScreenReceiver(uname);
                    Log.d("생성자","3");
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(receiver, filter);
            }
        }
        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }
}
