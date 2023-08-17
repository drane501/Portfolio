/*  Name:  Alex Spiegl 
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class order {

	private double orderSubtotal = 0;
	private double orderTotal = 0;
	private int currentNumItems = 0;
	private int totalItems = 0;
	private double discount1 = 0.9;
	private double discount2 = 0.85;
	private double discount3 = 0.8;
	
	private String filename = "transactions.txt";
	private ArrayList<String> items = new ArrayList<>();
	private StringBuilder viewOrder = new StringBuilder();
	private StringBuilder finishOrder = new StringBuilder();
	
	File file = new File(filename);
	String[] itemInfo = new String[7];
	
	public String getFinishOrder() {
		return this.finishOrder.toString();
	}
	
	
	public void setFinishOrder(String date, String time) {
		this.setOrderTotal();
		this.finishOrder.append("Date: " + date + " " + time);
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Number of line items: " + this.getTotalItems());
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Item# / ID / Title / Price / Qty / Disc %/ Subtotal");
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(this.getViewOrder());
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Order subtotal:   $" + new DecimalFormat("#0.00").format(this.getOrderSubtotal()));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Tax rate:     6%");
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Tax amount:     " + new DecimalFormat("#0.00").format(this.getOrderTotal()-this.getOrderSubtotal()));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Order total:      $" + new DecimalFormat("#0.00").format(this.getOrderTotal()));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append(System.getProperty("line.separator"));
		this.finishOrder.append("Thanks for shopping at the Nile Dot Com!");
	}
	
	public String getViewOrder() {
		return this.viewOrder.toString();
	}
	
	public void addToViewOrder(String order) {
		viewOrder.append(this.getTotalItems() + ". " + order);
		viewOrder.append(System.getProperty("line.separator"));
	}
	
	public String[] getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemID, String title, String stock, String price, String quantityOfItem, String discountPercentage, String totalDiscount) {
		itemInfo[0] = itemID;
		itemInfo[1] = title;
		itemInfo[2] = stock;
		itemInfo[3] = price;
		itemInfo[4] = quantityOfItem;
		itemInfo[5] = discountPercentage;
		itemInfo[6] = totalDiscount;

	}

	public double getTotalDiscount(double quantity, double itemPrice) {
		if(quantity >= 1 && quantity <= 4 )
			return (quantity * itemPrice);
		if(quantity >= 5 && quantity <= 9)
			return (discount1 * (quantity * itemPrice));
		if(quantity >= 10 && quantity <= 14)
			return (discount2 * (quantity * itemPrice));
		if(quantity >= 15)
			return (discount3 * (quantity * itemPrice));
		return 0.0;
	}
	
	public int getDiscountPercentage(int quantity) {
		if(quantity >= 1 && quantity <= 4 )
			return 0; //0% discount
		if(quantity >= 5 && quantity <= 9)
			return 10;//10% discount
		if(quantity >= 10 && quantity <= 14)
			return 15; //15% discount
		if(quantity >= 15)
			return 20; //20% discount
		return 0;
	}
	
	public String viewOrder() {
		return filename;
		
	}
	
	public void prepareTransaction() {
		String lineItem = new String();
		for(int i = 0; i< this.itemInfo.length; i++){
			lineItem += this.itemInfo[i] + ", "; 
		}
		items.add(lineItem);
	}
	
	public void printTransactions() throws IOException {
		Calendar calendar= Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat permutation = new SimpleDateFormat("ddMMyyyyHHmm");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a z");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");	
		
		this.setFinishOrder(dateFormat.format(date), time.format(date));

		if(file.exists() == false) {
			file.createNewFile();
		}

		PrintWriter outputStream = new PrintWriter(new FileWriter(filename, true));

		for(int i = 0; i< this.items.size(); i++){
			outputStream.append(permutation.format(date) + ", ");
			String lineItem = this.items.get(i);
			outputStream.append(lineItem);
			outputStream.append(dateFormat.format(date) + ", ");
			outputStream.append(time.format(date));
			outputStream.println();
		}	
		outputStream.flush();
		outputStream.close();
	}
	
	public int getCurrentNumItems() {
		return currentNumItems;
	}
	public void setCurrentNumItems(int currentNumItems) {
		this.currentNumItems = this.currentNumItems + currentNumItems;
	}
	public double getOrderSubtotal() {
		return orderSubtotal;
	}
	public void setOrderSubtotal(int quantity, double itemPrice) {
		this.orderSubtotal = this.orderSubtotal + this.getTotalDiscount(quantity, itemPrice);
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal() {
		this.orderTotal = this.orderSubtotal + (.06 * this.orderSubtotal);
	}
}