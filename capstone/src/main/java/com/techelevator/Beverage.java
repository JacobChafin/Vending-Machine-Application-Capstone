package com.techelevator;

public class Beverage extends Item {


    public Beverage(String name, Double cost) {
        super(name, cost);
    }

    @Override
    public String getSound() {
        return "Glug Glug, Yum!";
    }
}
