package com.sushinski.tokboxchat.interfaces;

public interface IAuthService {
    void openAuthSession(ISessionInteractor listener, String unique_app_key);
    void closeAuthSession(ISessionInteractor listener);
}
