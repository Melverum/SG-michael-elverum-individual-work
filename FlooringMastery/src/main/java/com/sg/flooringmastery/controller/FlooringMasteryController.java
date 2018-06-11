/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.service.InvalidStateException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.InvalidDateException;
import com.sg.flooringmastery.service.InvalidProductException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Melve
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean production = openConfig();

        do {

            switch (getMenuSelection()) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder(production);
                    break;
                case 3:
                    editOrder(production);
                    break;
                case 4:
                    removeOrder(production);
                    break;
                case 5:
                    exitMessage();
                    System.exit(0);
                default:
                    unknownCommand();
            }

        } while (keepGoing());

        exitMessage();
    }

    private boolean openConfig() {

        boolean production = false;
        try {

            production = service.getConfiguration();

        } catch (FlooringMasteryDaoException e) {
            view.displayErrorMessage(e.getMessage());

        }

        return production;
    }

    private int getMenuSelection() {

        return view.printMenuAndGetSelection();
    }

    private boolean keepGoing() {
        String answer = view.keepGoing();
        return service.validateAnswer(answer);
    }

    private void displayOrders() {

        try {

            LocalDate date = view.getDate();
            List<Order> orders = service.displayOrders(date);
            view.displayOrders(orders);

        } catch (FlooringMasteryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void addOrder(boolean production) {

        view.displayAddBanner();

        try {

            List<Product> products = service.returnProducts();
            Order currentOrder = new Order();
            do {
                currentOrder = view.getUserNameAndArea();
            } while (currentOrder == null);
            boolean validState = false;
            do {

                try {

                    currentOrder = view.getUserState(currentOrder);
                    service.getStateInfo(currentOrder);
                    validState = true;

                } catch (InvalidStateException e) {

                    view.displayErrorMessage(e.getMessage());

                }
            } while (validState == false);

            boolean validProduct = false;

            do {
                try {

                    currentOrder = view.getUserProduct(currentOrder, products);
                    service.getProductInfo(currentOrder, products);
                    validProduct = true;

                } catch (InvalidProductException e) {

                    view.displayErrorMessage(e.getMessage());
                }

            } while (validProduct == false);

            boolean answer = service.validateAnswer(view.placeOrder(currentOrder));
            if (answer == true) {

                service.calculateCost(currentOrder);
                service.determineOrderNumber(currentOrder);
                if (production == true) {

                    service.addOrderToFile(currentOrder);
                }
            } else {
                view.didNotSave();
            }

        } catch (FlooringMasteryDaoException e) {

            view.displayErrorMessage(e.getMessage());

        }
    }

    private void editOrder(boolean production) {

        view.displayEditBanner();

        try {

            LocalDate date = view.getDate();
            int orderNum = view.getOrderNum();
            Order editOrder = service.getOrder(date, orderNum);
            Order editedOrder = view.determineEditedProperty(editOrder);
            if (editedOrder != null) {
                service.getStateInfo(editedOrder);
                List<Product> products = service.returnProducts();
                service.getProductInfo(editedOrder, products);
                service.calculateCost(editedOrder);
                if (production == true) {
                    service.addOrderToFile(editedOrder, date);
                    view.displayEditSuccess(editedOrder);
                }
            }
        } catch (InvalidDateException e) {

            view.displayErrorMessage(e.getMessage());

        } catch (FlooringMasteryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (InvalidProductException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (InvalidStateException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeOrder(boolean production) {

        view.displayRemoveBanner();
        int orderNum = 0;

        try {
            LocalDate date = view.getDate();
            orderNum = view.getOrderNum();
            Order removeOrder = service.getOrder(date, orderNum);
            String answer = view.getDeleteAnswer(removeOrder);
            if ("yes".equals(answer)) {
                if(production == true){
                service.removeOrder(date, orderNum);
                view.removeOrderSuccess(removeOrder);
                }
            }

        } catch (InvalidDateException e) {

            view.displayErrorMessage(e.getMessage());

        }

    }

    private void unknownCommand() {

        view.displayUnknownCommand();

    }

    private void exitMessage() {

        view.displayExitMessage();

    }
}
