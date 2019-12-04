import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {


	public static void appLog(String message) throws IOException { 
		
		if(App.debug_mode == true) {
			System.out.println(message);
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
		Date dateobj = new Date(); 
		String stamp = df.format(dateobj);
		
		PrintWriter writer = new PrintWriter(new FileWriter("SWapi\\SWapi_Log.txt", true), true);
		writer.write(message+"[ "+stamp+" ]\n");
		writer.close();

	}
	
	public static void threadLog(String message) throws IOException { 
		PrintWriter writer = new PrintWriter(new FileWriter("SWapi\\SWapi_Thread_Log.txt", true), true);
		writer.write(message+"\n");
		writer.close();
		

	}
}


