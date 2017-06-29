package com.sushinski.tokboxchat.managers;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.model.EventMessage;
import com.sushinski.tokboxchat.model.OpenTokSession;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class SessionManager implements
        ISessionListener,
        Session.SessionListener,
        PublisherKit.PublisherListener{
    private IRequiredPresenterOps mPresenter;
    private Session mSession;
    private Publisher mPublisher;
    private Subscriber mSubscriber;

    @Inject
    public SessionManager(){
    }

    public SessionManager(@NonNull IRequiredPresenterOps presenter){
        mPresenter = presenter;
    }

    @Override
    public void onSessionReceived(OpenTokSession session_auth) {
        mSession = new Session.Builder(mPresenter.getAppContext(),
                session_auth.getApiKey(),
                session_auth.getSessionId()).build();
        mSession.setSessionListener(this);
        mSession.connect(session_auth.getToken());
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.pending_connection,
                        ""));
    }

    @Override
    public void onConnected(Session session) {
        mPublisher = new Publisher.Builder(mPresenter.getAppContext()).build();
        mPublisher.setPublisherListener(this);
        mPresenter.addPublisherView(getPublisherView());
        mSession.publish(mPublisher);
    }

    @Override
    public void onDisconnected(Session session) {
        mPresenter.clearPublisherView();
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        if (mSubscriber == null) {
            mSubscriber = new Subscriber.Builder(mPresenter.getAppContext(),
                    stream).build();
            mSession.subscribe(mSubscriber);
            mPresenter.addSubscriberView(getSubscriberView());
        }
    }


    @Override
    public void onStreamDropped(Session session, Stream stream) {
        if (mSubscriber != null) {
            mSubscriber = null;
            mPresenter.clearSubscriberView();
        }
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.pending_connection,
                        ""));
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.ERROR,
                        R.string.pending_connection,
                        ""));
    }

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {
    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {
    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.ERROR,
                        R.string.pending_connection,
                        ""));
    }

    @Override
    public View getPublisherView(){
        if(mPublisher != null){
            return mPublisher.getView();
        }else{
            return null;
        }
    }

    @Override
    public View getSubscriberView(){
        if(mSubscriber != null){
            return mSubscriber.getView();
        }else{
            return null;
        }
    }
}
