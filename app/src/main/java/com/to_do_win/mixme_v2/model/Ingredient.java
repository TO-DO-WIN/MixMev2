package com.to_do_win.mixme_v2.model;

public class Ingredient {

    private String name;
    private double volume;
    private String unit;
    private int id;
    private Category category;
    public enum Category {
        GARNISH (1),
        MIXER (3),
        LOW_ALCOHOL (5),
        LIQUEUR (7),
        SPIRIT (10);

        private final int weight;

        Category (int weight){
            this.weight = weight;
        }
        public int weight() {return weight;}
        public static Category getCategory(String catName){
            switch (catName){
                case "Garnish":
                    return GARNISH;
                case "Mixer":
                    return MIXER;
                case "Low-Alcohol":
                    return LOW_ALCOHOL;
                case "Liqueur":
                    return LIQUEUR;
                case "Spirit":
                    return SPIRIT;
                default:
                    return null;
            }
        }
    };


    public int getWeight() {
        return category.weight();
    }
    // may dedide we need a way to set weight, but for now only use category to determine weight
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }

    public Ingredient(String name, int id, Category cat){
        this.name = name;
        this.id = id;
        this.category = cat;
    }

    public Ingredient(String name, double volume, String unit, int id, Category cat) {
        this.name = name;
        this.volume = volume;
        this.unit = unit;
        this.id = id;
        this.category = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
