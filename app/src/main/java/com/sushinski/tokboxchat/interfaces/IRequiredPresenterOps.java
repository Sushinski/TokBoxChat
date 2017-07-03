package com.sushinski.tokboxchat.interfaces;

/**
 * Defines OpenTok-associated presenter class operations
 */
public interface IRequiredPresenterOps extends IRequiredOpenTokOps {
    /**
     * preseter tag used for retaining its state
     */
    String PRESENTER_TAG = "OpenTokPresenter";

    /**
     * Assignes view to presenter
     * @param view OpenTok-enabled view
     */
    void setView(IRequiredOpenTokViewOps view);
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onConfigurationChanged();

    /**
     * Inits presenter lifecycle
     */
    void initLifecycle();

    /**
     * Stops presenter lifecycle
     * @param re_init - Re-init lifecycle flag
     */
    void closeLifecycle(boolean re_init);
}
