package com.sushinski.tokboxchat.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.di.DaggerMainComponent;
import com.sushinski.tokboxchat.di.MainComponent;
import com.sushinski.tokboxchat.di.MainModule;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.managers.PermissionManager;
import com.sushinski.tokboxchat.model.EventMessage;

import org.greenrobot.eventbus.EventBus;


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
        setContentView(R.layout.activity_main);
        mPublisherViewContainer = (FrameLayout)findViewById(R.id.publisher_container);
        mSubscriberViewContainer = (RelativeLayout)findViewById(R.id.subscriber_container);
        mStatusString = (TextView) findViewById(R.id.textView3);
        mProgress = (ProgressBar) findViewById(R.id.progressBar2);
        MainComponent di_component = DaggerMainComponent.builder().
                mainModule(new MainModule(this)).build();
        mPresenter = di_component.getMainPresenter();
        initPresenter();
        mPresenter.onCreate();
    }

    @Override
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mPresenter.closeLifecycle(false);
        super.onBackPressed();
    }

    private void initPresenter(){
        if(!hasOpenTokViewPermissions()) {
            EventBus.getDefault().post(new EventMessage(EventMessage.Type.INFO,
                    R.string.not_enough_permissions));
        }else{
            mPresenter.setView(this);
        }
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
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
    public void showStatusMessage(int message_id) {
        mStatusString.setText(getResources().getText(message_id));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        initPresenter();
    }
}
