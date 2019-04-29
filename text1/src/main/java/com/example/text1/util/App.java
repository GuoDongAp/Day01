package com.example.text1.util;

import android.app.Application;

/**
 * Created by dell on 2019/4/26.
 */

public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getApp() {
        return app;
    }
}
