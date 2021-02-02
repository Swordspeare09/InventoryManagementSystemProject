/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author corte
 */
public class ModifyPartMenuController implements Initializable {

    @FXML
    private RadioButton isInHousePart;
    @FXML
    private ToggleGroup typeOfParts;
    @FXML
    private RadioButton isOutsourcedPart;
    @FXML
    private Label specialLabel;
    @FXML
    private TextField idTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField stockTF;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField maxTF;
    @FXML
    private TextField minTF;
    @FXML
    private TextField specialTF;
    
    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void sendPart(Part part)
    {
        idTF.setText(String.valueOf(part.getId()));
        nameTF.setText(part.getName());
        stockTF.setText(String.valueOf(part.getStock()));
        priceTF.setText(String.valueOf(part.getPrice()));
        maxTF.setText(String.valueOf(part.getMax()));
        minTF.setText(String.valueOf(part.getMin()));
        
        if(part instanceof InHousePart)
        {
            isInHousePart.setSelected(true);
            specialLabel.setVisible(true);
            specialLabel.setText("Machine ID");
            specialTF.setVisible(true);
            specialTF.setText(String.valueOf(((InHousePart) part).getMachineId()));
            
        }else if(part instanceof OutsourcedPart)
        {
            isOutsourcedPart.setSelected(true);
            specialLabel.setVisible(true);
            specialLabel.setText("Company Name");
            specialTF.setVisible(true);
            specialTF.setText(((OutsourcedPart)part).getCompanyNam());
        }
        
        
    }
    @FXML
    private void onActionInhouseRB(ActionEvent event) {
        
        specialLabel.setVisible(true);
        specialLabel.setText("Machine ID");
        specialTF.setVisible(true);
    }

    @FXML
    private void onActionOutsourcedRB(ActionEvent event) {
        
        specialLabel.setVisible(true);
        specialLabel.setText("Company Name");
        specialTF.setVisible(true);
    }

    @FXML
    private void onActionSaveModifiedPart(ActionEvent event) throws IOException {
        try
        {
            int id = Integer.parseInt(idTF.getText());
            String name = nameTF.getText();
            Double price = Double.parseDouble(priceTF.getText());
            int stock = Integer.parseInt(stockTF.getText());
            int min = Integer.parseInt(minTF.getText());
            int max = Integer.parseInt(maxTF.getText());
            
            

            if(min > max)
            {
                //Used to throw Arithmetic Exception
                int a = 100/0;
            }
            
            
            if(isInHousePart.isSelected())
            {
                int machineId = Integer.parseInt(specialTF.getText());
                Inventory.updatePart(id, new InHousePart(id, name, price, stock, min, max, machineId));
            }
            
            if(isOutsourcedPart.isSelected())
            {
                String companyName = specialTF.getText();
                Inventory.updatePart(id, new OutsourcedPart(id, name, price, stock, min, max, companyName));
            }
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
	stage.setScene(new Scene(scene));
	stage.show();
            
        
        }catch(ArithmeticException e )
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Invalid value for max and min fields");
            alert.showAndWait();
        }catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter valid values for new parts");
            alert.showAndWait();
        }
    }

    @FXML
    private void onActionCancelModifiedPart(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel Modifying Part?");

        Optional<ButtonType>  result = alert.showAndWait();
        
        if(result.isPresent()  && result.get() == ButtonType.OK)
        {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
	stage.setScene(new Scene(scene));
	stage.show();
        }
    }
    
}
