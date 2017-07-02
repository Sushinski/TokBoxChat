package com.sushinski.tokboxchat.data_source;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.UUID;

public class UniqueAppKeySource {
    private static  final String UNIQUE_APP_KEY =
            "com.sushinski.tokboxchat.data_source.UNIQUE_APP_KEY";
    private String mUniqueKey = null;

    public UniqueAppKeySource(@NonNull Context context){
            SharedPreferences settings =
                    context.getSharedPreferences(UNIQUE_APP_KEY, Context.MODE_PRIVATE);
            mUniqueKey = settings.getString(UNIQUE_APP_KEY, null);
            if (mUniqueKey == null) {
                SharedPreferences.Editor editor = settings.edit();
                UUID uuid = UUID.randomUUID();
                mUniqueKey = uuid.toString();
                editor.putString(UNIQUE_APP_KEY, mUniqueKey);
                editor.apply();
            }
    }

    public String getUniqueAppKey(){
        return mUniqueKey;
    }
}
