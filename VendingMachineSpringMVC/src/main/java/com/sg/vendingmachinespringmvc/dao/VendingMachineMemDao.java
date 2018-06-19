/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;


import com.sg.vendingmachinespringmvc.model.Item;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Melve
 */
@Component
public class VendingMachineMemDao implements VendingMachineDao {

    public static List<Item> items = new ArrayList<>();
    public static final String ITEM_FILE = "vendingmachineitems.txt";
    public static final String DELIMITER = "::";
 
//    public VendingMachineMemDao(){
//        items.add(new Item("1", "Snickers", new BigDecimal("1.50"), 7));
//        items.add(new Item("2", "Milky Way", new BigDecimal("1.25"), 4));
//        items.add(new Item("3", "Twix", new BigDecimal("1.50"), 1));
//    }
 

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        items.clear();
        loadList();
        return items;
    }

    @Override
    public void editItem(int quantityNew, Item item) throws VendingMachineDaoException {
        item.setQuantity(quantityNew);
        items.set(Integer.parseInt(item.getId()) - 1, item);
//        writeItem();
    }

//    @Override
//    public void removeItem(String id, Item item) throws VendingMachineDaoException {
//        items.remove(id);
////        writeItem();
//        
//    }

    @Override
    public Item getItem(int index)throws VendingMachineDaoException {
//        loadList();
        
        return items.get(index - 1);
    }

    private void loadList() throws VendingMachineDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
           File f = new File(getClass().getClassLoader().getResource(ITEM_FILE).getFile());
           scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "-_- Could not load items data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;
        Integer counter = 0;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            this.items.add(new Item(currentTokens[0], currentTokens[1], new BigDecimal(currentTokens[2]), Integer.parseInt(currentTokens[3].trim())));
            
            //Put the current students in the map!
            
        }
        //When all finished, close the scanner.
        scanner.close();
    }

//    private void writeItem() throws VendingMachineDaoException {
//
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(ITEM_FILE));
//        } catch (IOException e) {
//            throw new VendingMachineDaoException(
//                    "Could not save items.", e);
//        }
//
//        List<Item> itemList = this.getAllItems();
//        for (Item currentItem : itemList) {
//
//            out.println(currentItem.getId() + DELIMITER
//                    + currentItem.getItemName() + DELIMITER
//                    + currentItem.getPrice() + DELIMITER
//                    + currentItem.getQuantity());
//
//            // force PrintWriter to write line to the file
//            out.flush();
//        }
//        //close for clean up
//        out.close();
//    }
//    
//    public static final String AUDIT_FILE = "audit.txt";

 
}
