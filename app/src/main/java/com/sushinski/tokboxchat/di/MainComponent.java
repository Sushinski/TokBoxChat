package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.data_source.UniqueAppKeySource;
import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules={MainModule.class})
@Singleton
public interface MainComponent {
    IRequiredPresenterOps getMainPresenter();
}
