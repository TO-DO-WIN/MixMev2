package com.to_do_win.mixme_v2.controller;

import android.util.Log;
import android.util.SparseBooleanArray;

import com.to_do_win.mixme_v2.model.Catalog;
import com.to_do_win.mixme_v2.model.Drink;
import com.to_do_win.mixme_v2.model.Ingredient;
import com.to_do_win.mixme_v2.model.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Controller class for MixMe application. It is a singleton and instantiates Catalog class and User
 * class, both singletons as well. Most parameters accepted by methods are Strings from the UI classes.
 * Most methods pass parameters along to methods of the Catalog and User classes.
 */
public class Controller {

    private static Controller controller;
    private Catalog catalog;
    private User user;

    private Controller() {
        catalog = Catalog.getInstance();
        user = User.getInstance();
    }

    /**
     * Create a static method to get instance.
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    /**
     * Returns a complete list of all catalog ingredients. Catalog should maintain a complete list
     * that is alphabetized. UI activities call this method for the list as needed.
     *
     * @return a list of all catalog's ingredients' names
     */
    public ArrayList<String> getIngredientList() {
        ArrayList<String> ingredients = new ArrayList<>();

        for (Ingredient i : catalog.getAllIngredients()) {
            ingredients.add(i.getName());
        }
        return ingredients;
    }

    /**
     * Accepts as parameters a sparse boolean array of the ingredients being used for the search,
     * and three lists to add to. The lists should be empty lists when passed to this method. The
     * method will populate the lists with the results of the catalog's search method.
     * <p>
     * The sba of ingredients is transformed into a list of ingredient ids. This list is passed to
     * catalog as the set of working ingredient ids. Then the catalog's search method is called.
     *
     * @param sba               sparse boolean array of ingredients being used for search
     * @param makableNames      list of makeable drinks to be populated
     * @param nearMakableNames  list of drinks that can almost be made to be populated
     * @param nearMakableMatch  list of percent matches of near makeable drinks to be populated
     */
    public void searchDrinks(SparseBooleanArray sba, ArrayList<String> makableNames,
                             ArrayList<String> nearMakableNames, ArrayList<String> nearMakableMatch) {

        ArrayList<Ingredient> allIngredients = catalog.getAllIngredients();
        ArrayList<Integer> usingIngredientIDs = new ArrayList<>();

        // Transform sparse boolean into sorted integer list of workable ingredients' ids
        for (int i = 0; i < allIngredients.size(); i++) {
            if (sba.get(i) == true) {
                usingIngredientIDs.add(i);
            }
        }
        // Set ingredients and search
        catalog.setWorkingIngredientIDs(usingIngredientIDs);
        catalog.searchDrinks();

        // Populate lists by getting results from catalog
        makableNames.addAll(catalog.getMakableNames());
        nearMakableNames.addAll(catalog.getNearMakableNames());
        nearMakableMatch.addAll(catalog.getNearMakableMatch());
    }

    /**
     * Search method used when list of user ingredient ids is available and only makeable drinks
     * are needed. The UI Cabinet activity uses this after gaining the user's ingredients' ids to
     * search for the drinks that can be made.
     *
     * @param userIngredIDs     list of user's ingredients' ids
     * @param makableNames      list to be populated after calling catalog's search method
     */
    public void searchDrinks(ArrayList<Integer> userIngredIDs, ArrayList<String> makableNames) {
        catalog.setWorkingIngredientIDs(userIngredIDs);
        catalog.searchDrinks();
        makableNames.addAll(catalog.getMakableNames());
    }

    /**
     * Gets the ingredient names of the drink being created from the catalog.
     *
     * @return  list of ingredient names of creation drink
     */
    public ArrayList<String> getCreationIngredNames() {
        return catalog.getCreationIngredNames();
    }

    /**
     * Gets the ingredient volumes of the drink being created from the catalog.
     *
     * @return  list of ingredient volumes of creation drink
     */
    public ArrayList<String> getCreationVolumes() {
        return catalog.getCreationVolumes();
    }

