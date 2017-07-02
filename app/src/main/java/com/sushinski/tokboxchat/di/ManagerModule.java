package com.sushinski.tokboxchat.di;

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
    private final IRequiredPresenterOps mPresenter;
    public ManagerModule(IRequiredPresenterOps presenter){
        this.mPresenter = presenter;
    }

    /**
     * Provides auth key sorce service
     * @return key service instance
     */
    @Provides
    public IAuthService provideKeySource(){
        return new RestApiKeySource(); //replace with new LocalKeySource(); for builtin test keys
    }

    /**
     * Provides sorce of unique app key
     * @return Source of unique app key
     */
    @Provides
    public UniqueAppKeySource provideAppUniqueKeySource(){
        return new UniqueAppKeySource(mPresenter.getAppContext());
    }

    /**
     * Provides session interaction class
     * @return Interaction interface implementation
     */
    @Provides
    @Singleton
    public ISessionInteractor provideSessionListener(){
        return new SessionManager(mPresenter);
    }

    /**
     * Provides authentication manager class
     * @param listener authentication events listener
     * @param auth_key_source auth key sorce class
     * @param unique_key_source  unique app key source class
     * @return Auth Management implementation class
     */
    @Provides
    @Singleton
    public OpenTokAuthManager provideAuthManager(ISessionInteractor listener,
                                                 IAuthService auth_key_source,
                                                 UniqueAppKeySource unique_key_source){
        return new OpenTokAuthManager(listener, auth_key_source, unique_key_source);
    }

}
