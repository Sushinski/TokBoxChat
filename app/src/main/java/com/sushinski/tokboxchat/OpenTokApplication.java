package com.sushinski.tokboxchat;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class OpenTokApplication extends Application {
    /**
     * Initiates Crashlitics library
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}
