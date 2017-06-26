package com.sushinski.tokboxchat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenTokSession {

    @SerializedName("apiKey")
    @Expose
    public String apiKey;

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("sessionId")
    @Expose
    public String sessionId;

    public String getApiKey(){
        return apiKey;
    }

    public String getSessionId(){
        return sessionId;
    }

    public String getToken(){
        return token;
    }
}
