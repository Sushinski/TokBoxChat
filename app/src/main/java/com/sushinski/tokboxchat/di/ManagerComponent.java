package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {ManagerModule.class})
@Singleton
public interface ManagerComponent {
    ISessionInteractor getSessionListener();
    OpenTokAuthManager getAuthManager();
}
