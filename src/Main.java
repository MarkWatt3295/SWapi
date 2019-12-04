import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		File file = new File("SWapi\\config.txt");
		 file.getParentFile().mkdir(); 
		    try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to create directory " + file.getParent());
				e.printStackTrace();
			}
		
		StartUpCheck();

	}

	private static void StartUpCheck() {
		try {
			
			URL url = new URL("https://swapi.co/");
			URLConnection connection = url.openConnection();
			connection.connect();
			App.networkConnected = true; 
			System.out.println("\n==================================================================================");
			System.out.println("Connection Established");
			System.out.println("==================================================================================\n\n");
			
			Console console = new Console();
			console.clrscr(); //Clear the Windows call form console (e.g C:\Users\ - Just to make it pretty)
			console.app.clearLogs();
			
			Logger.appLog("SWapi started at : ");
			console.printGuiChoice();

		} catch (MalformedURLException e) {
			networkError();
		} catch (IOException e) {
			networkError();
		}
	}

	public static void networkError() {
		
		
		System.out.println("=====================================================================================================");
		System.out.println("\nIt looks like you dont have an active Network Connection.\nThis App requires a connection in order to access the Star Wars API.");
		System.out.println("=====================================================================================================\n");
		
		Scanner input = new Scanner(System.in); 
		System.out.println("Would you like to retry your connection?  Y / N ? :");
		String reply = input.nextLine();
		if(reply.equals("y") || reply.equals("Y")) {
			System.out.println("\n=====================================================================================================");
			System.out.println("\nRetrying...\n");
			System.out.println("=====================================================================================================");
			main(null);
		}
		else if(reply.equals("n") || reply.equals("N")) {
			
			System.out.println("\n=====================================================================================================");
			System.out.println("Exiting App............ May the force be with you!");
			System.out.println("=====================================================================================================");
			System.exit(0);
			
		}
		
		else {
			
			System.out.println("\n=====================================================================================================");
			System.out.println("There is only 'Y' or 'N'... There is no Try");
			System.out.println("=====================================================================================================\n");
			main(null);
		}
	}

}
