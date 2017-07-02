package com.sushinski.tokboxchat.interfaces;


import android.content.Context;
import android.view.View;

public interface IRequiredPresenterOps extends IRequiredOpenTokOps {
    String PRESENTER_TAG = "OpenTokPresenter";
    void setView(IRequiredOpenTokViewOps view);
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void initLifecycle();
    void closeLifecycle();
}
