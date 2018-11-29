package com.to_do_win.mixme_v2.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.to_do_win.mixme_v2.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {
    String packageName = "com.to_do_win.mixme_v2";
    private Button buttonSignIn;
    private EditText editText;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private Button buttonSaveData;
    private Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register =(Button) findViewById(R.id.buttonRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            //start profile activity here
            finish();
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.CabinetActivity");
            startActivity(intent);
        }

        editText = (EditText) findViewById(R.id.editTextEmailmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonLogin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);


        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    private void userLogin(){
        String email = editText.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText((this), "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText((this), "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    Intent intent = new Intent();
                    intent.setClassName(packageName,
                            packageName +".UI.CabinetActivity");
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void registerUser(){
        String email = editText.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT);
            return;
        }

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged on
                            //we will start the profile activity here
                            //display message
                            Toast.makeText(LoginActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent();
                            intent.setClassName(packageName,
                                    packageName +".UI.CabinetActivity");
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Registration Unsuccessful, Please try again", Toast.LENGTH_SHORT)
                                    .show();
                            //FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            // Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });
    }



    @Override
    public void onClick(View v) {
        if(v == buttonSignIn){
            userLogin();
        }

        if(v == textViewSignup){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        if(v == register){
            registerUser();
        }



    }
}

