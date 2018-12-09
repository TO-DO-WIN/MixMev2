package com.to_do_win.mixme_v2.model;

import com.to_do_win.mixme_v2.utilities.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Catalog class for the MixMe application. It is a singleton class that receives method calls
 * primarily from the Controller class. It maintains lists of all A_Drink, all ingredients, and
 * does most of the work while searching and creating A_Drink. For searches, it holds a list of the
 * ingredients used in the search, and lists of A_Drink that can be made with the ingredients as well
 * as A_Drink that can almost be made. It also holds a creation drink, which stores all attributes
 * of a drink while it is being created, and a recipe drink, which is the drink who's recipe is
 * to be displayed.
 */
public class Catalog {

    ////////////////////////////   Chinh's Addition /////////////////////////////////////
    //private ArrayList<A_Drink> allDrinks2;
    ////////////////////////////   Chinh's Addition /////////////////////////////////////

    private static final int MIN_PERCENT_MATCH = 51;
    private ArrayList<Ingredient> allIngredients;
    private ArrayList<Integer> workingIngredientIDs;
    private ArrayList<Drink> allDrinks;
    private ArrayList<Drink> makable;
    private ArrayList<Drink> nearMakable;
    private Drink creation;
    private ArrayList<Ingredient> newIngredients;
    private static final int NO_ID = -1;
    private Drink recipe;
    private Repository repository;

    //make a singleton
    private static Catalog catalog;

