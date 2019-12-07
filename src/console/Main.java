package console;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

	public static MenuActions menuactions = new MenuActions();
	
	public static void main(String[] args) {
		StartUpCheck();
	}

	public static void StartUpCheck() {
		
		menuactions.app.createDir();
		
		try {
			URL url = new URL("https://swapi.co/");
			URLConnection connection = url.openConnection();
			connection.connect();
			App.networkConnected = true; 	
			MenuActions.clrscr(); //Clear the Windows call form console (e.g C:\Users\ - Just to make it pretty)
			System.out.println("\n==================================================================================");
			System.out.println("Connection Established");
			System.out.println("==================================================================================\n\n");
			
			App.clearLogs();
			menuactions.console.printGuiChoice();

		} catch (MalformedURLException e) {
			App.networkError();
		} catch (IOException e) {
			App.networkError();
		}
	}

	

}
