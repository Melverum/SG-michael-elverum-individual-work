/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.sg.vendingmachine.dto.Coin;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Melve
 */
public class VendingMachineServiceLayer {

    VendingMachineDao dao;
    HashMap<String, Coin> coins = new HashMap<>();

    public VendingMachineServiceLayer(VendingMachineDao dao) {
        this.dao = dao;
    }

    public List<Item> getAllItems() throws VendingMachineDaoException {
        return dao.getAllItems();
    }

    public Item getItem(String itemId) throws VendingMachineDaoException {
        return dao.getItem(itemId);

    }

    public void updateItem(String id, Item item) throws VendingMachineDaoException {

        Item editedItem = item;
        int quantityInitial = editedItem.getQuantity();
        int quantityNew = quantityInitial - 1;
        editedItem.setQuantity(quantityNew);
        dao.editItem(quantityNew, editedItem);

    }

    public void ValidateMoneyAmount(BigDecimal userMoney) throws VendingMachineValidAmountException {
        try {
            if (BigDecimal.ZERO.equals(userMoney) || userMoney == null) {
                throw new VendingMachineValidAmountException(
                        "Invalid amount. Money must be greater than 0 and not null!");
            }
        } catch (NumberFormatException e) {
            throw new VendingMachineValidAmountException(
                    "Invalid amount. Money must be greater than 0 and not null!");
        }
    }

    public void hasEnoughMoney(BigDecimal userMoney, Item selectedItem) throws VendingMachineInsufficientFundsException {

        BigDecimal price = selectedItem.getPrice();
        if (userMoney.compareTo(price) < 0) {
            throw new VendingMachineInsufficientFundsException(
                    "You do not have enough money!");
        }

    }

    public boolean validSelection(Item selectedItem) throws VendingMachineInvalidSelectionException {

        if (selectedItem != null) {
            if (selectedItem.getQuantity() != 0) {
                return true;
            } else {
                throw new VendingMachineInvalidSelectionException(
                        "Sorry, that item is out of stock!");
            }
        } else {
            throw new VendingMachineInvalidSelectionException(
                    "Sorry that is not a valid selection!");
        }
    }

    public HashMap<String, Coin> getChange(BigDecimal userMoney, Item selectedItem) {

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        Coin quarter = new Coin("Quarter(s)");
        Coin dime = new Coin("Dime(s)");
        Coin nickel = new Coin("Nickel(s)");
        Coin pennie = new Coin("Pennie(s)");
        BigDecimal change = userMoney.subtract(selectedItem.getPrice());
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
            } else if (change.compareTo(new BigDecimal(0)) >= 0) {
                change = change.subtract(new BigDecimal(1));
                pennies++;

            } else {
                break;
            }

        }
        quarter.setNumberOf(quarters);
        dime.setNumberOf(dimes);
        nickel.setNumberOf(nickels);
        pennie.setNumberOf(pennies);

        coins.put(quarter.getName(), quarter);
        coins.put(dime.getName(), dime);
        coins.put(nickel.getName(), nickel);
        coins.put(pennie.getName(), pennie);

        return coins;

    }

}
