package com.sushinski.tokboxchat.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.di.DaggerManagerComponent;
import com.sushinski.tokboxchat.di.ManagerComponent;
import com.sushinski.tokboxchat.di.ManagerModule;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.model.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * OpenTok associated presenter implementation class
 */
public class MainPresenter  implements  IRequiredPresenterOps{
    private IRequiredOpenTokViewOps mView;
    private OpenTokAuthManager mAuthManager;
    private int mCurStatusMessageId = R.string.init_app;
    private ManagerComponent mComponent;

    public MainPresenter(){
    }

    /**
     * Assigns OpenTok-permitted view for interact with
     * Subscribes to eventbus events
     * Inits Auth an session managers
     * Starts lifecycle
     * @param view OpenTok-enabled view
     */
    @Override
    public void setView(@NonNull IRequiredOpenTokViewOps view) {
        if(view.hasOpenTokViewPermissions()) {
            mView = view;
            if(mComponent == null){
                mComponent = DaggerManagerComponent.builder().
                        managerModule(new ManagerModule(this)).build();
            }
        }
    }

    /**
     * Starts auth & opentok session lifecycle
     */
    @Override
    public void initLifecycle(){
        if(mAuthManager == null){
            mAuthManager = mComponent.getAuthManager();
        }
        mAuthManager.open();
    }

    /**
     * Closes auth and opentok sessions lifecycles
     * @param re_init - Re-init lifecycle flag
     */
    @Override
    public void closeLifecycle(boolean re_init) {
        if(mAuthManager != null){
            mAuthManager.close();
            if(re_init){
                mAuthManager.open();
            }
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {
        if(mView != null){
            EventBus.getDefault().register(this);
            mView.showStatusMessage(mCurStatusMessageId);
            initLifecycle();
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
        if(mView != null) {
            closeLifecycle(false);
            EventBus.getDefault().unregister(this); // cancel evenbus registration
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onConfigurationChanged() {
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
        mCurStatusMessageId = message_id; // save current message id foe retaining purposes
        if(mView != null){
            mView.showStatusMessage(message_id);
        }
    }

    /**
     * EventBus events receiver
     * @param message Event message received
     */
    @Subscribe
    public void onEvent(EventMessage message) {
        showStatusMessage(message.getMessageId());
    }
}
