/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.servicelayer.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.servicelayer.VendingMachineInvalidSelectionException;
import com.sg.vendingmachine.servicelayer.VendingMachineServiceLayer;
import com.sg.vendingmachine.servicelayer.VendingMachineValidAmountException;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Melve
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;

    }

    public void run() {
        boolean again = true;
        do {
            try {

                displayWelcomeMessage();
                displayAllItems();
                BigDecimal userMoney = getUsersMoney();
                if (userMoney.intValue() == -1) {
                    displayExitMessage();
                    System.exit(0);
                }
                Item selectedItem = getUserItem();
                getChange(userMoney, selectedItem);
                again = anotherSelection();
//                service.updateItem(selectedItem.getId(), selectedItem);

            } catch (VendingMachineDaoException e) {
                view.displayErrorMessage(e.getMessage());
            }

        } while (again == true);

        displayExitMessage();
    }

    public void displayExitMessage() {
        view.displayExitMessage();
    }

    public void displayWelcomeMessage() {
        view.displayWelcome();
    }

    public void displayAllItems() throws VendingMachineDaoException {
        List<Item> items = service.getAllItems();
        view.displayList(items);

    }

    public boolean anotherSelection() {
        return view.anotherSelection();
    }

    public BigDecimal getUsersMoney() throws VendingMachineDaoException {
        boolean isValid = true;
        BigDecimal userMoney = BigDecimal.ZERO;
        do {
            try {
                userMoney = view.getUserMoney();
                if (userMoney.equals(-1)) {
                    break;
                }
                service.ValidateMoneyAmount(userMoney);
                isValid = true;

            } catch (VendingMachineValidAmountException e) {
                isValid = false;
                view.displayErrorMessage(e.getMessage());

            }
        } while (isValid == false);

        return userMoney;
    }

    public Item getUserItem() throws VendingMachineDaoException {
        String userSelection = "";
//        Item selectedItem;
        Item selectedItem = new Item("", "", BigDecimal.ZERO);
        BigDecimal price = BigDecimal.ZERO;
        boolean isValid;
        do {
            try {
                userSelection = view.getUserSelection();
                selectedItem = service.getItem(userSelection);
                service.validSelection(selectedItem);
                isValid = true;
//           
            } catch (VendingMachineInvalidSelectionException e) {
                isValid = false;
                view.displayErrorMessage(e.getMessage());
            } catch (VendingMachineDaoException e) {
                isValid = false;
                view.displayErrorMessage(e.getMessage());
            }

        } while (isValid == false);

        return selectedItem;
    }

    public void getChange(BigDecimal userMoney, Item selectedItem) throws VendingMachineDaoException {
        Item blankItem = new Item("FAKE", "ITEM", BigDecimal.ZERO);
        try {
            service.hasEnoughMoney(userMoney, selectedItem);
            HashMap<String, Coin> coins = service.getChange(userMoney, selectedItem);
            view.displaySuccessfullDispense(selectedItem);
            view.displayChange(coins);
            service.updateItem(selectedItem.getId(), selectedItem);
        } catch (VendingMachineInsufficientFundsException e) {
            view.displayNotEnoughMoney();
            HashMap<String, Coin> coins = service.getChange(userMoney, blankItem);
            view.displayChange(coins);
        }

    }

}
