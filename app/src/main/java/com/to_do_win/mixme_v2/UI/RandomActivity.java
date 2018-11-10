package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.SharedPrefsManager;


public class RandomActivity extends AppCompatActivity implements LogToggle, View.OnClickListener {

    TextView greeting;
    String packageName = "com.to_do_win.mixme_v2";
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn, findRandomDrinkBtn;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        userName = SharedPrefsManager.getUserName(RandomActivity.this);

        if (userName == null) {

            setContentView(R.layout.random_guest);
            logBtn = (Button) findViewById(R.id.logBtn);
            greeting = (TextView) findViewById(R.id.greeting);


        } else {
            setContentView(R.layout.activity_random);
            logBtn = (Button) findViewById(R.id.logBtn);
            logBtn.setText("Log Out");

            greeting = (TextView) findViewById(R.id.greeting);
            greeting.setText(userName);

            createDrinkBtn = (Button) findViewById(R.id.createNVBtn);
            createDrinkBtn.setOnClickListener(this);

            favesBtn = (Button) findViewById(R.id.favesNVBtn);
            favesBtn.setOnClickListener(this);

            shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
            shoppingBtn.setOnClickListener(this);

            cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
            cabinetBtn.setOnClickListener(this);
        }

        logBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        findRandomDrinkBtn = (Button) findViewById(R.id.findRandomDrink);
        findRandomDrinkBtn.setOnClickListener(this);

        controller = Controller.getInstance();

    }

    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.findRandomDrink:
                String nameString = controller.getRandomDrink();

                intent.putExtra("drink", nameString);
                intent.setClassName(packageName,
                        packageName +".UI.DrinkRecipeActivity");
                startActivity(intent);
                break;

            case R.id.searchNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.SearchActivity");
                startActivity(intent);
                break;

            case R.id.createNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.CreateDrinkActivity");
                startActivity(intent);
                break;

            case R.id.favesNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.FavoritesActivity");
                startActivity(intent);
                break;

            case R.id.shoppingNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.ShoppingListActivity");
                startActivity(intent);
                break;

            case R.id.cabinetNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.CabinetActivity");
                startActivity(intent);
                break;

            case R.id.randomNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.RandomActivity");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void logToggle(String userName) {
        if (userName != null) {
            SharedPrefsManager.setUserName(RandomActivity.this, null);
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.RandomActivity");
            startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.LoginActivity");
            startActivity(intent);
        }
    }
}