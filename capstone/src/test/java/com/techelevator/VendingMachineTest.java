package com.techelevator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class VendingMachineTest {
    VendingMachine testVendingMachine = new VendingMachine();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Before

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    public void setup() {

        testVendingMachine.run();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void does_vending_machine_dispense_item_return_change(){

        //Test textMenu inputs. Does it crash?
        //Test purchaseMenu displays expected balance. Compare balancec and variable
        //How do we keep from writing to a file when testing?


    }
    @Test
    public void does_feedMoney_update_balance_to_twelve_dollars_after_userInput_2_and_4() {

        //Arrange
        double expected = 12;


        //Act


        //Assert

    }

    @Test
    public void does_finishTransaction_return_twentyone_quarters_and_one_dime_and_one_nickel_from_blance_of_five_forty(){

        //Arrange
        testVendingMachine.setBalance(5.40);
        String expected = "Here is your change!: 21 Quarters 1 Dimes 1 Nickles";

       //Act
        testVendingMachine.finishTransaction();
        System.out.println();


        //Assert
       Assert.assertEquals(expected, outContent.toString());



        }

    }

