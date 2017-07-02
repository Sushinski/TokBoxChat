package com.sushinski.tokboxchat.data_source;


import com.sushinski.tokboxchat.interfaces.IAuthService;
import com.sushinski.tokboxchat.interfaces.ISessionInteractor;
import com.sushinski.tokboxchat.model.OpenTokSession;

/**
 * Local key source file; returns pre-configured session auth keys
 */
public class LocalKeySource implements IAuthService {
    private static final String API_KEY = "45900062";
    private static final String SESSION_ID = "2_MX40NTkwMDA2Mn5-MTQ5OTAyOTI3NjY1OX5JZkt0aktGMHRSNXIyT2d2Y2szZmUrMit-UH4";
    private static final String TOKEN = "T1==cGFydG5lcl9pZD00NTkwMDA2MiZzaWc9ZmVlMTM3Njk5OGU2NGYxODIzMjkxYzJiYjI3ZTc2YW" +
            "I0ZWMxN2RhMzpzZXNzaW9uX2lkPTJfTVg0ME5Ua3dNREEyTW41LU1UUTVPVEF5T1RJM05qWTFPWDVKWmt0MGFrdEdNSFJTTlhJeVQyZDJZ" +
            "MnN6Wm1Vck1pdC1VSDQmY3JlYXRlX3RpbWU9MTQ5OTAyOTMxMyZub25jZT0wLjk1MjIxNTkwNDQxNjgwMzMmcm9sZT1tb2RlcmF0b3ImZX" +
            "hwaXJlX3RpbWU9MTUwMTYyMTMxMw==";

    public LocalKeySource() {
    }

    @Override
    public void openAuthSession(final ISessionInteractor listener, String unique_app_key) {
        OpenTokSession session = new OpenTokSession();
        session.apiKey = API_KEY;
        session.sessionId = SESSION_ID;
        session.token = TOKEN;
        if(listener != null) {
            listener.onAuthReceived(session);
        }
    }

    @Override
    public void closeAuthSession(final ISessionInteractor listener) {
        if(listener != null){
            listener.onAuthClosed();
        }
    }
}
