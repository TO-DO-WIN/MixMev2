package com.to_do_win.mixme_v2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Catalog {

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

        // Call to DB to get all ingredients and all drinks

        // mock this call for now
        // ingreds
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

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(a);
        ingredients.add(b);
        Drink z = new Drink("Srewdriver", ingredients, "Place ice in glass. Pour vodka " +
                "over ice. Pour orange juice.", "Tumbler", 0);

        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(d);
        ingredients2.add(b);
        Drink y = new Drink("Bloody Mary", ingredients2, "Optionally you can salt the rim of the glass. Add ice, and" +
                "ingredients.", "Pint glass", 0);

        ArrayList<Ingredient> ingredients3 = new ArrayList<>();
        ingredients3.add(c);
        ingredients3.add(h);
        ingredients3.add(b);
        Drink x = new Drink("White Russian", ingredients3);

        ArrayList<Ingredient> ingredients4 = new ArrayList<>();
        ingredients4.add(g);
        ingredients4.add(i);
        ingredients4.add(b);
        Drink w = new Drink("Moscow Mule", ingredients4);


//        Drink zy = new Drink("whiskey coke", new Ingredient[]{e, f});
//        Drink zx = new Drink("chinh's special", new Ingredient[]{j, k, l, m});
//        Drink xz = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink xy = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xx = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zc = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yc = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xc = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zv = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yv = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xv = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zb = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yb = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xb = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zn = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yn = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xn = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zm = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink ym = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink mx = new Drink("white russian", new Ingredient[]{b, c, h});


//        for (int i = 0; i < 1000; i++) {
//            Drink newDrink = new Drink("newDrink", new Ingredient[]{a, b, c, d, e, f, g, h});
//            allDrinks.add(newDrink);
//        }

        allDrinks.add(z);
        allDrinks.add(y);
        allDrinks.add(x);
        allDrinks.add(w);
//        allDrinks.add(zy);
//        allDrinks.add(zx);
//        allDrinks.add(xz);
//        allDrinks.add(xy);
//        allDrinks.add(xx);
//        allDrinks.add(zc);
//        allDrinks.add(yc);
//        allDrinks.add(xc);
//        allDrinks.add(zv);
//        allDrinks.add(yv);
//        allDrinks.add(xv);
//        allDrinks.add(zb);
//        allDrinks.add(yb);
//        allDrinks.add(xb);
//        allDrinks.add(zn);
//        allDrinks.add(yn);
//        allDrinks.add(xn);
//        allDrinks.add(zm);
//        allDrinks.add(ym);
//        allDrinks.add(mx);


    }

    public static Catalog getInstance() {
        if (catalog == null) {
            catalog = new Catalog();
        }
        return catalog;
    }

    public ArrayList<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(ArrayList<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    public ArrayList<Integer> getWorkingIngredientIDs() {
        return workingIngredientIDs;
    }

    public void setWorkingIngredientIDs(ArrayList<Integer> workingIngredients) {
        this.workingIngredientIDs = workingIngredients;
    }

    public ArrayList<Drink> getAllDrinks() {
        return allDrinks;
    }

    public void setAllDrinks(ArrayList<Drink> allDrinks) {
        this.allDrinks = allDrinks;
    }

    public ArrayList<Drink> getMakable() {
        return makable;
    }

    public void setMakable(ArrayList<Drink> makable) {
        this.makable = makable;
    }

    public ArrayList<Drink> getNearMakable() {
        return nearMakable;
    }

    public void setNearMakable(ArrayList<Drink> nearMakable) {
        this.nearMakable = nearMakable;
    }

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
            // for each drink ingredient, while not exceeding maximum number of unavailable
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

    public ArrayList<String> getMakableNames() {
        ArrayList<String> makableNames = new ArrayList<>();

        for (Drink d : makable) {
            makableNames.add(d.getName());
        }
        return makableNames;
    }

    public ArrayList<String> getNearMakableNames() {
        ArrayList<String> nearMakableNames = new ArrayList<>();

        for (Drink d : nearMakable) {
            nearMakableNames.add(d.getName());
        }
        return nearMakableNames;
    }

    public ArrayList<String> getNearMakableMatch() {
        ArrayList<String> nearMakableMatch = new ArrayList<>();

        for (Drink d : nearMakable) {
            nearMakableMatch.add(Integer.toString(d.getPercentMatch()));
        }
        return nearMakableMatch;
    }

    public void createDrink(String drinkName, ArrayList<String> ingredientNames,
                            ArrayList<String> ingredientVolumes, ArrayList<String> ingredientUnits,
                            ArrayList<Integer> ingredientIDs, String directions, String glassType,
                            ArrayList<String> ingredientCats) {

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientNames.size(); i++) {

            // set category from type String to type Category
            Ingredient.Category cat;
            switch (ingredientCats.get(i)) {
                case "GARNISH":
                    cat = Ingredient.Category.GARNISH;
                    break;
                case "MIXER":
                    cat = Ingredient.Category.MIXER;
                    break;
                case "LIQUEUR":
                    cat = Ingredient.Category.LIQUEUR;
                    break;
                case "SPIRIT":
                    cat = Ingredient.Category.SPIRIT;
                    break;

                // should handle for null which should never happen
                default:
                    cat = null;
            }


            ingredients.add(new Ingredient(ingredientNames.get(i), Double.parseDouble(ingredientVolumes.get(i)),
                    ingredientUnits.get(i), ingredientIDs.get(i), cat));
//            ingredients[i].setName(ingredientNames.get(i));
//            ingredients[i].setVolume(Double.parseDouble(ingredientVolumes.get(i)));
//            ingredients[i].setUnit(ingredientUnits.get(i));
//            ingredients[i].setId(ingredientIDs.get(i));
        }

        Drink d = new Drink(drinkName, ingredients, directions, glassType, 0);

        allDrinks.add(d);

    }

    private ArrayList<Ingredient> creationIngreds = new ArrayList<>();

    public ArrayList<String> getCreationIngredNames() {
        ArrayList<String> creationIngredNames = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            creationIngredNames.add(ingreds.get(i).getName());

        return creationIngredNames;
    }

    public ArrayList<String> getCreationVolumes() {
        ArrayList<String> creationVolumes = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++) {
            creationVolumes.add(Double.toString(ingreds.get(i).getVolume()));
        }

        return creationVolumes;
    }

    public ArrayList<String> getCreationUnits() {
        ArrayList<String> creationUnits = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++) {
            creationUnits.add(ingreds.get(i).getUnit());
        }

        return creationUnits;
    }

    public void removeCreationIngredient(int pos) {
        creation.removeIngredient(pos);
    }

    public void setCreationName(String creationName) {
        creation.setName(creationName);
    }

    public void setCreationInstructions(String creationInstuctions) {
        creation.setDirections(creationInstuctions);
    }

    public void setCreationGlassType(String creationGlassType) {
        creation.setGlassType(creationGlassType);
    }

    public String getCreationName() {
        return creation.getName();
    }

    public String getCreationInstructions() {
        return creation.getDirections();
    }

    public String getCreationGlassType() {
        return creation.getGlassType();
    }

    public void setCreationIngredient(int ingredientId, double ingredientVolume, String units,
                                      String name, Ingredient.Category category) {
        Ingredient i;

        if (ingredientId == NO_ID) {
            i = new Ingredient(name, ingredientVolume, units, NO_ID, category);
            newIngredients.add(i);
        } else {
            i = allIngredients.get(ingredientId);
            i.setVolume(ingredientVolume);
            i.setUnit(units);
        }

        creation.addIngredient(i);
    }

    public String getIngredientName(int ingredientID) {
        return allIngredients.get(ingredientID).getName();
    }
