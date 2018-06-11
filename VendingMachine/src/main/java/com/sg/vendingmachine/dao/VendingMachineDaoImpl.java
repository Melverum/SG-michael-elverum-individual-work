/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
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
import java.time.LocalDateTime;

/**
 *
 * @author Melve
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap();
    public static final String ITEM_FILE = "vendingmachineitems.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        loadList();
        return new ArrayList<Item>(items.values());
    }

    @Override
    public void editItem(int quantityNew, Item item) throws VendingMachineDaoException {
        item.setQuantity(quantityNew);
        writeItem();
    }

    @Override
    public void removeItem(String id, Item item) throws VendingMachineDaoException {
        items.remove(id);
        writeItem();
        
    }

    @Override
    public Item getItem(String itemId)throws VendingMachineDaoException {
        loadList();
        return items.get(itemId);
    }

    private void loadList() throws VendingMachineDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));

        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "-_- Could not load items data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(currentTokens[0], currentTokens[1], new BigDecimal(currentTokens[2]));
            currentItem.setQuantity(Integer.parseInt(currentTokens[3]));

            //Put the current students in the map!
            items.put(currentItem.getId(), currentItem);
        }
        //When all finished, close the scanner.
        scanner.close();
    }

    private void writeItem() throws VendingMachineDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException(
                    "Could not save items.", e);
        }

        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {

            out.println(currentItem.getId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getQuantity());

            // force PrintWriter to write line to the file
            out.flush();
        }
        //close for clean up
        out.close();
    }
    
    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachineDaoException {
        
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachineDaoException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
