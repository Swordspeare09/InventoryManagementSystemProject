/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author corte
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
	
	private static ObservableList<Part> allParts = FXCollections.observableArrayList();
	private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
        private static int partsId = 0;
        private static int productsId = 0;
        
        public static void addPart(Part newPart) {
                allParts.add(newPart);
                partsId++;
        }
	
	public static void addPart(InHousePart newPart) {
		allParts.add(newPart);
                partsId++;
	}
	
	public static void addPart(OutsourcedPart newPart) {
		allParts.add(newPart);
                partsId++;
	}
	
	public static void addProduct(Product newProduct) {
		allProducts.add(newProduct);
                productsId++;
	}
	
	public static Part lookupPart(int partId) {
		for(Part p : allParts)
		{
			if(p.getId() == partId)
			{
				return p;
			}
		}
		return null;
	}
	
	public static Product lookupProduct(int productId) {
		for(Product p : allProducts)
		{
			if(p.getId() == productId)
			{
				return p;
			}
		}
		return null;
	}
	
	public static ObservableList<Part> lookupPart(String partName) {
		
		ObservableList<Part> tempList = FXCollections.observableArrayList();
		
		for(Part p : allParts)
		{
			if(p.getName().toLowerCase().contains(partName.toLowerCase()))
			{
				tempList.add(p);
			}
		}
		if(!tempList.isEmpty())
		{
			return tempList;
		}else {
			return null;
		}
	}
	
	public static ObservableList<Product> lookupProduct(String productName) {
		
		ObservableList<Product> tempList = FXCollections.observableArrayList();
		
		for(Product p : allProducts)
		{
			if(p.getName().toLowerCase().contains(productName.toLowerCase()))
			{
				tempList.add(p);
			}
		}
		
		if(!tempList.isEmpty())
		{
			return tempList;
		}else {
			return null;
		}
	}
	public static void updatePart(int index, Part selectedPart) {
		
		allParts.set(index, selectedPart);	
		
	}
	
	public static void updateProduct(int index, Product newProduct) {
		allProducts.set(index, newProduct);
	}
	
	public static boolean deletePart(Part selectedPart) {
		return allParts.remove(selectedPart);
	}
	
	public static boolean deleteProduct(Product selectedProduct) {
                return allProducts.remove(selectedProduct);
	}
        
	public static ObservableList<Part> getAllParts() {
		return allParts;
	}
	public static int getNewPartId() {
		return partsId;
	}
	public static int getNewProductsId() {
		return productsId;
	}

	public static ObservableList<Product> getAllProducts() {
		return allProducts;
	}
}