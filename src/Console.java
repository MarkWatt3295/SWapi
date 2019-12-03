import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Console {
	private boolean displayheader = true;
	public static Scanner input = new Scanner(System.in);
	App app = new App();


	public void menuDraw(){
		if(App.networkConnected == true) {
			if(displayheader == true) {
				asciiDraw2();
				displayheader = false;
			}
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
			System.out.println("11 - Redraw Menu");
			
			System.out.println("AppOnline : "+App.networkConnected);
			System.out.println("\n==================================================================================\n");
			
			
			if(App.networkConnected == true) {
				try {
					int number = input.nextInt();
					
					if(App.networkConnected == true) {
					menuChoice(number);
					}
					else if (App.networkConnected == false){
						Main.networkError();
					}
				}
				catch (InputMismatchException e) {
					System.err.println("This is not the number you are looking for....\nEnter a number from the menu.\n");
					menuDraw();
				}
			}
			else {
				Main.networkError();
			}
		}



	}

	public void printGuiChoice(){
		asciiDraw();
		Thread thread = new Thread();
		thread.runTask1();
		System.out.println("\n==================================================================================\n");
		System.out.println("What kind of GUI would you like to use?");
		System.out.println("1 - Console Text");
		System.out.println("2 - Swing GUI");
		System.out.println("3 - Exit");
		System.out.println("\n==================================================================================\n");
		
		try {
			int number = input.nextInt();
			guiChooser(number);
		}
		catch (InputMismatchException e) {
			System.err.println("This is not the number you are looking for....\nEnter a number from the menu.\n");
			printGuiChoice();
		}

	}

	public void guiChooser(int command) {


		switch (command) {
		case 1:
			menuDraw();
			break;
		case 2:
			break;
		case 3:
			exitApp();
			break;

		default:
			System.err.println(command + " is not the dro.. number your looking for.\nTry again.");
					printGuiChoice();
					break;
		}
	}

	public void menuChoice(int command) {

		JsonObject jsonObject;

		switch (command) {
		case 1:
			System.out.println("All Characters");
			menuDraw();
			break;
		case 2:

			if(App.networkConnected == true) {

				try {
					System.out.println("Enter a Character to search for");
					Scanner input = new Scanner(System.in);
					String text = input.nextLine();
					app.swapiCharacterSearch(text);
					menuDraw();
				} catch (Exception e) {
					Main.networkError();
				}

			}
			else {
				Main.networkError();
			}


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
		case 10:
			exitApp();
			break;
		case 11:
			menuDraw();
			break;

		default:
			System.err.println(command + " is not the dro.. number your looking for.\nTry again.");
			menuDraw();
			break;
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

	private void asciiDraw() {
		System.out.println(" ____ _____  _    ____   __        __                  _    ____ ___ \r\n" + 
				" / ___|_   _|/ \\  |  _ \\  \\ \\      / /_ _ _ __ ___     / \\  |  _ \\_ _|\r\n" + 
				" \\___ \\ | | / _ \\ | |_) |  \\ \\ /\\ / / _` | '__/ __|   / _ \\ | |_) | | \r\n" + 
				"  ___) || |/ ___ \\|  _ <    \\ V  V / (_| | |  \\__ \\  / ___ \\|  __/| | \r\n" + 
				" |____/ |_/_/   \\_\\_| \\_\\    \\_/\\_/ \\__,_|_|  |___/ /_/   \\_\\_|  |___|\r\n" + 
				"                                                                      ");
		System.out.println("\nBy Mark Watt");
	}

	private void asciiDraw2() {
		System.out.println("  ______        ___    ____ ___ \r\n" + 
				" / ___\\ \\      / / \\  |  _ \\_ _|\r\n" + 
				" \\___ \\\\ \\ /\\ / / _ \\ | |_) | | \r\n" + 
				"  ___) |\\ V  V / ___ \\|  __/| | \r\n" + 
				" |____/  \\_/\\_/_/   \\_\\_|  |___|\r\n" + 
				"                                ");
	}


}
