/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Melve
 */
public class FlooringMasteryDaoImpl implements FlooringMasteryDao {

    private List<State> statesList = new ArrayList();
    private Map<String, Product> products = new HashMap<>();
    private List<Order> orderList = new ArrayList();
    private Map<String, State> states = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();
    public static final String STATES_FILE = "states.txt";
    public static final String PRODUCTS_FILE = "products.txt";
    public static final String CONFIG_FILE = "config.txt";
    public static final String DELIMITER = ",";

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringMasteryDaoException {

        String ordersByDate = "Orders_" + date.toString() + ".txt";
        orders.clear();
        loadOrders(ordersByDate);
        return new ArrayList<Order>(orders.values());

    }

    @Override
    public void addToFile(Order order, LocalDate date) throws FlooringMasteryDaoException {
        Order newOrder = orders.put(order.getOrderNumber(), order);
        writeFile(date);
    }

    @Override
    public void removeOrder(Order currentOrder, LocalDate date) throws FlooringMasteryDaoException {

        orders.remove(currentOrder.getOrderNumber(), currentOrder);
        writeFile(date);

    }

    @Override
    public String openConfig() throws FlooringMasteryDaoException {
        return loadConfig();
    }

    @Override
    public List<State> loadStateInfo() throws FlooringMasteryDaoException {
        loadStates();
        return new ArrayList<State>(states.values());

    }

    @Override
    public List<Product> loadProductInfo() throws FlooringMasteryDaoException {
        loadProducts();
        return new ArrayList<Product>(products.values());
    }

    private void writeFile(LocalDate date) throws FlooringMasteryDaoException {
        File file = new File("Orders_" + date.toString() + ".txt");
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save items.", e);
        }

        orderList = this.getOrdersByDate(date);

        orderList.stream().map((currentOrder) -> {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState().getStateAbb() + DELIMITER
                    + currentOrder.getState().getTaxRate() + DELIMITER
                    + currentOrder.getProduct().getType() + DELIMITER
                    + currentOrder.getProduct().getCostPerSqFt() + DELIMITER
                    + currentOrder.getProduct().getLaborCostPerSqFt() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTotal());
            return currentOrder;
        }).forEachOrdered((_item) -> {
            // force PrintWriter to write line to the file
            out.flush();
        });
        //close for clean up
        out.close();

    }

    private String loadConfig() throws FlooringMasteryDaoException {
        Scanner scanner;
        String currentLine = "";
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(CONFIG_FILE)));
        } catch (FileNotFoundException e) {

            throw new FlooringMasteryDaoException("");
        }
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

        }

        return currentLine;

    }

    private void loadOrders(String date) throws FlooringMasteryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(date)));

        } catch (FileNotFoundException e) {

            throw new FlooringMasteryDaoException("");

        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order();

            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);

            State currentState = new State();
            currentState.setStateAbb(currentTokens[2]);
            currentState.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setState(currentState);

            Product currentProduct = new Product();
            currentProduct.setType(currentTokens[4]);
            currentProduct.setCostPerSqFt(new BigDecimal(currentTokens[5]));
            currentProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[6]));
            currentOrder.setProduct(currentProduct);

            currentOrder.setArea(new BigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotal(new BigDecimal(currentTokens[11]));

            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        //When all finished, close the scanner.
        scanner.close();

    }

    private void loadStates() throws FlooringMasteryDaoException {

        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(STATES_FILE)));

        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load items data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            State currentState = new State();

            currentState.setStateAbb(currentTokens[0]);
            currentState.setTaxRate(new BigDecimal(currentTokens[1]));

            states.put(currentState.getStateAbb(), currentState);
        }
        //When all finished, close the scanner.
        scanner.close();
    }

    private void loadProducts() throws FlooringMasteryDaoException {

        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));

        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load items data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product();

            currentProduct.setType(currentTokens[0]);
            currentProduct.setCostPerSqFt(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
            products.put(currentProduct.getType(), currentProduct);
        }
        //When all finished, close the scanner.
        scanner.close();
    }

}
