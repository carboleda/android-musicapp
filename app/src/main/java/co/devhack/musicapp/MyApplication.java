package co.devhack.musicapp;

import android.app.Application;

import co.devhack.musicapp.helpers.SharedPreferencesUtil;

/**
 * Created by krlosf on 25/04/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtil.init(getApplicationContext());
    }
}
