package com.sushinski.tokboxchat.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.managers.SessionManager;
import com.sushinski.tokboxchat.view.MainActivity;

public class MainPresenter  implements  IRequiredPresenterOps{
    private IRequiredOpenTokViewOps mView;
    private SessionManager mSesManager;
    private OpenTokAuthManager mAuthManager;

    public MainPresenter(@NonNull IRequiredOpenTokViewOps view){
        mView = view;
        if(mView.hasOpenTokViewPermissions()){
            mSesManager = new SessionManager(this);// todo retain session state(keys etc)
            mAuthManager = new OpenTokAuthManager().setListener(mSesManager).build();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Context getViewContext() {
        if(mView != null) {
            return mView.getContext();
        }else{
            return null;
        }
    }

    @Override
    public void addPublisherView(View view) {
        if(mView != null){
            mView.addPublisherView(view);
        }
    }

    @Override
    public void addSubscriberView(View view) {
        if(mView != null){
            mView.addSubscriberView(view);
        }
    }

    @Override
    public void clearAllViews() {
        if(mView != null){
            mView.clearViews();
        }
    }
}
