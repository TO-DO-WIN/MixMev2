package com.to_do_win.mixme_v2.model;

import android.util.SparseBooleanArray;

import java.util.ArrayList;

public class User {

    private String userName;
    private ArrayList<Ingredient> myIngreds;
    private ArrayList<Ingredient> shoppingLS;
    private ArrayList<Ingredient> shoppingGS;
    private ArrayList<Drink> faves;
    Catalog catalog = Catalog.getInstance();

    // make a singleton
    private static User user;

    private User() {
        this.myIngreds = new ArrayList<>();
        this.shoppingLS = new ArrayList<>();
        this.shoppingGS = new ArrayList<>();
        this.faves = new ArrayList<>();


        // some data to use til fully functioning

        Ingredient f = new Ingredient("Coke", 0, Ingredient.Category.MIXER);
        Ingredient h = new Ingredient("Cream", 1, "Ounces", 1, Ingredient.Category.MIXER);
        Ingredient k = new Ingredient("Dark Rum", 2, Ingredient.Category.SPIRIT);
        Ingredient g = new Ingredient("Ginger Beer", 5, "Ounces", 3, Ingredient.Category.MIXER);
        Ingredient c = new Ingredient("Kahlua", 2, "Ounces", 4, Ingredient.Category.LIQUEUR);
        Ingredient j = new Ingredient("Light Rum", 5, Ingredient.Category.SPIRIT);
        Ingredient i = new Ingredient("Lime Wedge", 1, "Pieces", 6, Ingredient.Category.GARNISH);
        Ingredient a = new Ingredient("Orange Juice", 3, "Ounces", 7, Ingredient.Category.MIXER);
        Ingredient l = new Ingredient("Passion Fruit Juice", 8, Ingredient.Category.MIXER);
        Ingredient m = new Ingredient("Pinapple Juice", 9, Ingredient.Category.MIXER);
        Ingredient d = new Ingredient("Tomato Juice", 4, "Ounces", 10, Ingredient.Category.MIXER);
        Ingredient b = new Ingredient("Vodka", 11, "Ounces", 11, Ingredient.Category.SPIRIT);
        Ingredient e = new Ingredient("Whiskey", 12, Ingredient.Category.SPIRIT);


        // if (userName.equals("matt@mixme.com")) {
        myIngreds.add(a);
        myIngreds.add(b);
        myIngreds.add(e);

        shoppingLS.add(k);
        shoppingGS.add(f);
        shoppingGS.add(l);
        shoppingGS.add(g);

        //}
//
//        if (userName.equals("chinh@mixme.com")) {
//            myIngreds.add(h);
//            myIngreds.add(k);
//            myIngreds.add(b);
//
//            shoppingLS.add(a);
//            shoppingGS.add(f);
//            shoppingGS.add(l);
//            shoppingGS.add(g);
//        }
//
//        if (userName.equals("rachelle@mixme.com")) {
//            myIngreds.add(c);
//            myIngreds.add(g);
//            myIngreds.add(i);
//            myIngreds.add(m);
//            myIngreds.add(d);
//            myIngreds.add(b);
//
//            shoppingLS.add(a);
//            shoppingGS.add(f);
//            shoppingGS.add(l);
//        }
//
//        if (userName.equals("nick@mixme.com")) {
//            myIngreds.add(h);
//            myIngreds.add(k);
//            myIngreds.add(b);
//            myIngreds.add(i);
//            myIngreds.add(m);
//            myIngreds.add(d);
//
//            shoppingLS.add(a);
//            shoppingGS.add(f);
//            shoppingGS.add(l);
//            shoppingGS.add(g);
//        }
//
//        if (userName.equals("guillermo@mixme.com")) {
//            myIngreds.add(h);
//            myIngreds.add(k);
//            myIngreds.add(b);
//
//            shoppingLS.add(a);
//            shoppingGS.add(f);
//            shoppingGS.add(l);
//            shoppingGS.add(g);
//        }
    }

    //    public static User getInstance(String userName){
//        if (user == null){
//            user = new User(userName);
//        }
//        return user;
//    }
//
    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Ingredient> getMyIngreds() {
        return myIngreds;
    }

    public void setMyIngreds(ArrayList<Ingredient> myIngreds) {
        this.myIngreds = myIngreds;
    }

    public ArrayList<Ingredient> getShoppingLS() {
        return shoppingLS;
    }

    public void setShoppingLS(ArrayList<Ingredient> shoppingLS) {
        this.shoppingLS = shoppingLS;
    }

    public ArrayList<Ingredient> getShoppingGS() {
        return shoppingGS;
    }

    public void setShoppingGS(ArrayList<Ingredient> shoppingGS) {
        this.shoppingGS = shoppingGS;
    }

    public ArrayList<Drink> getFaves() {
        return faves;
    }

    public void setFaves(ArrayList<Drink> faves) {
        this.faves = faves;
    }

    public ArrayList<String> getMyIngredientNames() {
        ArrayList<String> ingredientNames = new ArrayList<>();

        for (Ingredient i : myIngreds)
            ingredientNames.add(i.getName());

        return ingredientNames;
    }

    public ArrayList<Integer> getMyIngredientIDs() {
        ArrayList<Integer> ingredientIDs = new ArrayList<>();

        for (Ingredient i : myIngreds)
            ingredientIDs.add(i.getId());

        return ingredientIDs;
    }

