import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {


	public static void appLog(String message)  { 

		if(App.debug_mode == true) {
			System.out.println("\n"+message+"\n");
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
		Date dateobj = new Date(); 
		String stamp = df.format(dateobj);

		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter("SWapi\\SWapi_Log.txt", true), true);
			writer.write("\n[ "+stamp+" ] "+message+"\n");
			writer.close();
		} catch (IOException e) {
			System.err.println("Failed to write appLog");
			e.printStackTrace();
			System.err.println("Unable to save appLog. Is the location missing?");
			System.err.println("Attempting to recreate Directory....");
			Main.menuactions.app.createDir();
		}


	}

	public static void threadLog(String message) throws IOException { 
	
		PrintWriter writer = new PrintWriter(new FileWriter("SWapi\\SWapi_Thread_Log.txt", true), true);
		writer.write(message+"\n");
		writer.close();


	}
}


