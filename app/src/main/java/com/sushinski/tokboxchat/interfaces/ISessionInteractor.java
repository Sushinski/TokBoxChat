package com.sushinski.tokboxchat.interfaces;

import android.view.View;

import com.sushinski.tokboxchat.model.OpenTokSession;

/**
 * Auth session interaction operations
 */
public interface ISessionInteractor {
    /**
     * Called when authorization responce received
     * @param session Auth responce
     */
    void onAuthReceived(OpenTokSession session);

    /**
     * Called when auth session closed
     */
    void onAuthClosed();

    /**
     * Returns OpenTok -associated publisher view
     * @return Opened Publisher view object
     */
    View getPublisherView();

    /**
     *  Returns OpenTok -associated ubscriber view
     * @return Opened Subscriber view object
     */
    View getSubscriberView();

}
