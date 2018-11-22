package com.to_do_win.mixme_v2.utilities;

import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;

public class UserManager {

    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    private UserManager() {};

    public static String getUserName(){
        return firebaseAuth.getCurrentUser().getEmail();
    }


//    public static String getUserName(Context context){
//        return getSharedPreferences(context).getString(USER_NAME, null);
//    }
//
    public static void userLogOut(){
        firebaseAuth.signOut();
    }

}
