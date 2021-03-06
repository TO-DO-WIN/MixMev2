package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.UserManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements
        View.OnClickListener, IngredientRecyclerViewAdapter.ItemClickListener {

    Controller controller;
    SearchView searchView;

    IngredientRecyclerViewAdapter adapter;
    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, useIngredsBtn, clearBtn, findDrinksBtn;
    Button createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";

    SparseBooleanArray sba;
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logToggle = new LogToggle();

        userName = UserManager.getUserName();

        if (!UserManager.getUserName().equals("guest")) {
            setContentView(R.layout.activity_search);
            greeting = (TextView) findViewById(R.id.greeting);

            greeting.setText(userName);
            logBtn = (Button) findViewById(R.id.logBtn);
            logBtn.setText("Log Out");

            useIngredsBtn = (Button) findViewById(R.id.useIngredsBtn);
            useIngredsBtn.setOnClickListener(this);

            createDrinkBtn = (Button) findViewById(R.id.createNVBtn);
            createDrinkBtn.setOnClickListener(this);

            favesBtn = (Button) findViewById(R.id.favesNVBtn);
            favesBtn.setOnClickListener(this);

            shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
            shoppingBtn.setOnClickListener(this);

            cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
            cabinetBtn.setOnClickListener(this);
        }
        else {
            setContentView(R.layout.search_guest);
            logBtn = (Button) findViewById(R.id.logBtn);

        }

        logBtn.setOnClickListener(this);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Here");

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        clearBtn = (Button) findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(this);

        findDrinksBtn = (Button) findViewById(R.id.findDrinks);
        findDrinksBtn.setOnClickListener(this);

        controller = Controller.getInstance();

        ArrayList<String> ingredientList = controller.getIngredientList();  // not used yet
        sba = new SparseBooleanArray();
        RecyclerView rv = findViewById(R.id.rvIngredients);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngredientRecyclerViewAdapter(this, ingredientList);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        ArrayList<String > makableNames;
        ArrayList<String > nearMakableNames;
        ArrayList<String > nearMakableMatch;

        Intent intent = new Intent();

        switch (v.getId()){

            case R.id.logBtn:
                startActivity(logToggle.logToggle(logBtn,greeting,packageName));
                break;
            case R.id.useIngredsBtn:
                makableNames = new ArrayList<>();
                nearMakableNames = new ArrayList<>();
                nearMakableMatch = new ArrayList<>();
                ArrayList<Integer> ingredientIds;
                if((ingredientIds = controller.getUserIngredientIDs()).size()<1){
                    Toast.makeText((this), "Cabinet empty",Toast.LENGTH_LONG).show();
                    break;
                }
                controller.searchDrinks(controller.getUserIngredientIDs(), makableNames,
                        nearMakableNames, nearMakableMatch);
                intent.putStringArrayListExtra("makableNames", makableNames);
                intent.putStringArrayListExtra("nearMakableNames", nearMakableNames);
                intent.putStringArrayListExtra("nearMakableMatch", nearMakableMatch);
                intent.setClassName(packageName,
                        packageName +".UI.DrinksFoundActivity");
                startActivity(intent);
                break;
            case R.id.findDrinks:
                makableNames = new ArrayList<>();
                nearMakableNames = new ArrayList<>();
                nearMakableMatch = new ArrayList<>();

                controller.searchDrinks(sba, makableNames,
                        nearMakableNames, nearMakableMatch);

                intent.putStringArrayListExtra("makableNames", makableNames);
                intent.putStringArrayListExtra("nearMakableNames", nearMakableNames);
                intent.putStringArrayListExtra("nearMakableMatch", nearMakableMatch);
                intent.setClassName(packageName,
                        packageName +".UI.DrinksFoundActivity");
                startActivity(intent);
                break;

            case R.id.createNVBtn:
                intent.setClassName(packageName,
                        packageName +".UI.CreateDrinkActivity");
                startActivity(intent);
                break;
            case R.id.searchNVBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.AllDrinksActivity");
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

        if (sba.get(position)) {
            sba.put(position, false);
        }
        else{
            sba.put(position, true);
        }}
}
