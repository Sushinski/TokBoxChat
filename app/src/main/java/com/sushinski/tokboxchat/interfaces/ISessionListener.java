package com.sushinski.tokboxchat.interfaces;

import android.view.View;

import com.sushinski.tokboxchat.model.OpenTokSession;

public interface ISessionListener {
    void onSessionReceived(OpenTokSession session);
    View getPublisherView();
    View getSubscriberView();

}
