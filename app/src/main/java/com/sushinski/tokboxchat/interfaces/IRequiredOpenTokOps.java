package com.sushinski.tokboxchat.interfaces;


import android.content.Context;
import android.view.View;

/**
 * Base operations for OpenTok-based applications functionality
 */
public interface IRequiredOpenTokOps {
    /**
     * Retrieves application context
     * @return Current Application context
     */
    Context getAppContext();

    /**
     * Adds opentok-based stream publisher view to current class
     * @param view OpenTok-publisher View to add
     */
    void addPublisherView(View view);

    /**
     * Adds opentok-based stream subscriber view to current class
     * @param view OpenTok-publisher View to add
     */
    void addSubscriberView(View view);

    /**
     * Clears current associated publisher view
     */
    void clearPublisherView();

    /**
     * Clears current associated subscriber view
     */
    void clearSubscriberView();

    /**
     * Shows status messages
     * @param message_id Message resource id to show
     */
    void showStatusMessage(int message_id);
}
