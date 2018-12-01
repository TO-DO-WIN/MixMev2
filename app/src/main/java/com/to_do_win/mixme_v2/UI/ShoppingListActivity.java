package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.UserManager;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity implements View.OnClickListener,
        ShoppingRecyclerViewAdapter.MyItemClickListener {

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
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logToggle = new LogToggle();

        userName = UserManager.getUserName();

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

        ingredsTV = (TextView) findViewById(R.id.liquorStoreTV);

        controller = Controller.getInstance();
        //ArrayList<Integer> userIngredIDs = controller.getUserIngredientIDs();

        items.addAll(controller.getUserShoppingLS());
        //ArrayList<String> makableNames = new ArrayList<>();
        String text = "Grocery Store List:";
        posOfText = items.size();
        //controller.searchDrinks(userIngredIDs, makableNames);
        items.add(text);
        items.addAll(controller.getUserShoppingGS());

        // no need to use recylerView if no ingredients in shopping list
        // display a text instead.
        if (posOfText < 1) {
            ingredsTV.setText("You do not have any ingredients in your liquor store shopping list.");
        }
        RecyclerView rv = findViewById(R.id.shoppingRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingRecyclerViewAdapter(this, items, posOfText);
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
                        packageName + ".UI.AllDrinksActivity");
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

            case R.id.addIngredientBtn:
                intent.setClassName(packageName,
                        packageName + ".UI.AddIngredientsToShoppingListActivity");
                startActivity(intent);
                break;
                
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.toCabBtn:

                addToCabinet(position);
                Toast.makeText(this, "Ingredient Has Been Added To Your Cabinet", Toast.LENGTH_LONG).show();
                break;
            case R.id.removeBtn:


                remove(position);
                break;
            default:
                break;
        }

        intent.setClassName(packageName,
                packageName + ".UI.ShoppingListActivity");
        startActivity(intent);

    }


    public void addToCabinet(int position) {

        controller.addToCabinet(items.get(position));
        controller.removeIngredientFromShoppingList(items.get(position));

    }

    public void remove(int position) {

        // get positive or negative integer to determine which shopping list to remove from
        int shopList = position - posOfText;

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////what to pass to controller remove from shopping list method//////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////could pas shopList int so no need to search both lists in user to remove the ingredient. currently searching
        //////////////////both lists
        ///////////////////or could have method for each list and call correct method from here to avoid searching both lists.
       // if (position != posOfText) {
            controller.removeIngredientFromShoppingList(items.get(position));
//            intent.setClassName(packageName,
//                    packageName + ".UI.ShoppingListActivity");
//            startActivity(intent);
        //}

    }
}
