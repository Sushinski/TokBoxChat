package com.sushinski.tokboxchat.interfaces;


import com.sushinski.tokboxchat.model.OpenTokSession;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRestApiService{
    @GET("session")
    Call<OpenTokSession> getSession();
}
