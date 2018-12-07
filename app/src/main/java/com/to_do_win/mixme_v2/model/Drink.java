package com.to_do_win.mixme_v2.model;

import java.util.ArrayList;

public class Drink {

    private static final int MAX_INGREDS = 15;
    private ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
    //private Ingredient[] ingreds = new Ingredient[MAX_INGREDS];
    private int percentMatch;
    private int numIngreds;
    private int totalWeight = 0;
    private ArrayList<RateReview> ratings = new ArrayList<>();
    private double rating;
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
    private String dateModified;


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
        this.strDrink = name;
        this.recipeIngredients = recipeIngredients;
        setIngredients(this.recipeIngredients);//Added method to populate ingredients from db to list for interaction
        this.numIngreds = recipeIngredients.size();
        setTotalWeight();
    }

    /**
     * Retrieves list of ingredient names and units
     * sets the ingredient names and units to the corrispoinding ingredient and measure attributes
     * */
    private void setIngredients(ArrayList<RecipeIngredient> recipeIngredients) {
        ArrayList<String> ingredientNames = getIngredientNames();
        ArrayList<String> ingredientUnits = getIngredientUnits();
        int max = ingredientNames.size();
        int i = 0;
        for (RecipeIngredient recipeIngredient : recipeIngredients){
            if(i<=max){
                ingredientNames.set(i, recipeIngredient.getIngredient().getName());
                ingredientUnits.set(i, recipeIngredient.unit);
                i++;
            }
        }

    }

    /**
     * Gets individual ingredients units and adds them to list
     * */
    private ArrayList<String> getIngredientUnits() {
        ArrayList<String> ingredientUnits = new ArrayList<>();
        ingredientUnits.add(this.strMeasure1);
        ingredientUnits.add(this.strMeasure2);
        ingredientUnits.add(this.strMeasure3);
        ingredientUnits.add(this.strMeasure4);
        ingredientUnits.add(this.strMeasure5);
        ingredientUnits.add(this.strMeasure6);
        ingredientUnits.add(this.strMeasure7);
        ingredientUnits.add(this.strMeasure8);
        ingredientUnits.add(this.strMeasure9);
        ingredientUnits.add(this.strMeasure10);
        ingredientUnits.add(this.strMeasure11);
        ingredientUnits.add(this.strMeasure12);
        ingredientUnits.add(this.strMeasure13);
        ingredientUnits.add(this.strMeasure14);
        ingredientUnits.add(this.strMeasure15);
        return ingredientUnits;
    }

    /**
     * gets individual ingredient names and adds them to list
     * */
    private ArrayList<String> getIngredientNames() {
        ArrayList<String> ingredientNames = new ArrayList<>();
        ingredientNames.add(this.strIngredient1);
        ingredientNames.add(this.strIngredient2);
        ingredientNames.add(this.strIngredient3);
        ingredientNames.add(this.strIngredient4);
        ingredientNames.add(this.strIngredient5);
        ingredientNames.add(this.strIngredient6);
        ingredientNames.add(this.strIngredient7);
        ingredientNames.add(this.strIngredient8);
        ingredientNames.add(this.strIngredient9);
        ingredientNames.add(this.strIngredient10);
        ingredientNames.add(this.strIngredient11);
        ingredientNames.add(this.strIngredient12);
        ingredientNames.add(this.strIngredient13);
        ingredientNames.add(this.strIngredient14);
        ingredientNames.add(this.strIngredient15);
        return ingredientNames;
    }

    public Drink(String name, ArrayList<RecipeIngredient> recipeIngredients, String directions, String glassType, int percentMatch) {
        this.strDrink = name;
        this.recipeIngredients = recipeIngredients;
        this.strInstructions = directions;
        this.strGlass = glassType;
        this.percentMatch = percentMatch;
        this.numIngreds = recipeIngredients.size();
        setTotalWeight();
    }

    public static int getMaxIngreds() {
        return MAX_INGREDS;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
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

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
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
}
