/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melve
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    
    private Item onlyItem;
    private List<Item> itemList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("A11", "Twix", new BigDecimal("2.04"));
        onlyItem.setQuantity(2);
        
        itemList.add(onlyItem);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        return itemList;
    }

    @Override
    public void editItem(int quantity, Item item) throws VendingMachineDaoException {
       
    }

    @Override
    public Item getItem(String itemId) throws VendingMachineDaoException {
        if (itemId.equals(onlyItem.getId())) {
            return onlyItem;
        } else {
            return null;
        }
    }
    
    @Override
    public void removeItem(String itemId, Item item){
        
    }
    
    @Override
    
    public void writeAuditEntry(String entry){
        
    }
    
}
