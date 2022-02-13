package com.techelevator;

public abstract class Item {
   private String name;
   private Double cost;
   private String type;

    public Item(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Item(String name, Double cost, String type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    //TODO change getBalance to bigDecimal

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getSound(){
        return "Noise";
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        // getSound is overrode from children
        return name + ", " + cost + ", " + getSound();
    }
}
