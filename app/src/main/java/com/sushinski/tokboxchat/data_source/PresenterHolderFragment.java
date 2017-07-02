package com.sushinski.tokboxchat.data_source;

import android.support.v4.app.Fragment;

import com.sushinski.tokboxchat.interfaces.IRequiredPresenterOps;

/**
 * Presenter holder fragment class used for retaining presenter instance
 */
public class PresenterHolderFragment extends Fragment {
    private IRequiredPresenterOps mPresenter;

    public PresenterHolderFragment(){
        super();
    }

    public void setRetainedPresenter(IRequiredPresenterOps presenter){
        mPresenter = presenter;
    }

    /**
     * Gets class-associated retained presenter instance
     * @return rretained presenter instance
     */
    public IRequiredPresenterOps getRetainedPresenter(){
        return mPresenter;
    }
}
