package com.sushinski.tokboxchat.di;

import com.sushinski.tokboxchat.interfaces.IRequiredOpenTokViewOps;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private IRequiredOpenTokViewOps mView;

    public MainModule(IRequiredOpenTokViewOps view){
        this.mView = view;
    }

    @Provides
    public IRequiredPresenterOps providePresenter(){
        return new MainPresenter(mView);
    }

    @Provides
    public IRequiredOpenTokViewOps provideView(){
        return mView;
    }
}
