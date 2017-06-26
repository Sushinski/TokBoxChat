package com.sushinski.tokboxchat.interfaces;

import android.content.Context;
import android.view.View;

public interface IRequiredOpenTokViewOps {
    Context getContext();
    boolean hasOpenTokViewPermissions();
    void addPublisherView(View view);
    void addSubscriberView(View view);
    void clearViews();
}
