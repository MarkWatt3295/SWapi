import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Console {
	
	App app = new App();
	

	public void menuDraw(){
	
		System.out.println("\n====================================\n");
		System.out.println("1 - All Characters");
		System.out.println("2 - Search Character");
		System.out.println("3 - Search History");
		System.out.println("4 - About");
		System.out.println("5 - Exit");
		System.out.println("\n====================================\n");
		Scanner input = new Scanner(System.in);
    	try {
    		int number = input.nextInt();
    		menuChoice(number);
    	}
	 catch (InputMismatchException e) {
		 System.err.println("This is not the number you are looking for....\nEnter a number from the menu.\n");
		 menuDraw();
	 }
    	
		
	}
	
	public void printGuiChoice(){
		
		System.out.println("\n====================================\n");
		System.out.println("What kind of GUI would you like to use?");
		System.out.println("1 - Console Text");
		System.out.println("2 - Swing GUI");
		System.out.println("3 - Exit");
		System.out.println("\n====================================\n");
		Scanner input = new Scanner(System.in);
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
               System.out.println("Enter a Character to search for");
               Scanner input = new Scanner(System.in);
           	   String text = input.nextLine();
           	
           	   try {
				app.swapiCharacterSearch(text);
				menuDraw();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
            case 5:
                exitApp();
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
	
	
	
}
