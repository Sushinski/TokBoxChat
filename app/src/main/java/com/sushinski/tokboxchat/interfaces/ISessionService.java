package com.sushinski.tokboxchat.interfaces;

import com.sushinski.tokboxchat.model.OpenTokSession;

public interface ISessionService {
    void getSession(ISessionListener listener);
}
