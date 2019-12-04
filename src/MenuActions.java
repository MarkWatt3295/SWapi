import java.io.IOException;
import java.util.Scanner;

import org.apache.http.client.methods.HttpGet;

public class MenuActions {
	
	private String character_text;
	App app = new App();
	ConsolePrint console = new ConsolePrint();

	public void guiChooser(int command) {
		switch (command) {
		case 1:
			clrscr();
			console.menuDraw();
			break;
		case 2:
			break;
		case 3:
			exitApp();
			break;

		default:
			
			endResult(false, 2, command + " is not the dro.. number you're looking for.\nTry again.");
			break;
		}
	}

	public void menuChoice(int command) {

		switch (command) {
		case 1:
			app.AllCharacters();
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
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;

		case 4:
			console.arraylistTable();
			endResult(false, 1, "Press \"Enter\" to return to the main menu.");
			break;
		case 5:
			HttpGet get = new HttpGet("https://swapi.co/api/people/?page=9");
			try {
				app.requestAll(get);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			break;

		case 3:
			break;
			
		case 5:
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
	}

	private void exitApp() {
		System.out.println("Exiting App");
		System.exit(0);

	}
	
	



}
