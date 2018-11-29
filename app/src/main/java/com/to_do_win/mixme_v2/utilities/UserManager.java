package com.to_do_win.mixme_v2.utilities;


import com.google.firebase.auth.FirebaseAuth;

public class UserManager {

    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static String getUserName(){
        firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth.getCurrentUser()==null ? "guest" : firebaseAuth.getCurrentUser().getEmail();
    }


//    public static String getUserName(Context context){
//        return getSharedPreferences(context).getString(USER_NAME, null);
//    }
//
    public static void userLogOut(){

        if(getUserName().equals("guest")){
            firebaseAuth.signOut();
        }

    }

}
