package com.to_do_win.mixme_v2.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Drink {

    private static final int MAX_INGREDS = 15;
    private String name;
    private ArrayList<Ingredient> ingreds = new ArrayList<>();
    //private Ingredient[] ingreds = new Ingredient[MAX_INGREDS];
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
        for (Ingredient i : ingreds) {
            totalWeight += i.getWeight();
        }
    }

    public Drink() {
    }

    public Drink(String name, ArrayList<Ingredient> ingreds) {
        this.name = name;
        this.ingreds = ingreds;
        this.numIngreds = ingreds.size();
        setTotalWeight();
    }

    public Drink(String name, ArrayList<Ingredient> ingreds, String directions, String glassType, int percentMatch) {
        this.name = name;
        this.ingreds = ingreds;
        this.directions = directions;
        this.glassType = glassType;
        this.percentMatch = percentMatch;
        this.numIngreds = ingreds.size();
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

    public ArrayList<Ingredient> getIngreds() {
        return ingreds;
    }

    public void setIngreds(ArrayList<Ingredient> ingreds) {
        this.ingreds = ingreds;
    }

    public void removeIngredient(int index) {
        totalWeight -= ingreds.get(index).getWeight();
        ingreds.remove(index);
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

        for (int i = 0; i < ingreds.size(); i++)
            ingredIDs.add(ingreds.get(i).getId());

        return ingredIDs;
    }

    public ArrayList<RateReview> getRatings() {
        return ratings;
    }

    public void addIngredient(Ingredient i) {
        totalWeight += i.getWeight();
        ingreds.add(i);
        numIngreds++;
    }

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
}
