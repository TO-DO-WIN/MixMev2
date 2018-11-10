package com.to_do_win.mixme_v2.controller;

import android.util.Log;
import android.util.SparseBooleanArray;

import com.to_do_win.mixme_v2.model.Catalog;
import com.to_do_win.mixme_v2.model.Drink;
import com.to_do_win.mixme_v2.model.Ingredient;
import com.to_do_win.mixme_v2.model.User;

import java.util.ArrayList;
import java.util.Collection;

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

    public ArrayList<String> getIngredientList() {
        // to be replaced with catalog.getAllIngredints()
        ArrayList<String> ingredients = new ArrayList<>();

        // populate list------------------to be replaced with call to get actual list from db
//        ingredients.add("Orange Juice");
//        ingredients.add("Vodka");
//        ingredients.add("Brandy");
//        ingredients.add("Whiskey");
//        ingredients.add("Rye");
//        ingredients.add("Dry Vermouth");
//        ingredients.add("Sour");
//        ingredients.add("Coke");
//        ingredients.add("Lime Juice");
//        ingredients.add("Midori");
//        ingredients.add("Jagermeister");
//        ingredients.add("Rumpleminze");
//        ingredients.add("Yukon Jack");
//        ingredients.add("Ginger Beer");
//        ingredients.add("Scotch");
//        ingredients.add("Beer");


        //return ingredients;
        for (Ingredient i : catalog.getAllIngredients()) {
            ingredients.add(i.getName());
        }

        return ingredients;
    }

    public void searchDrinks(SparseBooleanArray sba, ArrayList<String> makableNames,
                             ArrayList<String> nearMakableNames, ArrayList<String> nearMakableMatch) {

        //Search each drink for matching ingredients
        //add to each list if matching at least 66%

        ArrayList<Ingredient> allIngredients = catalog.getAllIngredients();
        ArrayList<Integer> usingIngredientIDs = new ArrayList<>();

        // turn sparse boolean into sorted integer list of workable ingredients' ids
        for (int i = 0; i < allIngredients.size(); i++) {
            if (sba.get(i) == true) {
                usingIngredientIDs.add(i);
            }
        }

        catalog.setWorkingIngredientIDs(usingIngredientIDs);

        StringBuilder test = new StringBuilder();
        test.append("ingrdient Ids: ");
        for (Integer i : usingIngredientIDs) {
            test.append(i.toString());
            test.append("\t");
        }
        Log.d("Debug", test.toString());


        catalog.searchDrinks();

        makableNames.addAll(catalog.getMakableNames());
        nearMakableNames.addAll(catalog.getNearMakableNames());
        nearMakableMatch.addAll(catalog.getNearMakableMatch());

    }

    public void createDrink(String drinkName, ArrayList<String> ingredientNames, ArrayList<String> ingredientVolumes,
                            ArrayList<String> ingredientUnits, ArrayList<Integer> ingredientIDs,
                            String directions, String glassType, ArrayList<String> ingredientCats) {

        // pass along data to catalog, except convert sparseBooleanArray to ArrayList of Integers
        // not using sba yet
        catalog.createDrink(drinkName, ingredientNames, ingredientVolumes, ingredientUnits,
                ingredientIDs, directions, glassType, ingredientCats);

    }


    public ArrayList<String> getCreationIngredNames() {
        return catalog.getCreationIngredNames();
    }

    public ArrayList<String> getCreationVolumes() {
        return catalog.getCreationVolumes();
    }

    public ArrayList<String> getCreationUnits() {
        return catalog.getCreationUnits();
    }

    public void removeCreationIngredient(int position) {
        catalog.removeCreationIngredient(position);
    }

    public void setCreationName(String creationName) {
        catalog.setCreationName(creationName);
    }

    public void setCreationInstructions(String creationInstuctions) {
        catalog.setCreationInstructions(creationInstuctions);
    }

    public void setCreationGlassType(String creationGlassType) {
        catalog.setCreationGlassType(creationGlassType);
    }

    public String getCreationName() {
        return catalog.getCreationName();
    }

    public String getCreationInstructions() {
        return catalog.getCreationInstructions();
    }

    public String getCreationGlassType() {
        return catalog.getCreationGlassType();
    }

    public String getIngredientName(int ingredientID) {
        return catalog.getIngredientName(ingredientID);
    }

    public void setCreationIngredient(int ingredientId, double ingredientVolume, String units, String newIngredientName,
                                      String category) {
        Ingredient.Category cat;
        if (ingredientId==-1) {
            cat = Ingredient.Category.getCategory(category);
        } else {
            cat = null;
        }
        catalog.setCreationIngredient(ingredientId, ingredientVolume, units, newIngredientName, cat);

    }

//    public void setCreationIngredient(String newIngredientName, int categoryID, double ingredientVolume, int units) {
//        catalog.setCreationIngredient(newIngredientName, categoryID, ingredientVolume, units);
//    }

    public String getRandomDrink(){
        return catalog.getRandomDrink();
    }

    public void addCreation() {
        catalog.addCreation();
    }

    public void setRecipe(String drinkName) {
        catalog.setRecipe(drinkName);
    }

    public ArrayList<String> getRecipeIngredients() { return catalog.getRecipeIngredients();}

    public ArrayList<String> getRecipeVolumes() { return catalog.getRecipeVolumes();}

    public ArrayList<String> getRecipeUnits() { return catalog.getRecipeUnits();}

    public String getRecipeInstructions() { return catalog.getRecipeInstructions();}

    public String getRecipeGlassType() { return catalog.getRecipeGlassType();}

    public ArrayList<Boolean> getHasIngredient(ArrayList<String> recipeIngredients) {
        ArrayList<Boolean> hasIngredient = new ArrayList<>();

        for (String recIng: getRecipeIngredients()){
            for (String userIng: user.getMyIngredientNames()){
                if (userIng.equals(recIng)){
                    hasIngredient.add(true);
                } else {
                    hasIngredient.add(false);}
            }
        }

        return hasIngredient;
    }

    public boolean isFavorite(String drinkName) {
        return user.isFavorite(drinkName);
    }

    public int getIngredientID(String newIngName) {
        return catalog.getIngredientID(newIngName);
    }

    public void addFavorite(String drinkName) {user.addFavorite(drinkName); }

    public ArrayList<String> getUserFavorites() {
        ArrayList<String> drinkNames = new ArrayList<>();
        for(Drink d: user.getFaves()){
            drinkNames.add(d.getName());
        }
        return  drinkNames;
    }
    public ArrayList<String> getUserIngredients() {
        return user.getMyIngredientNames();
    }

    public ArrayList<Integer> getUserIngredientIDs() {
        return user.getMyIngredientIDs();
    }


    public void addIngredientsToCabinet(SparseBooleanArray sba) {
        user.addIngredientsToCabinet(sba);
    }

    public void removeIngredientFromCabinet(String ingredName) {
        user.removeIngredientFromCabinet(ingredName);
    }

    public Collection<? extends String> getUserShoppingLS() {
        ArrayList<String> shoppingLS = new ArrayList<>();
        for (Ingredient i: user.getShoppingLS())
            shoppingLS.add(i.getName());
        return shoppingLS;
    }

    public Collection<? extends String> getUserShoppingGS() {
        ArrayList<String> shoppingGS = new ArrayList<>();
        for (Ingredient i: user.getShoppingGS())
            shoppingGS.add(i.getName());
        return shoppingGS;
    }
}
