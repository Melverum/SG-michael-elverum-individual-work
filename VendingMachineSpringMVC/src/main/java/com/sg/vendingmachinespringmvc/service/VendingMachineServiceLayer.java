/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDaoException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Melve
 */
@Component
public class VendingMachineServiceLayer {

    private boolean firstTime = true;
    private List<Item> items = new ArrayList<>();

    @Inject
    VendingMachineDao dao;
//    HashMap<String, Coin> coins = new HashMap<>();

    public VendingMachineServiceLayer(VendingMachineDao dao) {
        this.dao = dao;
    }

    public List<Item> getAllItems() {

        if (firstTime == true) {
            try {
                items = dao.getAllItems();
                firstTime = false;
            } catch (VendingMachineDaoException e) {
                //Failed to Load the page
            }
        }
        
        return items;
    }

    public Item getItem(int index) {
        Item item = new Item();
        
            item = items.get(index);
            if (item.getQuantity() > 0) {
//                dao.editItem(item.getQuantity() - 1, item);
                return item;
            } else {
                return null;
            }
      
    }

    public void updateItem(Item item) {

        Item editedItem = item;
        int quantityInitial = editedItem.getQuantity();
        int quantityNew = quantityInitial - 1;
        editedItem.setQuantity(quantityNew);
        items.set(Integer.parseInt(item.getId()) - 1, item);

    }

    public String getChange(BigDecimal price, BigDecimal userMoney) {
        String moneyString = "";
        Integer quarters = 0;
        Integer dimes = 0;
        Integer nickels = 0;

        BigDecimal change = userMoney.subtract(price);
        change = change.multiply(new BigDecimal(100));

        while (change.compareTo(BigDecimal.ZERO) > 0) {

            if (change.compareTo(new BigDecimal(25)) >= 0) {
                change = change.subtract(new BigDecimal(25));
                quarters++;
            } else if (change.compareTo(new BigDecimal(10)) >= 0) {
                change = change.subtract(new BigDecimal(10));
                dimes++;
            } else if (change.compareTo(new BigDecimal(5)) >= 0) {
                change = change.subtract(new BigDecimal(5));
                nickels++;
            } else {
                break;
            }

        }

        if (quarters > 0) {
            moneyString += "Quarters: " + quarters + " ";
        }
        if (dimes > 0) {
            moneyString += "Dimes: " + dimes + " ";
        }
        if (nickels > 0) {
            moneyString += "Nickels: " + nickels + " ";
        }

        return moneyString;

    }

}
