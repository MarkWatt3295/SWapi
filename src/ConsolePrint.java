import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.http.client.methods.HttpGet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConsolePrint {

	private boolean displayheader = true;

	public Scanner input = new Scanner(System.in);



	public void menuDraw(){
		if(displayheader == false) {
			MenuActions.clrscr();
			
		}

		if(App.networkConnected == true) {

			if(displayheader == true) {
				System.out.println("\n==================================================================================\n");
				System.out.println("Welcome to SWapi Java. Enter a menu option below and press \"Enter\"");
				System.out.println("\n==================================================================================\n");
				displayheader = false;
			}
			AsciiArt.asciiDraw2();
			System.out.println("\n==================================================================================\n");
			System.out.println("1 - All Characters");
			System.out.println("2 - Search Character");
			System.out.println("3 - View Search History");
			System.out.println("4 - View Search History (Tabular)");
			System.out.println("5 - Random Character Info");
			System.out.println("6 - Planet Search");
			System.out.println("7 - Ship Search");
			System.out.println("8 - Star Wars F.O.T.D");
			System.out.println("9 - About");
			System.out.println("");
			System.out.println("10 - Exit");
			System.out.println("11 - Advanced Menu");
			System.out.println("");
			System.out.println("==================================================================================");
			menuStatus();
			System.out.println("==================================================================================\n");


			if(App.networkConnected == true) {
				try {
					Scanner num_reader = new Scanner(System.in);
					int number = num_reader.nextInt();

					if(App.networkConnected == true) {
						Main.menuactions.menuChoice(number);
					}
					else if (App.networkConnected == false){
						App.networkError();
					}
				}
				catch (InputMismatchException e) {
					Main.menuactions.endResult(false, 1, "This is not the number you are looking for....\nPress \"Enter\" to Try Again.\n");


					//menuDraw();
				}
			}
			else {
				App.networkError();
			}
		}



	}

	public void printGuiChoice(){
		if(Main.menuactions.app.initialised == false) {
		Main.menuactions.app.initialiseApp();
		Main.menuactions.app.initialised = true;
		}
		MenuActions.clrscr();
		AsciiArt.asciiDraw();
		
		Logger.appLog("SWapi started at : ");
		System.out.println("\n==================================================================================\n");
		System.out.println("What kind of GUI would you like to use?\n");
		System.out.println("1 - Console Text");
		System.out.println("2 - Swing GUI");
		System.out.println("3 - Exit");
		System.out.println("\n==================================================================================\n");

		int number;
		try {
			Scanner guiChoice = new Scanner(System.in);
			number = guiChoice.nextInt();
			Main.menuactions.guiChooser(number);
		}
		catch (InputMismatchException e) {

			System.err.println();
			Main.menuactions.endResult(false, 2, "*Wave Hand* This is not the number you're looking for....\nEnter a number from the menu.\n\n\"Press Enter to Continue\"");
			
		}

	}

	public void arraylistTable() {

		System.out.println("-----------------------------------------------------------------------------");
		ArrayListTable aat = new ArrayListTable();
		int i = 1;
		for(Person person: 	Main.menuactions.app.People){
			String j = Integer.toString(i);
			aat.row0.add(j);
			aat.row1.add(person.getName());
			aat.row2.add(person.getGender());
			aat.row3.add(person.getSpecies());
			i++;
		}
		aat.buildTable(5);
		System.out.println("-----------------------------------------------------------------------------");

	}


	public void advancedMenuDraw(){
		MenuActions.clrscr();
		if(App.networkConnected == true) {

			AsciiArt.asciiDraw5();
			System.out.println("\n==================================================================================\n");
			System.out.println("1 - Enable Debug Text (Console Debug)");
			System.out.println("2 - Clear Character ArrayList");
			System.out.println("3 - Open SWapi Resource Folder");
			System.out.println("4 - Halt Threads [Running : "+Main.menuactions.app.thread.allow_thread +"]");
			System.out.println("5 - Disable Logs [LogsEnabled : "+Main.menuactions.app.enable_logs + "]");
			System.out.println("6 - Return to Main Menu");
			System.out.println("");
			System.out.println("==================================================================================");
			advancedMenuStatus();
			System.out.println("==================================================================================\n");


			if(App.networkConnected == true) {
				try {
					Scanner num_reader = new Scanner(System.in);
					int number = num_reader.nextInt();

					if(App.networkConnected == true) {
						Main.menuactions.advancedMenuChoice(number);
					}
					else if (App.networkConnected == false){
						App.networkError();
					}
				}
				catch (InputMismatchException e) {
					Main.menuactions.endResult(false, 3, "This is not the number you are looking for....\nPress \"Enter\" to retry.\n");
				
				}
			}
			else if(App.networkConnected == false){
				App.networkError();
			}
		}

	}

	/**
	 * A one line draw to create a status bar to quickly show variables
	 */
	public void menuStatus() {
		System.out.println("       [AppOnline : "+ App.networkConnected +"] | [DebugMode : "+App.debug_mode + "] | [DirectoryExists : "+App.directory_exists + "]");
	}
	/**
	 * A one line draw to create a status bar to quickly show advanced variables
	 */
	public void advancedMenuStatus() {
		System.out.println("       [AppOnline : "+ App.networkConnected +"] | [DebugMode : "+App.debug_mode + "] | [DirectoryExists : "+App.directory_exists + "]");
		System.out.println("       [ThreadsRunning : "+ Main.menuactions.app.thread.allow_thread +"] | [LogsEnabled : "+Main.menuactions.app.enable_logs + "] "
				+ "");
		
	}
	
	public void continueSearch(HttpGet get) {
		if(App.character_count > 0 ) {
		System.out.println("\nThere are : "+App.character_count + " character results.\n\nDo you want to display them all ?\n"
				+ "(This will take a while!)");
		System.out.println("==================================================================================\n");
		System.out.println("Enter \"Y\" / \"N\"");
		System.out.println("\n==================================================================================\n");
		Scanner yes_no = new Scanner(System.in);
		String answer = yes_no.nextLine();
		if(answer.equals("y") || answer.equals("Y")) {
			
			try {
				Main.menuactions.app.countedRequest(get);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(answer.equals("n") || answer.equals("N")) {
			Main.menuactions.console.menuDraw();
		}
		else {
			System.out.println("Invalid response ");
			continueSearch(get);
		}
		}
			
		}
	
	
	public void twoChoices() {
		System.out.println("==================================================================================\n");
		System.out.println("There are 2 options for viewing the Data.\n");
		System.out.println("1 - Basic View");
		System.out.println("2 - Advance View");
		System.out.println("==================================================================================\n");
		System.out.println("Choose a view from above and press \"Enter\"");
		int number;
		try {
			Scanner choice = new Scanner(System.in);
			number = choice.nextInt();
			Main.menuactions.dataChooser(number);
		}
		catch (InputMismatchException e) {

			System.err.println();
			Main.menuactions.endResult(false, 2, "*Wave Hand* This is not the number you're looking for....\nEnter a number from the menu.\n\n\"Press Enter to Continue\"");
			
		}
	}

}
