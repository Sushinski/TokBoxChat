package com.sushinski.tokboxchat.managers;

import android.support.annotation.NonNull;

import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.data_source.RestApiKeySource;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.interfaces.ISessionService;
import com.sushinski.tokboxchat.model.EventMessage;
import com.sushinski.tokboxchat.model.OpenTokSession;

import org.greenrobot.eventbus.EventBus;

public class OpenTokAuthManager implements ISessionListener {
    private OpenTokSession mSessionAuth;
    private ISessionService mSessionKeySource;
    private ISessionListener mListener = null;

    public OpenTokAuthManager(){
    }

    public OpenTokAuthManager setListener(ISessionListener listener){
        mListener = listener;
        return this;
    }

    public OpenTokAuthManager setKeySource(ISessionService session_key_source){
        mSessionKeySource = session_key_source;
        return this;
    }

    public OpenTokAuthManager build(){
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.requesting_auth,
                        ""));
        mSessionKeySource.getSession(this);
        return this;
    }

    public OpenTokSession getSessionAuth(){
        return mSessionAuth;
    }

    @Override
    public void onSessionReceived(OpenTokSession session) {
        mSessionAuth = session;
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.pending_connection,
                        ""));
        if(mListener != null){
            mListener.onSessionReceived(mSessionAuth);
        }
    }
}
