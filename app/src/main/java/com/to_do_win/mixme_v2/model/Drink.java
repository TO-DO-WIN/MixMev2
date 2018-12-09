package com.to_do_win.mixme_v2.model;

import java.util.ArrayList;

public class Drink {

    private static final int MAX_INGREDS = 15;
    private ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
    private String name;
    private String directions;
    private String glassType;
    private int percentMatch;
    private int numIngreds;
    private int totalWeight = 0;
    private ArrayList<RateReview> ratings = new ArrayList<>();
    private double rating;



    public int getTotalWeight() {
        return totalWeight;
    }

    private void setTotalWeight() {
        for (int i = 0; i < recipeIngredients.size(); i++)

            totalWeight += recipeIngredients.get(i).getIngredient().getWeight();

    }

    public Drink() {
    }

    public Drink(String name, ArrayList<RecipeIngredient> recipeIngredients) {
        this.name = name;
        this.recipeIngredients = recipeIngredients;
      //  setIngredients(this.recipeIngredients);//Added method to populate ingredients from db to list for interaction
        this.numIngreds = recipeIngredients.size();
        setTotalWeight();
    }

    /**
     * Retrieves list of ingredient names and units
     * sets the ingredient names and units to the corrispoinding ingredient and measure attributes
     * */
//    private void setIngredients(ArrayList<RecipeIngredient> recipeIngredients) {
//        ArrayList<String> ingredientNames = getIngredientNames();
//        ArrayList<String> ingredientUnits = getIngredientUnits();
//        int max = ingredientNames.size();
//        int i = 0;
//        for (RecipeIngredient recipeIngredient : recipeIngredients){
//            if(i<=max){
//                ingredientNames.set(i, recipeIngredient.getIngredient().getName());
//                ingredientUnits.set(i, recipeIngredient.unit);
//                i++;
//            }
//        }
//
//    }

    /**
     * Gets individual ingredients units and adds them to list
     * */
//    private ArrayList<String> getIngredientUnits() {
//        ArrayList<String> ingredientUnits = new ArrayList<>();
//        ingredientUnits.add(this.strMeasure1);
//        ingredientUnits.add(this.strMeasure2);
//        ingredientUnits.add(this.strMeasure3);
//        ingredientUnits.add(this.strMeasure4);
//        ingredientUnits.add(this.strMeasure5);
//        ingredientUnits.add(this.strMeasure6);
//        ingredientUnits.add(this.strMeasure7);
//        ingredientUnits.add(this.strMeasure8);
//        ingredientUnits.add(this.strMeasure9);
//        ingredientUnits.add(this.strMeasure10);
//        ingredientUnits.add(this.strMeasure11);
//        ingredientUnits.add(this.strMeasure12);
//        ingredientUnits.add(this.strMeasure13);
//        ingredientUnits.add(this.strMeasure14);
//        ingredientUnits.add(this.strMeasure15);
//        return ingredientUnits;
//    }

    /**
     * gets individual ingredient names and adds them to list
     * */
//    private ArrayList<String> getIngredientNames() {
//        ArrayList<String> ingredientNames = new ArrayList<>();
//        ingredientNames.add(this.strIngredient1);
//        ingredientNames.add(this.strIngredient2);
//        ingredientNames.add(this.strIngredient3);
//        ingredientNames.add(this.strIngredient4);
//        ingredientNames.add(this.strIngredient5);
//        ingredientNames.add(this.strIngredient6);
//        ingredientNames.add(this.strIngredient7);
//        ingredientNames.add(this.strIngredient8);
//        ingredientNames.add(this.strIngredient9);
//        ingredientNames.add(this.strIngredient10);
//        ingredientNames.add(this.strIngredient11);
//        ingredientNames.add(this.strIngredient12);
//        ingredientNames.add(this.strIngredient13);
//        ingredientNames.add(this.strIngredient14);
//        ingredientNames.add(this.strIngredient15);
//        return ingredientNames;
//    }

