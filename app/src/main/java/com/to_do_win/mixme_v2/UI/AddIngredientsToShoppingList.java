package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class AddIngredientsToShoppingList extends AppCompatActivity implements LogToggle, View.OnClickListener,
        IngredientRecyclerViewAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    SearchView searchView;
    Button clearBtn, submitBtn;
    Button createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";
    IngredientRecyclerViewAdapter adapter;
    Controller controller;
    SparseBooleanArray sba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients_to_shopping_list);

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

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        logBtn.setOnClickListener(this);

        searchView = (SearchView) findViewById(R.id.searchView);

        clearBtn = (Button) findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        controller = Controller.getInstance();
        ArrayList<String> ingredientList = controller.getIngredientList();
        sba = new SparseBooleanArray();

        RecyclerView rv = findViewById(R.id.rvIngredients);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngredientRecyclerViewAdapter(this, ingredientList);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()){

            case R.id.logBtn:
                logToggle(userName);
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
            case R.id.clearBtn:
                break;
            case R.id.submitBtn:
                controller.addIngredientsToShoppingList(sba);
                intent.setClassName(packageName,
                        packageName +".UI.ShoppingListActivity");
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        if (sba.get(position)) {
            sba.put(position, false);
        } else {
            sba.put(position, true);
        }

    }

    @Override
    public void logToggle(String userName) {
        if (userName != null) {
            SharedPrefsManager.setUserName(AddIngredientsToShoppingList.this, null);
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.SearchActivity");
            startActivity(intent);
        } else {

            // This should never happen...shouldn't be in ShoppingList without logged in
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.LoginActivity");
            startActivity(intent);
        }

    }
}
