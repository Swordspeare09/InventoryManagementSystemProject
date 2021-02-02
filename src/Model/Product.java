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

public class Product {
	
	private ObservableList<Part> assoicatedParts = FXCollections.observableArrayList();
	private int id;
	private String name;
	private double price;
	private int stock;
	private int min;
	private int max;
	
	public Product(int id, String name, double price, int stock, int min, int max) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
		
	}
	
	//Public mutator methods
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	//Public accessor methods
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public int getMin() {
		return this.min;
	}
	
	public int getMax() {
		return this.max;
	}
	
	//Public methods for adding to and removing from a Product's part list
	public void addAssociatedPart(Part part) {
		this.assoicatedParts.add(part);
	}
	
	public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
		if(this.assoicatedParts.contains(selectedAssociatedPart))
		{
			this.assoicatedParts.remove(selectedAssociatedPart);
			return true;
		}else {
			return false;
		}
	}
	
	public ObservableList<Part> getAllAssociatedParts() {
		return this.assoicatedParts;
	}
	
}
