package com.sushinski.tokboxchat.interfaces;


import android.view.View;

public interface IRequiredOpenTokOps {
    void addPublisherView(View view);
    void addSubscriberView(View view);
    void clearPublisherView();
    void clearSubscriberView();
}
