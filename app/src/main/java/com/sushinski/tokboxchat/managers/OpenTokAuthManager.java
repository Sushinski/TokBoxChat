package com.sushinski.tokboxchat.managers;

import android.support.annotation.NonNull;
import com.sushinski.tokboxchat.data_source.RestApiKeySource;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.model.OpenTokSession;

public class OpenTokAuthManager implements ISessionListener {
    private OpenTokSession mSessionAuth;
    private ISessionListener mListener = null;

    public OpenTokAuthManager(){
    }

    public OpenTokAuthManager setListener(ISessionListener listener){
        mListener = listener;
        return this;
    }

    public OpenTokAuthManager build(){
        new RestApiKeySource().getSession(this);
        return this;
    }

    public OpenTokSession getSessionAuth(){
        return mSessionAuth;
    }

    @Override
    public void onSessionReceived(OpenTokSession session) {
        mSessionAuth = session;
        if(mListener != null){
            mListener.onSessionReceived(mSessionAuth);
        }
    }
}
