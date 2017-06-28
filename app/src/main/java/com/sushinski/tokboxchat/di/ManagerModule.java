package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.interfaces.ISessionService;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.managers.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataSourceModule.class})
public class ManagerModule {
    private IRequiredPresenterOps mPresenter;
    public ManagerModule(IRequiredPresenterOps presenter){
        this.mPresenter = presenter;
    }

    @Provides
    @Singleton
    ISessionListener provideSessionLister(){
        return new SessionManager(mPresenter);
    }

    @Provides
    @Singleton
    OpenTokAuthManager provideAuthManager(ISessionListener listener,
                                          ISessionService key_source){
        return new OpenTokAuthManager().
                setListener(listener).
                setKeySource(key_source).
                build();
    }

}
