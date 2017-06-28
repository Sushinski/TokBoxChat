package com.sushinski.tokboxchat.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.di.DaggerMainComponent;
import com.sushinski.tokboxchat.di.MainComponent;
import com.sushinski.tokboxchat.di.MainModule;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.managers.PermissionManager;
import com.sushinski.tokboxchat.presenter.MainPresenter;

import android.support.annotation.NonNull;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements IRequiredOpenTokViewOps {

    private FrameLayout mPublisherViewContainer;
    private RelativeLayout mSubscriberViewContainer;
    private PermissionManager mPermissionManager;
    private TextView mStatusString;
    private ProgressBar mProgress;
    private View mPublisherView;
    private View mSubscriberView;
    private IRequiredPresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent di_component = DaggerMainComponent.builder().
                mainModule(new MainModule(this)).build();
        setContentView(R.layout.activity_main);
        mPublisherViewContainer = (FrameLayout)findViewById(R.id.publisher_container);
        mSubscriberViewContainer = (RelativeLayout)findViewById(R.id.subscriber_container);
        mStatusString = (TextView) findViewById(R.id.textView3);
        mProgress = (ProgressBar) findViewById(R.id.progressBar2);
        mPresenter = di_component.getMainPresenter();
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
        if(view != null) {
            mPublisherViewContainer.addView(view);
            mPublisherView = view;
        }
    }

    @Override
    public void addSubscriberView(View view) {
        if(view != null) {
            mStatusString.setVisibility(View.GONE);
            mProgress.setVisibility(View.GONE);
            mSubscriberViewContainer.addView(view);
            mSubscriberView = view;
        }
    }

    @Override
    public void clearPublisherView() {
        if(mPublisherView != null) {
            mPublisherViewContainer.removeView(mPublisherView);
            mPublisherView = null;
        }

    }

    @Override
    public void clearSubscriberView() {
        mStatusString.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.VISIBLE);
        if(mSubscriberView != null) {
            mSubscriberViewContainer.removeView(mSubscriberView);
            mSubscriberView = null;
        }
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
