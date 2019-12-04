import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main2 {

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
		
			URL url = null;
			try {
				url = new URL("https://swapi.co/");
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			URLConnection connection = null;
			try {
				connection = url.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				connection.connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			App.networkConnected = true; 
			
			System.out.println("\n==================================================================================");
			System.out.println("Connection Established");
			System.out.println("==================================================================================\n\n");
			Console console = new Console();
			console.clrscr(); //Clear the Windows call form console (e.g C:\Users\ - Just to make it pretty)
			console.app.clearLogs();
			try {
				Logger.appLog("SWapi started at : ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			console.printGuiChoice();

		
	}

	

}
