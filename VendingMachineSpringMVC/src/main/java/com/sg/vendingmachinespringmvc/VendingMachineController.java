/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Melve
 */
@Controller
public class VendingMachineController {
    boolean valid = true;
    Integer id = 0;
    BigDecimal money = new BigDecimal("0.00");
    String initialMessage = "Please Select An Item!";
    String outgoingMessage = "";
    String change;

    @Inject
    VendingMachineServiceLayer service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        if (outgoingMessage.isEmpty()) {
            model.addAttribute("message", initialMessage);
        } else {
            model.addAttribute("message", outgoingMessage);
        }
        if (id != 0) {
            model.addAttribute("clickedItem", id);
        }
        
        model.addAttribute("change", change);
        model.addAttribute("money", money);
        model.addAttribute("items", service.getAllItems());
        return "index";
    }

    @RequestMapping(value = "/selectItem/{id}", method = RequestMethod.POST)
    public String selectItem(@PathVariable Integer id) {

        this.id = id;

        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney/{money}", method = RequestMethod.POST)
    public String addMoney(@PathVariable String money, Model model) {
        this.change = "";
        if (money.equalsIgnoreCase("dollar")) {
            this.money = this.money.add(new BigDecimal("1.00"));
        } else if (money.equalsIgnoreCase("nickel")) {
            this.money = this.money.add(new BigDecimal(".05"));
        } else if (money.equalsIgnoreCase("dime")) {
            this.money = this.money.add(new BigDecimal(".10"));
        } else if (money.equalsIgnoreCase("quarter")) {
            this.money = this.money.add(new BigDecimal(".25"));
        }

        return "redirect:/";
    }
    
    @RequestMapping(value = "makePurchase/", method = RequestMethod.POST)
    public String badInput(){
        this.outgoingMessage = "Item is Required!";
        return "redirect:/";
    }

    @RequestMapping(value = "makePurchase/{id}", method = RequestMethod.POST)
    public String purchaseItem(@PathVariable String id, HttpServletRequest request) {
        
      
        Integer index = Integer.parseInt(id) - 1;
        Item updatedItem = service.getItem(index);
        
        if (updatedItem == null) {
            outgoingMessage = "OUT OF STOCK!";
            this.valid = false;
        } else {
            if(money.subtract(updatedItem.getPrice()).compareTo(new BigDecimal("0.00")) >= 0){
                this.change = service.getChange(updatedItem.getPrice(), this.money);
                service.updateItem(updatedItem);
                this.money = new BigDecimal("0.00");
                this.id = 0;
                this.valid = true;
                
            }
            else{
                outgoingMessage = "Please Deposit " + updatedItem.getPrice().subtract(this.money).setScale(2, RoundingMode.HALF_UP);
                this.valid = false;
            }
        }
        
        if (this.valid == true){
            outgoingMessage = "Thank You!";
        }
        
        return "redirect:/";
        
    }

    @RequestMapping(value = "getChange", method = RequestMethod.POST)
    public String getChange(BigDecimal money, Model model) {
        money = this.money;
        this.change = service.getChange(new BigDecimal("0.00"), money);
        model.addAttribute("change", this.change);
     
        this.money = new BigDecimal("0.00");
        return "redirect:/";
    }

}
