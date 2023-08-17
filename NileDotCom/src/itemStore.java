/*  Name:  Alex Spiegl 
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class itemStore extends JFrame {
	
	//Static Variables
		//Width + Height Declaration
	private static final int WIDTH = 600;
	private static final int HEIGHT = 300;
	private order order = new order();
	private ArrayList<item> inventory;
	
		//GUI Creation
			// Create Text Fields
		private JTextField tf_itemID = new JTextField();
		private JTextField tf_quantity = new JTextField();
		private JTextField tf_itemInfo = new JTextField();
		private JTextField tf_totalItems = new JTextField();

		//GUI Creation
			//Create Labels
		JLabel lb_itemID = new JLabel("Enter item ID for Item #1:");
		JLabel lb_quantity = new JLabel("Enter quantitiy for Item #1:");
		JLabel lb_itemInfo = new JLabel("Details for Item #1:");
		JLabel lb_subtotal = new JLabel("Order subtotal for 0 item(s):");
		
		//GUI Creation
			// Create Buttons
		private JButton bt_findItem = new JButton("Find item #1");
		private JButton bt_purchaseItem = new JButton("Purchase item #1");
		private JButton bt_viewOrder = new JButton("View Current Order");
		private JButton bt_finishOrder = new JButton("Complete Order - Check Out");
		private JButton bt_newOrder = new JButton("Start New Order");
		private JButton bt_exit = new JButton("Exit (Close App)");

	public itemStore() throws FileNotFoundException {
		
		//Constructor
			//Panel Setup
		this.getInventoryFromFile();
		Container pane = getContentPane();
		JPanel northPanel = new JPanel(new GridLayout(6,2,8,2));
		northPanel.add(lb_itemID);
		northPanel.add(tf_itemID);
		northPanel.add(lb_quantity);
		northPanel.add(tf_quantity);
		northPanel.add(lb_itemInfo);
		northPanel.add(tf_itemInfo);
		northPanel.add(lb_subtotal);
		northPanel.add(tf_totalItems);
		
		pane.setBackground(Color.black);
		pane.add(northPanel, BorderLayout.CENTER);
		
		//South Panel
			//Holds the required 6 buttons
		JPanel southPanel = new JPanel(new GridLayout(4,2,8,4));
		southPanel.add(bt_findItem);
		southPanel.add(bt_purchaseItem);
		southPanel.add(bt_viewOrder);
		southPanel.add(bt_finishOrder);
		southPanel.add(bt_newOrder);
		southPanel.add(bt_exit);
		
		//Panel
			//Color Settings
		pane.setBackground(Color.black);
		northPanel.setBackground(Color.black);
		southPanel.setBackground(Color.blue);
		lb_itemID.setForeground(Color.yellow);
		lb_quantity.setForeground(Color.yellow);
		lb_itemInfo.setForeground(Color.yellow);
		lb_subtotal.setForeground(Color.yellow);
		
		//Panel
			//Positional Settings
		pane.add(southPanel, BorderLayout.SOUTH);
		centerFrame(WIDTH, HEIGHT);
		
		//Field Deactivation
			//Buttons + Fields
		this.bt_purchaseItem.setEnabled(false);
		this.bt_viewOrder.setEnabled(false);
		this.bt_finishOrder.setEnabled(false);

		this.tf_totalItems.setEnabled(false);
		this.tf_itemInfo.setEnabled(false);
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		
		bt_findItem.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			
			try {
				String itemIDs = tf_itemID.getText();
				System.out.println(itemIDs);
					boolean flag = itemIDs.contains("X");
				
					if (flag == true){
					JOptionPane.showMessageDialog(null, "item ID " + itemIDs + " not in file.");
						} 
					else {
						int itemID = Integer.parseInt(tf_itemID.getText());
						int quantityOfItem = Integer.parseInt(tf_quantity.getText());
				
						//Linear Item Search
						int itemIndex = linearSearch(itemID);
				
						//Item Found
						if(itemIndex != -1) {
					
						//Pass data to order
						item founditem = inventory.get(itemIndex);
					
						if (founditem.getStock().contains("true")) {
							order.setItemInfo(founditem.getitemID() + "", founditem.getName(), founditem.getStock(), founditem.getPrice() + "", quantityOfItem + "", order.getDiscountPercentage(quantityOfItem) + "", order.getTotalDiscount(quantityOfItem, founditem.getPrice()) + "");
							String itemInfo = founditem.getitemID() + founditem.getName() + " $" + founditem.getPrice() + " " + quantityOfItem + " " + order.getDiscountPercentage(quantityOfItem) + "% " + order.getTotalDiscount(quantityOfItem, founditem.getPrice());
							tf_itemInfo.setText(itemInfo);
							bt_purchaseItem.setEnabled(true);
							bt_findItem.setEnabled(false);
							order.setOrderSubtotal(quantityOfItem, founditem.getPrice());
							tf_itemInfo.setEnabled(false);
							tf_totalItems.setEnabled(false);
														} 
							else {
								JOptionPane.showMessageDialog(null, "item ID " + itemID + " Sorry... that item is out of stock, please try another item.");
								}
							}
							else 
							{
							JOptionPane.showMessageDialog(null, "item ID " + itemID + " not in file.");
							}
					}
				}
			catch (NumberFormatException ef){
			JOptionPane.showMessageDialog(null, "Invalid input for number of Line Items or Quantity of Items's");
				}
			}
		});
		
		bt_purchaseItem.addActionListener(new ActionListener(){		
			
			public void actionPerformed(ActionEvent e) {
				
				int quantityOfItem = Integer.parseInt(tf_quantity.getText());
				
				//Item Increment + Subtotal update
				order.setCurrentNumItems(quantityOfItem);
				order.setTotalItems(order.getTotalItems() + 1);
				
				JOptionPane.showMessageDialog(null, "Item #" + order.getTotalItems() + " accepted");
				
				//Transaction
				order.prepareTransaction();
				
				//Add Item to View Order
				order.addToViewOrder(tf_itemInfo.getText());
				
				//Renable Fields + Button
				bt_findItem.setEnabled(true);
				bt_viewOrder.setEnabled(true);
				bt_finishOrder.setEnabled(true);
				bt_purchaseItem.setEnabled(false);
				
				//Renable Fields + Button
					//Change Button Text
				bt_findItem.setText("Find Item #" + (order.getTotalItems() + 1));
				bt_purchaseItem.setText("Purchase Item #" + (order.getTotalItems() + 1));
				
				//Renable Fields + Button
					//TF Updates
				tf_itemID.setText("");
				tf_quantity.setText("");
				tf_totalItems.setText("$" +  new DecimalFormat("#0.00").format(order.getOrderSubtotal()));
				
				//Renable Fields + Button
					//Label Updates
				lb_subtotal.setText("Order subtotal for " + order.getCurrentNumItems() + " item(s)");
				lb_itemID.setText("Enter item ID for Item #" + (order.getTotalItems() + 1) + ":");
				lb_quantity.setText("Enter quantity for Item #" + (order.getTotalItems() + 1) + ":");
				lb_itemInfo.setText("Item #" + (order.getTotalItems() + 1) + " info:");
			}
		});
		
		bt_viewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, order.getViewOrder());
			}
		});
		
		bt_finishOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					order.printTransactions();
					JOptionPane.showMessageDialog(null, order.getFinishOrder());

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				itemStore.super.dispose(); 
			}
		});
		
		bt_newOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemStore.super.dispose();
				try {
					itemStore.main(null);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		bt_exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				itemStore.super.dispose(); 
			}
		});
	}
	
	//Linear Search Method
	public int linearSearch(int itemID) {
		
		for(int i = 0; i < this.inventory.size(); i++) {
			item currentitem = inventory.get(i);
			if(currentitem.getitemID() == itemID)
				return i;
		}
		return -1;
	}
	
	//Frame Position Method
	public void centerFrame(int frameWidth, int frameHeight) {
		
		Toolkit aToolkit = Toolkit.getDefaultToolkit();
		Dimension screen = aToolkit.getScreenSize();
		int xPositionOfFrame = (screen.width - frameWidth) / 2;
		int yPositionOfFrame = (screen.height - frameHeight) / 2;
		setBounds(xPositionOfFrame, yPositionOfFrame, frameWidth, frameHeight);
	}
	
	//File IO Method
	public void getInventoryFromFile() throws FileNotFoundException, NumberFormatException {
		
		//Load Items into ArrayList
		this.inventory = new ArrayList<item>();
		File file = new File("inventory.txt");
		Scanner textFile = new Scanner(file);

		//Scan Inventory into ArrayList
		while (textFile.hasNextLine()) {
			try {
			String item = textFile.nextLine();
			String[] itemInfo = item.split(",");
			
			//Set Fields and Create Item
			item currentitem = new item();
			currentitem.setitemID(Integer.parseInt(itemInfo[0]));
			currentitem.setName(itemInfo[1]);
			currentitem.setStock(itemInfo[2]);
			currentitem.setPrice(Double.parseDouble(itemInfo[3]));

			//Add Item into Inventory
			inventory.add(currentitem);
				} 
			catch (NumberFormatException ef) {
				
				}
			}
			
		//Close File
		textFile.close();
		
		//File Testing
		for (int i = 0; i < inventory.size(); i++) {
			item current = inventory.get(i);
			System.out.println(current.getitemID() + ", " + current.getName() + ", " + current.getPrice());
		}
	}

	
	public ArrayList<item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<item> inventory) {
		this.inventory = inventory;
	}
	
	//Driver Code
	public static void main(String[] args) throws FileNotFoundException {
		itemStore frame = new itemStore();
		frame.setVisible(true); // display window
		frame.setTitle("Nile Dot Com - Spring 2023");
		frame.setLocationRelativeTo(null); // center windows
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close window
	}
}