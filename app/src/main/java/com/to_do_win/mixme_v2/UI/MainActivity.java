package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.utilities.UserManager;


/**
 * Main activity will display logo while connecting to DB and checking SharedPrefernces to
 * determine if a user is currently logged in or not. Upon determination of logged in status,
 * intent will be started for either Cabinet (logged in) or LogIn (not logged in).
 */
public class MainActivity extends AppCompatActivity {

    private Button buttonRegister;
    private Button buttonGoToAddDrink;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String packageName = "com.to_do_win.mixme_v2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();

        // Check if User is Already Logged In

        if (UserManager.getUserName().equals("guest")) {
            intent.setClassName(packageName,
                    packageName + ".UI.LoginActivity");
        }else {
            intent.setClassName(packageName,
                    packageName +".UI.CabinetActivity");
        }
        startActivity(intent);
    }







}
