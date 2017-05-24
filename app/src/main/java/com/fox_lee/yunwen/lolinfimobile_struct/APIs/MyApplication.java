package com.fox_lee.yunwen.lolinfimobile_struct.APIs;

import android.app.Application;

import com.flurry.android.FlurryAgent;
import com.fox_lee.yunwen.lolinfimobile_struct.Constants.Key;

/**
 * Created by Yunwen on 6/2/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new FlurryAgent.Builder()
                .withLogEnabled(false)
                .build(this, Key.FLURRY_API_KEY);
    }
}
