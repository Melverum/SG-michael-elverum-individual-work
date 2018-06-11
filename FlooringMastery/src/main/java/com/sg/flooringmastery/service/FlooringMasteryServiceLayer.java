package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryServiceLayer {

    FlooringMasteryDao dao;
    LocalDate date = LocalDate.now();

    public FlooringMasteryServiceLayer(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    public boolean getConfiguration() throws FlooringMasteryDaoException {
        boolean isProduction = false;

        try {

            String production = dao.openConfig();
            if ("production".equalsIgnoreCase(production)) {
                isProduction = true;
            }
        } catch (FlooringMasteryDaoException e) {

        }

        return isProduction;
    }

    public List<Product> returnProducts() throws FlooringMasteryDaoException {

        List<Product> products = dao.loadProductInfo();
        return products;

    }

    public List<Order> displayOrders(LocalDate date) throws FlooringMasteryDaoException {

        List<Order> orders = dao.getOrdersByDate(date);
        return orders;

    }

    public void calculateCost(Order currentOrder) {

        BigDecimal area = currentOrder.getArea();
        BigDecimal materialCost = (area.multiply(currentOrder.getProduct().getCostPerSqFt())).setScale(2);
        BigDecimal laborCost = (area.multiply(currentOrder.getProduct().getLaborCostPerSqFt()));
        BigDecimal tax = laborCost.add(materialCost).multiply(currentOrder.getState().getTaxRate().movePointLeft(2));
        BigDecimal total = materialCost.add(laborCost).add(tax);

        currentOrder.setMaterialCost(materialCost.setScale(2, RoundingMode.HALF_UP));
        currentOrder.setLaborCost(laborCost.setScale(2, RoundingMode.HALF_UP));
        currentOrder.setTax(tax.setScale(2, RoundingMode.HALF_UP));
        currentOrder.setTotal(total.setScale(2, RoundingMode.HALF_UP));
    }

    public void getStateInfo(Order currentOrder)
            throws FlooringMasteryDaoException, InvalidStateException {

        State currentState = currentOrder.getState();

        List<State> states = dao.loadStateInfo();

        for (int i = 0; i < states.size(); i++) {

            if (currentState.getStateAbb().toUpperCase().equals(states.get(i).getStateAbb())) {
                currentOrder.getState().setTaxRate(states.get(i).getTaxRate());
                break;
            } else if (i == states.size() - 1) {
                throw new InvalidStateException(
                        "Invalid State.");
            }

        }
    }

    public boolean validateAnswer(String answer) {
        boolean keepPlaying = false;
        if ("yes".equalsIgnoreCase(answer)) {
            keepPlaying = true;
        }

        return keepPlaying;
    }

    public void removeOrder(LocalDate removalDate, int orderNum) throws InvalidDateException {

        try {

            List<Order> orders = dao.getOrdersByDate(removalDate);
            for (int i = 0; i < orders.size(); i++) {
                if (orderNum == orders.get(i).getOrderNumber()) {
                    dao.removeOrder(orders.get(i), removalDate);
                }else if(i == orders.size() - 1){
                    throw new InvalidDateException ("Order does not exist");
                }
            }

        } catch (FlooringMasteryDaoException e) {
            throw new InvalidDateException("The date you entered does not contain any orders.");
        }

    }

    public void getProductInfo(Order currentOrder, List<Product> products) throws InvalidProductException {

        Product currentProduct = currentOrder.getProduct();

        for (int i = 0; i < products.size(); i++) {

            if (currentProduct.getType().equalsIgnoreCase(products.get(i).getType())) {
                currentOrder.getProduct().setCostPerSqFt(products.get(i).getCostPerSqFt());
                currentOrder.getProduct().setLaborCostPerSqFt(products.get(i).getLaborCostPerSqFt());
                break;
            } else if (i == products.size() - 1) {
                throw new InvalidProductException(
                        "Invalid Product.");
            }
        }
    }

    public void determineOrderNumber(Order currentOrder) {
        Integer maxNumber = 0;
        try {
            List<Order> orders = dao.getOrdersByDate(date);
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getOrderNumber() > maxNumber) {
                    maxNumber = orders.get(i).getOrderNumber();
                }

            }
            currentOrder.setOrderNumber(maxNumber + 1);

        } catch (FlooringMasteryDaoException e) {
            currentOrder.setOrderNumber(1001);
        }
    }

    public Order getOrder(LocalDate date, int orderNum) throws InvalidDateException {
        Order editedOrder = new Order();
        try {
            List<Order> orders = dao.getOrdersByDate(date);

            for (Order order : orders) {
                if (order.getOrderNumber() == orderNum) {
                    editedOrder = order;
                }
            }

            if (editedOrder.getOrderNumber() == null) {
                throw new InvalidDateException("That order does not exist.");

            }
        } catch (FlooringMasteryDaoException e) {
            throw new InvalidDateException(
                    "Order Number: " + orderNum + " does not exist for that date");
        }

        return editedOrder;
    }

    public void addOrderToFile(Order order) {

        try {
            dao.addToFile(order, date);

        } catch (FlooringMasteryDaoException e) {

        }
    }

    public void addOrderToFile(Order order, LocalDate date) {

        try {

            dao.addToFile(order, date);

        } catch (FlooringMasteryDaoException e) {

        }
    }

}
