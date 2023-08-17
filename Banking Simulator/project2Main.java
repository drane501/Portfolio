/*   
     Name: Alex Spiegl
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 15, 2023 
*/ 

package bankAccount;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class project2Main {

	public static int bankAccount = 0;
	public static FileWriter writer;
	
	public static void main(String[] args)
	{
		bankAccount = 0;

		//Try to create log file
		try {
		      File obj = new File("transactions.txt");
		      if (obj.createNewFile()) 
		      {
		    	  System.out.println("File created: " + obj.getName());
		      }
		      
		      else 
		      {
		    	  System.out.println("File already exists.");
		      }
		    }
		
		catch (IOException e) 
		{ 
			System.out.println("An error occurred in log file creation.");
			e.printStackTrace();
		}
		
		//Try to create file writer
		try (FileWriter myWriter = new FileWriter("transactions.txt", true)) {
			
			//Create deposit Threads
			depositThread dthread1 = new depositThread(1);
			depositThread dthread2 = new depositThread(2);
			depositThread dthread3 = new depositThread(3);
			depositThread dthread4 = new depositThread(4);
			depositThread dthread5 = new depositThread(5);
			
			//Create withdrawal threads
			withdrawThread wthread1 = new withdrawThread(1);
			withdrawThread wthread2 = new withdrawThread(2);
			withdrawThread wthread3 = new withdrawThread(3);
			withdrawThread wthread4 = new withdrawThread(4);
			withdrawThread wthread5 = new withdrawThread(5);
			withdrawThread wthread6 = new withdrawThread(6);
			withdrawThread wthread7 = new withdrawThread(7);
			withdrawThread wthread8 = new withdrawThread(8);
			withdrawThread wthread9 = new withdrawThread(9);
			withdrawThread wthread10 = new withdrawThread(10);
			
			//Output display
			System.out.print("Deposit Agents"); 
			System.out.print("\t\t\t\t");
			System.out.print("Withdrawal Agents"); 
			System.out.print("\t\t\t\t\t");
			System.out.print("Balance");
			System.out.println("");
			System.out.print("--------------");
			System.out.print("\t\t\t\t");
			System.out.print("-----------------");
			System.out.print("\t\t\t\t");
			System.out.print("-----------------------");
			System.out.println("");
			
			//Run threads
			dthread1.start();
			dthread2.start();
			dthread3.start();
			dthread4.start();
			dthread5.start();
			wthread1.start();
			wthread2.start();
			wthread3.start();
			wthread4.start();
			wthread5.start();
			wthread6.start();
			wthread7.start();
			wthread8.start();
			wthread9.start();
			wthread10.start();
			
			myWriter.close();
		}
		
		catch (IOException e) 
		{
		System.out.println("\nAn error occurred while log file.");
		e.printStackTrace();
		}
			
	}
}