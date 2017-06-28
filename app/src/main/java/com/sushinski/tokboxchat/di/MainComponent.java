package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;

import dagger.Component;

@Component(modules={MainModule.class})
public interface MainComponent {
    IRequiredPresenterOps getMainPresenter();
    IRequiredOpenTokViewOps getMainView();
}
