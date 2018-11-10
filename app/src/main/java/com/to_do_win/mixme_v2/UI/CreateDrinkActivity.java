package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class CreateDrinkActivity extends AppCompatActivity implements LogToggle,
        View.OnClickListener, CreateRecyclerViewAdapter.ItemClickListener{

    String userName;
    TextView greeting;
    EditText drinkNameET, instructionsET, glassTypeET;
    Button logBtn, addIngredientBtn, submitBtn;
    Button searchDrinksBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";
    Controller controller;
    CreateRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_drink);

        controller = Controller.getInstance();

        userName = SharedPrefsManager.getUserName(CreateDrinkActivity.this);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        addIngredientBtn = (Button) findViewById(R.id.addIngredientBtn);
        addIngredientBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        favesBtn = (Button) findViewById(R.id.favesNVBtn);
        favesBtn.setOnClickListener(this);

        shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
        shoppingBtn.setOnClickListener(this);

        cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
        cabinetBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        drinkNameET = (EditText) findViewById(R.id.drinkName);
        instructionsET = (EditText) findViewById(R.id.instructionsText);
        glassTypeET = (EditText) findViewById(R.id.glassTypeText);

        if (getIntent().getBooleanExtra("use values", false)){
            drinkNameET.setText(controller.getCreationName());
            instructionsET.setText(controller.getCreationInstructions());
            glassTypeET.setText(controller.getCreationGlassType());
        }

        ArrayList<String> ingreds = controller.getCreationIngredNames();
        ArrayList<String> volumes = controller.getCreationVolumes();
        ArrayList<String> units = controller.getCreationUnits();

        RecyclerView rv = findViewById(R.id.rvIngredients);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CreateRecyclerViewAdapter(this, ingreds, volumes, units);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.addIngredientBtn:

                controller.setCreationName(drinkNameET.getText().toString());
                controller.setCreationInstructions(instructionsET.getText().toString());
                controller.setCreationGlassType(glassTypeET.getText().toString());

                intent.setClassName(packageName,
                        packageName +".UI.SelectIngredientActivity");
                startActivity(intent);
                break;

            case R.id.submitBtn:
                controller.setCreationName(drinkNameET.getText().toString());
                controller.setCreationInstructions(instructionsET.getText().toString());
                controller.setCreationGlassType(glassTypeET.getText().toString());

                controller.addCreation();
                intent.setClassName(packageName,
                        packageName +".UI.SearchActivity");
                startActivity(intent);
                break;

            case R.id.searchNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.SearchActivity");
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
    public void onItemClick(View view, int position) {
        Controller controller = Controller.getInstance();

        controller.removeCreationIngredient(position);
        controller.setCreationName(drinkNameET.getText().toString());
        controller.setCreationInstructions(instructionsET.getText().toString());
        controller.setCreationGlassType(glassTypeET.getText().toString());

        // possibly a booleanextra to determine whether to start intent with previous values of
        // name, instructs, and glasstype; then would get boolean extra upon starting activity
        // and using with conditional statements
        Intent intent = new Intent();
        intent.putExtra("use values", true);
        intent.setClassName(packageName,
                packageName +".UI.CreateDrinkActivity");
        startActivity(intent);
    }

    @Override
    public void logToggle(String userName) {
        SharedPrefsManager.setUserName(CreateDrinkActivity.this, null);
        Intent intent = new Intent();
        intent.setClassName(packageName,
                packageName +".UI.SearchActivity");
        startActivity(intent);
    }
}
