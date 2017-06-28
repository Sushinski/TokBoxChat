package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.data_source.LocalKeySource;
import com.sushinski.tokboxchat.interfaces.ISessionService;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {
    @Provides
    public ISessionService provideKeySource(){
        return new LocalKeySource();
    }
}
