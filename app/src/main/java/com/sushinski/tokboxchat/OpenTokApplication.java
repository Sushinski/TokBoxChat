package com.sushinski.tokboxchat;

import android.app.Application;


public class OpenTokApplication {
    public class NomprenomApp extends Application {
        /**
         * Initiates Crashlitics and Activeandroid libraries
         */
        @Override
        public void onCreate() {
            super.onCreate();
            //Fabric.with(this, new Crashlytics());
            //ActiveAndroid.initialize(this);
        }

        /**
         * Deactivates Activeandroid library
         */
        @Override
        public void onTerminate() {
            super.onTerminate();
            //ActiveAndroid.dispose();
        }

    }
}
