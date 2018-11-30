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

public class DrinkRecipeActivity extends AppCompatActivity implements View.OnClickListener,
        DrinkRecipeRecyclerViewAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";
    TextView drinkNameTV, instructionsTV, glassTV;
    Button drinkRatingBtn;
    RecyclerView rv;
    Controller controller;
    DrinkRecipeRecyclerViewAdapter adapter;

    Button addFavesBtn, rateBtn;

    String drinkName;
    boolean isFavorite;

    ArrayList<IngredientStatus> ingredientStatuses;
    ArrayList<String> recipeIngredients;
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logToggle = new LogToggle();

        userName = UserManager.getUserName();
        Boolean user = (!userName.equals("guest"));

        if (user) {
            setContentView(R.layout.activity_drink_recipe);
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

            addFavesBtn = (Button) findViewById(R.id.addFavesBtn);
            addFavesBtn.setOnClickListener(this);

            rateBtn = (Button) findViewById(R.id.rateBtn);
            rateBtn.setOnClickListener(this);
        } else {
            setContentView(R.layout.drink_recipe_guest);
            logBtn = (Button) findViewById(R.id.logBtn);
        }

        logBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        controller = Controller.getInstance();

        Intent intent = getIntent();
        drinkName = intent.getStringExtra("drink");
        controller.setRecipe(drinkName);

        recipeIngredients = controller.getRecipeIngredients();
        ArrayList<String> recipeVolumes = controller.getRecipeVolumes();
        ArrayList<String> recipeUnits = controller.getRecipeUnits();

        ingredientStatuses = new ArrayList<>();

        if (user) {

            isFavorite = controller.isFavorite(drinkName);
            if (isFavorite) addFavesBtn.setText("Remove From Favorites");

            ArrayList<Boolean> hasInCabinet = controller.getHasIngredient();
            ArrayList<Boolean> hasInShoppingList = controller.getHasInShopping();
            for (int i = 0; i < recipeIngredients.size(); i++) {
                if (hasInCabinet.get(i)) ingredientStatuses.add(IngredientStatus.CABINET);
                else if (hasInShoppingList.get(i))
                    ingredientStatuses.add(IngredientStatus.SHOPPING);
                else ingredientStatuses.add(IngredientStatus.NONE);
            }
        }

        String instructions = controller.getRecipeInstructions();
        String glassType = controller.getRecipeGlassType();
        String rating = "Rating = " + controller.getRecipeRating();

        drinkNameTV = (TextView) findViewById(R.id.drink_name);
        drinkNameTV.setText(drinkName);

        drinkRatingBtn = (Button) findViewById(R.id.ratingBtn);
        drinkRatingBtn.setText(rating);
        drinkRatingBtn.setOnClickListener(this);

        instructionsTV = (TextView) findViewById(R.id.instructionsTV);
        instructionsTV.setText(instructions);

        glassTV = (TextView) findViewById(R.id.glassTypeTV);
        glassTV.setText(glassType);

        RecyclerView rv = findViewById(R.id.rvIngredients);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // must pass values for user/non-user, and for ingredient being in user's cabinet and or shopping list
        // use different constructors for whether user or not, and within RecipeActivity or just in
        // create activity
        adapter = new DrinkRecipeRecyclerViewAdapter(this, recipeIngredients, recipeVolumes,
                recipeUnits, user, ingredientStatuses);
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
            //////////////////////////////////////////////////////////////////////////////rating button to display ratings and reviews. no layout created yet.
            case R.id.ratingBtn:
                intent.putExtra("drinkName", drinkName);
                intent.setClassName(packageName,
                        packageName + ".UI.RateReviewActivity");
                startActivity(intent);
                break;

            case R.id.addFavesBtn:
                if (!isFavorite) {
                    controller.addFavorite(drinkName);
                    Toast.makeText(this, "Drink added to your favorites", Toast.LENGTH_LONG).show();
                } else {
                    controller.removeFavorite(drinkName);
                    Toast.makeText(this, "Drink removed from your favorites", Toast.LENGTH_LONG).show();
                }
                intent.putExtra("drink", drinkName);
                intent.setClassName(packageName,
                        packageName + ".UI.DrinkRecipeActivity");
                startActivity(intent);
                break;

            case R.id.rateBtn:
                intent.putExtra("drinkName", drinkName);
                intent.setClassName(packageName,
                        packageName + ".UI.RateReviewActivity");
                startActivity(intent);
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
        String ingredientName = recipeIngredients.get(position);
        switch (ingredientStatuses.get(position)) {
            case NONE:
                controller.addToShoppingList(ingredientName);
                Toast.makeText(this, "Ingredient Added to Your Shopping List", Toast.LENGTH_SHORT).show();
                break;
            case SHOPPING:
                controller.addToCabinet(ingredientName);
                controller.removeIngredientFromShoppingList(ingredientName);
                Toast.makeText(this, "Ingredient Added to Your Cabinet", Toast.LENGTH_LONG).show();
                break;
            case CABINET:
                controller.removeIngredientFromCabinet(ingredientName);
                Toast.makeText(this, "Ingredient Removed from Your Cabinet", Toast.LENGTH_LONG).show();
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("drink", drinkName);
        intent.setClassName(packageName,
                packageName + ".UI.DrinkRecipeActivity");
        startActivity(intent);

    }

    public enum IngredientStatus {NONE, SHOPPING, CABINET}
}
