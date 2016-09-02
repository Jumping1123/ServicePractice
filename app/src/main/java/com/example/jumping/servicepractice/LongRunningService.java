package com.example.jumping.servicepractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by Jumping on 2016/8/18.
 */
public class LongRunningService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().toString());
            }
        }).start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int delaytime = 10 * 1000;
        long triggerattime = SystemClock.elapsedRealtime() + delaytime;
        Intent intent2 = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent2, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerattime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
