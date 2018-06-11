/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
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
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        service = new VendingMachineServiceLayer(dao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(1, service.getAllItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        Item item = service.getItem("A11");
        assertNotNull(item);
        item = service.getItem("A1");
        assertNull(item);
    }

    /**
     * Test of updateItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateItem() throws Exception {
        Item item1 = service.getItem("A11");
        assertNotNull(item1);

        int updateQuantity = 3;
        item1.setQuantity(updateQuantity);
        service.updateItem(item1.getId(), item1);
        assertEquals(2, item1.getQuantity());

    }

    /**
     * Test of ValidateMoneyAmount method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateMoneyAmount() throws Exception {
        BigDecimal userMoney = BigDecimal.ZERO;
        Item item = service.getItem("A11");
        try {
            service.ValidateMoneyAmount(userMoney);
            fail("Expected VendingMachineValidAmountException");
        } catch (VendingMachineValidAmountException e) {
            return;
        }
        
        BigDecimal userMoney2 = new BigDecimal("1.5");
        Item item2 = service.getItem("A11");
        
        try{
            service.ValidateMoneyAmount(userMoney);
        }  catch (VendingMachineValidAmountException e){
            fail("Not Expecting VendingMachineValidAmoutException");
        }

    }

    /**
     * Test of hasEnoughMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testHasEnoughMoney() throws Exception {

        Item item1 = new Item("A4", "MilkyWay", new BigDecimal("1.95"));
        BigDecimal userMoney = new BigDecimal("1.35");

        try {           //Bad Input
            service.hasEnoughMoney(userMoney, item1);
            fail("Expected VeningMachineInsufficientFundsException");
        } catch (VendingMachineInsufficientFundsException e) {
            return;
        }
        
        Item item2 = new Item("A4", "MilkyWay", new BigDecimal("1.95"));
        BigDecimal userMoney2 = new BigDecimal("2.35");
        
        try{      //Good Input
            service.hasEnoughMoney(userMoney2, item2);
        }catch(VendingMachineInsufficientFundsException e){
            fail("Not Expecting VendingMachineInsufficientFundsException");
        }
    }

    /**
     * Test of validSelection method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidSelection() throws Exception {

        String invalidItem = "A2";
        Item testItem = service.getItem(invalidItem);
 
        try {  //Bad Input
            service.validSelection(testItem);
            fail("Expected VendingMachineInvalidSelectionException");
        } catch (VendingMachineInvalidSelectionException e) {
            return;
        }
        
        Item quantity0 = new Item("A7", "Mike", new BigDecimal("1.25"));
        quantity0.setQuantity(0);
        
        try{ //Still Bad Input
            service.validSelection(quantity0);
            fail("Expected VendingMcahineInvalidSelectionException");
        }catch(VendingMachineInvalidSelectionException e){
            return;
        }
        
        String validItem = "A11";
        Item validTestItem = service.getItem(validItem);
        
        try{  //Good Input!
            service.validSelection(validTestItem);
        }catch(VendingMachineInvalidSelectionException e){
            fail("Not expecting VendingMachineInvalidSelectionException");
        }
        
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetChange() {

        try {
            Item testItem = service.getItem("A11");
            BigDecimal userMoney = new BigDecimal("2.45");
            HashMap<String, Coin> coins = service.getChange(userMoney, testItem);
            if (coins.get("Quarter(s)").getNumberOf() != 1) {
                fail("Quarters should have been 1");
            } else if (coins.get("Dime(s)").getNumberOf() != 1) {
                fail("Dimes should have been 1");
            } else if (coins.get("Nickel(s)").getNumberOf() != 1) {
                fail("Nickels should have been 1");
            } else if (coins.get("Pennie(s)").getNumberOf() != 1) {
                fail("Pennies should have been 1");
            }
        } catch (VendingMachineDaoException e) {
            fail("Not expecting VendingMachineDaoException");
        }

    }

}
