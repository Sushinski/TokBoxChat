package com.sushinski.tokboxchat.di;

import android.content.Context;

import com.sushinski.tokboxchat.data_source.RestApiKeySource;
import com.sushinski.tokboxchat.data_source.UniqueAppKeySource;
import com.sushinski.tokboxchat.interfaces.IAuthService;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.managers.OpenTokAuthManager;
import com.sushinski.tokboxchat.managers.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {
    private IRequiredPresenterOps mPresenter;
    public ManagerModule(IRequiredPresenterOps presenter){
        this.mPresenter = presenter;
    }

    @Provides
    public IAuthService provideKeySource(){
        return new RestApiKeySource(); //replace with new LocalKeySource(); for builtin test keys
    }

    @Provides
    public UniqueAppKeySource provideAppUniqueKeySource(){
        return new UniqueAppKeySource(mPresenter.getAppContext());
    }

    @Provides
    @Singleton
    public ISessionInteractor provideSessionListener(){
        return new SessionManager(mPresenter);
    }

    @Provides
    @Singleton
    public OpenTokAuthManager provideAuthManager(ISessionInteractor listener,
                                                 IAuthService auth_key_source,
                                                 UniqueAppKeySource unique_key_source){
        return new OpenTokAuthManager(listener, auth_key_source, unique_key_source);
    }

}
