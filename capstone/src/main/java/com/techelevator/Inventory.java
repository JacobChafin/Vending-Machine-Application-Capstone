package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {
    private Map<String, List<Item>> currentInventory = new HashMap<>(); //Lets us store any of the objects that are type item
    //Map currentInventory is of a string which corresponds to an item slot
    //The list of items is the collections of items at that specific item slot
    private List<String> slots = new ArrayList<>(Arrays.asList("A1" , "A2" , "A3" ,"A4" , "B1" ,"B2" , "B3" , "B4" , "C1" , "C2" , "C3" , "C4", "D1", "D2" , "D3" ,"D4" ));

    public List<String> getSlots() {
        return slots;
    }

    public void loadInventory() {
        String location;
        String name;
        String itemType;
        double cost;


        File dataFile = new File("vendingmachine.csv");
        try (Scanner dataInput = new Scanner(dataFile)) {
            while (dataInput.hasNextLine()) {
                String currentLine = dataInput.nextLine();
                String[] parsedLine = currentLine.split("\\|");
                List<Item> slotInventory = new ArrayList<>();
                Item currentItem = null;
                //A1 -> parseLine[0]
                //Potato -> Crisps parseLine[1]
                //3.05 -> parseLine[2]
                //Chip -> parseLine[3]
                if (parsedLine[3].equals("Chip")) {
                    currentItem = new Chips(parsedLine[1], Double.parseDouble(parsedLine[2]));
                } else if (parsedLine[3].equals("Candy")) {
                    currentItem = new Candy(parsedLine[1], Double.parseDouble(parsedLine[2]));
                } else if (parsedLine[3].equals("Drink")) {
                    currentItem = new Beverage(parsedLine[1], Double.parseDouble(parsedLine[2]));
                } else if (parsedLine[3].equals("Gum")) {
                    currentItem = new Gum(parsedLine[1], Double.parseDouble(parsedLine[2]));
                } else {
                    System.out.println("Invalid item in inventory!");
                    System.exit(1);
                }
                for (int i = 0; i < 5; i++) {
                    slotInventory.add(currentItem);
                }
                currentInventory.put(parsedLine[0], slotInventory);


            }
            //Parse data file into map key
            //Location to Map key
            //Construct item with name and cost
            //Save item into list of items
            //For loop for the quantity
            //Save list of items into Map value
            //Repeat for each line of vendingmachine.csv
            //Write method test for no inventory

        } catch (FileNotFoundException e) {
            System.out.println("File Does Not Exist");
        }
    }

    public void displayInventory(){
        //Print entire inventory to console per unique location of each item
        // Print location, price, name, quantity

        for (int i = 0; i < slots.size() ; i++) {
            List<Item> currentItem = currentInventory.get(slots.get(i));
            if (currentItem.isEmpty()) {
                System.out.println( slots.get(i) + "SOLD OUT!");
            } else {
                double price = currentItem.get(0).getCost();
                String name = currentItem.get(0).getName();
                int quantity = currentItem.size();
                System.out.println(slots.get(i) + ", " + price + ", " + name + ", " + quantity);
            }
        }
    }

    public void updateInventory(String itemSlot ){
        //Pass location into this method, so we know to remove first item from list
        //itemSlot will describe the location
        currentInventory.get(itemSlot).remove(0);
    }

    public int checkInventorySlot(String itemSlot){
        //Returns quantity of items at itemSlot (quantity.size)
     return currentInventory.get(itemSlot).size();

    }
    public Item getItemAtSlot(String itemSlot) {
        return currentInventory.get(itemSlot).get(0);
    }

}
