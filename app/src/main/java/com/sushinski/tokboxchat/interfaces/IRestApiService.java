package com.sushinski.tokboxchat.interfaces;


import com.sushinski.tokboxchat.model.OpenTokSession;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRestApiService{
    @GET("session/new/{app_key}")
    Call<OpenTokSession> getSession(@Path("app_key") String app_key);

    @DELETE("session/delete/{session_id}")
    Call<ResponseBody> deleteSession(@Path("session_id") String session_id);
}
