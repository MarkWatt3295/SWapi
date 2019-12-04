import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Console {
	private boolean displayheader = true;
	private String character_text;
	public static Scanner input = new Scanner(System.in);
	App app = new App();


	public void menuDraw(){
		if(displayheader == false) {
			clrscr();
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
			System.out.println("3 - Search History");
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
			System.out.println("[ AppOnline : "+App.networkConnected+" ]");
			System.out.println("\n==================================================================================\n");


			if(App.networkConnected == true) {
				try {
					Scanner num_reader = new Scanner(System.in);
					int number = num_reader.nextInt();

					if(App.networkConnected == true) {
						menuChoice(number);
					}
					else if (App.networkConnected == false){
						App.networkError();
					}
				}
				catch (InputMismatchException e) {
					endResult(false, 1, "This is not the number you are looking for....\nEnter a number from the menu.\n");


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
		System.out.println("\n==================================================================================\n");
		System.out.println("What kind of GUI would you like to use?");
		System.out.println("1 - Console Text");
		System.out.println("2 - Swing GUI");
		System.out.println("3 - Exit");
		System.out.println("\n==================================================================================\n");

		int number;
		try {
			Scanner guiChoice = new Scanner(System.in);
			number = guiChoice.nextInt();
			guiChooser(number);
		}
		catch (InputMismatchException e) {

			System.err.println();
			endResult(true, 2, "*Wave Hand* This is not the number you're looking for....\nEnter a number from the menu.\n\n\"Press Enter to Continue\"");
		}

	}

	public void guiChooser(int command) {


		switch (command) {
		case 1:
			clrscr();
			menuDraw();
			break;
		case 2:
			break;
		case 3:
			exitApp();
			break;

		default:
			System.err.println(command + " is not the dro.. number you're looking for.\nTry again.");
			printGuiChoice();
			break;
		}
	}

	public void menuChoice(int command) {

		switch (command) {
		case 1:
			System.out.println("All Characters");
			break;
			
		case 2:
			System.out.println("Enter a Character to search for");
			Scanner input = new Scanner(System.in);
			character_text = input.nextLine();
			checkString(character_text);
			app.swapiCharacterSearch(character_text);
			endResult(false, 1, "Press \"ENTER\" to continue...");
			break;
			
		case 3:
			System.out.println("\nList of all searched Characters : ["+app.People.size()+"]\n");
			if(app.People.size() == 0) {
				System.err.println("You currently haven't searched for anyone.");
			}
			else {
				app.People.forEach(person -> {
					person.personPrint();
					System.out.println("\n");
				});
			}
			menuDraw();
			break;
			
		case 4:
			System.out.println("4 pressed");
			arraylistTable();
			break;
			
		case 9:
			clrscr();
			System.out.println("==================================================================================\n");
			AsciiArt.asciiDraw3();
			System.out.println("\n==================================================================================\n");
			aboutText(true);
			System.out.println("\n");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
			
		case 10:
			endResult(false, 0, "Thank you for using SWapi Java. May the Force be with you.\n\nPress \"Enter\" to Exit");
			exitApp();
			break;
		case 11:
			advancedMenuDraw();
			break;

		default:
			System.err.println(command + " is not the dro.. number your looking for.\nTry again.");
			endResult(false, 0, "Press \"ENTER\" to continue...");
			menuDraw();
			break;
		}
	}

	public void advancedMenuChoice(int command) {

		switch (command) {
		case 1:
			System.out.println("All Characters");
			break;
			
		case 2:
			System.out.println("Enter a Character to search for");
			Scanner input = new Scanner(System.in);
			character_text = input.nextLine();
			checkString(character_text);
			app.swapiCharacterSearch(character_text);
			endResult(false, 1, "Press \"ENTER\" to continue...");
			break;
			
		case 3:
			System.out.println("\nList of all searched Characters : ["+app.People.size()+"]\n");
			if(app.People.size() == 0) {
				System.err.println("You currently haven't searched for anyone.");
			}
			else {
				app.People.forEach(person -> {
					person.personPrint();
					System.out.println("\n");
				});
			}
			menuDraw();
			break;
			
		case 4:
			System.out.println("4 pressed");
			arraylistTable();
			break;
			
		case 9:
			clrscr();
			System.out.println("==================================================================================\n");
			AsciiArt.asciiDraw3();
			System.out.println("\n==================================================================================\n");
			aboutText(true);
			System.out.println("\n");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;

		default:
			System.err.println(command + " is not the dro.. number your looking for.\nTry again.");
			endResult(false, 0, "Press \"ENTER\" to continue...");
			menuDraw();
			break;
		}
	}

	private void arraylistTable() {

		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%5s %20s %10s", "NAME", "GENDER", "SPECIES");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		for(Person person: app.People){
			System.out.format("%5s %10s %10s",
					person.getName(), person.getGender(), person.getSpecies());
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------");

	}

	/**
	 * End result clears the screen, redraws menu and shows a message
	 * @param clearscreen - clear the cmd screen
	 * @param menudraw - Draw menu (1 - menu , 2 - gui)
	 * @param message - To be displayed
	 */
	private void endResult(Boolean clearscreen, int menudraw, String message) {
		if(clearscreen == true) {
			clrscr();
		}
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		if(menudraw == 1) {
			menuDraw();
		}
		else if(menudraw == 2) {
			printGuiChoice();
		}
	}




	private void exitApp() {
		System.out.println("Exiting App");
		System.exit(0);

	}

	public void commaandChoice(String command, String searchquery) {

		JsonObject jsonObject;

		switch (command) {
		case "films":

			break;
		case "planets":

			break;

		default:
			System.out.println(command + " is not a available command");
			break;
		}
	}


	/**
	 * This method is used to clear the Windows Console screen.
	 * It removes all text for a clear black screen.
	 */
	public static void clrscr(){
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
			System.err.println("\nFailed to clear Windows screen\n");
		}
	}

	
	/**
	 * This method will be used to print the About text. Using string return to grab text when in GUI
	 * and sytem print to display in cosole.
	 * @param print - True to enable console print.
	 * @return - Returns the about text.
	 */
	private String aboutText(boolean print) {
		String text = "Along time ago, In a Galaxy Far, Far away....";
		String text2 = "I was tasked with creating a program to interact with the Star Wars API.\nThis Java program is my implementation of this";
		if(print == true) {
			System.out.println(text+"\n\n"+text2);
		}
		return text + "\n\n"+ text2;
		
	}
	
	private void checkString(String text) {
		System.out.println("Search Query before : "+text);
		text = text.replaceAll("[^a-zA-Z0-9]"," ");
		System.out.println("Search Query After : "+text);
		text = text.replaceAll(" ","+");
		System.out.println("Search Query2 After : "+text);
		character_text = text;
	}

	private void advancedMenuDraw(){
		
		if(App.networkConnected == true) {
			
			AsciiArt.asciiDraw4();
			System.out.println("\n==================================================================================\n");
			System.out.println("1 - Enable Debug Text (Console Debug)");
			System.out.println("2 - ");
			System.out.println("3 - ");
			System.out.println("4 - ");
			System.out.println("5 - Return to Main Menu");
			System.out.println("");
			System.out.println("[ AppOnline : "+App.networkConnected+" ]");
			System.out.println("\n==================================================================================\n");


			if(App.networkConnected == true) {
				try {
					Scanner num_reader = new Scanner(System.in);
					int number = num_reader.nextInt();

					if(App.networkConnected == true) {
						menuChoice(number);
					}
					else if (App.networkConnected == false){
						App.networkError();
					}
				}
				catch (InputMismatchException e) {
					endResult(false, 1, "This is not the number you are looking for....\nEnter a number from the menu.\n");
				}
			}
			else if(App.networkConnected == false){
				App.networkError();
			}
		}

	}

}
