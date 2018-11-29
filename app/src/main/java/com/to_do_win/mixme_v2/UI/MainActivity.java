package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.utilities.UserManager;


/**
 * Main activity will display logo while connecting to DB and checking SharedPrefernces to
 * determine if a user is currently logged in or not. Upon determination of logged in status,
 * intent will be started for either Cabinet (logged in) or LogIn (not logged in).
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String packageName = "com.to_do_win.mixme_v2";
        Intent intent = new Intent();

        // Check if User is Already Logged In

        if (UserManager.getUserName().equals("fail")){
            intent.setClassName(packageName,
                    packageName +".UI.LoginActivity");
        } else {
            intent.setClassName(packageName,
                    packageName +".UI.CabinetActivity");
        }
        startActivity(intent);
    }
}
