package com.sushinski.tokboxchat.interfaces;

import com.sushinski.tokboxchat.model.OpenTokSession;

public interface ISessionListener {
    void onSessionReceived(OpenTokSession session);
}
