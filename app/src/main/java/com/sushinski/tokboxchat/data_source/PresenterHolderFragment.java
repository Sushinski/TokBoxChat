package com.sushinski.tokboxchat.data_source;

import android.support.v4.app.Fragment;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;


public class PresenterHolderFragment extends Fragment {
    private IRequiredPresenterOps mPresenter;

    public PresenterHolderFragment(){
        super();
    }

    public void setRetainedPresenter(IRequiredPresenterOps presenter){
        mPresenter = presenter;
    }

    public IRequiredPresenterOps getRetainedPresenter(){
        return mPresenter;
    }
}
