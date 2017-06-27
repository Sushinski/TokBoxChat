package com.sushinski.tokboxchat.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.managers.SessionManager;
import com.sushinski.tokboxchat.model.EventMessage;
import com.sushinski.tokboxchat.view.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainPresenter  implements  IRequiredPresenterOps{
    private IRequiredOpenTokViewOps mView;
    private SessionManager mSesManager;
    private OpenTokAuthManager mAuthManager;

    public MainPresenter(@NonNull IRequiredOpenTokViewOps view){
        mView = view;
        EventBus.getDefault().register(this);
        if(mView.hasOpenTokViewPermissions()){
            mSesManager = new SessionManager(this);// todo retain session state(keys etc)
            mAuthManager = new OpenTokAuthManager().setListener(mSesManager).build();
        }
    }


    @Override
    public void onStart() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mView = null;
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

    @Subscribe
    public void onEvent(EventMessage message) {
        if(mView != null){
            String message_text = mView.getContext().getString(message.getMessageId());
            mView.showStatusMessage(message_text);
        }
    }
}
