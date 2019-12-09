package console;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import gui.AppWindow;

/**
 * This class is used to control what happens with passed data and
 * what the different menus should do.
 * @author Mark
 *
 */
public class MenuActions {

	private String character_text; //String used to store the searched character
	private String planet_search, ship_search, vehicle_search; //String to hold searchterms
	
	public App app = new App();
	public ConsolePrint console = new ConsolePrint();



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
		
			App.using_gui = true;
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
	 * 6 - Planet search
	 * 7 - Ship Search
	 * 8 - Vehicle Search
	 * 9 - Fact of the day (not implemented)
	 * 10 - About
	 * 11 - Exit
	 * 12 - Advanced Menu
	 * 
	 * @param command
	 */
	public void menuChoice(int command) {

		switch (command) {
		case 1:
			App.disable_midis = true; //Disable midi print out (as it spans console)
			app.swapiCharacterSearch(null, null, "ping");;
			System.out.println("==================================================================================\n");
			System.out.println("All Characters Loaded\n");
			System.out.println("==================================================================================\n");
			App.disable_midis = false; //Enable midi print out
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
				Logger.appLog("Random number is is "+r);
				app.swapiCharacterSearch(null, Integer.toString(r), "search_by_number");
			}
			else {
				r = App.getRandomNumberInRange(1, 87);
				Logger.appLog("Random number is is "+r);
				app.swapiCharacterSearch(null, Integer.toString(r), "search_by_number");
			}
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
			
		case 6:
			System.out.println("Enter a Planet to search for");
			Scanner planet_input = new Scanner(System.in);
			planet_search = planet_input.nextLine();
			String planet = checkString(planet_search);
			app.swapiCharacterSearch(planet, null, "planet_search");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
		case 7:
			System.out.println("Enter a Spaceship to search for");
			Scanner ship_input = new Scanner(System.in);
			ship_search = ship_input.nextLine();
			String ship = checkString(ship_search);
			app.swapiCharacterSearch(ship, null, "ship_search");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
		case 8:
			System.out.println("Enter a Vehicle to search for");
			Scanner vehicle_input = new Scanner(System.in);
			vehicle_search = vehicle_input.nextLine();
			String vehicle = checkString(vehicle_search);
			app.swapiCharacterSearch(vehicle, null, "vehicle_search");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
		case 9:
			clrscr();
			System.out.println("==================================================================================\n");
			AsciiArt.sadAscii();
			System.out.println("\n==================================================================================\n");
			System.out.println("Star Wars F.O.T.D (fact of the day) was a planned feature. The feature would get\n"
					+ "a random star wars fact from a star wars facts api and display the message on startup.\n");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
		case 10:
			clrscr();
			System.out.println("==================================================================================\n");
			AsciiArt.asciiDraw3();
			System.out.println("\n==================================================================================\n");
			aboutText(true);
			System.out.println("\n");
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;

		case 11:
			endResult(false, 0, "Thank you for using SWapi Java. May the Force be with you.\n\nPress \"Enter\" to Exit");
			exitApp();
			break;
		case 12:
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
			openSwapiFolder();
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
	 * Open the swapi resource folder
	 */
	public static void openSwapiFolder() {
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
	public String aboutText(boolean print) {
		String text = "                    A B O U T   S W A P I   J A V A \n"
				+"==================================================================================\n"
				+"Along time ago, In a Galaxy Far, Far away....\n\n"
				
				+ "I was tasked with creating a program to interact with the Star Wars API."
				+ "\nThis Java program is my implementation of this.\n"
				+ "\nI have created a working Console Applicationthat allows a user to search for various\n"
				+ "things in the Star Wars Universe using the Star Wars API and entering commands into\nthe"
				+ "console app. In addition to this I have also made a Swing GUI that allows the\n"
				+ "user to enter searches graphically.\n\n\n"
				+ "This application was written by Mark Watt \n\n"
				+"==================================================================================\n"
				+"                       T H E   C O N S O L E   A P P \n"
				+"==================================================================================\n"
				+ "The Console app allows the user to enter a series of commands by using printed\n"
				+ "menus (e.g. press 1 and hit Enter). The user can list all Characters in the API,\n"
				+ "Search for individual characters by name and also search Vehicles, Planets and\n"
				+ "Spaceships by name.\n\n"
				+"==================================================================================\n"
				+"                           T H E   G U I    A P P \n"
				+"==================================================================================\n"
				+ "The GUI offers the same functionality (minus displaying all characters) but with\n"
				+ "a graphical user interfce. The user can press buttons to interact with what they\n"
				+ "want.\n\n"
				+"==================================================================================\n"
				+"                         P L A N N E D   F E A T U R E S \n"
				+"==================================================================================\n"
				+ "I have thouroughly enjoyed working on this little project. I have a lot of ideas\n"
				+ "and plans which I will continue working on.\n"
				+ "This is the first API I have properly interacted with and found it very intuative.\n"
				+ "Once I get to a stage in the app where I have made everything I have wanted,\n"
				+ "I think I will try and improve on the API interaction in another programming\nlanguage."
				+ "\n"
				+ "Here are a couple of Features I plan to add as I further develop SWapi Java.\n"
				
				+ "\n\n"
				+ "FUTURE FEATURES : \n\n"
				+ "- Refactor spaghetti Code."
				+ "- Combine object (Ships and Vehicles).\nVehicles should inherit Ships.\n\n"
				+ "- Improve search checks."
				+ "\n  Currently only a characer search is checked.\n"
				+ "  When a user enters a character to search for, or character data is recieved\n"
				+ "  in a response, the search is first checked against local data before a GET\n"
				+ "  is done. This improves speed and less Gets!\n\n"
				+ "- Link more data\n"
				+ "- Better Offline Checks\n"
				+ "- More threads (Threads in GUI to allow interaction while waiting for a response)\n"
				+ "- Better data visualiation (GUI tables)\n"
				+ "- Serialize data (save data so it can be reloaded on next App run\n"
				+ "- Full data copy (copy all data from SWapi and have a comparison check if newer data.\n"
				+ "- Save outputs to .csv , .txt, .json and other useful types\n"
				+ "- Nicer GUI design\n"
				+ "- GUI customisation\n"
				+ "- Import data (.json, .txt files) containing star wars data.\n"
				+ "- Edit and ammend data (to fix spellings and capitalisations in swapi data).\n"
				+ "- GUI debug tools\n"
				+ "- Scaleable GUI\n"
				+ "- Wookie Encoding\n\n"
				+ "- [BUG] - Ctrl + Z kills Scanner read in Console app\n"
				+ "- [BUG] - Random character search fails to display on certain numbers\n"
				+ "- [BUG] - GUI text area not clearing text as expected\n"
				+ "\n\n"
				+"==================================================================================\n"
				+"                          C O P Y R I G H T   N O T I C E \n"
				+"==================================================================================\n"
				+ "I am not associated or affiliated with Star Wars or SWapi.\n\n"
				+ "SWapi is a free open source API available at : https://swapi.co"
				+ "\n\nStar Wars and all associated names and references are copyright Lucasfilmltd\n\n"
				+ "The icons used in the GUI are copyright free and can be found at :\nhttps://www.flaticon.com\n"
				+ "\nThe background used in the GUI dashboard is a copyright free background\n"
				+ "available at : https://wallpaperaccess.com/star-wars\n\n"
				+ "\nThe Ascii Art used in my console prints where not made by me. They are taken from\n"
				+ "a free to use Ascii art generator page."
				+ "The Ascii art can be found at : https://www.asciiart.eu/movies/star-wars\n"
				+"==================================================================================\n"
				+"                          S O U R C E    C O D E \n"
				+"==================================================================================\n"
				+ "The Source code for this App can be found on my personal GitHub. You can grab it\n"
				+ "at : https://github.com/MarkWatt3295/SWapi/"
				;
		if(print == true) {
			System.out.println(text);
		}
		return text;

	}

	/**
	 * Take the entered string and remove illegal characters.
	 * I swap them for pluses as the request handler can understand them.
	 * They also return better responses.
	 * @param text
	 */
	private String checkString(String text) {
		Logger.appLog("[checkString] Search Query before : "+text);
		text = text.replaceAll("[^a-zA-Z0-9]"," ");
		Logger.appLog("[checkString] Search Query After Replace : "+text);
		text = text.replaceAll(" ","+");
		Logger.appLog("[checkString] Search Query After Replace 2: "+text);
		return text;
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
