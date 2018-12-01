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

public class FavoritesActivity extends AppCompatActivity implements View.OnClickListener,
        FavoritesRecyclerViewAdapter.ItemClickListener {

    TextView greeting, favoritesTV;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";
    FavoritesRecyclerViewAdapter adapter;
    Controller controller;
    ArrayList<String> drinks = new ArrayList<>();
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logToggle = new LogToggle();

        userName = UserManager.getUserName();

        if (userName == null) {

            // This should never happen...shouldn't be in Favorites without logged in
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.LogInActivity");
            startActivity(intent);
        }

        setContentView(R.layout.activity_favorites);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        favoritesTV = (TextView) findViewById(R.id.favorites);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        createDrinkBtn = (Button) findViewById(R.id.createNVBtn);
        createDrinkBtn.setOnClickListener(this);

        favesBtn = (Button) findViewById(R.id.favesNVBtn);
        favesBtn.setOnClickListener(this);

        shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
        shoppingBtn.setOnClickListener(this);

        cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
        cabinetBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        controller = Controller.getInstance();
        ArrayList<String> favorites = controller.getUserFavorites();

        if(favorites.size() == 0){
            favoritesTV.setText("You do not have any favorites");
        }

        RecyclerView rv = findViewById(R.id.rvDrinks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoritesRecyclerViewAdapter(this, favorites);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);
    }

    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                startActivity(logToggle.logToggle(logBtn,greeting,packageName));
                break;

            case R.id.searchNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.AllDrinksActivity");
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
    public void onItemClick(View view, int position) {

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
