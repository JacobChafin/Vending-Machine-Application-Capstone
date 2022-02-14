package com.techelevator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SalesReport {
    //create map - each entry will hold (String - item name) and (int - # sales of the item)
    Map<String, Integer> salesLog= new HashMap<>();

    public void setUpSalesLog(String itemName) {
        //adds an item name to the map with a count of 0 sales
        //we call this every time we add an item our inventory - see Inventory class loadInventory() method
        salesLog.put(itemName, 0);
    }

    public void logPurchase(String itemName) {
        //logs the purchase of a single item in the map.
        int previousItemSales = salesLog.get(itemName);
        salesLog.put(itemName, previousItemSales + 1);
    }

    public void newSalesReport(){
        LocalDateTime now = LocalDateTime.now();
        String fileName = now.toString();
        fileName = fileName.replace(":","-");
        fileName = fileName.substring(0,19);
        fileName = "SalesReport" + fileName + ".txt";
        System.out.println("Generating sales report, saving to file "+ fileName);
        System.out.println();
        File salesReportFile = new File(fileName);


        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(salesReportFile))) {

            for(Map.Entry<String,Integer> entry : salesLog.entrySet()){
                dataOutput.println(entry.getKey() + "|" + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