    private Catalog() {
        this.allIngredients = new ArrayList<>();
        this.workingIngredientIDs = new ArrayList<>();
        this.allDrinks = new ArrayList<>();
        this.makable = new ArrayList<>();
        this.nearMakable = new ArrayList<>();
        this.creation = new Drink();
        this.newIngredients = new ArrayList<>();

        // Call to DB to get all ingredients and all A_Drink
       // allDrinks2 = new ArrayList<>();

        // mock this call for now
        // ingreds
//        Ingredient f = new Ingredient("Coke", 0, Ingredient.Category.MIXER);
//        Ingredient h = new Ingredient("Cream", 1, "Ounces", 1, Ingredient.Category.MIXER);
//        Ingredient k = new Ingredient("Dark Rum", 2, Ingredient.Category.SPIRIT);
//        Ingredient g = new Ingredient("Ginger Beer", 5, "Ounces", 3, Ingredient.Category.MIXER);
//        Ingredient c = new Ingredient("Kahlua", 2, "Ounces", 4, Ingredient.Category.LIQUEUR);
//        Ingredient j = new Ingredient("Light Rum", 5, Ingredient.Category.SPIRIT);
//        Ingredient i = new Ingredient("Lime Wedge", 1, "Pieces", 6, Ingredient.Category.GARNISH);
//        Ingredient a = new Ingredient("Orange Juice", 3, "Ounces", 7, Ingredient.Category.MIXER);
//        Ingredient l = new Ingredient("Passion Fruit Juice", 8, Ingredient.Category.MIXER);
//        Ingredient m = new Ingredient("Pinapple Juice", 9, Ingredient.Category.MIXER);
//        Ingredient d = new Ingredient("Tomato Juice", 4, "Ounces", 10, Ingredient.Category.MIXER);
//        Ingredient b = new Ingredient("Vodka", 1.5, "Ounces", 11, Ingredient.Category.SPIRIT);
//        Ingredient e = new Ingredient("Whiskey", 12, Ingredient.Category.SPIRIT);

        Ingredient f = new Ingredient("Coke", 0, Ingredient.Category.MIXER);
        Ingredient h = new Ingredient("Cream", 1, Ingredient.Category.MIXER);
        Ingredient k = new Ingredient("Dark Rum", 2, Ingredient.Category.SPIRIT);
        Ingredient g = new Ingredient("Ginger Beer", 3, Ingredient.Category.MIXER);
        Ingredient c = new Ingredient("Kahlua", 4, Ingredient.Category.LIQUEUR);
        Ingredient j = new Ingredient("Light Rum", 5, Ingredient.Category.SPIRIT);
        Ingredient i = new Ingredient("Lime Wedge", 6, Ingredient.Category.GARNISH);
        Ingredient a = new Ingredient("Orange Juice", 7, Ingredient.Category.MIXER);
        Ingredient l = new Ingredient("Passion Fruit Juice", 8, Ingredient.Category.MIXER);
        Ingredient m = new Ingredient("Pinapple Juice", 9, Ingredient.Category.MIXER);
        Ingredient d = new Ingredient("Tomato Juice", 10, Ingredient.Category.MIXER);
        Ingredient b = new Ingredient("Vodka", 11, Ingredient.Category.SPIRIT);
        Ingredient e = new Ingredient("Whiskey", 12, Ingredient.Category.SPIRIT);

        allIngredients.add(f);
        allIngredients.add(h);
        allIngredients.add(k);
        allIngredients.add(g);
        allIngredients.add(c);
        allIngredients.add(j);
        allIngredients.add(i);
        allIngredients.add(a);
        allIngredients.add(l);
        allIngredients.add(m);
        allIngredients.add(d);
        allIngredients.add(b);
        allIngredients.add(e);


        ArrayList<Drink.RecipeIngredient> recipeIngredients = new ArrayList<>();
        Drink.RecipeIngredient screw1 = new Drink().new RecipeIngredient(a, 5, "Ounces");
        Drink.RecipeIngredient screw2 = new Drink().new RecipeIngredient(b, 1.5, "Ounces");

        recipeIngredients.add(screw1);
        recipeIngredients.add(screw2);
        Drink z = new Drink("Srewdriver", recipeIngredients, "Place ice in glass. Pour vodka " +
                "over ice. Pour orange juice.", "Tumbler", 0);

        ArrayList<Drink.RecipeIngredient> bloodyIngredients = new ArrayList<>();
        Drink.RecipeIngredient bloody1 = new Drink().new RecipeIngredient(d, 5, "Ounces");
        Drink.RecipeIngredient bloody2 = new Drink().new RecipeIngredient(b, 1.5, "Ounces");

        bloodyIngredients.add(bloody1);
        bloodyIngredients.add(bloody2);
        Drink y = new Drink("Bloody Mary", bloodyIngredients, "Place ice in glass. Pour vodka " +
                "over ice. Pour tomato juice.", "Tumbler", 0);

        ArrayList<Drink.RecipeIngredient> russianIngredients = new ArrayList<>();
        Drink.RecipeIngredient russian1 = new Drink().new RecipeIngredient(c, 1.5, "Ounces");
        Drink.RecipeIngredient russian2 = new Drink().new RecipeIngredient(b, 2, "Ounces");
        Drink.RecipeIngredient russian3 = new Drink().new RecipeIngredient(h, .5, "Ounces");

        russianIngredients.add(russian1);
        russianIngredients.add(russian2);
        russianIngredients.add(russian3);
        Drink x = new Drink("White Russian", russianIngredients, "Place ice in glass. Pour vodka " +
                "khalua over ice. Top with cream. Stir and enjoy.", "Tumbler", 0);

        ArrayList<Drink.RecipeIngredient> moscowIngredients = new ArrayList<>();
        Drink.RecipeIngredient moscow1 = new Drink().new RecipeIngredient(g, 5, "Ounces");
        Drink.RecipeIngredient moscow2 = new Drink().new RecipeIngredient(b, 1.5, "Ounces");
        Drink.RecipeIngredient moscow3 = new Drink().new RecipeIngredient(i, 1, "Piece");

        moscowIngredients.add(moscow1);
        moscowIngredients.add(moscow2);
        moscowIngredients.add(moscow3);
        Drink  w= new Drink("Moscow Mule", moscowIngredients, "Place ice in glass. Pour vodka " +
                "over ice. Pour ginger beer, and place lime wedge on rim of mug.", "Copper Mug", 0);

        allDrinks.add(z);
        allDrinks.add(y);
        allDrinks.add(x);
        allDrinks.add(w);

    }


////////////////////////////   Chinh's Addition /////////////////////////////////////

    /**
     * Adds a single drink into the allDrinks list
     * @param aDrink The passed drink to be added.
     */
//    public void insertDrink(A_Drink aDrink){
//        allDrinks2.add(aDrink);
//    }

    /**
     * Gets and returns the list of all A_Drink.
     *
     * @return list of all A_Drink
     */
//    public ArrayList<A_Drink> getAllDrinks() {
//        return allDrinks2;
//    }

    public ArrayList<Drink> getAllDrinks() {
        return allDrinks;
    }

////////////////////////////   Chinh's Addition /////////////////////////////////////


    public static Catalog getInstance() {
        if (catalog == null) {
            catalog = new Catalog();
        }
        return catalog;
    }

