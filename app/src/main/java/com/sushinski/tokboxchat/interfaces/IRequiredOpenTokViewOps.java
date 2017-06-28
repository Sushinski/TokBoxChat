package com.sushinski.tokboxchat.interfaces;

import android.content.Context;
import android.view.View;

public interface IRequiredOpenTokViewOps extends IRequiredOpenTokOps{
    Context getContext();
    boolean hasOpenTokViewPermissions();
    void showStatusMessage(String message);
}
