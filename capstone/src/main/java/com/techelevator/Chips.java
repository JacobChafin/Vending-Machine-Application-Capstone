package com.techelevator;

public class Chips extends Item {

    public Chips(String name, Double cost) {
        super(name, cost, "Chips");
    }

    @Override
    public String getSound() {
        return "Crunch Crunch, Yum!";
    }
}
