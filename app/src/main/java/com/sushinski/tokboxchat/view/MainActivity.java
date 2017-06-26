package com.sushinski.tokboxchat.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.managers.PermissionManager;
import com.sushinski.tokboxchat.presenter.MainPresenter;

import android.support.annotation.NonNull;

import android.view.View;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity implements IRequiredOpenTokViewOps {

    private FrameLayout mPublisherViewContainer;
    private FrameLayout mSubscriberViewContainer;
    private PermissionManager mPermissionManager;
    public MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPublisherViewContainer = (FrameLayout)findViewById(R.id.publisher_container);
        mSubscriberViewContainer = (FrameLayout)findViewById(R.id.subscriber_container);
        mPresenter = new MainPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean hasOpenTokViewPermissions() {
        if(mPermissionManager == null){
            mPermissionManager = new PermissionManager(this);
        }
        return mPermissionManager.isPermissionsGranted();
    }


    @Override
    public void addPublisherView(View view) {
        mPublisherViewContainer.addView(view);
    }

    @Override
    public void addSubscriberView(View view) {
        mSubscriberViewContainer.addView(view);
    }

    @Override
    public void clearViews() {
        mSubscriberViewContainer.removeAllViews();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
