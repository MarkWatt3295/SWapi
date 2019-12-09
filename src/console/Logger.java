package console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The logger class creates log entries in a set text file.
 * These log entries contain a time and date stamp.
 * The log entires are stored in a text file and can be read when debugging or finding out
 * what has happened in the app.
 * 
 * There is a main log file and a thread log file.
 * @author Mark
 *
 */
public class Logger {


	/**
	 * Create a log file.
	 * A message passed in gets appended to the end of SWapi_Log.txt
	 * Logs will only be written if enable_logs is true.
	 * @param message - The message you want to log
	 */
	public static void appLog(String message)  { 
		if(Main.menuactions.app.enable_logs == true) {
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
				App.createDir();
			}
		}

	}

	/**
	 * Create a thread log file.
	 * A thread log message gets appended to SWapi_Thread_Log.txt
	 * Logs will only be written if enable_logs is true.
	 * @param message - The message you want to log
	 */
	public static void threadLog(String message) throws IOException { 
		if(Main.menuactions.app.enable_logs == true) {
			try {
				PrintWriter writer = new PrintWriter(new FileWriter("SWapi\\SWapi_Thread_Log.txt", true), true);
				writer.write(message+"\n");
				writer.close();
			} catch (IOException e) {
				System.err.println("Failed to write threadLog");
				e.printStackTrace();
				System.err.println("Unable to save threadLog. Is the location missing?");
				System.err.println("Attempting to recreate Directory....");
				App.createDir();
			}
		}
	}
}


