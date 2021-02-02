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
public class InHousePart extends Part{
	
	private int machineId;

	public InHousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
		super(id, name, price, stock, min, max);

		this.machineId = machineId;
	}
	
	//Mutator Method
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	
	//Accessor Method
	public int getMachineId() {
		return this.machineId;
	}

}
