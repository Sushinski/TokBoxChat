package com.sushinski.tokboxchat.interfaces;


import android.content.Context;
import android.view.View;

public interface IRequiredOpenTokOps {
    Context getAppContext();
    void addPublisherView(View view);
    void addSubscriberView(View view);
    void clearPublisherView();
    void clearSubscriberView();
    void showStatusMessage(int message_id);
}
