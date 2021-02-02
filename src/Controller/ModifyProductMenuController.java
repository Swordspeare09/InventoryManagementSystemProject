/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import static inventorymanagmentsystemproject.InventoryManagmentSystemProject.isNumeric;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author corte
 */
public class ModifyProductMenuController implements Initializable {

    @FXML
    private TextField partSearchTF;
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
    private TableView<Part> partsTV;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Part> assocPartTV;
    @FXML
    private TableColumn<Part, Integer> assocPartIDCol;
    @FXML
    private TableColumn<Part, String> assocPartNameCol;
    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    private ObservableList<Part> tempPartsList =  FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partsTV.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));     
    }    
    
    public void sendProduct(Product product)
    {
        idTF.setText(String.valueOf(product.getId()));
        nameTF.setText(product.getName());
        stockTF.setText(String.valueOf(product.getStock()));
        priceTF.setText(String.valueOf(product.getPrice()));
        maxTF.setText(String.valueOf(product.getMax()));
        minTF.setText(String.valueOf(product.getMin()));
    
        tempPartsList = product.getAllAssociatedParts();
        assocPartTV.setItems(tempPartsList);
        
    }

    @FXML
    private void onActionSearchPartList(ActionEvent event) {
        
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
    private void onActionAddAssocPart(ActionEvent event) {
        tempPartsList.add(partsTV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onActionDeleteAssocPart(ActionEvent event) {
        tempPartsList.remove(assocPartTV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onActionCancelModifiedProduct(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel Modifying Product?");

        Optional<ButtonType>  result = alert.showAndWait();
        
        if(result.isPresent()  && result.get() == ButtonType.OK)
        {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
	stage.setScene(new Scene(scene));
	stage.show();
        }
    }

    @FXML
    private void onActionSaveModifiedProduct(ActionEvent event) throws IOException {
        
        try
        {
        int id = Integer.parseInt(idTF.getText());
        String name = nameTF.getText();
        Double price = Double.parseDouble(priceTF.getText());
        //Default value for new product will be set to 0
        if(stockTF.getText().isEmpty())
        {
            stockTF.setText("0");
        }
        int stock = Integer.parseInt(stockTF.getText());
        
        int min = Integer.parseInt(minTF.getText());
        int max = Integer.parseInt(maxTF.getText());
        
        if(min > max)
        {
            //Used to throw Arithmetic Exception
            int a = 100/0;
        }
        
        if(tempPartsList.isEmpty())
        {
            //Used to throw NullPointer Exception
            String x = null;
            System.out.println(x.charAt(0));
        }
        
        Product tempProduct = new Product(id, name, price, stock, min, max);
        
        tempPartsList.forEach((Part) -> {
            
            tempProduct.addAssociatedPart(Part);
            
        });
        
        Inventory.updateProduct(id, tempProduct);
        
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
            alert.setContentText("Please enter valid values for new product");
            alert.showAndWait();
        }catch(NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Product must have at least one part assoicated with it");
            alert.showAndWait();
        }
    }
    
    
}
