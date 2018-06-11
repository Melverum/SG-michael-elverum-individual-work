/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Melve
 */
public interface FlooringMasteryDao {

    public String openConfig()
            throws FlooringMasteryDaoException;

    public List<Order> getOrdersByDate(LocalDate date)
            throws FlooringMasteryDaoException;

    public List<State> loadStateInfo()
            throws FlooringMasteryDaoException;

    public void removeOrder(Order currentOrder, LocalDate date)
            throws FlooringMasteryDaoException;

    public List<Product> loadProductInfo()
            throws FlooringMasteryDaoException;

    public void addToFile(Order order, LocalDate date)
            throws FlooringMasteryDaoException;

}
