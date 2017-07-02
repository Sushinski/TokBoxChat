package com.sushinski.tokboxchat.interfaces;

import android.view.View;

import com.sushinski.tokboxchat.model.OpenTokSession;

public interface ISessionInteractor {
    void onAuthReceived(OpenTokSession session);
    void onAuthClosed();
    View getPublisherView();
    View getSubscriberView();

}
