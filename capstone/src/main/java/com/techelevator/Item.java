package com.techelevator;

public class Item {
   private String name;
   private Double cost;

    public Item(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

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

    @Override
    public String toString() {
        // getSound is overrode from children
        return name + ", " + cost + ", " + getSound();
    }
}
