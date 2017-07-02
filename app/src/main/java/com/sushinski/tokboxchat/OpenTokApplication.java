package com.sushinski.tokboxchat;

import android.app.Application;
import android.support.annotation.NonNull;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;


public class OpenTokApplication extends Application {
    /**
     * Initiates Crashlitics library
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
