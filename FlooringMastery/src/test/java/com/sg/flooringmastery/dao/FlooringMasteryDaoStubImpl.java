/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {
    private Order onlyOrder;
    private List<Order> orderList = new ArrayList<>();
    private List<State> stateList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();
    
    public FlooringMasteryDaoStubImpl() {
        onlyOrder = new Order();
        State onlyState = new State();
        Product onlyProduct = new Product();
        onlyOrder.setOrderNumber(1003);
        onlyOrder.setCustomerName("Alex Forsythe");
        onlyOrder.setArea(new BigDecimal("400.00"));
        onlyOrder.setState(onlyState);
        onlyOrder.getState().setStateAbb("MN");
        onlyOrder.getState().setTaxRate(new BigDecimal("6.88"));
        onlyOrder.setProduct(onlyProduct);
        onlyOrder.getProduct().setType("Tile");
        onlyOrder.getProduct().setCostPerSqFt(new BigDecimal("3.50"));
        onlyOrder.getProduct().setLaborCostPerSqFt(new BigDecimal("4.10"));
        onlyOrder.setMaterialCost(new BigDecimal("1000.00"));
        onlyOrder.setLaborCost(new BigDecimal("1200.00"));
        onlyOrder.setTax(new BigDecimal("120.00"));
        onlyOrder.setTotal(new BigDecimal("2320.00"));
        
        
//        productList.add(onlyProduct);
//        stateList.add(onlyState);
        orderList.add(onlyOrder);
    }
    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringMasteryDaoException {
        return orderList;
    }

    @Override
    public List<State> loadStateInfo() throws FlooringMasteryDaoException {
        State mn = new State();
        mn.setStateAbb("MN");
        stateList.add(mn);
        return stateList;
    }

    @Override
    public void removeOrder(Order currentOrder, LocalDate date) throws FlooringMasteryDaoException {
       
    }

    @Override
    public List<Product> loadProductInfo() throws FlooringMasteryDaoException {
        Product tile = new Product();
        tile.setType("tile");
        productList.add(tile);
        return productList;
    }

    @Override
    public void addToFile(Order order, LocalDate date) throws FlooringMasteryDaoException {
        
    }

    @Override
    public String openConfig() throws FlooringMasteryDaoException {
        
        return "testing";
       
    }
    
}
