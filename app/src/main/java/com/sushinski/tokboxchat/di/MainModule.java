package com.sushinski.tokboxchat.di;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sushinski.tokboxchat.data_source.PresenterHolderFragment;
import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;
import com.sushinski.tokboxchat.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private final AppCompatActivity mActivity;

    public MainModule(@NonNull AppCompatActivity activity){
        this.mActivity = activity;

    }

    @Provides
    @Singleton
    public IRequiredPresenterOps providePresenter(){
        IRequiredPresenterOps presenter;
        FragmentManager fm = mActivity.getSupportFragmentManager();
        PresenterHolderFragment phf =
                (PresenterHolderFragment) fm.
                        findFragmentByTag(IRequiredPresenterOps.PRESENTER_TAG);
        if(phf == null){
            presenter = new MainPresenter();
            phf = new PresenterHolderFragment();
            phf.setRetainInstance(true);
            phf.setRetainedPresenter(presenter);
            fm.beginTransaction().add(phf, IRequiredPresenterOps.PRESENTER_TAG).commit();
        }else{
            presenter = phf.getRetainedPresenter();
        }
        return presenter;
    }
}
