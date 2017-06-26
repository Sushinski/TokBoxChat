package com.sushinski.tokboxchat.data_source;

import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.sushinski.tokboxchat.interfaces.IRestApiService;
import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.interfaces.ISessionService;
import com.sushinski.tokboxchat.model.OpenTokSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiKeySource implements ISessionService {
    public final static String mAuthServerUrl = "http://10.0.3.2:8000/"; // todo: inject this ;

    public RestApiKeySource() {
    }


    @Override
    public void getSession(final ISessionListener listener) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mAuthServerUrl).
                addConverterFactory(GsonConverterFactory.create()).build();
        IRestApiService session_service = retrofit.create(IRestApiService.class);
        Call<OpenTokSession> call = session_service.getSession();
        call.enqueue(new Callback<OpenTokSession>() {

            @Override
            public void onResponse(Call<OpenTokSession> call, Response<OpenTokSession> response) {
                if (listener != null) {
                    listener.onSessionReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<OpenTokSession> call, Throwable t) {

            }
        });
    }
}
