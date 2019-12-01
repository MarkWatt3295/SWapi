import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Console {
	
	App app = new App();
	Model model = new Model(app);

	public void menuDraw(){
	
		System.out.println("\n====================================\n");
		System.out.println("1 - All Characters");
		System.out.println("2 - Search Character");
		System.out.println("3 - About");
		System.out.println("4 - Exit");
		System.out.println("\n====================================\n");
		Scanner input = new Scanner(System.in);
    	int number = input.nextInt();
    	menuChoice(number);
		
	}
	
	public void printGuiChoice(){
		
		System.out.println("\n====================================\n");
		System.out.println("What kind of GUI would you like to use?");
		System.out.println("1 - Console Text");
		System.out.println("2 - Swing GUI");
		System.out.println("3 - Exit");
		System.out.println("\n====================================\n");
		Scanner input = new Scanner(System.in);
    	int number = input.nextInt();
    	guiChooser(number);
		
	}
	
	public void guiChooser(int command) {

	      
        switch (command) {
            case 1:
              System.out.println("You chose Text");
              menuDraw();
                break;
            case 2:
               System.out.println("You chose GUI");
                break;
            case 3:
               exitApp();
                 break;
           
            default:
                System.out.println(command + " is not a available command");
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
           	   System.out.println("Text is : "+text);
           	   try {
				app.swapiCharacterSearch(text);
				app.person.personPrint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case 4:
                exitApp();
                 break;
           
            default:
                System.out.println(command + " is not a available command");
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
