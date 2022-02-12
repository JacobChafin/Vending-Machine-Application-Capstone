package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        // temp. item attributes that allow us to create the item and store it in the map (at given location)
        String location;
        String name;
        String itemType;
        double cost;

// create file object so we can read from it with a scanner (try with resources)
        File dataFile = new File("vendingmachine.csv");
        try (Scanner dataInput = new Scanner(dataFile)) {
            // try with resources closes after it is done
            while (dataInput.hasNextLine()) {
// runs as long as there is a next line
                List<Item> slotInventory = new ArrayList<>();
                //temp variable to hold 5 item objects at each slot (is entered as the map value)
                Item currentItem = null;
// current item = null needed to compile and prevent error
                String currentLine = dataInput.nextLine();
                //at the current line of the txt doc copy the data into a string called current line
                String[] parsedLine = currentLine.split("\\|");
// chop "current line" up into 4 Strings separating by the  | character (using \\ to escape so we can use |)
                //A1|Potato Crisps|3.05|Chip
                //item slot @ parseLine[0] -- "A1"
                //item name @ parseLine[1] -- "Crisps"
                //item cost @ parseLine[2] -- "3.05" is still a string at this point
                //item type @ parseLine[3] -- "Chip"
// 4 level if else logic used to create the correct (child) item by reading element at index[3] (which is the item type)
                // creating new Items using constructor from the item class
                if (parsedLine[3].equals("Chip")) {
                    currentItem = new Chips(parsedLine[1], Double.parseDouble(parsedLine[2])); // makes the price a double (argument needs (string name, double price)
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
                    // add the item we made 5 times to the list using a for loop (condition to stop at 5 loops)
                    slotInventory.add(currentItem);
                }
                currentInventory.put(parsedLine[0], slotInventory);
                // Map.put(slot, list of 5 items)
                // key is location/slot (called using the element at index[0]) add our slot inventory list to the map as value
                 //A1|Potato Crisps|3.05|Chip
                // we do this logic for every line of the txt file (chop, make item object, populate list, add to map)
            }
            //summary
            //Parse data file into map key
            //Location to Map key
            //Construct item with name and cost
            //Save item into list of items
            //For loop for the quantity
            //Save list of items into Map value
            //Repeat for each line of vendingmachine.csv
            //Write method test for no inventory
// required for try with resources in case there is no file to read from
        } catch (FileNotFoundException e) {
            System.out.println("File Does Not Exist");
        }
    }

    public void displayInventory(){
        //Print entire inventory to console per unique location of each item
        // Print location, price, name, quantity
        System.out.println();
        System.out.println("________________________________________________________________________________________________________________________________________________________________________________");
        // using our manual array list found below (slots) to represent our actual slots (a look-up table) || maps are not ordered,so we need this in sequence as a reference to order everything
        //slots = new ArrayList<>(Arrays.asList("A1" , "A2" , "A3" ,"A4" , "B1" ,"B2" , "B3" , "B4" , "C1" , "C2" , "C3" , "C4", "D1", "D2" , "D3" ,"D4" ));
        for (int i = 0; i < slots.size() ; i++) {
            List<Item> currentItemList = currentInventory.get(slots.get(i));
            // temp list (that holds all the objects at current slot)
            // looking up in map what list of items is located at the slot -- then saving it to currentItemList (which is a list of items)
            boolean isEndOfRow = (i==3 || i == 7 || i == 11 || i==15);
            // separate our print rows by A,B,C,D by printing next line at the last item of each row (println A4 at i ==3, println B4 at i ==7, println C4 at i == 11, println D4 at i ==15)
            if(!isEndOfRow){
                // not the end of the row so we print
                if (currentItemList.isEmpty()) {
                    // if the list that we pulled out is empty it is sold out
                    System.out.print("(" + slots.get(i)+ ") SOLD OUT!");
                } else {
                    // drill into list to get the item at index zero (if there is one at zero then we have at least one left)
                    // chaining to get the cost of the item at index zero in the list of items at the current slot we are checking (controlled by the i in the for loop)
                    double price = currentItemList.get(0).getCost();
                    // big decimal bs
                    BigDecimal priceBigDecimal = new BigDecimal(price+.009).setScale(2, RoundingMode.DOWN);
                    // pulling out the name from the item at index 0 of the list of items at the current slot we are checking (controlled by the i in the for loop)
                    String name = currentItemList.get(0).getName();
                    // checks the size of the list of items to see how many item objects there are left at the current slot we are checking (controlled by the i in the for loop)
                    int quantity = currentItemList.size();
                    System.out.print("(" + slots.get(i) + ")" + " Price: $" + priceBigDecimal + " Item: " + name + " Qty: " + quantity + " || ");
                }
            }
            else {
                // is the end of the row so we println
                if (currentItemList.isEmpty()) {
                    System.out.println("(" + slots.get(i)+ ") SOLD OUT!");
                } else {
                    double price = currentItemList.get(0).getCost();
                    BigDecimal priceBigDecimal = new BigDecimal(price+.009).setScale(2, RoundingMode.DOWN);
                    String name = currentItemList.get(0).getName();
                    int quantity = currentItemList.size();
                    System.out.println("(" + slots.get(i) + ")" + " Price: $" + priceBigDecimal + " Item: " + name + " Qty: " + quantity);
                }
            }
        }
        System.out.println("________________________________________________________________________________________________________________________________________________________________________________");
        System.out.println();
    }

    public Item vendItem(String itemSlot ){
        //Pass location into this method, so we know to remove first item from list
        //itemSlot will describe the location
        return currentInventory.get(itemSlot).remove(0);
    }

    public int checkInventorySlot(String itemSlot){
        //Returns quantity of items at itemSlot (quantity.size)
     return currentInventory.get(itemSlot).size();

    }
    public Item getItemAtSlot(String itemSlot) {
        return currentInventory.get(itemSlot).get(0);
    }

}