    /**
     * Gets the ingredient units of the drink being created from the catalog.
     *
     * @return  list of ingredient units of creation drink
     */
    public ArrayList<String> getCreationUnits() {
        return catalog.getCreationUnits();
    }

    /**
     * Calls on the catalog to remove an ingredient from the drink being created. The position of
     * the ingredient in the list from the Create Drink activity of the UI is the same as the
     * index of the list of ingredients in the catolog's creation drink.
     *
     * @param position  position of the element to be removed from the drink being created
     */
    public void removeCreationIngredient(int position) {
        catalog.removeCreationIngredient(position);
    }

    /**
     * Calls on the catalog to set the name of the drink being created with the name being
     * received from the UI.
     *
     * @param creationName  the name of the drink being created as received from the UI
     */
    public void setCreationName(String creationName) {
        catalog.setCreationName(creationName);
    }

    /**
     * Calls on the catalog to set the instructions of the drink being created with the
     * instructions being received from the UI.
     *
     * @param creationInstuctions  String value of the instructions of the drink being
     *                             created as received from the UI
     */
    public void setCreationInstructions(String creationInstuctions) {
        catalog.setCreationInstructions(creationInstuctions);
    }

    /**
     * Calls on the catalog to set the glass type of the drink being created with the glass type being
     * received from the UI.
     *
     * @param creationGlassType  String value of the glass type being created as received from the UI
     */
    public void setCreationGlassType(String creationGlassType) {
        catalog.setCreationGlassType(creationGlassType);
    }

    /**
     * Calls on the catalog to get the name of the drink being created.
     *
     * @return  the name of the drink being created
     */
    public String getCreationName() {
        return catalog.getCreationName();
    }

    /**
     * Calls on the catalog to get the instructions of the drink being created.
     *
     * @return  the instructions of the drink being created
     */
    public String getCreationInstructions() {
        return catalog.getCreationInstructions();
    }

    /**
     * Calls on the catalog to get the glass type of the drink being created.
     *
     * @return  the glass type of the drink being created
     */
    public String getCreationGlassType() {
        return catalog.getCreationGlassType();
    }

    /**
     * Calls on the catalog to get the name of the a drink ingredient. Used by UI Ingredient
     * Volume activity when creating a drink.
     *
     * @param   ingredientID    int id of the ingredient
     * @return                  the name of the ingredient
     */
    public String getIngredientName(int ingredientID) {
        return catalog.getIngredientName(ingredientID);
    }

    /**
     * Calls on the catalog to get the id of the drink ingredient. It is used to determine if an
     * ingredient being created is indeed a new ingredient. Ingredients are not to be duplicated.
     *
     * @param newIngName    name of ingredient being created
     * @return              an integer id which is -1 if it is indeed a new ingredient, or a non-negative
     *                      value if user is attempting to duplicate an ingredient
     */
    public int getIngredientID(String newIngName) {
        return catalog.getIngredientID(newIngName);
    }

    /**
     * Calls on the catalog to set the ingredient as an ingredient for the drink being created. Newly
     * created ingredients are identified by a -1 value for the ingredient id. New ingredients also
     * have a non-null value for newIngredientName and category.
     * <p>
     * New ingredients' category String is converted to type Ingredient.Category before calling
     * catalog's setCreationIngredient method.
     *
     * @param ingredientId      int id of the ingredient, if new ingredient -1 is passed in
     * @param ingredientVolume  double value for volume of the ingredient in the drink being created
     * @param units             String value of unit of measurement for the volume
     *                          of the ingredient in the drink
     * @param newIngredientName the name of the ingredient if it is a new ingredient, otherwise null
     * @param category          String value of the category of the ingredient if is new, otherwise null
     */
    public void setCreationIngredient(int ingredientId, double ingredientVolume, String units,
                                      String newIngredientName, String category) {
        Ingredient.Category cat;
        if (ingredientId==-1) {
            cat = Ingredient.Category.getCategory(category);
        } else {
            cat = null;
        }
        catalog.setCreationIngredient(ingredientId, ingredientVolume, units, newIngredientName, cat);

    }

