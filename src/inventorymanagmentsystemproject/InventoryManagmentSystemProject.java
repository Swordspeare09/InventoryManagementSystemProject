/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagmentsystemproject;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author corte
 */
public class InventoryManagmentSystemProject extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        addDefaultValues();
        
        launch(args);
    }
    
    public static void addDefaultValues() {
        
        //Starter Parts for filling table
	InHousePart washer = new InHousePart(0, "washer", .25, 50, 25, 100, 1234);
	InHousePart bolt = new InHousePart(1, "bolt", .50, 60, 30, 90, 1235);
	InHousePart screw = new InHousePart(2, "screw", .25, 100, 50, 200, 1236);
	InHousePart locknut = new InHousePart(3, "locknut", .25, 50, 25, 100, 1237);
	OutsourcedPart mircochip = new OutsourcedPart(4, "microchip", 10.50, 10, 2, 20, "AMD");
        
        Inventory.addPart(washer);
        Inventory.addPart(bolt);
        Inventory.addPart(screw);
        Inventory.addPart(locknut);
        Inventory.addPart(mircochip);
        
        Product junkBundle = new Product(0, "Junk bundle", 5.99, 10, 5, 20);
        Product screwBundle = new Product(1, "Screw bundle", 5.99, 10, 5, 20);
        
        screwBundle.addAssociatedPart(screw);
        screwBundle.addAssociatedPart(bolt);
        screwBundle.addAssociatedPart(washer);
        
        Inventory.addProduct(screwBundle);
        Inventory.addProduct(junkBundle);
        
    }
    public static boolean isNumeric(String num) {
        try {  
            Integer.parseInt(num);  
		return true;
            } catch(NumberFormatException e)
            {  
		return false;  
		  }  
	}
    
    
}
