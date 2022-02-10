package com.techelevator;

public class Candy extends Item {

    public Candy(String name, Double cost) {
        super(name, cost);
    }

    @Override
    public String getSound() {
        return "Munch Much, Yum!";
    }
}
