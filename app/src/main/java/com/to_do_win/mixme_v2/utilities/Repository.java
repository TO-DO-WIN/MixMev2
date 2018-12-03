package com.to_do_win.mixme_v2.utilities;


import com.google.firebase.database.DataSnapshot;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.model.Catalog;
import com.to_do_win.mixme_v2.model.Drink;
import com.to_do_win.mixme_v2.model.Ingredient;
import com.to_do_win.mixme_v2.model.User;

import java.util.ArrayList;

/**
 * Class to handle all communication with database. Typically the controller will call on
 * repository methods to get and set database entries.
 *
 */

public class Repository {

    private static Repository repository;
    Catalog catalog;
    Controller controller;
    User user;

    private Repository() {
//        catalog = Catalog.getInstance();
//        user = User.getInstance();
//        controller = Controller.getInstance();
    }

    /**
     * Create a static method to get instance.
     */
    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    /**
     * Gets all drinks checks each drink for unlisted ingredients. Populates catalog's list
     * of drinks and ingredients.
     */
    public void getAllDrinks(){
        ArrayList<Drink> allDrinks = new ArrayList<>();

        // we can decide to let the catalog get the list of ingredients from the list of drinks
        // instead of doing it here.
        ArrayList<Ingredient> allIngredients = new ArrayList<>();

        // for loop should grab a drink, turn it into a Drink object, add it to list of all drinks,
        // check each ingredient to see if it is in allIngredients, if not add it. Drink object needs to
        // include the list of Rate/Review objects.
//        for (DataSnapshot postSnapshot: snapshot.getChildren()) {

//            A_Drink singleDrink = postSnapshot.getValue(A_Drink.class);
//        }
        catalog.setAllDrinks(allDrinks);
        catalog.setAllIngredients(allIngredients);
    }

    /**
     * Get user data should be used to get all user data from db and set the data to the user
     * object in the system.
     */
    public void getUserData(){

        // get all user data and set using user methods.

        String userName = null;
        ArrayList<String> favorites = null;
        ArrayList<String> shoppingList = null;
        ArrayList<String> cabinetList = null;

        user.setUserName(userName);

        user.setFavesByName(favorites);

        user.setShoppingListsByName(shoppingList);

        user.setCabinetListByName(cabinetList);

    }

    public void addIngredientToCabinet(Ingredient ingredient){
        String ingredientName = ingredient.getName();
        String userName = user.getUserName();


    }

    public void removeIngredientFromCabinet(Ingredient ingredient){
        String ingredientName = ingredient.getName();
        String userName = user.getUserName();


    }

    public void addIngredientToShoppingList(Ingredient ingredient) {
        String ingredientName = ingredient.getName();
        String userName = user.getUserName();


    }

    public void removeIngredientFromShoppingList(Ingredient ingredient) {
        String ingredientName = ingredient.getName();
        String userName = user.getUserName();


    }

    public void addDrinkToFaves(String drink){
        String userName = user.getUserName();

    }

    public void removeDrinkFromFaves(String drink) {
        String userName = user.getUserName();

    }

    public void addDrink(Drink drink){

    }

    public void addRateReview(String drink, String userName, float rating, String review){


    }
}
