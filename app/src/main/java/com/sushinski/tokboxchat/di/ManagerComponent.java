package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {ManagerModule.class})
@Singleton
public interface ManagerComponent {
    ISessionListener getSessionListener();
    OpenTokAuthManager getAuthManager();
}
