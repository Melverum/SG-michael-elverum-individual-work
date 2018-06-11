/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Melve
 */
public class VendingMachineDaoTest {
    private VendingMachineDao dao = new VendingMachineDaoImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
//        List<Item> itemList = dao.getAllItems();
//        for(Item item : itemList){
//            dao.removeItem(item.getId(), item);
//        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllItems() throws Exception {
        int numberOfItems = dao.getAllItems().size();   //Making sure that the Dao is returning the correct number of Items
        assertEquals(5, numberOfItems);
        
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditItem() throws Exception {   //Test to see that the Items edited number gets set correctly.
        
        Item testItem = dao.getItem("A1"); //Known Item
        int editedQuantitity = 2;
        dao.editItem(editedQuantitity, testItem);
        Item fromDao = dao.getItem(testItem.getId());
        assertEquals(testItem.getQuantity(), fromDao.getQuantity());
        
    }
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetItem() throws Exception{
        
        Item testItem = dao.getItem("A1");
        assertEquals(2, testItem.getQuantity());

}  
}