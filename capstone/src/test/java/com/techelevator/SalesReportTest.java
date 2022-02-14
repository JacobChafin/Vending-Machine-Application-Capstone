package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class SalesReportTest {
    SalesReport salesReport = new SalesReport();

    @Before
    public void setUp() {
        salesReport.setUpSalesLog("Baseball Hat");
        salesReport.setUpSalesLog("Straw hat");
        salesReport.setUpSalesLog("Tophat");
        salesReport.setUpSalesLog("Yarmulke");
        salesReport.setUpSalesLog("Knit hat");
    }

    @Test
    public void logPurchase_properly_increments_single_purchase_count_in_Map_salesLog() {

        //Arrange

        int expected = 1;

        //Act
        salesReport.logPurchase("Tophat");
        Map<String,Integer> actualMap = salesReport.getSalesLog();
        int actual = actualMap.get("Tophat");


        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void newSalesReport_properly_logs_many_purchases_count_in_salesreport_file() {

        //Arrange
        salesReport.logPurchase("Knit hat");
        salesReport.logPurchase("Tophat");
        salesReport.logPurchase("Tophat");
        salesReport.logPurchase("Knit hat");
        salesReport.logPurchase("Yarmulke");
        salesReport.logPurchase("Tophat");
        int expectedNumLines = 5;

    //expected file looks like:
    //
    //Yarmulke|1
    //Tophat|3
    //Straw hat|0
    //Baseball Hat|0
    //Knit hat|2
    //


        //Act
        Boolean actual = true;
        int actualNumLines = 0;

        //generates sales report and opens it back up as a file object.
        File dataFile = new File(salesReport.newSalesReport());

        //verify line-by-line for expected text. if any lines don't match, set actual flag to false.
        //also counts number of lines read, to check if lines are duplicated.
        try (Scanner dataInput = new Scanner(dataFile)) {

            while (dataInput.hasNextLine()) {
                actualNumLines++;
                String currentLine = dataInput.nextLine();
                if (currentLine.contains("Yarmulke") && (!currentLine.endsWith("1"))) {
                    actual = false;
                }
                if (currentLine.contains("Tophat") && (!currentLine.endsWith("3"))) {
                    actual = false;
                }
                if (currentLine.contains("Straw hat") && (!currentLine.endsWith("0"))) {
                    actual = false;
                }
                if (currentLine.contains("Baseball Hat") && (!currentLine.endsWith("0"))) {
                    actual = false;
                }
                if (currentLine.contains("Knit hat") && (!currentLine.endsWith("2"))) {
                    actual = false;
                }
            }

        } catch(
                FileNotFoundException e){
            System.out.println("File Does Not Exist");
        }

        //Assert

        Assert.assertEquals(true, actual);
        Assert.assertEquals(expectedNumLines, actualNumLines);
    }



}
