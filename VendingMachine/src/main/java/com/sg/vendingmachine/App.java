
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Melve
 */
public class App {

    public static void main(String[] args) {

//        // Instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
//        
//        // Instantiate the View and wire the UserIO implementation into it
//        VendingMachineView myView = new VendingMachineView(myIo);
//        
//        // Instantiate the DAO
//        VendingMachineDao myDao = new VendingMachineDaoImpl();
//        //Instantiate the ServiceLayer and wire dao into it.
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayer(myDao);
//        // Instantiate the Controller and wire the Service Layer into it
//        VendingMachineController controller = new VendingMachineController(myService, myView);
//        // Kick off the Controller
        
        
         ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }

}