    /**
     * Calls on catalog to add drink being created to list of all drinks.
     */
    public void addCreation() {
        catalog.addCreation();
    }

    /**
     * Calls on the catalog to set the recipe of the drink which the UI Drink Recipe activity is
     * wanting to display. A String of the name of the drink is accepted and passed to catalog
     * method.
     *
     * @param drinkName     the name of the drink
     */
    public void setRecipe(String drinkName) {
        catalog.setRecipe(drinkName);
    }

    /**
     * Calls on catalog to get ingredient names of the recipe wanting to be displayed by Drink
     * Recipe activity.
     *
     * @return      list of ingredient names
     */
    public ArrayList<String> getRecipeIngredients() { return catalog.getRecipeIngredients();}

    /**
     * Calls on catalog to get String values of ingredient volumes of the recipe wanting to be
     * displayed by Drink Recipe activity.
     *
     * @return      String list of ingredient volumes
     */
    public ArrayList<String> getRecipeVolumes() { return catalog.getRecipeVolumes();}

    /**
     * Calls on catalog to get units of measurements for the ingredients of the recipe wanting
     * to be displayed by Drink Recipe activity.
     *
     * @return      String list of ingredient units of measurement
     */
    public ArrayList<String> getRecipeUnits() { return catalog.getRecipeUnits();}

    /**
     * Calls on catalog to get the instructions of the drink recipe wanting to be displayed by the
     * Drink Recipe activity.
     *
     * @return      the drink's instructions
     */
    public String getRecipeInstructions() { return catalog.getRecipeInstructions();}

    /**
     * Calls on catalog to get the glass type of the drink recipe wanting to be displayed by the
     * Drink Recipe activity.
     *
     * @return      the drink's glass type
     */
    public String getRecipeGlassType() { return catalog.getRecipeGlassType();}

    public String getRecipeRating() { return catalog.getRecipeRating();}

    /**
     * Creates and returns a list of boolean values which correspond to the user's having of
     * ingredients in a list of recipe ingredients. Method iterates through each of the user's
     * cabinet ingredients for each recipe ingredient.
     *
     * @return      boolean array of which drink recipe ingredients the user has in cabinet
     */
    public ArrayList<Boolean> getHasIngredient() {
        ArrayList<Boolean> hasIngredient = new ArrayList<>();

        for (String recIng: getRecipeIngredients()){
            Boolean found = false;
            for (int i = 0; i < user.getMyIngredientNames().size() && !found; i++)

                if (user.getMyIngredientNames().get(i).equals(recIng)){
                    found = true;
                }

            if (found) hasIngredient.add(true);
            else hasIngredient.add(false);
        }
        return hasIngredient;
    }

    public ArrayList<Boolean> getHasInShopping() {
        ArrayList<Boolean> hasInShopping = new ArrayList<>();
        Ingredient ingredient;
        Boolean found;

        // for each recipe ingredient as a string
        for (int i = 0; i < getRecipeIngredients().size(); i++){
            found = false;
            for (int j = 0; j < user.getShoppingLS().size() && !found; j++){
                ingredient = user.getShoppingLS().get(j);
                if (ingredient.getName().equals(getRecipeIngredients().get(i)))
                    found = true;
            }
            for (int j = 0; j < user.getShoppingGS().size() && !found; j++) {
                ingredient = user.getShoppingGS().get(j);
                if (ingredient.getName().equals(getRecipeIngredients().get(i)))
                    found = true;
            }
            if (found) hasInShopping.add(true);
            else hasInShopping.add(false);
        }
        return  hasInShopping;
    }

    /**
     * Calls on user's method to get boolean value of whether a drink is a favorite of the user.
     *
     * @param drinkName     the name of the drink in question
     * @return              boolean of is user favorite
     */
    public boolean isFavorite(String drinkName) {
        return user.isFavorite(drinkName);
    }

