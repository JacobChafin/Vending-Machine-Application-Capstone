package com.techelevator;

public class Candy extends Item {

    public Candy(String name, Double cost) {
        super(name, cost, "Candy");
    }

    @Override
    public String getSound() {
        return "Munch Munch, Yum!";
    }
}
