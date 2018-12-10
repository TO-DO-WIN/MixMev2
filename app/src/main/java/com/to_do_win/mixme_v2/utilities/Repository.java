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
 */

public class Repository {

    private static Repository repository;
    Catalog catalog;
    Controller controller;
    User user;

    private Repository() {
        catalog = Catalog.getInstance();
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
     *
     * @param snapshot
     */
    public void getAllDrinks(DataSnapshot snapshot) {
        ArrayList<Drink> allDrinks = new ArrayList<>();

        // we can decide to let the catalog get the list of ingredients from the list of drinks
        // instead of doing it here.
        ArrayList<Ingredient> allIngredients = new ArrayList<>();

        // for loop should grab a drink, turn it into a Drink object, add it to list of all drinks,
        // check each ingredient to see if it is in allIngredients, if not add it. Drink object needs to
        // include the list of Rate/Review objects.
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {

            RepoDrink singleDrink = postSnapshot.getValue(RepoDrink.class);
            Drink d = new Drink();

            // add ingredients that aren't empty to drink and to list of all ingredients if not there
            // first a list of them
            ArrayList<String> dbIngreds = new ArrayList<>();
            ArrayList<String> dbMeasurements = new ArrayList<>();
            ArrayList<String> dbCats = new ArrayList<>();

            dbIngreds.add(singleDrink.strIngredient1);
            dbIngreds.add(singleDrink.strIngredient2);
            dbIngreds.add(singleDrink.strIngredient3);
            dbIngreds.add(singleDrink.strIngredient4);
            dbIngreds.add(singleDrink.strIngredient5);
            dbIngreds.add(singleDrink.strIngredient6);
            dbIngreds.add(singleDrink.strIngredient7);
            dbIngreds.add(singleDrink.strIngredient8);
            dbIngreds.add(singleDrink.strIngredient9);
            dbIngreds.add(singleDrink.strIngredient10);
            dbIngreds.add(singleDrink.strIngredient11);
            dbIngreds.add(singleDrink.strIngredient12);
            dbIngreds.add(singleDrink.strIngredient13);
            dbIngreds.add(singleDrink.strIngredient14);
            dbIngreds.add(singleDrink.strIngredient15);

            dbMeasurements.add(singleDrink.strMeasure1);
            dbMeasurements.add(singleDrink.strMeasure2);
            dbMeasurements.add(singleDrink.strMeasure3);
            dbMeasurements.add(singleDrink.strMeasure4);
            dbMeasurements.add(singleDrink.strMeasure5);
            dbMeasurements.add(singleDrink.strMeasure6);
            dbMeasurements.add(singleDrink.strMeasure7);
            dbMeasurements.add(singleDrink.strMeasure8);
            dbMeasurements.add(singleDrink.strMeasure9);
            dbMeasurements.add(singleDrink.strMeasure10);
            dbMeasurements.add(singleDrink.strMeasure11);
            dbMeasurements.add(singleDrink.strMeasure12);
            dbMeasurements.add(singleDrink.strMeasure13);
            dbMeasurements.add(singleDrink.strMeasure14);
            dbMeasurements.add(singleDrink.strMeasure15);

            dbCats.add(singleDrink.strCategory1);
            dbCats.add(singleDrink.strCategory2);
            dbCats.add(singleDrink.strCategory3);
            dbCats.add(singleDrink.strCategory4);
            dbCats.add(singleDrink.strCategory5);
            dbCats.add(singleDrink.strCategory6);
            dbCats.add(singleDrink.strCategory7);
            dbCats.add(singleDrink.strCategory8);
            dbCats.add(singleDrink.strCategory9);
            dbCats.add(singleDrink.strCategory10);
            dbCats.add(singleDrink.strCategory11);
            dbCats.add(singleDrink.strCategory12);
            dbCats.add(singleDrink.strCategory13);
            dbCats.add(singleDrink.strCategory14);
            dbCats.add(singleDrink.strCategory15);

            int i = 0;
            do {
                Ingredient ingredient = catalog.getIngredientByName(dbIngreds.get(i));
                if (ingredient == null) {
                    // get Ingredient category enum from string
                    Ingredient.Category cat;
                    switch (dbCats.get(i)) {
                        case "Spirit":
                            cat = Ingredient.Category.SPIRIT;
                            break;
                        case "Liqueur":
                            cat = Ingredient.Category.LIQUEUR;
                            break;
                        case "Low-Alcohol":
                            cat = Ingredient.Category.LOW_ALCOHOL;
                            break;
                        case "Mixer":
                            cat = Ingredient.Category.MIXER;
                            break;
                        case "Garnish":
                            cat = Ingredient.Category.GARNISH;
                            break;
                        default:
                            cat = null;


                    }
                    // create the new ingredient and put it in catalog
                    ingredient = new Ingredient(dbIngreds.get(i), -1, cat);
                    catalog.addIngredient(ingredient);
                }
                Drink.RecipeIngredient recipeIngredient =
                        new Drink().new RecipeIngredient(ingredient, dbMeasurements.get(i));
                d.addRecipeIngredient(recipeIngredient);
                i++;
            } while (dbIngreds.get(i) != null);


        }
        catalog.setAllDrinks(allDrinks);
    }

    /**
     * Get user data should be used to get all user data from db and set the data to the user
     * object in the system.
     */
    public void getUserData() {

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

    public void addIngredientToCabinet(Ingredient ingredient) {
        String ingredientName = ingredient.getName();
        String userName = user.getUserName();


    }

    public void removeIngredientFromCabinet(Ingredient ingredient) {
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

    public void addDrinkToFaves(String drink) {
        String userName = user.getUserName();

    }

    public void removeDrinkFromFaves(String drink) {
        String userName = user.getUserName();

    }

    public void addDrink(Drink drink) {

    }

    public void addRateReview(String drink, String userName, float rating, String review) {


    }

    public static class RepoDrink {

        public RepoDrink() {
        }

        private String strDrink;
        private String idDrink;
        private String strDrinkES;
        private String strDrinkDE;
        private String strDrinkFR;
        private String strVideo;
        private String strCategory;
        private String strIBA;
        private String strAlcoholic;
        private String strGlass;
        private String strInstructions;
        private String strInstructionsES;
        private String strInstructionsDE;
        private String strInstructionsFR;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5;
        private String strIngredient6;
        private String strIngredient7;
        private String strIngredient8;
        private String strIngredient9;
        private String strIngredient10;
        private String strIngredient11;
        private String strIngredient12;
        private String strIngredient13;
        private String strIngredient14;
        private String strIngredient15;
        private String strMeasure1;
        private String strMeasure2;
        private String strMeasure3;
        private String strMeasure4;
        private String strMeasure5;
        private String strMeasure6;
        private String strMeasure7;
        private String strMeasure8;
        private String strMeasure9;
        private String strMeasure10;
        private String strMeasure11;
        private String strMeasure12;
        private String strMeasure13;
        private String strMeasure14;
        private String strMeasure15;
        private String strCategory1;
        private String strCategory2;
        private String strCategory3;
        private String strCategory4;
        private String strCategory5;
        private String strCategory6;
        private String strCategory7;
        private String strCategory8;
        private String strCategory9;
        private String strCategory10;
        private String strCategory11;
        private String strCategory12;
        private String strCategory13;
        private String strCategory14;
        private String strCategory15;
        private String dateModified;

        public String getStrDrink() {
            return strDrink;
        }

        public void setStrDrink(String strDrink) {
            this.strDrink = strDrink;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public void setStrGlass(String strGlass) {
            this.strGlass = strGlass;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getIdDrink() {
            return idDrink;
        }

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public String getStrDrinkES() {
            return strDrinkES;
        }

        public void setStrDrinkES(String strDrinkES) {
            this.strDrinkES = strDrinkES;
        }

        public String getStrDrinkDE() {
            return strDrinkDE;
        }

        public void setStrDrinkDE(String strDrinkDE) {
            this.strDrinkDE = strDrinkDE;
        }

        public String getStrDrinkFR() {
            return strDrinkFR;
        }

        public void setStrDrinkFR(String strDrinkFR) {
            this.strDrinkFR = strDrinkFR;
        }

        public String getStrVideo() {
            return strVideo;
        }

        public void setStrVideo(String strVideo) {
            this.strVideo = strVideo;
        }

        public String getStrCategory() {
            return strCategory;
        }

        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }

        public String getStrIBA() {
            return strIBA;
        }

        public void setStrIBA(String strIBA) {
            this.strIBA = strIBA;
        }

        public String getStrAlcoholic() {
            return strAlcoholic;
        }

        public void setStrAlcoholic(String strAlcoholic) {
            this.strAlcoholic = strAlcoholic;
        }

        public String getStrInstructionsES() {
            return strInstructionsES;
        }

        public void setStrInstructionsES(String strInstructionsES) {
            this.strInstructionsES = strInstructionsES;
        }

        public String getStrInstructionsDE() {
            return strInstructionsDE;
        }

        public void setStrInstructionsDE(String strInstructionsDE) {
            this.strInstructionsDE = strInstructionsDE;
        }

        public String getStrInstructionsFR() {
            return strInstructionsFR;
        }

        public void setStrInstructionsFR(String strInstructionsFR) {
            this.strInstructionsFR = strInstructionsFR;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public void setStrDrinkThumb(String strDrinkThumb) {
            this.strDrinkThumb = strDrinkThumb;
        }

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public void setStrIngredient1(String strIngredient1) {
            this.strIngredient1 = strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public void setStrIngredient2(String strIngredient2) {
            this.strIngredient2 = strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public void setStrIngredient3(String strIngredient3) {
            this.strIngredient3 = strIngredient3;
        }

        public String getStrIngredient4() {
            return strIngredient4;
        }

        public void setStrIngredient4(String strIngredient4) {
            this.strIngredient4 = strIngredient4;
        }

        public String getStrIngredient5() {
            return strIngredient5;
        }

        public void setStrIngredient5(String strIngredient5) {
            this.strIngredient5 = strIngredient5;
        }

        public String getStrIngredient6() {
            return strIngredient6;
        }

        public void setStrIngredient6(String strIngredient6) {
            this.strIngredient6 = strIngredient6;
        }

        public String getStrIngredient7() {
            return strIngredient7;
        }

        public void setStrIngredient7(String strIngredient7) {
            this.strIngredient7 = strIngredient7;
        }

        public String getStrIngredient8() {
            return strIngredient8;
        }

        public void setStrIngredient8(String strIngredient8) {
            this.strIngredient8 = strIngredient8;
        }

        public String getStrIngredient9() {
            return strIngredient9;
        }

        public void setStrIngredient9(String strIngredient9) {
            this.strIngredient9 = strIngredient9;
        }

        public String getStrIngredient10() {
            return strIngredient10;
        }

        public void setStrIngredient10(String strIngredient10) {
            this.strIngredient10 = strIngredient10;
        }

        public String getStrIngredient11() {
            return strIngredient11;
        }

        public void setStrIngredient11(String strIngredient11) {
            this.strIngredient11 = strIngredient11;
        }

        public String getStrIngredient12() {
            return strIngredient12;
        }

        public void setStrIngredient12(String strIngredient12) {
            this.strIngredient12 = strIngredient12;
        }

        public String getStrIngredient13() {
            return strIngredient13;
        }

        public void setStrIngredient13(String strIngredient13) {
            this.strIngredient13 = strIngredient13;
        }

        public String getStrIngredient14() {
            return strIngredient14;
        }

        public void setStrIngredient14(String strIngredient14) {
            this.strIngredient14 = strIngredient14;
        }

        public String getStrIngredient15() {
            return strIngredient15;
        }

        public void setStrIngredient15(String strIngredient15) {
            this.strIngredient15 = strIngredient15;
        }

        public String getStrMeasure1() {
            return strMeasure1;
        }

        public void setStrMeasure1(String strMeasure1) {
            this.strMeasure1 = strMeasure1;
        }

        public String getStrMeasure2() {
            return strMeasure2;
        }

        public void setStrMeasure2(String strMeasure2) {
            this.strMeasure2 = strMeasure2;
        }

        public String getStrMeasure3() {
            return strMeasure3;
        }

        public void setStrMeasure3(String strMeasure3) {
            this.strMeasure3 = strMeasure3;
        }

        public String getStrMeasure4() {
            return strMeasure4;
        }

        public void setStrMeasure4(String strMeasure4) {
            this.strMeasure4 = strMeasure4;
        }

        public String getStrMeasure5() {
            return strMeasure5;
        }

        public void setStrMeasure5(String strMeasure5) {
            this.strMeasure5 = strMeasure5;
        }

        public String getStrMeasure6() {
            return strMeasure6;
        }

        public void setStrMeasure6(String strMeasure6) {
            this.strMeasure6 = strMeasure6;
        }

        public String getStrMeasure7() {
            return strMeasure7;
        }

        public void setStrMeasure7(String strMeasure7) {
            this.strMeasure7 = strMeasure7;
        }

        public String getStrMeasure8() {
            return strMeasure8;
        }

        public void setStrMeasure8(String strMeasure8) {
            this.strMeasure8 = strMeasure8;
        }

        public String getStrMeasure9() {
            return strMeasure9;
        }

        public void setStrMeasure9(String strMeasure9) {
            this.strMeasure9 = strMeasure9;
        }

        public String getStrMeasure10() {
            return strMeasure10;
        }

        public void setStrMeasure10(String strMeasure10) {
            this.strMeasure10 = strMeasure10;
        }

        public String getStrMeasure11() {
            return strMeasure11;
        }

        public void setStrMeasure11(String strMeasure11) {
            this.strMeasure11 = strMeasure11;
        }

        public String getStrMeasure12() {
            return strMeasure12;
        }

        public void setStrMeasure12(String strMeasure12) {
            this.strMeasure12 = strMeasure12;
        }

        public String getStrMeasure13() {
            return strMeasure13;
        }

        public void setStrMeasure13(String strMeasure13) {
            this.strMeasure13 = strMeasure13;
        }

        public String getStrMeasure14() {
            return strMeasure14;
        }

        public void setStrMeasure14(String strMeasure14) {
            this.strMeasure14 = strMeasure14;
        }

        public String getStrMeasure15() {
            return strMeasure15;
        }

        public void setStrMeasure15(String strMeasure15) {
            this.strMeasure15 = strMeasure15;
        }

        public String getStrCategory1() {
            return strCategory1;
        }

        public void setStrCategory1(String strCategory1) {
            this.strCategory1 = strCategory1;
        }

        public String getStrCategory2() {
            return strCategory2;
        }

        public void setStrCategory2(String strCategory2) {
            this.strCategory2 = strCategory2;
        }

        public String getStrCategory3() {
            return strCategory3;
        }

        public void setStrCategory3(String strCategory3) {
            this.strCategory3 = strCategory3;
        }

        public String getStrCategory4() {
            return strCategory4;
        }

        public void setStrCategory4(String strCategory4) {
            this.strCategory4 = strCategory4;
        }

        public String getStrCategory5() {
            return strCategory5;
        }

        public void setStrCategory5(String strCategory5) {
            this.strCategory5 = strCategory5;
        }

        public String getStrCategory6() {
            return strCategory6;
        }

        public void setStrCategory6(String strCategory6) {
            this.strCategory6 = strCategory6;
        }

        public String getStrCategory7() {
            return strCategory7;
        }

        public void setStrCategory7(String strCategory7) {
            this.strCategory7 = strCategory7;
        }

        public String getStrCategory8() {
            return strCategory8;
        }

        public void setStrCategory8(String strCategory8) {
            this.strCategory8 = strCategory8;
        }

        public String getStrCategory9() {
            return strCategory9;
        }

        public void setStrCategory9(String strCategory9) {
            this.strCategory9 = strCategory9;
        }

        public String getStrCategory10() {
            return strCategory10;
        }

        public void setStrCategory10(String strCategory10) {
            this.strCategory10 = strCategory10;
        }

        public String getStrCategory11() {
            return strCategory11;
        }

        public void setStrCategory11(String strCategory11) {
            this.strCategory11 = strCategory11;
        }

        public String getStrCategory12() {
            return strCategory12;
        }

        public void setStrCategory12(String strCategory12) {
            this.strCategory12 = strCategory12;
        }

        public String getStrCategory13() {
            return strCategory13;
        }

        public void setStrCategory13(String strCategory13) {
            this.strCategory13 = strCategory13;
        }

        public String getStrCategory14() {
            return strCategory14;
        }

        public void setStrCategory14(String strCategory14) {
            this.strCategory14 = strCategory14;
        }

        public String getStrCategory15() {
            return strCategory15;
        }

        public void setStrCategory15(String strCategory15) {
            this.strCategory15 = strCategory15;
        }
    }
}
