package com.sushinski.tokboxchat.managers;

import android.support.annotation.NonNull;

import com.sushinski.tokboxchat.R;
import com.sushinski.tokboxchat.data_source.UniqueAppKeySource;
import com.sushinski.tokboxchat.interfaces.IAuthService;
import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.model.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Auth session interaction implementation
 */
public class OpenTokAuthManager{
    private final IAuthService mSessionKeySource;
    private ISessionInteractor mListener = null;
    private UniqueAppKeySource mUniqueKeySource = null;

    public OpenTokAuthManager(@NonNull ISessionInteractor listener,
                              @NonNull IAuthService session_key_source,
                              @NonNull UniqueAppKeySource source){
        mListener = listener;
        mSessionKeySource = session_key_source;
        mUniqueKeySource = source;
    }

    public void open(){
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.requesting_auth));
        mSessionKeySource.openAuthSession(mListener, mUniqueKeySource.getUniqueAppKey());
    }

    public void close(){
        EventBus.getDefault().post(
                new EventMessage(
                        EventMessage.Type.INFO,
                        R.string.close_remote));
        mSessionKeySource.closeAuthSession(mListener);
    }
}