    public boolean isFavorite(String drinkName) {
        for (Drink d : faves) {
            if (d.getName().equals(drinkName)) return true;
        }
        return false;
    }

    public void addFavorite(String drinkName) {

        Drink d = catalog.getDrinkByName(drinkName);
        faves.add(d);
    }

    public void addIngredientsToCabinet(SparseBooleanArray sba) {
        ArrayList<Ingredient> allIngreds;
        allIngreds = catalog.getAllIngredients();
        for (int i = 0; i < allIngreds.size(); i++) {
            if (sba.get(i)) {
                myIngreds.add(allIngreds.get(i));
            }
        }
    }

    public void removeIngredientFromCabinet(String ingredName) {
        for (Ingredient i : myIngreds) {
            if (i.getName().equals(ingredName)) {
                myIngreds.remove(i);
                return;
            }
        }
    }

    public void setupUser(String userName) {
        Ingredient f = new Ingredient("Coke", 0, Ingredient.Category.MIXER);
        Ingredient h = new Ingredient("Cream", 1, "Ounces", 1, Ingredient.Category.MIXER);
        Ingredient k = new Ingredient("Dark Rum", 2, Ingredient.Category.SPIRIT);
        Ingredient g = new Ingredient("Ginger Beer", 5, "Ounces", 3, Ingredient.Category.MIXER);
        Ingredient c = new Ingredient("Kahlua", 2, "Ounces", 4, Ingredient.Category.LIQUEUR);
        Ingredient j = new Ingredient("Light Rum", 5, Ingredient.Category.SPIRIT);
        Ingredient i = new Ingredient("Lime Wedge", 1, "Pieces", 6, Ingredient.Category.GARNISH);
        Ingredient a = new Ingredient("Orange Juice", 3, "Ounces", 7, Ingredient.Category.MIXER);
        Ingredient l = new Ingredient("Passion Fruit Juice", 8, Ingredient.Category.MIXER);
        Ingredient m = new Ingredient("Pinapple Juice", 9, Ingredient.Category.MIXER);
        Ingredient d = new Ingredient("Tomato Juice", 4, "Ounces", 10, Ingredient.Category.MIXER);
        Ingredient b = new Ingredient("Vodka", 11, "Ounces", 11, Ingredient.Category.SPIRIT);
        Ingredient e = new Ingredient("Whiskey", 12, Ingredient.Category.SPIRIT);


        if (userName.equals("matt@mixme.com")) {
            myIngreds.add(a);
            myIngreds.add(b);
            myIngreds.add(e);

            shoppingLS.add(k);
            shoppingGS.add(f);
            shoppingGS.add(l);
            shoppingGS.add(g);

        }

        if (userName.equals("chinh@mixme.com")) {
            myIngreds.add(h);
            myIngreds.add(k);
            myIngreds.add(b);

            shoppingLS.add(a);
            shoppingGS.add(f);
            shoppingGS.add(l);
            shoppingGS.add(g);
        }

        if (userName.equals("rachelle@mixme.com")) {
            myIngreds.add(c);
            myIngreds.add(g);
            myIngreds.add(i);
            myIngreds.add(m);
            myIngreds.add(d);
            myIngreds.add(b);

            shoppingLS.add(a);
            shoppingGS.add(f);
            shoppingGS.add(l);
        }

        if (userName.equals("nick@mixme.com")) {
            myIngreds.add(h);
            myIngreds.add(k);
            myIngreds.add(b);
            myIngreds.add(i);
            myIngreds.add(m);
            myIngreds.add(d);

            shoppingLS.add(a);
            shoppingGS.add(f);
            shoppingGS.add(l);
            shoppingGS.add(g);
        }

        if (userName.equals("guillermo@mixme.com")) {
            myIngreds.add(h);
            myIngreds.add(k);
            myIngreds.add(b);

            shoppingLS.add(a);
            shoppingGS.add(f);
            shoppingGS.add(l);
            shoppingGS.add(g);
        }
    }

    public void clearUser() {
        user = new User();
    }

    public void addIngredientsToShoppingList(SparseBooleanArray sba) {
        ArrayList<Ingredient> allIngreds;
        allIngreds = catalog.getAllIngredients();
        for (int i = 0; i < allIngreds.size(); i++) {
            if (sba.get(i)) {
                Ingredient ingredient = allIngreds.get(i);
                Ingredient.Category category = ingredient.getCategory();

                if (!inShoppingList(ingredient)) {
                    switch (category) {
                        case SPIRIT:
                        case LIQUEUR:
                        case LOW_ALCOHOL:
                            shoppingLS.add(ingredient);
                            break;
                        case MIXER:
                        case GARNISH:
                            shoppingGS.add(ingredient);
                            break;
                        default:

                    }
                }
            }
        }
    }

    private boolean inShoppingList(Ingredient ingredient) {
        if (shoppingGS.contains(ingredient)) {
            return true;
        }
        if (shoppingLS.contains(ingredient)){
            return true;
        }
        return false;
    }

    public void removeIngredientFromShoppingList(String ingredientName) {
        for (Ingredient i : shoppingLS) {
            if (i.getName().equals(ingredientName)) {
                shoppingLS.remove(i);
                return;
            }
        }
        for (Ingredient i : shoppingGS) {
            if (i.getName().equals(ingredientName)) {
                shoppingGS.remove(i);
                return;
            }
        }
    }
}

