package com.example.shop4shop;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LENOVO on 28-11-2017.
 */

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("login", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedin(boolean loggedin) {
        editor.putBoolean("loggedInmode", loggedin);
        editor.commit();
    }

    public boolean loggedin() {
        return sharedPreferences.getBoolean("loggedInmode", false);
    }
}
