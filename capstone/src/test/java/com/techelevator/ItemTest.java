package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    @Test
    public void toString_candy_goes_munch_munch_yum(){

        //Arrange
        Item testCandy = new Candy("testItem", 1.0);
        String expected = "testItem" + ", "  + 1.0 + ", " + "Munch Munch, Yum!";
        //Act
        String actual = testCandy.toString(); //Once we have a candy we can call toString()

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void toString_beverage_goes_glug_glug_yum(){

        //Arrange
        Item testBeverage = new Beverage("Surge", 1.99);
        String expected = "Surge" + ", "  + 1.99 + ", " + "Glug Glug, Yum!";
        //Act
        String actual = testBeverage.toString(); //Once we have a candy we can call toString()

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void toString_gum_goes_chew_chew_yum(){

        //Arrange
        Item testGum = new Gum("Hubba Bubba", 1.01);
        String expected = "Hubba Bubba" + ", "  + 1.01 + ", " + "Chew Chew, Yum!";
        //Act
        String actual = testGum.toString(); //Once we have a candy we can call toString()

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void toString_chips_goes_crunch_crunch_yum(){

        //Arrange
        Item testChips = new Chips("Chips Ahoy", 1.99);
        String expected = "Chips Ahoy" + ", "  + 1.99 + ", " + "Crunch Crunch, Yum!";
        //Act
        String actual = testChips.toString(); //Once we have a candy we can call toString()

        //Assert
        Assert.assertEquals(expected, actual);

    }





}
