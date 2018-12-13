package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.UserManager;

import java.util.ArrayList;

public class AllDrinksActivity extends AppCompatActivity implements
        View.OnClickListener{

    Controller controller;
    SearchView searchView;
    ListView listView;
    ArrayList<String> drinkNames;
    ArrayAdapter<String> lvAdapter;
    Button searchByIngredsBtn;

    TextView greeting;
    Button logBtn;
    String userName;
    Button createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";

    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logToggle = new LogToggle();

        userName = UserManager.getUserName();

        if (!UserManager.getUserName().equals("guest")) {
            setContentView(R.layout.activity_all_drinks);
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
        }
        else {
            setContentView(R.layout.all_drinks_guest);
            logBtn = (Button) findViewById(R.id.logBtn);

        }

        logBtn.setOnClickListener(this);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Here");

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        searchByIngredsBtn = (Button) findViewById(R.id.searchByIngredsBtn);
        searchByIngredsBtn.setOnClickListener(this);

        controller = Controller.getInstance();
        listView = (ListView) findViewById(R.id.lv_drink_names);
        lvAdapter = new ArrayAdapter<String>(AllDrinksActivity.this, android.R.layout.simple_list_item_1, controller.getDrinkNames());
        listView.setAdapter(lvAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent intent = new Intent();
                Toast.makeText(getBaseContext(), controller.getDrinkNames().get(index),Toast.LENGTH_LONG).show();
                intent.putExtra("drink", controller.getDrinkNames().get(index));
                intent.setClassName(packageName,
                        packageName + ".UI.DrinkRecipeActivity");
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(drinkNames.contains(query)){
                    lvAdapter.getFilter().filter(query);
                    lvAdapter.getFilter().filter(query);
                    Toast.makeText(getBaseContext(), "Match found",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                lvAdapter.getFilter().filter(text);
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){

            case R.id.logBtn:
                startActivity(logToggle.logToggle(logBtn,greeting,packageName));
                break;

            case R.id.searchByIngredsBtn:
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


}
