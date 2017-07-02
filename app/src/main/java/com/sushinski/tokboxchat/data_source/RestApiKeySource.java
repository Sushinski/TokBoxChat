package com.sushinski.tokboxchat.data_source;

import com.sushinski.tokboxchat.interfaces.IAuthService;
import com.sushinski.tokboxchat.interfaces.IRestApiService;
import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.model.OpenTokSession;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Key source api class used for interacting with remote auth server
 */
public class RestApiKeySource implements IAuthService {
    /**
     * test server "http://85.143.215.126/" (may be unavailable ), could be replaced with local
     *  django serv instance (see https://github.com/Sushinski/OpenTokAuthServer)
     *  ip-s could be: "http://10.0.3.2:8000/" for genymotion emulator and test django serv
     */
    private final static String mAuthServerUrl = "http://85.143.215.126/";
    private final IRestApiService mSessionService;
    private OpenTokSession mCurKey = null;

    /**
     * public oonstaructor configures auth session service
     */
    public RestApiKeySource() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mAuthServerUrl).
                addConverterFactory(GsonConverterFactory.create()).build();
        mSessionService = retrofit.create(IRestApiService.class);
    }

    /**
     * Opens auth session
     * @param listener retrieved keys listener
     * @param unique_app_key unique app key used for session identity
     */
    @Override
    public void openAuthSession(final ISessionInteractor listener, final String unique_app_key) {
        Call<OpenTokSession> call = mSessionService.getSession(unique_app_key);
        call.enqueue(new Callback<OpenTokSession>() {

            @Override
            public void onResponse(Call<OpenTokSession> call, Response<OpenTokSession> response) {
                if (listener != null) {
                    mCurKey = response.body();
                    listener.onAuthReceived(mCurKey);
                }
            }

            @Override
            public void onFailure(Call<OpenTokSession> call, Throwable t) {
                mCurKey = null;
            }
        });
    }

    /**
     * Close auth session for current key
     * @param listener Session close responce listener
     */
    @Override
    public void closeAuthSession(final ISessionInteractor listener) {
        if( mCurKey!= null) {
            Call<ResponseBody> call = mSessionService.deleteSession(mCurKey.getSessionId());
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (listener != null) {
                        listener.onAuthClosed();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                }
            });
        }
    }
}
