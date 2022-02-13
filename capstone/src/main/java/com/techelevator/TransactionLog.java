package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TransactionLog {
    //writing to a file using different methods to record 3 different types of state change, FEED money, Purchase, and Change
    //create a file object, so we can try with resources and write to file
  String fileName;
    File transactionLogFile;
    // represents current date and time which is used in every entry to the log
    LocalDate today = LocalDate.now();
    LocalTime now = LocalTime.now();

    public TransactionLog(String fileName) {
        transactionLogFile = new File(fileName);
        this.fileName = fileName;
    }

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
        // notes for manual construction using second, minutes, and hours adding zeros where needed and AM, PM (12 hour time)
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
// need argument item, location, and balance to write the line that will record a sale
        // reference logFeedMoneyAudit as it works the same but with a different println using an extra argument
        // example : //01/01/2016 12:00:20 PM Crunchie B4 $10.00 $8.50  (balance is manually calculated during println)
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(transactionLogFile, true))) {
            dataOutput.println(civilianTime() + " " + item.getName() + " " + location + " $" + balance + " $" + (balance-item.getCost()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void logFeedMoneyAudit(int fedMoney, Double balance){
        //01/01/2016 12:00:15 PM FEED MONEY: (fedMoney: $5.00, balance(which the updated after the feed): $10.00)
        // how it should look ^^
        // the method that logs when a feed money state change happens saves to file and closes
        // takes 2 arguments fedMoney and balance to write a text line to a document recording the transaction
        //try with resources closes document after it runs
        // We are appending to what is currently there by using the wrapper FileOutputStream and append: true
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(transactionLogFile, true))) {
            // telling the writer what to put on a new line of the txt
            //01/01/2016 12:00:15 PM FEED MONEY: $5.00 $10.00
            dataOutput.println(civilianTime() + " FEED MONEY: $" + fedMoney + ".00 $" + (balance + fedMoney));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     public void logGiveChangeAudit(double balance){
        // reference other audits, this is the same but with one argument "balance"
         //01/01/2016 12:01:35 PM GIVE CHANGE: $7.50 $0.00
         // we cheat and manually set balance to zero but BigDecimal always has other plans
         try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(transactionLogFile, true))) {
             dataOutput.println(civilianTime() + " GIVE CHANGE: $" + balance + " $0.00");

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }






}
