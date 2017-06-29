package com.sushinski.tokboxchat.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.sushinski.tokboxchat.R;
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
    private String mCurStatusMessage = "";
    private boolean mViewPermissionsGranted;

    public MainPresenter(){
    }

    @Override
    public void setView(@NonNull IRequiredOpenTokViewOps view) {
        mView = view;
        mViewPermissionsGranted = view.hasOpenTokViewPermissions();
        if( mViewPermissionsGranted &&
                (mSesListener == null || mAuthManager == null)){
            ManagerComponent component = DaggerManagerComponent.builder().
                    managerModule(new ManagerModule(this)).build();
            mSesListener = component.getSessionListener();
            mAuthManager = component.getAuthManager();
        }
    }

    @Override
    public void onCreate() {
        if(mView != null){
            EventBus.getDefault().register(this);
            mView.showStatusMessage(mCurStatusMessage);
        }
    }

    @Override
    public void onStart() {
        if(mView != null && mViewPermissionsGranted) {
            addPublisherView(mSesListener.getPublisherView());
            addSubscriberView(mSesListener.getSubscriberView());
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
    }

    @Override
    public void onDestroy() {
        if(mView != null) {
            EventBus.getDefault().unregister(this);
            mViewPermissionsGranted = false;
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
    public void showStatusMessage(String message) {
        mCurStatusMessage = message;
        if(mView != null){
            mView.showStatusMessage(mCurStatusMessage);
        }
    }


    @Subscribe
    public void onEvent(EventMessage message) {
        if(mView != null){
            String msg = mView.getAppContext().
                    getString(message.getMessageId());
            showStatusMessage(msg);
        }
    }
}