    /**
     * Calls on user's addFavorite method to add given drink to user's list of favorite drinks.
     *
     * @param drinkName the name of the drink to be added to favorites
     */
    public void addFavorite(String drinkName) {user.addFavorite(drinkName); }

    public void removeFavorite(String drinkName) {user.removeFavorite(drinkName); }

    /**
     * Iterates through collection of user's favorites and pulls out the name of each drink. The name
     * of each favorite drink is added to list and returned.
     *
     * @return  the list of names of favorite drinks
     */
    public ArrayList<String> getUserFavorites() {
        ArrayList<String> drinkNames = new ArrayList<>();
        for(Drink d: user.getFaves()){
            drinkNames.add(d.getName());
        }
        return  drinkNames;
    }

    /**
     * Calls on user's getMyIngredientNames method to get user's ingredient names, and returns them.
     *
     * @return  list of user's ingredients' names
     */
    public ArrayList<String> getUserIngredients() {
        return user.getMyIngredientNames();
    }

    /**
     * Calls on user's getUserIngredientIDs method to get user's ingredient ids, and returns them.
     *
     * @returnn list of user's ingredients' ids
     */
    public ArrayList<Integer> getUserIngredientIDs() {
        return user.getMyIngredientIDs();
    }

    /**
     * Calls on user's addIngredientsToCabinet method passing a sparse boolean array of values that
     * correlates to the list of all ingredients. This method should only be used when adding
     * ingredients from a list of all ingredients, not from a Drink Recipe activity.
     *
     * @param sba   list of boolean values correlating to all ingredients in catalog
     */
    public void addIngredientsToCabinet(SparseBooleanArray sba) {
        user.addIngredientsToCabinet(sba);
    }

    /**
     * Calls on user's removeIngredientFromCabinet method passing the ingredient name along.
     *
     * @param ingredName    the name of the ingredient to be removed
     */
    public void removeIngredientFromCabinet(String ingredName) {
        user.removeIngredientFromCabinet(ingredName);
    }

    /**
     * Creates and returns a list of type String. Ingredients from user's liquor store shopping list
     * are iterated over, getting each name and adding it to the list.
     *
     * @return  the list of ingredient names in user's liquor store shopping list
     */
    public Collection<? extends String> getUserShoppingLS() {
        ArrayList<String> shoppingLS = new ArrayList<>();
        for (Ingredient i: user.getShoppingLS())
            shoppingLS.add(i.getName());
        return shoppingLS;
    }

    /**
     * Creates and returns a list of type String. Ingredients from user's liquor store shopping list
     * are iterated over, getting each name and adding it to the list.
     *
     * @return  the list of ingredient names in user's grocery store shopping list
     */
    public Collection<? extends String> getUserShoppingGS() {
        ArrayList<String> shoppingGS = new ArrayList<>();
        for (Ingredient i: user.getShoppingGS())
            shoppingGS.add(i.getName());
        return shoppingGS;
    }


    public void setupUser(String userName) {
        user.setupUser(userName);
    }

    public void clearUser(){
        user.clearUser();
    }

    public String getRandomDrink(){
        return catalog.getRandomDrink();
    }

    public void addIngredientsToShoppingList(SparseBooleanArray sba) {
        user.addIngredientsToShoppingList(sba);
    }

    public void removeIngredientFromShoppingList(String ingredientName) {
        user.removeIngredientFromShoppingList(ingredientName);
    }

    public void addToCabinet(String ingredName) {
        user.addToCabinet(ingredName);
    }

//    public boolean userReviewed(String drinkName, String userName) {
//        return catalog.userReviewed(drinkName, userName);
//    }

    public float getUserRating(String drinkName, String userName) {
        return catalog.getUserRating(drinkName, userName);
    }

    public String getUserReview(String drinkName, String userName) {
        return catalog.getUserReview(drinkName, userName);
    }

    public void setRating(String drinkName, String userName, float rating, String review) {
        catalog.addDrinkRating(drinkName, userName, rating, review);
    }



}

