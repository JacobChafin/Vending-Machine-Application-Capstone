package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class VendingMachine {
    // set up scanner and set up variables
    // transaction log created and big decimal object for conversions
    Scanner scanner =new Scanner(System.in);
    String userInput;
    Inventory inventory;
    double balance = 0;
    TransactionLog transactionLog = new TransactionLog();
    BigDecimal balanceBigDecimal;


//create and load the inventory from file
//print the intro and start main text menu
    public void run() {
        inventory = new Inventory();
        inventory.loadInventory();
        // loads the inventory from the text file by ...-- see inventory comments
        System.out.println();
        System.out.println("******************************************");
        System.out.println("****                                  ****");
        System.out.println("**** Welcome to the Vendo-Matic 800!! ****");
        System.out.println("****                                  ****");
        System.out.println("******************************************");
        System.out.println("************by Umbrella Corp.*************");
        System.out.println();
        // runs text menu
        textMenu();
    }


//run the main text menu
    public void textMenu() {

    //displays main menu options.
    //asks for user input, continues to ask until a valid entry is made

        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        // Pretty up!!
        userInput = scanner.nextLine();
        // user input ^^ needs to be 1, 2, or 3 to pass the while loop (if input is correct while loop is skipped)
        while (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3"))) {
            System.out.println();
            System.out.println("Invalid Entry, Please Try Again!");
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            // Pretty up!!
            userInput = scanner.nextLine();
        }

    //a valid entry has been made, do the appropriate action below we have input as 1, 2, or 3 at this point

        //display the inventory and return to the text menu if input was 1
        if (userInput.equals("1")) {
            inventory.displayInventory();
            // see inventory displayInventory() ^^
            textMenu();
        }

        //go to the purchase menu if input is 2
        else if (userInput.equals("2")) {
            purchaseMenu();
        }

        //go to the exit method to exit if inout is 3
        else if (userInput.equals("3")) {
            exit();
        }
        //Easter Egg!! need change the while loop above
    }


//exit the program
    public void exit() {
        System.out.println("Thanks for Using Vendo-Matic 800!!");
        System.exit(0);
        // ends the program successful termination

    }


//the purchase menu
    public void purchaseMenu() {
    //displays purchase menu options
    //asks for user input, continues to ask until valid entry is made
        balanceBigDecimal = new BigDecimal(balance).setScale(2, RoundingMode.UP);
        System.out.println();
        System.out.println("******************************************");
        System.out.println("*******  Please select an option  ********");
        System.out.println("******************************************");
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("Current Balance: $" + balanceBigDecimal);
        // Pretty up!!
        userInput = scanner.nextLine();
        while (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3"))) {
            System.out.println("Invalid Entry, Please Try Again!");
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("Current Balance: $" + balanceBigDecimal);
            // Pretty up!!
            userInput = scanner.nextLine();
        }

    //a valid entry has been made, do the appropriate action below

        //go to the feed money menu
        if (userInput.equals("1")) {
            feedMoney();
        }

        //go to the select product menu
        else if (userInput.equals("2")) {
            selectProduct();
        }

        //go to the finishTransaction method to give change
        else if (userInput.equals("3")) {
            finishTransaction();
        }

    }


//the feed money menu
    public void feedMoney() {

    //displays defined amounts of dollars for the user to feed into the machine
    //asks for user input, continues to ask until valid entry is made
        System.out.println();
        System.out.println("******************************************");
        System.out.println("******  Select Currency to Enter  ********");
        System.out.println("******************************************");
        System.out.println("");
        System.out.println("(1) $1.00");
        System.out.println("(2) $2.00");
        System.out.println("(3) $5.00");
        System.out.println("(4) $10.00");
        System.out.println("(5) Done Feeding Money");
        System.out.println();
        System.out.println("Current Balance: $" + balanceBigDecimal);
        // Pretty up!!
        userInput = scanner.nextLine();
        while (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4") || userInput.equals("5"))) {
            System.out.println("Select Currency to Enter");
            System.out.println("(1) $1.00");
            System.out.println("(2) $2.00");
            System.out.println("(3) $5.00");
            System.out.println("(4) $10.00");
            System.out.println("(5) Done Feeding Money");
            System.out.println("Current Balance: $" + balanceBigDecimal);
            // Pretty up!!
            userInput = scanner.nextLine();
        }

    //a valid entry has been made, add the appropriate value to balance and return to feed money menu
    //log the money fed and updated balance to the log file
    //OR go back to purchase menu if user is done feeding money

        if (userInput.equals("1")) {
            balance = balance + 1;
            transactionLog.logFeedMoneyAudit(1,balance);
        } else if (userInput.equals("2")) {
            balance = balance + 2;
            transactionLog.logFeedMoneyAudit(2,balance);
        } else if (userInput.equals("3")) {
            balance = balance + 5;
            transactionLog.logFeedMoneyAudit(5,balance);
        } else if (userInput.equals("4")) {
            balance = balance + 10;
            transactionLog.logFeedMoneyAudit(10,balance);
        } else if (userInput.equals("5")) {
            purchaseMenu();
        }
        balanceBigDecimal = new BigDecimal(balance).setScale(2, RoundingMode.UP);
        feedMoney();
    }


//the select product menu
    public void selectProduct() {
        //display the inventory for user to choose from and ask for user to input a slot
        inventory.displayInventory();
        System.out.println("Please Enter an (Item Slot)");
        userInput = scanner.nextLine();

        //if user input is not a valid slot, return to the purchase menu
        if(!inventory.getSlots().contains(userInput)) {
            System.out.println("Invalid Slot");
            purchaseMenu();
        }

        //if slot has no items left, indicate sold out and return to the purchase menu
        if (inventory.checkInventorySlot(userInput) == 0) {
            System.out.println("SOLD OUT!");
            purchaseMenu();
        }
        //if item costs more than the current balance available, indicate and return to purchase menu
        else if (balance < inventory.getItemAtSlot(userInput).getCost()) {
            System.out.println("Sorry not enough money to purchase " + inventory.getItemAtSlot(userInput).getName());
            purchaseMenu();
        }

        //log sale of item in log file
        //remove selected item from inventory, and print out its Name, cost, sound using the Item class toString method
        //subtract cost of item from balance and return to purchase menu
        else {
            transactionLog.logSaleAudit(inventory.getItemAtSlot(userInput),userInput,balance);

            //both print out and remove item from inventory in one step
            balance = balance - inventory.getItemAtSlot(userInput).getCost();
            System.out.println(inventory.vendItem(userInput));

            balanceBigDecimal = new BigDecimal(balance).setScale(2, RoundingMode.UP);
            System.out.println("$" + balanceBigDecimal);
            purchaseMenu();
        }

    }

//log starting balance and 'final balance' to file
//return change and go back to the main text menu
    public void finishTransaction() {
        int numberOfQuarters = 0;
        int numberOfDimes = 0;
        int numberOfNickles = 0;

        transactionLog.logGiveChangeAudit(balance);

        //figure out how many quarters can be given from balance
        numberOfQuarters = (int) (balance / .25);
        //subtract value of quarters given from balance
        balance = balance - (numberOfQuarters * .25);

        //figure out how many dimes can be given from remaining balance
        numberOfDimes = (int) (balance / .1);
        //subtract value of dimes given from remaining balance
        balance = balance - (numberOfDimes * .1) + .009;

        //figure out how many nickels can be given from remaining balance
        numberOfNickles = (int) (balance / .05);
        //subtract value of nickels from remaining balance - balance should be zero now.
        balance = balance - (numberOfNickles * .05);




        // 1.40 / .25 = (int) 5.6 is 5
        // numberOfQuarters = (int) 1.40 / .25
        // balance  = balance - (5 * .25)
        //balance = 1.40 - 1.25 = .15
        //numberOfDimes = (int) balance (.15) / .1
        // balance = balance - .1 ¢
        //numberOfNickles = (int)
        System.out.println("Here is your change!: ");
        System.out.println(numberOfQuarters + " Quarters");
        System.out.println(numberOfDimes + " Dimes");
        System.out.println(numberOfNickles + " Nickles");
        balanceBigDecimal = new BigDecimal(balance).setScale(2, RoundingMode.DOWN);
        System.out.println(balanceBigDecimal);
        textMenu();
    }

}

