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
import com.to_do_win.mixme_v2.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity implements LogToggle, View.OnClickListener,
        ShoppingRecyclerViewAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";

    TextView ingredsTV;
    ShoppingRecyclerViewAdapter adapter;
    Button addIngredsBtn;
    Controller controller;

    int posOfText;
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(ShoppingListActivity.this);

        if (userName == null) {

            // This should never happen...shouldn't be in ShoppingList without logged in
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName + ".UI.LogInActivity");
            startActivity(intent);
        }

        setContentView(R.layout.activity_shopping);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

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
        addIngredsBtn = (Button) findViewById(R.id.addIngredientBtn);
        addIngredsBtn.setOnClickListener(this);

        ingredsTV = (TextView) findViewById(R.id.ingredientsTV);

        controller = Controller.getInstance();
        //ArrayList<Integer> userIngredIDs = controller.getUserIngredientIDs();

        items.addAll(controller.getUserShoppingLS());
        //ArrayList<String> makableNames = new ArrayList<>();
        String text = "Grocery Store List:";
        posOfText = items.size();
        //controller.searchDrinks(userIngredIDs, makableNames);
        items.add(text);
        items.addAll(controller.getUserShoppingGS());

        // no need to use recylerView if no ingredients in cabinet
        // display a text instead.
        if (posOfText < 1){
            ingredsTV.setText("You do not have any ingredients in your shopping list.");
        }
        else {
            RecyclerView rv = findViewById(R.id.shoppingRV);
            rv.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ShoppingRecyclerViewAdapter(this, items, posOfText);
            adapter.setClickListener(this);
            rv.setAdapter(adapter);
        }
    }

    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
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
    public void logToggle(String userName) {
        if (userName != null) {
            SharedPrefsManager.setUserName(ShoppingListActivity.this, null);
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName + ".UI.SearchActivity");
            startActivity(intent);
        } else {

            // This should never happen...shouldn't be in ShoppingList without logged in
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName + ".UI.LoginActivity");
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