    /**
     * Gets and returns list of ingredients.
     *
     * @return the list of ingredients
     */
    public ArrayList<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    /**
     * Sets list of ingredients to the list passed in.
     *
     * @param allIngredients the list of ingredients to set allIngredients to
     */
    public void setAllIngredients(ArrayList<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    /**
     * Gets and returns list of the working ingredients' ids.
     *
     * @return the list of working ingredients' ids
     */
    public ArrayList<Integer> getWorkingIngredientIDs() {
        return workingIngredientIDs;
    }

    /**
     * Sets the list of working ingredient ids to the list of integers passed in.
     *
     * @param workingIngredients the list to which to set the working ingredients
     */
    public void setWorkingIngredientIDs(ArrayList<Integer> workingIngredients) {
        this.workingIngredientIDs = workingIngredients;
    }

    /**
     * Sets the list of all A_Drink to the list of A_Drink passed in.
     *
     * @param allDrinks the list to which to set the list of A_Drink
     */
    public void setAllDrinks(ArrayList<Drink> allDrinks) {
        this.allDrinks = allDrinks;
    }

    /**
     * Gets and returns the list of makeable A_Drink.
     *
     * @return the list of makeable A_Drink
     */
    public ArrayList<Drink> getMakable() {
        return makable;
    }

    /**
     * Sets the list of makeable A_Drink to the list of A_Drink passed in.
     *
     * @param makable the list to which to set the list of A_Drink
     */
    public void setMakable(ArrayList<Drink> makable) {
        this.makable = makable;
    }

    /**
     * Gets and returns the list of near makeable A_Drink.
     *
     * @return the list of near makeable A_Drink
     */
    public ArrayList<Drink> getNearMakable() {
        return nearMakable;
    }

    /**
     * Sets the list of near makeable A_Drink to the list of A_Drink passed in.
     *
     * @param nearMakable the list to which to set the list of A_Drink
     */
    public void setNearMakable(ArrayList<Drink> nearMakable) {
        this.nearMakable = nearMakable;
    }

    /**
     * Searches A_Drink by iterating through every drink in the system. It compares each of the drink's
     * ingredients' id to the set of working ingredient ids. Working ingredient ids should have been
     * set before this method is called.
     * <p>
     * For each drink iteration, a current weight is tracked, and an unavailable weight is tracked.
     * If the drink ingredient is not in the set of working ingredients, the ingredient's weight is
     * added to the unavailable weight. If the drink ingredient is in the set of working ingredients,
     * the ingredient's weight is added to the current weight. At all times, the sum of the current
     * weight and the unavailable weight is equal to the total weight of all ingredients iterated
     * so far.
     * <p>
     * A calculation is made to determine the maximum unavailable weight that would still allow
     * for the drink to be a near match. If the unavailable weight exceeds this maximum unavailable
     * weight, the loop is exited and method goes on the to the next drink if there is one.
     * <p>
     * When the drink iteration finishes iterating through all of its ingredients, the current
     * weight is compared to the total drink weight to determine the percent match of the drink
     * with the given set of working ingredients.
     * <p>
     * If the percent match is 100, the drink is added to the makeable A_Drink list, otherwise if the
     * percent match is greater than the minimum percent match constant, it is added to the near
     * makeable list. In both cases, the percent match attribute of the drink is set accordingly.
     * <p>
     * At the start of the method, both makeable and near makeable lists are cleared of the A_Drink
     * that are stored from the previous running of the method.
     */
    public void searchDrinks() {

        makable.clear();
        nearMakable.clear();

        for (Drink d : allDrinks) {
            int currentWeight = 0;
            int totalIngreds = d.getNumIngreds();
            int totalDrinkWeight = d.getTotalWeight();
            int maxUnavailableWeight = ((100 - MIN_PERCENT_MATCH) * totalDrinkWeight) / 100;
            int unavailableWeight = 0;

            ArrayList<Integer> ingredIDs = d.getIngredIDs();
            // for each drink ingredient, while not exceeding maximum number of unavailable weight
            for (int i = 0; i < totalIngreds && unavailableWeight <= maxUnavailableWeight; i++) {
                // binary search for drink ingredient among ordered workingIngredients
                // if found increment presentIngreds
                // if not found increment unavailableIngreds
                if (Collections.binarySearch(workingIngredientIDs, ingredIDs.get(i)) < 0) {
                    unavailableWeight += allIngredients.get(ingredIDs.get(i)).getWeight();
                } else {
                    currentWeight += allIngredients.get(ingredIDs.get(i)).getWeight();
                }
            }
            int percent = (int) ((currentWeight * 100.0f) / totalDrinkWeight);

            if (percent == 100) {
                d.setPercentMatch(100);
                makable.add(d);
            } else if (percent >= MIN_PERCENT_MATCH) {
                d.setPercentMatch(percent);
                nearMakable.add(d);
            }
        }
    }

    /**
     * Populates a list of Strings with the names of the A_Drink in the makeable A_Drink list, and
     * returns it.
     *
     * @return the list of makeable drink names
     */
    public ArrayList<String> getMakableNames() {
        ArrayList<String> makableNames = new ArrayList<>();

        for (Drink d : makable) {
            makableNames.add(d.getName());
        }
        return makableNames;
    }

    /**
     * Populates a list of Strings with the names of the A_Drink in the near makeable A_Drink list, and
     * returns it.
     *
     * @return the list of near makeable drink names
     */
    public ArrayList<String> getNearMakableNames() {
        ArrayList<String> nearMakableNames = new ArrayList<>();

        for (Drink d : nearMakable) {
            nearMakableNames.add(d.getName());
        }
        return nearMakableNames;
    }

    /**
     * Populates a list of Strings with the toString value of the percent match of the A_Drink in
     * the near makeable A_Drink list, and returns it.
     *
     * @return the list of near makeable percent matches as Strings
     */
    public ArrayList<String> getNearMakableMatch() {
        ArrayList<String> nearMakableMatch = new ArrayList<>();

        for (Drink d : nearMakable) {
            nearMakableMatch.add(Integer.toString(d.getPercentMatch()));
        }
        return nearMakableMatch;
    }

    /**
     * Populates a list with the names of all ingredients in the creation drink and returns it.
     *
     * @return the list of creation ingredient names
     */
    public ArrayList<String> getCreationIngredNames() {
        ArrayList<String> creationIngredNames = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            creationIngredNames.add(ingreds.get(i).getName());

        return creationIngredNames;
    }

    /**
     * Populates a list with the toString values of the volumes of all ingredients in the creation
     * drink and returns it.
     *
     * @return the list of creation ingredient volumes as Strings
     */
    public ArrayList<String> getCreationVolumes() {
        ArrayList<String> creationVolumes = new ArrayList<>();

        ArrayList<Drink.RecipeIngredient> ingreds = creation.getRecipeIngredients();
        for (int i = 0; i < ingreds.size(); i++) {
            creationVolumes.add(Double.toString(ingreds.get(i).getVolume()));
        }
        return creationVolumes;
    }

    /**
     * Populates a list with the units of all ingredients in the creation drink and returns it.
     *
     * @return the list of creation ingredient units
     */
    public ArrayList<String> getCreationUnits() {
        ArrayList<String> creationUnits = new ArrayList<>();

        ArrayList<Drink.RecipeIngredient> ingreds = creation.getRecipeIngredients();
        for (int i = 0; i < ingreds.size(); i++) {
            creationUnits.add(ingreds.get(i).getUnit());
        }
        return creationUnits;
    }

    public ArrayList<String> getCreationVolumeUnits() {
        ArrayList<String> creationVolumeUnits = new ArrayList<>();

        ArrayList<Drink.RecipeIngredient> ingreds = creation.getRecipeIngredients();
        for (int i = 0; i < ingreds.size(); i++) {
            creationVolumeUnits.add(ingreds.get(i).getVolumeUnit());
        }
        return creationVolumeUnits;
    }

    /**
     * Calls on the drink's removeIngredient method to remove an ingredient at a position in its
     * ingredient list.
     *
     * @param pos the index of the ingredient to be removed
     */
    public void removeCreationIngredient(int pos) {
        creation.removeIngredient(pos);
        ///////////////////////////////////////////////////////////////////////////////////////////////////TO DO -- IF REMOVING A NEWLY CREATED INGREDIENT IT MUST BE REMOVED FROM LIST OF NEW INGREDIENTS
    }

    /**
     * Calls on the drink's setName method to set the name of the creation drink.
     *
     * @param creationName the String to which to set the creation drink's name
     */
    public void setCreationName(String creationName) {
        creation.setName(creationName);
    }

    /**
     * Calls on the drink's setInstructions method to set the instructions of the creation drink.
     *
     * @param creationInstructions the String to which to set the creation drink's instructions
     */
    public void setCreationInstructions(String creationInstructions) {
        creation.setDirections(creationInstructions);
    }

    /**
     * Calls on the drink's setGlassType method to set the glass type of the creation drink.
     *
     * @param creationGlassType the String to which to set the creation drink's glass type
     */
    public void setCreationGlassType(String creationGlassType) {
        creation.setGlassType(creationGlassType);
    }

    /**
     * Calls on the drink's getName method to get the name of the creation drink, and returns it.
     *
     * @return the name of the creation drink
     */
    public String getCreationName() {
        return creation.getName();
    }

    /**
     * Calls on the drink's getInstructions method to get the instructions of the creation drink,
     * and returns it.
     *
     * @return the instructions of the creation drink
     */
    public String getCreationInstructions() {
        return creation.getDirections();
    }

    /**
     * Calls on the drink's getGlassType method to get the glass type of the creation drink,
     * and returns it.
     *
     * @return the glass type of the creation drink
     */
    public String getCreationGlassType() {
        return creation.getGlassType();
    }

    /**
     * Calls on the drink's addIngredient method to add ingredient from the parameters passed in.
     * If it is a new ingredient, the id will match the NO_ID constant and a new Ingredient is
     * created with the parameters and added to the list of new ingredients. Otherwise, the
     * ingredient already exists and only the volume and the units need to be set. Then the
     * ingredient is added via drink's method.
     *
     * @param ingredientId     the id of an existing ingredient or value of -1 for a new one
     * @param ingredientVolume the volume of the ingredient to be added
     * @param units            the units of measurement of the volume of the ingredient
     * @param name             the name of the ingredient if it is new, otherwise null
     * @param category         the category of the ingredient if it is new, otherwise null
     */
    public void setCreationIngredient(int ingredientId, double ingredientVolume, String units,
                                      String name, Ingredient.Category category) {
        Ingredient i;
        Drink.RecipeIngredient recipeIngredient;

        if (ingredientId == NO_ID) {
            String upperName = upperCaseAllFirst(name);
            i = new Ingredient(upperName, NO_ID, category);
            recipeIngredient = new Drink().new RecipeIngredient(i, ingredientVolume, units);
            newIngredients.add(i);
        } else {
            i = allIngredients.get(ingredientId);
            recipeIngredient = new Drink().new RecipeIngredient(i, ingredientVolume, units);
        }
        creation.addRecipeIngredient(recipeIngredient);
    }

    /**
     * Gets the name of the ingredient from the id of the ingredient. The parameter actually
     * is used to get the index of the allIngredients list, which should be the same as its id.
     * After receiving the ingredient from the list it calls on the ingredient's getName method
     * to get the name.
     *
     * @param ingredientID the index of the list and the id of the ingredient
     * @return the name of the ingredient at the given index
     */
    public String getIngredientName(int ingredientID) {
        return allIngredients.get(ingredientID).getName();
    }

    /**
     * Adds the creation drink to the set of all A_Drink. If there are any new ingredients, they are
     * added to list of all ingredients and the list is ordered alphabetically and given new ids
     * based on their alphabetical order. The creation drink is then assigned a new empty drink.
     */
    public void addCreation() {
        allDrinks.add(creation);
        repository.addDrink(creation);
        if (newIngredients.size() > 0) {
            allIngredients.addAll(newIngredients);
            Collections.sort(allIngredients);
            for (int i = 0; i < allIngredients.size(); i++){
                allIngredients.get(i).setId(i);
            }
            newIngredients.clear();
        }
        creation = new Drink();
    }

    /**
     * Finds the drink from the list of all A_Drink that's name matches the name passed in. It sets
     * the recipe drink to the drink found.
     *
     * @param drinkName the name of the drink to which to set the recipe
     */
    public void setRecipe(String drinkName) {
        for (Drink d : allDrinks) {
            if (d.getName().equals(drinkName))
                recipe = d;
        }
    }

    /**
     * Creates and returns a list of the names of all ingredients in the recipe drink.
     *
     * @return  the list of ingredient names of the recipe drink
     */
    public ArrayList<String> getRecipeIngredients() {

        ArrayList<String> recipeIngredients = new ArrayList<>();

        ArrayList<Ingredient> ingreds = recipe.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            recipeIngredients.add(ingreds.get(i).getName());

        return recipeIngredients;
    }

    /**
     * Creates and returns a list of the toString values of the volumes of all ingredients in
     * the recipe drink.
     *
     * @return  the list of Strings of the volumes of the recipe drink
     */
    public ArrayList<String> getRecipeVolumes() {

        ArrayList<String> recipeVolumes = new ArrayList<>();

        ArrayList<Drink.RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
        for (int i = 0; i < recipeIngredients.size(); i++)
            recipeVolumes.add(Double.toString(recipeIngredients.get(i).getVolume()));

        return recipeVolumes;
    }

    /**
     * Creates and returns a list of the units of measurement of all ingredients in
     * the recipe drink.
     *
     * @return  the list of the units of the recipe drink
     */
    public ArrayList<String> getRecipeUnits() {

        ArrayList<String> recipeUnits = new ArrayList<>();

        ArrayList<Drink.RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
        for (int i = 0; i < recipeIngredients.size(); i++)
            recipeUnits.add(recipeIngredients.get(i).getUnit());

        return recipeUnits;
    }

    /**
     * Calls on the drink's getInstructions method to get the recipe drink's instructions and
     * returns them.
     *
     * @return  the instuctions for the recipe drink
     */
    public String getRecipeInstructions() {
        return recipe.getDirections();
    }

    /**
     * Calls on the drink's getGlassType method to get the recipe drink's glass type and
     * returns it.
     *
     * @return  the glass type for the recipe drink
     */
    public String getRecipeGlassType() {
        return recipe.getGlassType();
    }

    public String getRecipeRating() {
        return String.format("%.2f", recipe.getDrinkRating());
    }

    /**
     * Gets an ingredients id from the passed in ingredient name. Primarily used to check if an
     * ingredient is new. It returns the id of the ingredient if it exists, or -1 if it is a
     * new ingredient. It calls on upperCaseAllFirst method to assure that the ingredient is
     * case insensitive.
     *
     * @param newIngName    the name of the ingredient for which an id is returned, if existent
     * @return              the id of the ingredient if it exists, or -1 if it is new
     */
    public int getIngredientID(String newIngName) {
        // format with capital first letter of every word
        String formatIngredientName = upperCaseAllFirst(newIngName);

        for (Ingredient i : allIngredients) {
            if (i.getName().equals(formatIngredientName))
                return i.getId();
        }
        return -1;
    }

    /**
     * Used to format a String such that the first letter and all letters following a whitespace
     * character are capitalized.
     *
     * @param value     the String to be formatted
     * @return          the formatted version of the input String
     */
    public static String upperCaseAllFirst(String value) {

        char[] array = value.toCharArray();
        // Uppercase first letter.
        array[0] = Character.toUpperCase(array[0]);

        // Uppercase all letters that follow a whitespace character.
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }
        // Result.
        return new String(array);
    }