    public Drink(String name, ArrayList<RecipeIngredient> recipeIngredients, String directions, String glassType, int percentMatch) {
        this.name = name;
        this.recipeIngredients = recipeIngredients;
        this.directions = directions;
        this.glassType = glassType;
        this.percentMatch = percentMatch;
        this.numIngreds = recipeIngredients.size();
        setTotalWeight();
    }

    public static int getMaxIngreds() {
        return MAX_INGREDS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    // next two methods are for getting and setting the ingredient object contained inside the
    // recipeIngredient objects
    public ArrayList<Ingredient> getIngreds() {
        ArrayList<Ingredient> ingreds = new ArrayList<>();
        for (int i = 0; i < recipeIngredients.size(); i++) {
            ingreds.add(recipeIngredients.get(i).getIngredient());
        }
        return ingreds;
    }

    public void setIngreds(ArrayList<Ingredient> ingreds) {
        for (int i = 0; i < ingreds.size(); i++) {
            recipeIngredients.get(i).setIngredient(ingreds.get(i));
        }
    }

    public ArrayList<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(ArrayList<RecipeIngredient> recipeIngredients){
        this.recipeIngredients =  recipeIngredients;
    }

    public void removeIngredient(int index) {
        totalWeight -= recipeIngredients.get(index).ingredient.getWeight();
        recipeIngredients.remove(index);
        numIngreds--;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getGlassType() {
        return glassType;
    }

    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }

    public int getPercentMatch() {
        return percentMatch;
    }

    public void setPercentMatch(int percentMatch) {
        this.percentMatch = percentMatch;
    }

    public int getNumIngreds() {
        return numIngreds;
    }

    public void setNumIngreds(int numIngreds) {
        this.numIngreds = numIngreds;
    }

    public ArrayList<Integer> getIngredIDs() {
        ArrayList<Integer> ingredIDs = new ArrayList<>();

        for (int i = 0; i < recipeIngredients.size(); i++)
            ingredIDs.add(recipeIngredients.get(i).ingredient.getId());

        return ingredIDs;
    }

    public ArrayList<RateReview> getRatings() {
        return ratings;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        totalWeight += recipeIngredient.ingredient.getWeight();
        recipeIngredients.add(recipeIngredient);
        numIngreds++;
    }

//    public void addIngredient(Ingredient i) {
//        totalWeight += i.getWeight();
//        ingreds.add(i);
//        numIngreds++;
//    }

    public void addRating(String userName, float rating, String review) {
        ratings.add(new RateReview(userName, rating, review));
        setRating();
    }

    public void replaceRating(String userName, float rating, String review) {
        int indexOfRating = findRatingIndexByName(userName);
        ratings.get(indexOfRating).setRating(rating);
        ratings.get(indexOfRating).setReview(review);
        setRating();
    }

    public int findRatingIndexByName(String userName){
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////return index of rating from list which reefers to the user in question.
        ///////////////to be used in above method for replacing a rating from a particular user.
        //////////////also may be used in catalog methods to find rating and review based on a userName.
        for (int i = 0; i < ratings.size(); i++){
            if (ratings.get(i).getUser().equals(userName))
                return i;
        }
        return -1;
    }

    public void setRating(){
        double sum = 0;
        for (RateReview r: ratings){
            sum += r.getRating();
        }
        rating = sum / ratings.size();
    }

    public double getDrinkRating(){
        return rating;
    }

    public class RateReview {

        private String user;
        private float rating;
        private String review;

        public RateReview(String user, float rating, String review){
            this.user = user;
            this.rating = rating;
            this.review = review;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }
    }

    public class RecipeIngredient {

        private Ingredient ingredient;
        private double volume;
        private String unit;

        public RecipeIngredient(Ingredient ingredient, double volume, String unit) {
            this.ingredient = ingredient;
            this.volume = volume;
            this.unit = unit;
        }

        public Ingredient getIngredient() {
            return ingredient;
        }

        public void setIngredient(Ingredient ingredient) {
            this.ingredient = ingredient;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }


}
