package com.sushinski.tokboxchat.interfaces;


import android.content.Context;
import android.view.View;

public interface IRequiredPresenterOps extends IRequiredOpenTokOps {
    void onStart();
    void onPause();
    void onDestroy();
    Context getViewContext();
}
