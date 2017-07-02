package com.sushinski.tokboxchat.data_source;


import android.content.Context;
import android.support.annotation.NonNull;

import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.interfaces.IAuthService;
import com.sushinski.tokboxchat.model.OpenTokSession;

public class LocalKeySource implements IAuthService {
    private static final String API_KEY = "45900062";
    private static final String SESSION_ID = "2_MX40NTkwMDA2Mn5-MTQ5ODc0ODQxMjY1N356alJPVmFaRzNlb1J2dXI3aDI3NnN4b1B-UH4";
    private static final String TOKEN = "T1==cGFydG5lcl9pZD00NTkwMDA2MiZzaWc9N2U2ODY3NDJmMzQ0MDczMDZkMGExZWEyYjI1Y2UxZTdkNzEwODVhMzpzZXNzaW9uX2lkPTJfTVg0ME5Ua3dNREEyTW41LU1UUTVPRGMwT0RReE1qWTFOMzU2YWxKUFZtRmFSek5sYjFKMmRYSTNhREkzTm5ONGIxQi1VSDQmY3JlYXRlX3RpbWU9MTQ5ODc0ODQ0MSZub25jZT0wLjQzMzQwNTg2MTQ1ODk4NTc0JnJvbGU9cHVibGlzaGVyJmV4cGlyZV90aW1lPTE0OTg3NTIwMzk=";

    public LocalKeySource() {
    }

    @Override
    public void openAuthSession(ISessionInteractor listener, String unique_app_key) {
        OpenTokSession session = new OpenTokSession();
        session.apiKey = API_KEY;
        session.sessionId = SESSION_ID;
        session.token = TOKEN;
        listener.onAuthReceived(session);
    }

    @Override
    public void closeAuthSession(ISessionInteractor listener) {

    }
}
