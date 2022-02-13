package com.techelevator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SalesReport {
    Map<String, Integer> salesLog= new HashMap<>();

    public void setUpSalesLog(String itemName) {
        salesLog.put(itemName, 0);
    }

    public void logPurchase(String itemName) {
        int previousItemSales = salesLog.get(itemName);
        salesLog.put(itemName, previousItemSales + 1);
    }

    public void newSalesReport(){
        LocalDateTime now = LocalDateTime.now();
        String fileName = now.toString();
        fileName = fileName.replace(":","-");
        File salesReportFile = new File("salesReport:" + fileName  + ".txt");


        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(salesReportFile))) {

            for(Map.Entry<String,Integer> entry : salesLog.entrySet()){
                dataOutput.println(entry.getKey() + "\\|" + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
