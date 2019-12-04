import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConsolePrint {

	private boolean displayheader = true;

	public Scanner input = new Scanner(System.in);



	public void menuDraw(){
		if(displayheader == false) {
			Main.menuactions.clrscr();
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
			System.out.println("4 - Random Character");
			System.out.println("5 - Advanced Search (All Info)");
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
					Main.menuactions.endResult(false, 1, "This is not the number you are looking for....\nEnter a number from the menu.\n");


					//menuDraw();
				}
			}
			else {
				App.networkError();
			}
		}



	}

	public void printGuiChoice(){
		AsciiArt.asciiDraw();
		Thread thread = new Thread();
		thread.runTask1();
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
			Main.menuactions.endResult(true, 2, "*Wave Hand* This is not the number you're looking for....\nEnter a number from the menu.\n\n\"Press Enter to Continue\"");
		}

	}

	public void arraylistTable() {

		System.out.println("-----------------------------------------------------------------------------");
		ArrayListTable aat = new ArrayListTable();
		
		for(Person person: 	Main.menuactions.app.People){
			
			aat.row1.add(person.getName());
			aat.row2.add(person.getGender());
			aat.row3.add(person.getSpecies());
		}
		aat.buildTable(5);
		System.out.println("-----------------------------------------------------------------------------");

	}


	public void advancedMenuDraw(){
		Main.menuactions.clrscr();
		if(App.networkConnected == true) {

			AsciiArt.asciiDraw5();
			System.out.println("\n==================================================================================\n");
			System.out.println("1 - Enable Debug Text (Console Debug)");
			System.out.println("2 - ");
			System.out.println("3 - ");
			System.out.println("4 - ");
			System.out.println("5 - Return to Main Menu");
			System.out.println("");
			System.out.println("==================================================================================");
			menuStatus();
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

	public void menuStatus() {
		System.out.println("[AppOnline : "+ App.networkConnected +"] | [DebugMode : "+App.debug_mode + "] | [DirectoryExists : "+App.directory_exists + "]");
	}

}
