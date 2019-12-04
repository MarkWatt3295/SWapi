import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Thread {


	private String task1run;
	private int task1totals = 0;
	private String task1next;
	public boolean write_logs = true;

	public String getTask1run() {
		return task1run;
	}

	public void setTask1run(String task1run) {
		this.task1run = task1run;
	}

	public int getTask1totals() {
		return task1totals;
	}

	public void setTask1totals(int task1totals) {
		this.task1totals = task1totals;
	}

	public String getTask1next() {
		return task1next;
	}

	public void setTask1next(String task1next) {
		this.task1next = task1next;
	}

	public void runTask1() {

		Runnable runnable = new Runnable() {

			public void run() {

				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
				Date dateobj = new Date(); 
				//System.out.println(df.format(dateobj));
				String stamp = df.format(dateobj);

				Date newdate = DateTimeUtils.sumTimeToDate(dateobj, 0, 0, 1);
				task1next = df.format(newdate);
				task1totals++;
				if(write_logs == true) {
					try {
						Logger.threadLog(("Thread 1 Ran at : "+stamp+"\nAppOnline = "+App.networkConnected+", Running again at : "+task1next+"\n"));

					} catch (IOException e) {
						System.err.println("Unable to save Thread Log. Is the location missing?");
						System.err.println("Attempting to recreate Directory....");
						Main.menuactions.app.createDir();
					}
				}

				try {
					URL url = new URL("http://www.google.com");
					URLConnection connection = url.openConnection();
					connection.connect();
					App.networkConnected = true;

				} catch (MalformedURLException e) {
					App.networkConnected = false;
				} catch (IOException e) {
					App.networkConnected = false;
				}

				File f = new File("SWapi");
				if (f.exists() && f.isDirectory()) {
					App.directory_exists = true;
				}
				else {
					App.directory_exists = false;
				}
			}
		};

		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}

}
