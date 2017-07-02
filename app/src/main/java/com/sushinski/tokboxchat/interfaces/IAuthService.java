package com.sushinski.tokboxchat.interfaces;

/**
 * Authentication service interface
 */
public interface IAuthService {
    /**
     * Open auth session
     * @param listener Auth events listener
     * @param unique_app_key Unique app key used for indetity
     */
    void openAuthSession(ISessionInteractor listener, String unique_app_key);
    void closeAuthSession(ISessionInteractor listener);
}
