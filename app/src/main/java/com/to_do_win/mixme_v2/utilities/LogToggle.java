package com.to_do_win.mixme_v2.utilities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogToggle {


    public Intent logToggle(Button logBtn, TextView greeting, String packageName) {

        if (!UserManager.getUserName().equals("guest")) {
            UserManager.userLogOut();
            greeting.setText("Hello, Guest");
            logBtn.setText("Log In");
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.SearchActivity");
            return intent;

        } else {

            // This should never happen...shouldn't be in Cabinet without logged in
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName + ".UI.LoginActivity");
            return intent;
        }
    }


}
