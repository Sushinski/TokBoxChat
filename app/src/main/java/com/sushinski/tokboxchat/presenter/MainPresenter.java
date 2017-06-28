package com.sushinski.tokboxchat.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.sushinski.tokboxchat.di.DaggerManagerComponent;
import com.sushinski.tokboxchat.di.ManagerComponent;
import com.sushinski.tokboxchat.di.ManagerModule;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.managers.SessionManager;
import com.sushinski.tokboxchat.model.EventMessage;
import com.sushinski.tokboxchat.model.OpenTokSession;
import com.sushinski.tokboxchat.view.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainPresenter  implements  IRequiredPresenterOps{
    private IRequiredOpenTokViewOps mView;
    private ISessionListener mSesListener;
    private OpenTokAuthManager mAuthManager;

    public MainPresenter(@NonNull IRequiredOpenTokViewOps view){
        ManagerComponent component = DaggerManagerComponent.builder().
                managerModule(new ManagerModule(this)).build();
        mView = view;
        if(mView.hasOpenTokViewPermissions()){
            mSesListener = component.getSessionListener();
            mAuthManager = component.getAuthManager();
        }
    }


    @Override
    public void setView(IRequiredOpenTokViewOps view) {
        mView = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
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
    public void clearPublisherView() {
        if(mView != null){
            mView.clearPublisherView();
        }
    }

    @Override
    public void clearSubscriberView() {
        if(mView != null){
            mView.clearSubscriberView();
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
