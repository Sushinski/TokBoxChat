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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;


public class MainActivity extends AppCompatActivity implements IRequiredOpenTokViewOps {

    private FrameLayout mPublisherViewContainer;
    private RelativeLayout mSubscriberViewContainer;
    private PermissionManager mPermissionManager;
    public MainPresenter mPresenter;
    private TextView mStatusString;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPublisherViewContainer = (FrameLayout)findViewById(R.id.publisher_container);
        mSubscriberViewContainer = (RelativeLayout)findViewById(R.id.subscriber_container);
        mStatusString = (TextView) findViewById(R.id.textView3);
        mProgress = (ProgressBar) findViewById(R.id.progressBar2);
        mPresenter = new MainPresenter(this); // todo inject this
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
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onPause(){
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void addPublisherView(View view) {
        mPublisherViewContainer.addView(view);
    }

    @Override
    public void addSubscriberView(View view) {
        mStatusString.setVisibility(View.GONE);
        mProgress.setVisibility(View.GONE);
        mSubscriberViewContainer.addView(view);
    }

    @Override
    public void clearViews() {
        mStatusString.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.VISIBLE);
        mSubscriberViewContainer.removeAllViews();
    }

    @Override
    public void showStatusMessage(String message) {
        mStatusString.setText(message);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
