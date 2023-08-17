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

public class withdrawThread extends Thread {

	private int num;

	public withdrawThread(int n) {
		num = n;
	}
	
	public void run() {
		
		while(true) {
			
			//When withdrawing pick random number 1-99
			Random ranw = new Random();
			int z = ranw.nextInt(98) + 1;
			
			if (project2Main.bankAccount - z < 0)
			{
				//Withdraw Blocked Insufficient Funds
				System.out.println("\t\t\t\t\t" + "Agent WT" + num + " withdraws $" + z + "   " + "\t\t\t" + "(******) WITHDRAWL BLOCKED - INSUFFICIENT FUNDS!!!");
			} 
			
			//When sufficient funds are present
			else
			{
				project2Main.bankAccount = project2Main.bankAccount - z;
				System.out.println("\t\t\t\t\t" + "Agent WT" + num + " withdraws $" + z + "   " + "\t\t\t" + "(-) Balance is $" + project2Main.bankAccount);
				
				//Withdraw flag for amounts larger than 74
				if (z >= 75)
				{
					try {
					System.out.println("\n* * * Flagged Transaction - Withdrawl Agent WT" + num + " Made a Withdrawl In Excess of $75.00 USD - See Flagged Transaction Log.\n");
					
					//Write flag to log file
					FileWriter myWriter = new FileWriter("transactions.txt", true);
					myWriter.write("\tWithdrawal Agent WT" + num + " issued withdrawal of $" + z + ".00 at " + java.time.LocalDateTime.now() + "\n\n");
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
					int sleepTime = ranw.nextInt(949) + 51;
					sleep(sleepTime);		
				}
				
				catch (Exception e) {
					System.out.println("\nThread error occured.");
				    e.printStackTrace();
				}
			}
		}
	}
}