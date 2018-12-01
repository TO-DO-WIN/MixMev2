package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.to_do_win.mixme_v2.utilities.UserManager;

import java.util.ArrayList;

public class AddIngredientsToCabinetActivity extends AppCompatActivity implements View.OnClickListener,
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
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient_to_cabinet);
        logToggle = new LogToggle();

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
            case R.id.clearBtn:
                break;
            case R.id.submitBtn:
                controller.addIngredientsToCabinet(sba);
                intent.setClassName(packageName,
                        packageName +".UI.CabinetActivity");
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

}
