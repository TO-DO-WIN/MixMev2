package com.to_do_win.mixme_v2.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";
    private static final String USER_NAME = "userName";

    private SharedPrefsManager() {};

    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getUserName(Context context){
        return getSharedPreferences(context).getString(USER_NAME, null);
    }

    public static void setUserName(Context context, String userName){
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

}
