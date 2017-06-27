package com.sushinski.tokboxchat.interfaces;


import android.content.Context;
import android.view.View;

public interface IRequiredPresenterOps {
    void onStart();
    void onPause();
    void onDestroy();
    Context getViewContext();
    void addPublisherView(View view);
    void addSubscriberView(View view);
    void clearAllViews();
}