/*
    This version of setCreationIngredient method is for newly created ingredients. A newly created ingredient
    will need to have a name, not just id (doesn't have an id yet), also it needs a category. Here, the new
    ingredient can be stored in db and given and id. Ingredient list will need to be repopulated with new
    ingredient inserted in the list.
 */

    public void addCreation() {
        allDrinks.add(creation);

        int prevIngredientsSize = allIngredients.size();

        allIngredients.addAll(newIngredients);
        // need to assign ids to new ingredients
        for (int i = prevIngredientsSize; i < allIngredients.size(); i++) {
            allIngredients.get(i).setId(i);
        }

        creation = new Drink();
    }

    public void setRecipe(String drinkName) {
        for (Drink d : allDrinks) {
            if (d.getName().equals(drinkName))
                recipe = d;
        }
    }

    public ArrayList<String> getRecipeIngredients() {

        ArrayList<String> recipeIngredients = new ArrayList<>();

        ArrayList<Ingredient> ingreds = recipe.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            recipeIngredients.add(ingreds.get(i).getName());

        return recipeIngredients;
    }

    public ArrayList<String> getRecipeVolumes() {

        ArrayList<String> recipeVolumes = new ArrayList<>();

        ArrayList<Ingredient> ingreds = recipe.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            recipeVolumes.add(Double.toString(ingreds.get(i).getVolume()));

        return recipeVolumes;
    }

    public ArrayList<String> getRecipeUnits() {

        ArrayList<String> recipeUnits = new ArrayList<>();

        ArrayList<Ingredient> ingreds = recipe.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            recipeUnits.add(ingreds.get(i).getUnit());

        return recipeUnits;
    }

    public String getRecipeInstructions() {
        return recipe.getDirections();
    }

    public String getRecipeGlassType() {
        return recipe.getGlassType();
    }

    public int getIngredientID(String newIngName) {
        // format with capital first letter of every word
        String formatIngredientName = upperCaseAllFirst(newIngName);

        for (Ingredient i : allIngredients) {
            if (i.getName().equals(formatIngredientName))
                return i.getId();
        }
        return -1;
    }

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

   public Drink getDrinkByName(String drinkName) {
        for(Drink d: allDrinks){
            if(drinkName.equals(d.getName()))
                return d;
        }
        return null;


   }

    public String getRandomDrink() {

        Random random = new Random();
        int drinkNumber = random.nextInt(allDrinks.size());
        return allDrinks.get(drinkNumber).getName();
    }
}
