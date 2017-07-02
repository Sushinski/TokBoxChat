package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules={MainModule.class})
@Singleton
public interface MainComponent {
    IRequiredPresenterOps getMainPresenter();
}
