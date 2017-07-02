package com.sushinski.tokboxchat.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.sushinski.tokboxchat.di.DaggerManagerComponent;
import com.sushinski.tokboxchat.di.ManagerComponent;
import com.sushinski.tokboxchat.di.ManagerModule;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.model.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainPresenter  implements  IRequiredPresenterOps{
    private IRequiredOpenTokViewOps mView;
    private ISessionInteractor mSesInteractor;
    private OpenTokAuthManager mAuthManager;
    private int mCurStatusMessageId = 0;
    private ManagerComponent mComponent;

    public MainPresenter(){
    }

    @Override
    public void setView(@NonNull IRequiredOpenTokViewOps view) {
        if(view.hasOpenTokViewPermissions()) {
            mView = view;
            EventBus.getDefault().register(this);
            if(mComponent == null){
                mComponent = DaggerManagerComponent.builder().
                        managerModule(new ManagerModule(this)).build();
                mSesInteractor = mComponent.getSessionListener();
                mAuthManager = mComponent.getAuthManager();
                initLifecycle();
            }
        }else {
            mView = null;
        }
    }

    @Override
    public void initLifecycle(){
        if(mAuthManager != null){
            mAuthManager.open();
        }
    }

    @Override
    public void closeLifecycle() {
        if(mAuthManager != null){
            mAuthManager.close();
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onCreate() {
        if(mView != null){
            mView.showStatusMessage(mCurStatusMessageId);
        }
    }

    @Override
    public void onStart() {
        if(mView != null) {
            addPublisherView(mSesInteractor.getPublisherView());
            addSubscriberView(mSesInteractor.getSubscriberView());
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        clearPublisherView();
        clearSubscriberView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        if(mView != null) {
            mView = null;
        }
    }

    @Override
    public Context getAppContext() {
        if(mView != null) {
            return mView.getAppContext();
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

    @Override
    public void showStatusMessage(int message_id) {
        mCurStatusMessageId = message_id;
        if(mView != null){
            mView.showStatusMessage(message_id);
        }
    }

    @Subscribe
    public void onEvent(EventMessage message) {
        showStatusMessage(message.getMessageId());
    }
}
