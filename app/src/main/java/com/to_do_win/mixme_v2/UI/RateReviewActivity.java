package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.UserManager;

public class RateReviewActivity extends AppCompatActivity implements LogToggle, View.OnClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;
    String packageName = "com.to_do_win.mixme_v2";
    TextView drinkNameTV;
    RatingBar drinkRatingRB;
    EditText drinkReviewET;
    Controller controller;

    Button clearBtn, submitBtn;

    String drinkName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = UserManager.getUserName();

        if (userName == null) {
            // should never happen, shouldn't be in this activity when
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName + ".UI.LogInActivity");
            startActivity(intent);
        }

        setContentView(R.layout.activity_rate_review);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);
        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        drinkName = getIntent().getStringExtra("drinkName");
        drinkNameTV = (TextView) findViewById(R.id.drink_name);
        drinkNameTV.setText(drinkName);

        drinkRatingRB = (RatingBar) findViewById(R.id.ratingBar);

        drinkReviewET = (EditText) findViewById(R.id.reviewText);

        clearBtn = (Button) findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

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

        if (controller.getUserRating(drinkName, userName) != -1){
            float rating = controller.getUserRating(drinkName, userName);
            String review = controller.getUserReview(drinkName, userName);

            drinkRatingRB.setRating(rating);
            drinkReviewET.setText(review);
        }


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle();
                break;

            case R.id.clearBtn:
                drinkRatingRB.setRating(0);
                drinkReviewET.setText(null);
               // Toast.makeText(this, "Drink added to your favorites", Toast.LENGTH_LONG).show();
                break;

            case R.id.submitBtn:
                float rating = drinkRatingRB.getRating();
                String review = drinkReviewET.getText().toString();
                controller.setRating(drinkName, userName, rating, review);
                intent.putExtra("drink", drinkName);
                intent.setClassName(packageName,
                        packageName + ".UI.DrinkRecipeActivity");
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
    public void logToggle() {
        if (!UserManager.getUserName().equals("guest")) {
            UserManager.userLogOut();
            greeting.setText("Hello, Guest");
            logBtn.setText("Log In");
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.SearchActivity");
            startActivity(intent);
        } else {

            // This should never happen...shouldn't be in Cabinet without logged in
            Intent intent = new Intent();
            intent.setClassName(packageName,
                    packageName +".UI.LoginActivity");
            startActivity(intent);
        }
    }

}
