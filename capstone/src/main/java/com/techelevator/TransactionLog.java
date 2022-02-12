package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TransactionLog {
    File transactionLogFile = new File("Log.txt");
    LocalDateTime dateTime = LocalDateTime.now();
    LocalDate today = LocalDate.now();
    LocalTime now = LocalTime.now();

//    public void printDateTime(){
//        //time.getHour()
//        //time.getMinute()
//        //time.getSecond()
//
//        System.out.println("datetime: " + dateTime);
//        System.out.println("today: " + today);
//        System.out.println("now: " + now);
//    }
    public String civilianTime(){
        //01/01/2016 12:00:15 PM
        int convertedHour;
        String convertedMinute = Integer.toString(now.getMinute());
        String convertedSecond = Integer.toString(now.getSecond());
        String amPm;

        if(now.getHour()>12){
            convertedHour = now.getHour()-12;
            amPm = "PM";
        }
        else{
            convertedHour = now.getHour();
            amPm = "AM";
        }

        String convertedConvertedHour = Integer.toString(convertedHour);
        if(convertedHour<10){
            convertedConvertedHour = "0"+convertedHour;
        }

        if(now.getMinute()<10){
            convertedMinute = "0"+now.getMinute();
        }

        if(now.getSecond()<10){
            convertedSecond = "0"+now.getSecond();
        }

        return today.toString() + " " + convertedConvertedHour + ":" + convertedMinute + ":" + convertedSecond + " " + amPm;
    }

    public void logSaleAudit(Item item, String location, Double balance){

        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(transactionLogFile, true))) {
            //01/01/2016 12:00:20 PM Crunchie B4 $10.00 $8.50
            dataOutput.println(civilianTime() + " " + item.getName() + " " + location + " $" + balance + " $" + (balance-item.getCost()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void logFeedMoneyAudit(int fedMoney, Double balance){
        //01/01/2016 12:00:15 PM FEED MONEY: $5.00 $10.00
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(transactionLogFile, true))) {
            dataOutput.println(civilianTime() + " FEED MONEY: $" + fedMoney + ".00 $" + balance);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     public void logGiveChangeAudit(double balance){
         //01/01/2016 12:01:35 PM GIVE CHANGE: $7.50 $0.00
         try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(transactionLogFile, true))) {
             dataOutput.println(civilianTime() + " GIVE CHANGE: $" + balance + " $0.00");

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }






}
