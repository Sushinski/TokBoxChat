package com.sushinski.tokboxchat.managers;


import android.content.pm.PackageInstaller;
import android.support.annotation.NonNull;
import android.util.Log;

import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.model.OpenTokSession;

public class SessionManager implements
        ISessionListener,
        Session.SessionListener,
        PublisherKit.PublisherListener{
    private static final String LOG_TAG = SessionManager.class.getSimpleName();
    private IRequiredPresenterOps mPresenter;
    private Session mSession;
    private Publisher mPublisher;
    private Subscriber mSubscriber;

    public SessionManager(@NonNull IRequiredPresenterOps presenter){
        mPresenter = presenter;
    }

    @Override
    public void onSessionReceived(OpenTokSession session_auth) {
        mSession = new Session.Builder(mPresenter.getViewContext(),
                session_auth.getApiKey(),
                session_auth.getSessionId()).build();
        mSession.setSessionListener(this);
        mSession.connect(session_auth.getToken());
    }

    @Override
    public void onConnected(Session session) {
        Log.i(LOG_TAG, "Session Connected");
        mPublisher = new Publisher.Builder(mPresenter.getViewContext()).build();
        mPublisher.setPublisherListener(this);
        mPresenter.addPublisherView(mPublisher.getView());
        mSession.publish(mPublisher);
    }

    @Override
    public void onDisconnected(Session session) {
        Log.i(LOG_TAG, "Session Disconnected");
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        Log.i(LOG_TAG, "Stream Received");

        if (mSubscriber == null) {
            mSubscriber = new Subscriber.Builder(mPresenter.getViewContext(), stream).build();
            mSession.subscribe(mSubscriber);
            mPresenter.addSubscriberView(mSubscriber.getView());
        }
    }


    @Override
    public void onStreamDropped(Session session, Stream stream) {
        Log.i(LOG_TAG, "Stream Dropped");
        if (mSubscriber != null) {
            mSubscriber = null;
            mPresenter.clearAllViews();
        }
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {
        Log.e(LOG_TAG, "Session error: " + opentokError.getMessage());
    }


    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {
        Log.i(LOG_TAG, "Publisher onStreamCreated");
    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {
        Log.i(LOG_TAG, "Publisher onStreamDestroyed");
    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {
        Log.e(LOG_TAG, "Publisher error: " + opentokError.getMessage());
    }
}