    /**
     * Searches for a drink by its name attribute and returns the drink object.
     *
     * @param drinkName     the name of the drink
     * @return              the drink object that has the name of the input parameter
     */
    public Drink getDrinkByName(String drinkName) {
        for (Drink d : allDrinks) {
            if (drinkName.equals(d.getName()))
                return d;
        }
        return null;
    }

    public Ingredient getIngredientByName(String ingredientName){
        for (Ingredient i: allIngredients){
            if (ingredientName.equals(i.getName()))
                return i;
        }
        return null;
    }

    public String getRandomDrink() {

        Random random = new Random();
        int drinkNumber = random.nextInt(allDrinks.size());
        return allDrinks.get(drinkNumber).getName();
    }

//    public boolean userReviewed(String drinkName, String userName) {
//        Drink d = getDrinkByName(drinkName);
//        ArrayList<Drink.RateReview> ratings = d.getRatings();
//        for (int i = 0; i < ratings.size(); i++){
//            if (ratings.get(i).getUser().equals(userName))
//                return true;
//        }
//
//        return false;
//    }

    public float getUserRating(String drinkName, String userName) {
        Drink d = getDrinkByName(drinkName);
        ArrayList<Drink.RateReview> ratings = d.getRatings();
        for (int i = 0; i < ratings.size(); i++){
            if (ratings.get(i).getUser().equals(userName))
                return ratings.get(i).getRating();
        }

        return -1;
    }

    public String getUserReview(String drinkName, String userName) {
        Drink d = getDrinkByName(drinkName);
        ArrayList<Drink.RateReview> ratings = d.getRatings();
        for (int i = 0; i < ratings.size(); i++){
            if (ratings.get(i).getUser().equals(userName))
                return ratings.get(i).getReview();
        }
        return null;
    }

    public void addDrinkRating(String drinkName, String userName, float rating, String review) {
        Drink d = getDrinkByName(drinkName);

        if (getUserRating(drinkName, userName) == -1) {
            d.addRating(userName, rating, review);
        } else {
            d.replaceRating(userName, rating, review);
        }
    }



}
