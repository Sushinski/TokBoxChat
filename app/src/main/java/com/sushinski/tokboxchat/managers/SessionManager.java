package com.sushinski.tokboxchat.managers;

import android.support.annotation.NonNull;
import android.util.Log;
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

public class SessionManager implements
        ISessionListener,
        Session.SessionListener,
        PublisherKit.PublisherListener{
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
        mPublisher = new Publisher.Builder(mPresenter.getViewContext()).build();
        mPublisher.setPublisherListener(this);
        mPresenter.addPublisherView(mPublisher.getView());
        mSession.publish(mPublisher);
    }

    @Override
    public void onDisconnected(Session session) {
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.pending_connection,
                        ""));
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        if (mSubscriber == null) {
            mSubscriber = new Subscriber.Builder(mPresenter.getViewContext(), stream).build();
            mSession.subscribe(mSubscriber);
            mPresenter.addSubscriberView(mSubscriber.getView());
        }
    }


    @Override
    public void onStreamDropped(Session session, Stream stream) {
        if (mSubscriber != null) {
            mSubscriber = null;
            mPresenter.clearAllViews();
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
    }
}
