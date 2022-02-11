package com.techelevator;

import java.util.Scanner;

public class VendingMachine {
    Scanner scanner =new Scanner(System.in);
    String userInput;
    Inventory inventory;
    double balance = 0;


    public void run() {
        inventory = new Inventory();
        inventory.loadInventory();
        textMenu();
    }

    public void textMenu() {
        System.out.println();
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        // Pretty up!!
        userInput = scanner.nextLine();
        while (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3"))) {
            System.out.println("Invalid Entry, Please Try Again!");
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            // Pretty up!!
            userInput = scanner.nextLine();
        }
        if (userInput.equals("1")) {
            inventory.displayInventory();
            textMenu();
       } else if (userInput.equals("2")) {
            purchaseMenu();
        } else if (userInput.equals("3")) {
            exit();
        }
        //Easter Egg!!
    }
        public void exit() {
            System.out.println("Thanks for Using Vendo-Matic 800!!");
            System.exit(1);

        }
        public void purchaseMenu() {

            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("Current Balance: $" + balance);
            // Pretty up!!
            userInput = scanner.nextLine();
            while (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3"))) {
                System.out.println("Invalid Entry, Please Try Again!");
                System.out.println("(1) Feed Money");
                System.out.println("(2) Select Product");
                System.out.println("(3) Finish Transaction");
                System.out.println("Current Balance: $" + balance);
                // Pretty up!!
                userInput = scanner.nextLine();
            }
            if (userInput.equals("1")) {
                feedMoney();

            } else if (userInput.equals("2")) {
                selectProduct();

            } else if (userInput.equals("3")) {
               finishTransaction();
            }

        }
             public void feedMoney() {
                 System.out.println();
                 System.out.println("Select Currency to Enter");
                 System.out.println("(1) $1.00");
                 System.out.println("(2) $2.00");
                 System.out.println("(3) $5.00");
                 System.out.println("(4) $10.00");
                 System.out.println("(5) Done Feeding Money");
                 System.out.println("Current Balance: $" + balance);
                 // Pretty up!!
                 userInput = scanner.nextLine();
                 while (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4") ||userInput.equals("5"))) {
                     System.out.println("Select Currency to Enter");
                     System.out.println("(1) $1.00");
                     System.out.println("(2) $2.00");
                     System.out.println("(3) $5.00");
                     System.out.println("(4) $10.00");
                     System.out.println("(5) Done Feeding Money");
                     System.out.println("Current Balance: $" + balance);
                     // Pretty up!!
                     userInput = scanner.nextLine();
                 }
                 if (userInput.equals("1")) {
                     balance = balance + 1;
                 } else if (userInput.equals("2")) {
                    balance = balance + 2;
                 } else if (userInput.equals("3")) {
                    balance = balance + 5;
                 } else if (userInput.equals("4")) {
                     balance = balance + 10;
                 } else if (userInput.equals("5")) {
                     purchaseMenu();
                 }
                 feedMoney();
             }

          public void selectProduct() {
              inventory.displayInventory();
              System.out.println("Please Enter an Item Slot");
              userInput = scanner.nextLine();
              while (!inventory.getSlots().contains(userInput)) {
                  System.out.println("Invalid Slot");
                  purchaseMenu();
                  // Pretty up!!
                  userInput = scanner.nextLine();
              }
              if (inventory.checkInventorySlot(userInput) == 0) {
                  System.out.println("SOLD OUT!");
                  purchaseMenu();
              } else if (balance < inventory.getItemAtSlot(userInput).getCost()) {
                  System.out.println("Sorry not enough money to purchase " + inventory.getItemAtSlot(userInput).getName());
                  purchaseMenu();
              } else {
                  System.out.println(inventory.vendItem(userInput));
                  balance = balance - inventory.getItemAtSlot(userInput).getCost();
                  System.out.println("$" + balance);
                  purchaseMenu();
              }

          }
          public void finishTransaction() {
        int numberOfQuarters = 0;
        int numberOfDimes = 0;
        int numberOfNickles = 0;
        numberOfQuarters = (int) (balance / .25);
        balance = balance - (numberOfQuarters * .25);
        numberOfDimes = (int) (balance / .1);
        balance = balance - (numberOfDimes * .1);
        numberOfNickles = (int) (balance / .05);
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
              System.out.println(balance);
textMenu();
}

}

