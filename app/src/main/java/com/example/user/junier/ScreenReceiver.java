package com.example.user.junier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.example.user.junier.MainActivity.tag;

/**
 * Created by user on 2017-08-02.
 */

public class ScreenReceiver extends BroadcastReceiver  {

    public static boolean screen;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            screen = true;
            Log.d(tag,"화면 꺼진부분");
            Intent intent1 = new Intent(context,LockPage.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}
