package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.UserManager;

import java.util.ArrayList;

public class DrinksFoundActivity extends AppCompatActivity implements
        View.OnClickListener, DrinkRecyclerViewAdapter.ItemClickListener {

    TextView greeting, canMake;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";
    DrinkRecyclerViewAdapter adapter;
    Controller controller;
    ArrayList<Object> drinkObjects = new ArrayList<>();
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        logToggle = new LogToggle();

        userName = UserManager.getUserName();

        if (!UserManager.getUserName().equals("guest")) {
            setContentView(R.layout.activity_drinks_found);
            greeting = (TextView) findViewById(R.id.greeting);
            greeting.setText(userName);
            logBtn = (Button) findViewById(R.id.logBtn);
            logBtn.setText("Log Out");

            createDrinkBtn = (Button) findViewById(R.id.createNVBtn);
            createDrinkBtn.setOnClickListener(this);

            favesBtn = (Button) findViewById(R.id.favesNVBtn);
            favesBtn.setOnClickListener(this);

            shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
            shoppingBtn.setOnClickListener(this);

            cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
            cabinetBtn.setOnClickListener(this);

        } else {
            setContentView(R.layout.drinks_found_guest);
            logBtn = (Button) findViewById(R.id.logBtn);
        }

        logBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);



        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        ArrayList<String> makables = getIntent().getStringArrayListExtra("makableNames");
        ArrayList<String> nearMakables = getIntent().getStringArrayListExtra("nearMakableNames");
        ArrayList<String> nearMakableMatch = getIntent().getStringArrayListExtra("nearMakableMatch");

        canMake = (TextView) findViewById(R.id.canMake);

        if(makables.size()==0){
            canMake.setText("Sorry, there are no A_Drink you can make with the ingredients selected");
        }


        for (String d: makables){
            drinkObjects.add(new DrinkForAdapter(d, "100"));
        }
        drinkObjects.add("You can almost make these A_Drink");
        for (int i = 0; i < nearMakables.size(); i++){
            drinkObjects.add(new DrinkForAdapter(nearMakables.get(i), nearMakableMatch.get(i)));
        }


        RecyclerView rv = findViewById(R.id.rvDrinks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DrinkRecyclerViewAdapter(this, drinkObjects);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                startActivity(logToggle.logToggle(logBtn,greeting,packageName));
                break;

            case R.id.searchNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.SearchActivity");
                startActivity(intent);
                break;

            case R.id.createNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.CreateDrinkActivity");
                startActivity(intent);
                break;

            case R.id.favesNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.FavoritesActivity");
                startActivity(intent);
                break;

            case R.id.shoppingNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.ShoppingListActivity");
                startActivity(intent);
                break;

            case R.id.cabinetNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.CabinetActivity");
                startActivity(intent);
                break;

            case R.id.randomNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.RandomActivity");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        // probably need to handle for clicking on the text item which is not a DrinkForAdapter

        DrinkForAdapter drink = (DrinkForAdapter) drinkObjects.get(position);
        String nameString = drink.getDrinkName();

        Intent intent = new Intent();
        intent.putExtra("drink", nameString);
        intent.setClassName(packageName,
                packageName + ".UI.DrinkRecipeActivity");
        startActivity(intent);

    }

    public class DrinkForAdapter{
        public String drinkName;
        public String drinkPercent;

        DrinkForAdapter(String drinkName, String drinkPercent){
            this.drinkName = drinkName;
            this.drinkPercent = drinkPercent;
        }

        public String getDrinkName() {
            return drinkName;
        }

        public void setDrinkName(String drinkName) {
            this.drinkName = drinkName;
        }

        public String getDrinkPercent() {
            return drinkPercent;
        }

        public void setDrinkPercent(String drinkPercent) {
            this.drinkPercent = drinkPercent;
        }
    }
}
