/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static inventorymanagmentsystemproject.InventoryManagmentSystemProject.isNumeric;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author corte
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */

    Stage stage;
    Parent scene;
    
    
    @FXML
    private javafx.scene.control.TextField partSearchTF;

    @FXML
    private javafx.scene.control.TextField productSearchTF;
    @FXML
    private TableView<Part> partsTV;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partsNameCol;
    @FXML
    private TableColumn<Part, Integer> partsInventoryCol;
    @FXML
    private TableColumn<Part, Double> partsPriceCol;
    @FXML
    private TableView<Product> productTV;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partsTV.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTV.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
    }    

    @FXML
    private void onActionAddPart(javafx.event.ActionEvent event) throws IOException {

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = FXMLLoader.load(getClass().getResource("/View/AddPartMenu.fxml"));
	stage.setScene(new Scene(scene));
	stage.show();
    }

    @FXML
    private void onActionModifyPart(javafx.event.ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ModifyPartMenu.fxml"));
        loader.load();
        
        ModifyPartMenuController MPMController = loader.getController();
        MPMController.sendPart(partsTV.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
	stage.setScene(new Scene(scene));
	stage.show();
        
    }

    @FXML
    private void onActionDeletePart(javafx.event.ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Part?");

        Optional<ButtonType>  result = alert.showAndWait();
        if(result.isPresent()  && result.get() == ButtonType.OK)
        {
            Inventory.deletePart(partsTV.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void onActionAddProduct(javafx.event.ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = FXMLLoader.load(getClass().getResource("/View/AddProductMenu.fxml"));
	stage.setScene(new Scene(scene));
	stage.show();
    }

    @FXML
    private void onActionModifyProduct(javafx.event.ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ModifyProductMenu.fxml"));
        loader.load();
        
        ModifyProductMenuController MPMController = loader.getController();
        MPMController.sendProduct(productTV.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
	stage.setScene(new Scene(scene));
	stage.show();
        
    }

    @FXML
    private void onActionDeleteProduct(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Product?");

        Optional<ButtonType>  result = alert.showAndWait();
        
        if(result.isPresent()  && result.get() == ButtonType.OK)
        {
            Inventory.deleteProduct(productTV.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void onActionExitProgram(javafx.event.ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onActionSearchPartList(javafx.event.ActionEvent event) {
        
        if(!(partSearchTF.getText().isEmpty()))
        {
            ObservableList<Part> tempList = FXCollections.observableArrayList();
            String tempName = partSearchTF.getText();

            if(isNumeric(tempName))
            {
            Part tempPart2 = Inventory.lookupPart(Integer.parseInt(tempName));
            if(tempPart2 != null)
                {
                    if(!tempList.contains(tempPart2))
                    {
                        tempList.add(tempPart2);
                    }
                }
            }else {
		tempList = Inventory.lookupPart(tempName);
            }
            partsTV.setItems(tempList);
        }else {
            partsTV.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    private void onActionSearchProductList(javafx.event.ActionEvent event) {
        
        if(!(productSearchTF.getText().isEmpty()))
        {
            ObservableList<Product> tempList = FXCollections.observableArrayList();
            String tempName = productSearchTF.getText();

            if(isNumeric(tempName))
            {
            Product tempPart2 = Inventory.lookupProduct(Integer.parseInt(tempName));
            if(tempPart2 != null)
                {
                    if(!tempList.contains(tempPart2))
                    {
                        tempList.add(tempPart2);
                    }
                }
            }else {
		tempList = Inventory.lookupProduct(tempName);
            }
            productTV.setItems(tempList);
        }else {
            productTV.setItems(Inventory.getAllProducts());
        }
    }
    
    
}
