package console;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import gui.AppWindow;

public class MenuActions {

	private String character_text; //String used to store the searched character
	private String planet_search;
	public App app = new App();
	ConsolePrint console = new ConsolePrint();



	public void dataChooser(int command) {

		switch (command) {
		case 1:
			app.setAdvanced_print(false);
			break;
		case 2:
			app.setAdvanced_print(true);
			break;
		default:
			endResult(false, 4, command + " is not the dro.. number you're looking for.\nTry again.");
			break;
		}
	}

	public void advancedTableChoice(int command) {

		switch (command) {
		case 1:
			console.arraylistTable();
			break;
		case 2:
			console.advancedArraylistTable();
			break;
		default:
			endResult(false, 4, command + " is not the dro.. number you're looking for.\nTry again.");
			break;
		}
	}

	public void guiChooser(int command) {
		switch (command) {
		case 1:
			clrscr();
			console.menuDraw();
			break;
		case 2:
		
			app.using_gui = true;
			AppWindow.main(null);
			
			break;
		case 3:
			exitApp();
			break;

		default:
			endResult(false, 2, command + " is not the dro.. number you're looking for.\nTry again.");
			break;
		}
	}

	/**
	 * Big Switch statement for the main menu print
	 * 1 - Print all characters
	 * 2 - Search for a character
	 * 3 - Re display character info
	 * 4 - Display info tabular
	 * 5 - Random character
	 * 
	 * @param command
	 */
	public void menuChoice(int command) {

		switch (command) {
		case 1:
			app.swapiCharacterSearch(null, null, "ping");;
			System.out.println("==================================================================================\n");
			System.out.println("All Characters Loaded\n");
			System.out.println("==================================================================================\n");
			endResult(false, 1, "Press \"ENTER\" to continue...");
			break;

		case 2:
			System.out.println("Enter a Character to search for");
			Scanner input = new Scanner(System.in);
			character_text = input.nextLine();
			checkString(character_text);
			console.twoChoices();
			app.swapiCharacterSearch(character_text, null, "search_by_name");
			endResult(false, 1, "Press \"ENTER\" to continue...");
			break;

		case 3:

			if(app.People.size() == 0) {
				System.err.println("You currently haven't searched for anyone.");
			}
			else {
				System.out.println("\nList of all searched Characters : ["+app.People.size()+"]\n");
				app.People.forEach(person -> {
					person.personPrint(app.isAdvanced_print());
					System.out.println("\n");
				});
			}
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;

		case 4:
			console.drawAdvancedTable();
			endResult(false, 1, "Press Enter to Continue\"");
			
			break;
		case 5:
			int r;
			System.out.println("The Force is selecting a Character for you...");

			if(App.character_count > 0) {

				r = App.getRandomNumberInRange(1, App.character_count);
				System.err.println("R is "+r);
				app.swapiCharacterSearch(null, Integer.toString(r), "search_by_number");
			}
			else {
				r = App.getRandomNumberInRange(1, 87);
				System.err.println("R is "+r);
				app.swapiCharacterSearch(null, Integer.toString(r), "search_by_number");
			}
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
		case 6:
			System.out.println("Enter a Planet to search for");
			Scanner planet_input = new Scanner(System.in);
			planet_search = planet_input.nextLine();
			checkString(planet_search);
			app.swapiCharacterSearch(planet_search, null, "planet_search");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
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
			clrscr();
			console.advancedMenuDraw();
			break;

		default:
			System.err.println(command + " is not the dro.. number your looking for.\nTry again.");
			endResult(false, 0, "Press \"ENTER\" to continue...");
			console.menuDraw();
			break;
		}
	}

	public void advancedMenuChoice(int command) {

		switch (command) {
		case 1:
			if(App.debug_mode == false) {
				App.debug_mode = true;
				System.out.println("Debug Mode is now Enabled");
			}
			else if(App.debug_mode == true) {
				App.debug_mode = false;
				System.out.println("Debug Mode is now Disabled");
			}
			endResult(false, 3, "Press \"Enter\" to return to Advanced Menu");
			break;

		case 2:
			System.out.println("Person Array = "+app.People.size()+" Films Array = "+app.Films.size() + "\n");
			app.People.clear();
			app.Films.clear();
			System.out.println("Character Arrays have been Cleared");
			System.out.println("Person Array = "+app.People.size()+" Films Array = "+app.Films.size() + "\n");
			endResult(false, 3, "Press \"Enter\" to return to Advanced Menu");
			break;

		case 3:
			System.out.println("Opening SWapi Folder...");
			Desktop desktop = Desktop.getDesktop();
			File dirToOpen = null;
			try {
				dirToOpen = new File("SWapi");
				try {
					desktop.open(dirToOpen);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalArgumentException iae) {
				System.out.println("File Not Found");
			}
			endResult(false, 3, "Press \"Enter\" to return to Advanced Menu");
			break;
		case 4:
			if(Main.menuactions.app.thread.allow_thread == false) {
				Main.menuactions.app.thread.allow_thread = true;
				System.out.println("Threads Enabled");
			}
			else if(Main.menuactions.app.thread.allow_thread == true) {
				Main.menuactions.app.thread.allow_thread = false;
				System.out.println("Threads Disabled");
			}
			endResult(false, 3, "Press \"Enter\" to return to Advanced Menu");
			break;
		case 5:
			if(Main.menuactions.app.enable_logs == false) {
				Main.menuactions.app.enable_logs = true;
				System.out.println("Logs Enabled");
			}
			else if(Main.menuactions.app.enable_logs == true) {
				Main.menuactions.app.enable_logs = false;
				System.out.println("Logs Disabled");
			}
			endResult(false, 3, "Press \"Enter\" to return to Advanced Menu");
			break;
		case 6:
			clrscr();
			console.menuDraw();
			break;

		default:
			System.err.println("Your path you must decide.\nTry again.");
			endResult(false, 0, "Press \"ENTER\" to continue...");
			console.advancedMenuDraw();
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

	/**
	 * Take the entered string and remove illegal characters.
	 * I swap them for pluses as the request handler can understand them.
	 * They also return better responses.
	 * @param text
	 */
	private void checkString(String text) {
		Logger.appLog("[checkString] Search Query before : "+text);
		text = text.replaceAll("[^a-zA-Z0-9]"," ");
		Logger.appLog("[checkString] Search Query After Replace : "+text);
		text = text.replaceAll(" ","+");
		Logger.appLog("[checkString] Search Query After Replace 2: "+text);
		character_text = text;
	}

	/**
	 * End result clears the screen, redraws menu and shows a message
	 * @param clearscreen - clear the cmd screen
	 * @param menudraw - Draw menu (1 - menu , 2 - gui)
	 * @param message - To be displayed
	 */
	public void endResult(Boolean clearscreen, int menudraw, String message) {
		if(clearscreen == true) {
			clrscr();
		}
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		if(menudraw == 1) {
			console.menuDraw();
		}
		else if(menudraw == 2) {
			console.printGuiChoice();
		}
		else if(menudraw == 3) {
			console.advancedMenuDraw();
		}
		else if(menudraw == 4) {
			console.twoChoices();
		}

	}

	private void exitApp() {
		System.out.println("Exiting App");
		System.exit(0);

	}





}
