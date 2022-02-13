package com.techelevator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;

public class InventoryTest {



    @Test
    public void loadInventory_creates_proper_map_from_file_with_4_items(){
        Inventory inventory = new Inventory("testvendingmachine.csv");
        inventory.loadInventory();
        //Arrange
        Map<String, List<Item>> expected = new HashMap<>();
        List<Item> A1 = new ArrayList<>();
        List<Item> B1 = new ArrayList<>();
        List<Item> C1 = new ArrayList<>();
        List<Item> D1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            A1.add(new Chips("Potato Crisps", 3.05));
        }
        for (int i = 0; i < 5; i++) {
            B1.add(new Candy("Moonpie", 1.80));
        }
        for (int i = 0; i < 5; i++) {
            C1.add(new Beverage("Cola", 1.25));
        }
        for (int i = 0; i < 5; i++) {
            D1.add(new Gum("U-Chews", 0.85));
        }
        expected.put("A1",A1);
        expected.put("B1",B1);
        expected.put("C1",C1);
        expected.put("D1",D1);


        boolean equalMaps = true;

        //act
        Map<String, List<Item>> actual = inventory.getCurrentInventory();
        if (actual.size() == expected.size()) {
            for (Map.Entry<String, List<Item>> count : actual.entrySet()) {
                List<Item> actualItemList= actual.get(count.getKey());
                List<Item> expectedItemList= expected.get(count.getKey());
                if(((actualItemList.size()==expectedItemList.size()) &&
                        (actualItemList.get(0).getName().equals(expectedItemList.get(0).getName())) &&
                        ((actualItemList.get(0).getCost()==expectedItemList.get(0).getCost()) &&
                                (actualItemList.get(0).getType().equals(expectedItemList.get(0).getType())))
                )){
                    equalMaps = true;
                }
            }
        } else{
            equalMaps = false;
        }
        if((actual.size() == 0) && (expected.size()==0)){
            equalMaps = true;
        }
        //assert
        Assert.assertEquals(true, equalMaps);

    }


    @Test
    public void loadInventory_creates_empty_map_from_file_with_no_items(){
        Inventory inventory = new Inventory("testemptyvendingmachine.csv");
        inventory.loadInventory();
        //Arrange
        Map<String, List<Item>> expected = new HashMap<>();

        boolean equalMaps = true;

        //act
        Map<String, List<Item>> actual = inventory.getCurrentInventory();
        if (actual.size() == expected.size()) {
            for (Map.Entry<String, List<Item>> count : actual.entrySet()) {
                List<Item> actualItemList= actual.get(count.getKey());
                List<Item> expectedItemList= expected.get(count.getKey());
                if(((actualItemList.size()==expectedItemList.size()) &&
                        (actualItemList.get(0).getName().equals(expectedItemList.get(0).getName())) &&
                        ((actualItemList.get(0).getCost()==expectedItemList.get(0).getCost()) &&
                                (actualItemList.get(0).getType().equals(expectedItemList.get(0).getType())))
                )){
                    equalMaps = true;
                }
            }
        } else{
            equalMaps = false;
        }
        if((actual.size() == 0) && (expected.size()==0)){
            equalMaps = true;
        }
        //assert
        Assert.assertEquals(true, equalMaps);

    }

    @Test
    public void vendItem_returns_correct_item_and_removes_item_from_inventory_list(){
        Inventory inventory = new Inventory("testvendingmachine.csv");
        inventory.loadInventory();
        //Arrange
        //B1|Moonpie|1.80|Candy
        Item expectedItem = new Candy("Moonpie",1.80);
        String expectedItemToString = expectedItem.toString();
        int expectedSlotInventory = 4;
        Item actualItem;

        //act
        actualItem = inventory.vendItem("B1");
        int actualSlotInventory = inventory.checkInventorySlot("B1");
        String actualItemToString = actualItem.toString();

        //assert
        Assert.assertEquals(expectedSlotInventory,actualSlotInventory);
        Assert.assertEquals(expectedItemToString,actualItemToString);

    }





}
