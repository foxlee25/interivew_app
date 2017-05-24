package com.fox_lee.yunwen.lolinfimobile_struct.Activity;

/**
 * Created by Yunwen on 4/29/2016.
 */
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fox_lee.yunwen.lolinfimobile_struct.BroadcastReceiver.AlarmReceiver;
import com.fox_lee.yunwen.lolinfimobile_struct.Service.LongRunning;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlarmActivity extends Activity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, alarmIntent, 0);

        //initial alarm time of the date?
        findViewById(R.id.startAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        findViewById(R.id.stopAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        findViewById(R.id.startAlarmAt10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAt10();
            }
        });
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long interval = 1000 * 60 * 60 * 24;

        /* Set the alarm to start at a certain time */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Log.d("AlarmActivity", "The setting time is: " + calendar.getTimeInMillis());

        calendar.setTime(new Date()); //
        calendar.set(Calendar.HOUR, 12);
        calendar.add(Calendar.YEAR, 0); // Add 0 years to current year
        calendar.add(Calendar.DATE, 0); // Add 0 days to current date
        calendar.add(Calendar.MONTH, 0); // Add 0 months to current month
        Log.d("AlarmActivity", "The setting time is: " + calendar.getTimeInMillis());

        /* Repeating on every day interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);
    }
}
