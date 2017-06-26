package com.sushinski.tokboxchat.data_source;


import com.sushinski.tokboxchat.interfaces.ISessionListener;
import com.sushinski.tokboxchat.interfaces.ISessionService;
import com.sushinski.tokboxchat.model.OpenTokSession;

public class LocalKeySource implements ISessionService{
    private static final String API_KEY = "45900062";
    private static final String SESSION_ID = "2_MX40NTkwMDA2Mn5-MTQ5ODI0ODYyMzI1MH5GcTh4RWhtSnVZdzM5R0gzSmFIa3dLblR-fg";
    private static final String TOKEN = "T1==cGFydG5lcl9pZD00NTkwMDA2MiZzaWc9MGI4MGE0MjY0OGFjYmI2ZTkxMjA1ZmIzYjQ1YjhjMmI3YmUzYTRiZjpzZXNzaW9uX2lkPTJfTVg0ME5Ua3dNREEyTW41LU1UUTVPREkwT0RZeU16STFNSDVHY1RoNFJXaHRTblZaZHpNNVIwZ3pTbUZJYTNkTGJsUi1mZyZjcmVhdGVfdGltZT0xNDk4MjQ4NzA3Jm5vbmNlPTAuOTI0MjMxMDUyNTI1NzI5MyZyb2xlPW1vZGVyYXRvciZleHBpcmVfdGltZT0xNTAwODQwNzA0";

    @Override
    public void getSession(ISessionListener listener) {
        OpenTokSession session = new OpenTokSession();
        session.apiKey = API_KEY;
        session.sessionId = SESSION_ID;
        session.token = TOKEN;
        listener.onSessionReceived(session);
    }
}
