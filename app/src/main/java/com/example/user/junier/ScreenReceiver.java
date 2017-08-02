package com.example.user.junier;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by user on 2017-08-02.
 */

public class ScreenReceiver extends BroadcastReceiver {

    private KeyguardManager.KeyguardLock keyLock = null;
    private KeyguardManager km = null;
    private static String uname;

    public ScreenReceiver() {

    }

    public ScreenReceiver(String uname) {
        this.uname = uname;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            if (km == null) {
                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            }
            if (keyLock == null) {
                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
            }
            disableKeyguard();
            Intent intent1 = new Intent(context, LockPage.class);
            intent1.putExtra("id", uname);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }

    public void reenableKeyguard() {
        keyLock.reenableKeyguard();
    }

    public void disableKeyguard() {
        keyLock.disableKeyguard();
    }


}
