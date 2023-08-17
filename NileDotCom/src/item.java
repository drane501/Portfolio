/*  Name:  Alex Spiegl 
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 

public class item {
	private int i_ID;
	private double i_Price;
	private String i_Name;
	private String i_Stock;

	//Getters + Setters for itemID
	public int getitemID() {
		return i_ID;
	}
	public void setitemID(int itemID) {
		this.i_ID = itemID;
	}
	
	//Getters + Setters for itemPrice
	public double getPrice() {
		return i_Price;
	}
	public void setPrice(double price) {
		this.i_Price = price;
	}
	
	//Getters + Setters for itemName
	public String getName() {
		return i_Name;
	}
	public void setName(String name) {
		this.i_Name = name;
	}
	
	//Getters + Setters for itemStock
	public String getStock() {
		return i_Stock;
	}
	public void setStock(String Stock) {
		this.i_Stock = Stock;
	}
}
