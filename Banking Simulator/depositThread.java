/*   
     Name: Alex Spiegl
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 15, 2023 
*/ 

package bankAccount;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class depositThread extends Thread {

	private int num;
	
	public depositThread(int n) {
		num = n;
	}
	
	public void run() {
		
		while(true) {
			
			//When depositing pick random number 1-500 in dollars
			Random rand = new Random();
			int y = rand.nextInt(499) + 1;
			
			//Deposit added to account
			project2Main.bankAccount = project2Main.bankAccount + y;
			
			//Deposit with balance markers
			System.out.println("Agent DT"+ num + " deposits $" + y + "   " + "\t\t\t\t\t\t\t\t" + "(+) Balance is $" + project2Main.bankAccount);
			
			//Deposit flag for amounts larger than 349
			if (y >= 350)
			{
				try {
					System.out.println("\n* * * Flagged Transaction - Deposit Agent WT" + num + " Made a Deposit In Excess of $350.00 USD - See Flagged Transaction Log.\n");
				
					//Write flag to log file
					FileWriter myWriter = new FileWriter("transactions.txt", true);
					myWriter.write("Depositor Agent DT" + num + " issued deposit of $" + y + ".00 at " + java.time.LocalDateTime.now() + "\n\n");	
					myWriter.close();
				}
				
				//Catch for IO exception
				catch (IOException e) {
				      System.out.println("\nAn error occurred while log file.");
				      e.printStackTrace();
				}		
			}
			
			try {
				//Sleep statement for random time
				int sleepTime = rand.nextInt(949) + 51;
				sleep(sleepTime);
				
			}
			
			catch (Exception e) {
				System.out.println("\nThread error occured.");
			    e.printStackTrace();
			}
		}
	}
}
