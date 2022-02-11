package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachine {
    Scanner scanner =new Scanner(System.in);
    String userInput;
    Inventory inventory;


    public void run() {
        inventory = new Inventory();
        inventory.loadInventory();
        textMenu();
    }

    public void textMenu() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        // Pretty up!!
        userInput = scanner.nextLine();
        while (!(userInput.equals("1")||userInput.equals("2") || userInput.equals("3"))) {
            System.out.println("Invalid Entry, Please Try Again!");
            System.out.println("(1) Display Vending Machine Items" );
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            // Pretty up!!
            userInput = scanner.nextLine();
        }
        if (userInput.equals("1")) {

            inventory.displayInventory();

        }

    }

}

