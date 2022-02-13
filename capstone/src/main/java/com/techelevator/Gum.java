package com.techelevator;

public class Gum extends Item {

    public Gum(String name, Double cost) {
        super(name, cost, "Gum");
    }

    @Override
    public String getSound() {
        return "Chew Chew, Yum!";
    }
}
