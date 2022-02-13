package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionLogTest {
    TransactionLog transactionLog = new TransactionLog("TestLog.txt");
    File dataFile = new File("TestLog.txt");
    @Before
    public void setUp() {
//We will always check the last line of the log
        String lastLine;
        try (Scanner dataInput = new Scanner(dataFile)) {

            while (dataInput.hasNextLine()) {
                lastLine = dataInput.nextLine();
            }
        } catch(
                    FileNotFoundException e){
                System.out.println("File Does Not Exist");
            }

        }
    @Test
    public void does_logSaleAudit_write_to_TestLog_dot_txt() {

        //Arrange
        Item item = new Candy("Take Five", 0.99);
        String location = "A3";
        double balance = 5.0;
        String expected = "Take Five A3 $5.0 $4.01";
        String lastLine = "";
        //Act
        transactionLog.logSaleAudit(item, location, balance);

        try (Scanner dataInput = new Scanner(dataFile)) {

            while (dataInput.hasNextLine()) {
                lastLine = dataInput.nextLine();
            }
        } catch(
                FileNotFoundException e){
            System.out.println("File Does Not Exist");
        }

        String actual = lastLine.substring(23);

        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void does_logFeedMoneyAudit_write_to_TestLog_dot_txt() {

        //Arrange
        int fedMoney = 10;
        double balance = 10.0;
        String expected = "FEED MONEY: $10.00 $20.0";
        String lastLine = "";
        //Act
        transactionLog.logFeedMoneyAudit(fedMoney, balance);

        try (Scanner dataInput = new Scanner(dataFile)) {

            while (dataInput.hasNextLine()) {
                lastLine = dataInput.nextLine();
            }
        } catch(
                FileNotFoundException e){
            System.out.println("File Does Not Exist");
        }

        String actual = lastLine.substring(23);

        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void does_logGiveChangeAudit_write_to_TestLog_dot_txt() {

        //Arrange
        double balance = 6.95;
        String expected = "GIVE CHANGE: $6.95 $0.00";
        String lastLine = "";
        //Act
        transactionLog.logGiveChangeAudit(balance);

        try (Scanner dataInput = new Scanner(dataFile)) {

            while (dataInput.hasNextLine()) {
                lastLine = dataInput.nextLine();
            }
        } catch(
                FileNotFoundException e){
            System.out.println("File Does Not Exist");
        }

        String actual = lastLine.substring(23);

        //Assert
        Assert.assertEquals(expected, actual);
    }


    }


