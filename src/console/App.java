package console;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import gui.AppWindow;
import gui.GuiController;


/**
 * This class is used to store all of the main data in the app.
 * The majority of its boolean variables have been made static
 * to allow various other classes to access the information.
 * @author Mark
 *
 */
public class App {

	public static boolean disable_midis = false; //Used to disable the midi-chlorian message
	public static boolean tableShow = false;
	public static boolean networkErrorShow = false; //Used to set if the Network error pops up in GUI
	public static boolean using_gui = false; //Is the App in GUI mode
	public static boolean debug_mode = false; //Enable and disable debug mode (Console prints)
	public static boolean networkConnected = false;
	public static boolean directory_exists = false;

	public static int character_count = 0; //The ammount of characters counted from Api get.

	public String next_page = "null"; //Initially null as Json passes null as a string
	public boolean allow_next = true; //Is next page allowed

	public static Thread thread = new Thread(); //Create the main thread

	public boolean initialised = false;
	public boolean enable_logs = true;
	private boolean new_char = true;
	private boolean advanced_print = false;


	private String temptype = "";


	//ARRAY LISTS for each of the main category being stored and collected
	public List<Person> People = new ArrayList<>();
	public List<Films> Films = new ArrayList<>();
	public List<Vehicles> Vehicles = new ArrayList<>();
	public List<Planets> Planets = new ArrayList<>();
	public List<Spaceship> Spaceship = new ArrayList<>();


	private BufferedReader reader;
	private HttpResponse response;
	public JsonArray temp_array;
	public boolean first_run = true;

	//Person Variables
	private String name = "";
	private String gender = "";
	private String height = "";
	private String mass = "";
	private String hair_color = "";
	private String skin_color = "";
	private String eye_color = "";
	private String birth_year = "";
	private String home_world = "";


	/**
	 * Is advanced print enabled?
	 * @return
	 */
	public boolean isAdvanced_print() {
		return advanced_print;
	}

	/**
	 * Set advanced print
	 * @param advanced_print
	 */
	public void setAdvanced_print(boolean advanced_print) {
		this.advanced_print = advanced_print;
	}

	/**
	 * 
	 * @param searchquery - When using command (search by name). The parameter is a name to
	 * to search for. E.g (Luke, Anakin etc).
	 * @param searchnumber - Enter a number when searching for a person number
	 * @param command - Command names {@link swapiCharacterSearch} in switch statement
	 */
	public void swapiCharacterSearch(String searchquery, String searchnumber, String command)  {

		HttpGet httpGet = null;

		switch (command) {

		case "search_by_name":
			httpGet = new HttpGet("https://swapi.co/api/people/?search=" + searchquery);

			try {
				personRequest(httpGet, "display", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "search_by_number":
			httpGet = new HttpGet("https://swapi.co/api/people/" + searchnumber);
			try {
				personRequest(httpGet, "display", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "ping":
			httpGet = new HttpGet("https://swapi.co/api/people");
			try {
				personRequest(httpGet, "ping", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "planet_search":
			httpGet = new HttpGet("https://swapi.co/api/planets/?search=" + searchquery);
			try {
				personRequest(httpGet, "displayplanets", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "ship_search":
			httpGet = new HttpGet("https://swapi.co/api/starships/?search=" + searchquery);
			try {
				personRequest(httpGet, "displayships", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "vehicle_search":
			httpGet = new HttpGet("https://swapi.co/api/vehicles/?search=" + searchquery);
			try {
				personRequest(httpGet, "display_vehicles", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;


		default:
			System.out.println(command + " is not a available command");
			break;
		}

	}





	/**
	 * Used to directly execute a http get using just a type switch
	 * @param uri - The searchspace
	 * @param type - category to search for (species etc)
	 */
	public void swapiSearch(String uri, String type) {
		HttpGet httpGet = new HttpGet(uri);
		Logger.appLog("[ StraightSwapiSearch ] The straight get is : "+httpGet); 

		try {
			personRequest(httpGet, "swapi_response", type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	//BUILDER
	/**
	 * This is the main request builder. Most requests come through this method.
	 * The request utilizes a couple of parameters to pass into a switch statement.
	 * @param getRequest - The request to build
	 * @param command -  Switch command
	 * @param type -  type if applicable
	 * @return
	 * @throws Exception
	 */
	public JsonObject personRequest(HttpGet getRequest, String command, String type) throws Exception {
		Logger.appLog("PUNCH IT CHEWIE! : "+getRequest + " " + command + " " + type);
		if(App.disable_midis == false) {
			System.out.println("\nParsing Midi-chlorians, This may take a second...\n");
		}
		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		Logger.appLog("[personRequestGet : "+getRequest+"\n");


		if (response.getStatusLine().getStatusCode() != 200) {

			errorCode(response);
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode() );

		}

		reader = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			Logger.appLog("[PersonRequest-SB-line]"+line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());

		boolean is_an_array = false;
		JsonArray arr = null;
		if(jsonObject.toString().contains("results")) {
			Logger.appLog("Contains an Array of Results\n");
			arr = jsonObject.getAsJsonArray("results");
			is_an_array = true;
		}
		else {
			Logger.appLog("Doesn't contain an Array of Results\n");
			is_an_array = false;
		}


		switch (command) {
		case "display":
			if(is_an_array == true) {
				displayAll(arr);
			}
			else if (is_an_array == false) {
				displaySingle(jsonObject);
			}

			break;
		case "displayplanets":
			displayPlanets(arr);
			break;

		case "displayships":
			displayShips(arr);
			break;
		case "display_vehicles":
			displayVehicles(arr);
			break;

			//The ping switch is used to send a request out just to get the count element
		case "ping":
			JsonElement count = jsonObject.get("count");
			Logger.appLog("COUNT IS : "+count);
			JsonElement next = jsonObject.get("next");
			Logger.appLog("THE NEXT PAGE TO SEARCH IS : "+next);
			next_page = getRequest.getURI().toString();
			character_count= count.getAsInt();
			if(character_count > 0) {
				Main.menuactions.console.continueSearch(getRequest);
			}
			break;

		case "counted_request":
			Logger.appLog("New Request is now : "+next_page);
			//Main.menuactions.console.twoChoices();
			String getty = getRequest.getURI().toString();
			Logger.appLog("Getty is : "+getty);

			while(allow_next != false) {

				Logger.appLog("Next page is : "+next_page);
				getRequest = new HttpGet(next_page);
				Logger.appLog("Actual get is : "+getRequest);

				JsonElement nextcount = jsonObject.get("next");
				String s = "";
				displayAll(arr);
				try{
					s = nextcount.getAsString();
					next_page = s;
					Logger.appLog("S IS : "+s);
					HttpGet newReq = new HttpGet(next_page);
					Main.menuactions.app.personRequest(newReq, "counted_request", null);
				}
				catch(UnsupportedOperationException e) {
					Logger.appLog("Bounced out of while as no Json left : "+e.getLocalizedMessage());
					allow_next = false;
				}

			}
			break;
		case "swapi_response":
			if(type == "species") {
				String name =  jsonObject.get("name").getAsString();
				temptype = name;
			}
			else if(type == "films") {
				String title =  jsonObject.get("title").getAsString();
				String id =  jsonObject.get("episode_id").getAsString();
				String opening_crawl =  jsonObject.get("opening_crawl").getAsString();
				String release_date = jsonObject.get("release_date").getAsString();
				Films f = new Films();
				f.setTitle(title);
				f.setEpisodeId(id);
				f.setOpeningCrawl(opening_crawl);
				f.setRelease_date(release_date);
				Films.add(f);
			}

			else if(type == "vehicles") {
				String name =  jsonObject.get("name").getAsString();
				String model =  jsonObject.get("model").getAsString();
				String manufacturer =  jsonObject.get("manufacturer").getAsString();
				Vehicles v = new Vehicles();
				v.setName(name);
				v.setModel(model);
				v.setManufacturer(manufacturer);
				Vehicles.add(v);
			}
			else if(type == "planets") {
				String name =  jsonObject.get("name").getAsString();
				String climate =  jsonObject.get("climate").getAsString();
				Planets p = new Planets();
				p.setName(name);
				p.setClimate(climate);
				System.err.println("Planets name : "+p.getName());
				Planets.add(p);
			}

		}

		reader.close();
		return jsonObject;
	}



	/**
	 * Pass in an array of a character to save the results in the person arraylist.
	 * @param arr
	 */
	public void displayAll(JsonArray arr) {

		if(arr.size() != 0) {
			Logger.appLog("Total Search Results per page : "+arr.size()+"\n");
		}
		if(arr.size() == 0) {
			System.out.println("\n==================================================================================");
			System.out.println("Mmm... No results are there. Try again you will.");
			System.out.println("==================================================================================\n");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Mmm... No results are there. Try again you will.");
			}
		}

		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
			if(App.using_gui == true) {
				GuiController.mDialog("c3po.png", "There are multiple results for this search. It may take a second", "Multiple Results for this search");
			}
		}

		for (int i = 0; i < arr.size(); i++) {	
			new_char = true;
			//System.out.println("NEW CHAR IS "+new_char);

			Person p = new Person();
			JsonObject result = arr.get(i).getAsJsonObject();

			//System.out.println("Array is : "+arr.toString());

			name = arr.get(i).getAsJsonObject().get("name").getAsString();
			gender = arr.get(i).getAsJsonObject().get("gender").getAsString();
			height = arr.get(i).getAsJsonObject().get("height").getAsString();
			mass = arr.get(i).getAsJsonObject().get("mass").getAsString();
			hair_color = arr.get(i).getAsJsonObject().get("hair_color").getAsString();
			skin_color = arr.get(i).getAsJsonObject().get("skin_color").getAsString();
			eye_color = arr.get(i).getAsJsonObject().get("eye_color").getAsString();
			birth_year = arr.get(i).getAsJsonObject().get("birth_year").getAsString();
			home_world = arr.get(i).getAsJsonObject().get("homeworld").getAsString();


			p.setName(name);
			p.setGender(gender);
			p.setHeight(height);
			p.setMass(mass);
			p.setHair_color(hair_color);
			p.setSkin_color(skin_color);
			p.setEye_color(eye_color);
			p.setBirthYear(birth_year);


			if(App.networkConnected == false){  
				System.err.println("ERRRRRRRROOOOOORRRR"); 
				App.networkError();
				break;
			}

			duplicationCheck(p, result);
		}
	}

	/**
	 * duplicationCheck checks the person ArrayList to see if the person all ready exists.
	 * If the person exists the data is pulled from the ArrayList to save sending repeated
	 * requests. If the person doesn't exist they are searched for normally.
	 * 
	 * I created this method to try and reduce the ammounts of request being sent out
	 * as the SWapi API throttles the response speed for your IP address after so many
	 * requests.
	 * 
	 * @param p - The person to check
	 * @param result - The JSON result
	 */
	private void duplicationCheck(Person p , JsonObject result) {
		Logger.appLog("[Display All Duplication Check]");

		//Check if the person is in the Array before adding them again
		for(Person pp: People) {
			if(pp.getName().equals(p.getName())) {
				Logger.appLog("Not a new Character");
				Logger.appLog(("SNAP! : "+p.getName()));
				new_char = false;
				pp.personPrint(advanced_print);

			}
		}

		if(new_char == true) {

			Logger.appLog("New Character Detected\n");
			JsonArray species = result.getAsJsonArray("species");
			JsonArray films = result.getAsJsonArray("films");
			JsonArray vehicles = result.getAsJsonArray("vehicles");
			JsonArray starships = result.getAsJsonArray("starships");

			printSubCall("name", species);
			p.setSpecies(temptype);

			printSubCall("title", films);
			Films.forEach(film -> {
				p.addFilm(film);

			});
			Films.clear();

			printSubCall("name", vehicles);
			Vehicles.forEach(vehicle -> {
				p.addVehicles(vehicle);

			});
			Vehicles.clear();


			People.add(p);
			p.personPrint(advanced_print);
			System.out.println("\n");

		}
	}

	/**
	 * Used to display the planets information.
	 * json array gets parsed.
	 * Planets are then added into the planet arraylist
	 * @param arr
	 */
	public void displayPlanets(JsonArray arr) {

		if(arr.size() != 0) {
			Logger.appLog("Total Search Results per page : "+arr.size()+"\n");
		}
		if(arr.size() == 0) {
			System.out.println("\n==================================================================================");
			System.out.println("Mmm... No results are there. Try again you will.");
			System.out.println("==================================================================================\n");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Mmm... No results are there. Try again you will.");
			}
		}

		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
			if(App.using_gui == true) {
				GuiController.mDialog("c3po.png", "There are multiple results for this search. It may take a second", "Multiple Results for this search");
			}
		}

		for (int i = 0; i < arr.size(); i++) {	

			Planets p = new Planets();
			JsonObject result = arr.get(i).getAsJsonObject();

			Logger.appLog("Planets Array is : "+arr.toString());

			String name = arr.get(i).getAsJsonObject().get("name").getAsString();
			String rotationPeriod = arr.get(i).getAsJsonObject().get("rotation_period").getAsString();
			String orbitalPeriod = arr.get(i).getAsJsonObject().get("orbital_period").getAsString();
			String diameter = arr.get(i).getAsJsonObject().get("diameter").getAsString();
			String climate = arr.get(i).getAsJsonObject().get("climate").getAsString();
			String gravity = arr.get(i).getAsJsonObject().get("gravity").getAsString();
			String terrain = arr.get(i).getAsJsonObject().get("terrain").getAsString();
			String surfaceWater = arr.get(i).getAsJsonObject().get("surface_water").getAsString();
			String population = arr.get(i).getAsJsonObject().get("population").getAsString();
			System.out.println("Name is "+name);

			p.setName(name);
			p.setRotationPeriod(rotationPeriod);
			p.setOrbitalPeriod(orbitalPeriod);
			p.setDiameter(diameter);
			p.setClimate(climate);
			p.setGravity(gravity);
			p.setTerrain(terrain);
			p.setSurfaceWater(surfaceWater);
			p.setPopulation(population);

			Planets.add(p);
			p.planetsPrint();

			if(App.networkConnected == false){  
				System.err.println("ERRRRRRRROOOOOORRRR"); 
				App.networkError();
				break;
			}


		}
	}

	/**
	 * 
	 * @param arr
	 */
	public void displayShips(JsonArray arr) {
		Logger.appLog("Ships ARRAY IS : "+arr.toString());
		if(arr.size() != 0) {
			Logger.appLog("Total Search Results per page : "+arr.size()+"\n");
		}
		if(arr.size() == 0) {
			System.out.println("\n==================================================================================");
			System.out.println("Mmm... No results are there. Try again you will.");
			System.out.println("==================================================================================\n");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Mmm... No results are there. Try again you will.");
			}
		}

		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
			if(App.using_gui == true) {
				GuiController.mDialog("c3po.png", "There are multiple results for this search. It may take a second", "Multiple Results for this search");
			}
		}

		for (int i = 0; i < arr.size(); i++) {	

			Spaceship ship = new Spaceship();
			JsonObject result = arr.get(i).getAsJsonObject();
			Logger.appLog("Spaceship Array is : "+arr.toString());
			String name = arr.get(i).getAsJsonObject().get("name").getAsString();
			String model = arr.get(i).getAsJsonObject().get("model").getAsString();
			String manufacturer = arr.get(i).getAsJsonObject().get("manufacturer").getAsString();
			String cost_in_credits = arr.get(i).getAsJsonObject().get("cost_in_credits").getAsString();
			String length = arr.get(i).getAsJsonObject().get("length").getAsString();
			String max_atmosphering_speed = arr.get(i).getAsJsonObject().get("max_atmosphering_speed").getAsString();
			String crew = arr.get(i).getAsJsonObject().get("crew").getAsString();
			String passengers = arr.get(i).getAsJsonObject().get("crew").getAsString();
			String cargo_capactity = arr.get(i).getAsJsonObject().get("cargo_capacity").getAsString();
			String consumables = arr.get(i).getAsJsonObject().get("consumables").getAsString();
			String hyperdrive_rating = arr.get(i).getAsJsonObject().get("hyperdrive_rating").getAsString();
			String mglt = arr.get(i).getAsJsonObject().get("MGLT").getAsString();
			String starship_class = arr.get(i).getAsJsonObject().get("starship_class").getAsString();

			ship.setName(name);
			ship.setModel(model);
			ship.setManufacturer(manufacturer);
			ship.setCost_in_credits(cost_in_credits);
			ship.setLength(length);
			ship.setMax_atmosphering_speed(max_atmosphering_speed);
			ship.setCrew(crew);
			ship.setPassengers(passengers);
			ship.setCargo_capactity(cargo_capactity);
			ship.setConsumables(consumables);
			ship.setHyperdrive_rating(hyperdrive_rating);
			ship.setMglt(mglt);
			ship.setStarship_class(starship_class);


			Spaceship.add(ship);
			ship.planetsPrint();

			if(App.networkConnected == false){  
				System.err.println("ERRRRRRRROOOOOORRRR"); 
				App.networkError();
				break;
			}


		}
	}


	public void displayVehicles(JsonArray arr) {

		if(arr.size() != 0) {
			Logger.appLog("Total Search Results per page : "+arr.size()+"\n");
		}
		if(arr.size() == 0) {
			System.out.println("\n==================================================================================");
			System.out.println("Mmm... No results are there. Try again you will.");
			System.out.println("==================================================================================\n");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Mmm... No results are there. Try again you will.");
			}
		}

		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
			if(App.using_gui == true) {
				GuiController.mDialog("c3po.png", "There are multiple results for this search. It may take a second", "Multiple Results for this search");
			}
		}

		for (int i = 0; i < arr.size(); i++) {	

			Vehicles vehicle = new Vehicles();
			JsonObject result = arr.get(i).getAsJsonObject();
			Logger.appLog("Vehicle Array is : "+arr.toString());
			String name = arr.get(i).getAsJsonObject().get("name").getAsString();
			String model = arr.get(i).getAsJsonObject().get("model").getAsString();
			String manufacturer = arr.get(i).getAsJsonObject().get("manufacturer").getAsString();
			String cost_in_credits = arr.get(i).getAsJsonObject().get("cost_in_credits").getAsString();
			String length = arr.get(i).getAsJsonObject().get("length").getAsString();
			String max_atmosphering_speed = arr.get(i).getAsJsonObject().get("max_atmosphering_speed").getAsString();
			String crew = arr.get(i).getAsJsonObject().get("crew").getAsString();
			String passengers = arr.get(i).getAsJsonObject().get("crew").getAsString();
			String cargo_capactity = arr.get(i).getAsJsonObject().get("cargo_capacity").getAsString();
			String consumables = arr.get(i).getAsJsonObject().get("consumables").getAsString();
			String vehicle_class = arr.get(i).getAsJsonObject().get("vehicle_class").getAsString();

			vehicle.setName(name);
			vehicle.setModel(model);
			vehicle.setManufacturer(manufacturer);
			vehicle.setCost_in_credits(cost_in_credits);
			vehicle.setLength(length);
			vehicle.setMax_atmosphering_speed(max_atmosphering_speed);
			vehicle.setCrew(crew);
			vehicle.setPassengers(passengers);
			vehicle.setCargo_capacity(cargo_capactity);
			vehicle.setConsumables(consumables);
			vehicle.setVehicle_class(vehicle_class);

			Vehicles.add(vehicle);
			vehicle.vehiclePrint();
			if(App.networkConnected == false){  
				System.err.println("ERRRRRRRROOOOOORRRR"); 
				App.networkError();
				break;
			}


		}
	}




	/**
	 * Call the Print for a single passed user. This is used when there isn't a results array
	 * @param job - A json object
	 */
	public void displaySingle(JsonObject job) {


		Person p = new Person();
		Films f = new Films();

		name = job.getAsJsonObject().get("name").getAsString();
		gender = job.getAsJsonObject().get("gender").getAsString();
		height = job.getAsJsonObject().get("height").getAsString();
		mass = job.getAsJsonObject().get("mass").getAsString();
		hair_color = job.getAsJsonObject().get("hair_color").getAsString();
		skin_color = job.getAsJsonObject().get("skin_color").getAsString();
		eye_color = job.getAsJsonObject().get("eye_color").getAsString();
		birth_year = job.getAsJsonObject().get("birth_year").getAsString();
		home_world = job.getAsJsonObject().get("homeworld").getAsString();


		p.setName(name);
		p.setGender(gender);
		p.setHeight(height);
		p.setMass(mass);
		p.setHair_color(hair_color);
		p.setSkin_color(skin_color);
		p.setEye_color(eye_color);
		p.setBirthYear(birth_year);



		duplicationCheck(p, job);

		if(App.networkConnected == false){  
			System.err.println("ERRRRRRRROOOOOORRRR"); 
			App.networkError();
		}

	}






	public JsonObject deserialize(String json) {
		Gson gson = new Gson();
		JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
		return jsonClass;
	}


	/**
	 * The sub call is used to run a secondary search on uri's passed in.
	 * @param entity
	 * @param jsonArray
	 */
	public void printSubCall(String entity, JsonArray jsonArray)  {

		Logger.appLog("[ SubPrint call ] Item : "+entity+"Array : "+jsonArray+" Array Size : "+jsonArray.size());

		if (jsonArray.size() != 0) {
			for (int j = 0; j < jsonArray.size(); j++) {
				Logger.appLog("Currently on loop : "+j);
				if(App.networkConnected == false){  
					System.err.println("A connection error has occured"); 
					App.networkError();
					break;
				}
				JsonElement character = jsonArray.get(j);
				String uri = character.getAsString();
				Logger.appLog("print uri is : "+uri);

				if(uri.contains("species")){
					try {
						swapiSearch(uri, "species");
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
				else if(uri.contains("films")){

					//System.out.println("contains films");
					try {
						swapiSearch(uri, "films");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else if(uri.contains("vehicles")){

					//System.out.println("contains films");
					try {
						swapiSearch(uri, "vehicles");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			//System.out.println("Finished uri check");
		}


	}

	public static void clearLogs() {
		try {
			Runtime.getRuntime().exec("cmd /c del Swapi\\SWapi_Log.txt");
		} catch (IOException e) {
			System.err.println("Unable to delete Swapi_Log.txt");
		}

		try {
			Runtime.getRuntime().exec("cmd /c del Swapi\\SWapi_Thread_Log.txt");
		} catch (IOException e) {
			System.err.println("Unable to delete Swapi_Thread_Log.txt");
		}
	}

	public static void networkError() {
		if(App.using_gui == false) {
			System.out.println("=====================================================================================================");
			System.out.println("\nIt looks like you dont have an active Network Connection.\nThis App requires a connection in order to access the Star Wars API.");
			System.out.println("=====================================================================================================\n");

			Scanner input = new Scanner(System.in); 
			System.out.println("Would you like to retry your connection?  Y / N ? :");
			String reply = input.nextLine();
			if(reply.equals("y") || reply.equals("Y")) {
				System.out.println("\n=====================================================================================================");
				System.out.println("\nRetrying...\n");
				System.out.println("=====================================================================================================");
				Main.main(null);
			}
			else if(reply.equals("n") || reply.equals("N")) {

				System.out.println("\n=====================================================================================================");
				System.out.println("Exiting App............ May the force be with you!");
				System.out.println("=====================================================================================================");
				System.exit(0);

			}

			else {

				System.out.println("\n=====================================================================================================");
				System.out.println("There is only 'Y' or 'N'... There is no Try");
				System.out.println("=====================================================================================================\n");
				Main.main(null);
			}
		}
		else if(App.using_gui == true) {
			Object[] options = {"Retry Connection",
			"Quit Application"};

			int n = JOptionPane.showOptionDialog(AppWindow.frame,
					"It Looks Like there may be a connection issue?\n"
							+ "Do you want to retry the connection?"
							,
							"Unable to Connect to SWAPI ",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);


			if (n == JOptionPane.YES_OPTION) {
				GuiController.networkCheck();
				AppWindow.frame.show();
				App.networkErrorShow = false;
				System.out.println("Retry Pressed");


			} else if (n == JOptionPane.NO_OPTION) {
				System.out.println("Shutting down app");
				System.exit(0);
			} else if (n == JOptionPane.CLOSED_OPTION) {
				System.exit(0);

			} 
		}
	}

	public static void createDir() {
		File file = new File("SWapi\\config.txt");
		file.getParentFile().mkdir(); 
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Failed to create directory " + file.getParent());
			e.printStackTrace();
		}
	}

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * InitialiseApp is used to start the App thread and log the first message.
	 */
	public static void initialiseApp() {
		thread.runTask1();
		Logger.appLog("SWapi started at : ");
	}

	/**
	 * A starwars related http error catch
	 * @param response
	 */
	private void errorCode(HttpResponse response) {
		AsciiArt.errorDraw("HTTP ERROR : "+response.getStatusLine().getStatusCode());
		if(response.getStatusLine().getStatusCode() == 503) {
			System.out.println("Let me do the talking Chewie...");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Error 503 you have. Your fault, it is not.");
			}
		}
		else if(response.getStatusLine().getStatusCode() == 500) {
			System.out.println("I've got a bad feeling about this Chewie...");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Error 500 you have. Your fault, it is not.");
			}
		}
		else if(response.getStatusLine().getStatusCode() == 504) {
			System.out.println("I've got a bad feeling about this Chewie...");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Error 504 you have. Gateway Timeout it seems.");
			}
		}
		else if(response.getStatusLine().getStatusCode() == 403) {
			System.out.println("I don't think we're supposed to be here Chewie.");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Error 403 you have. The Forbidden Darkside have you gone.");
			}
		}
		else {
			System.out.println("Blast it Chewie! We didn't account for this");
			if(App.using_gui == true) {
				GuiController.yodaDialog("Http Error you have. Fix it you must.");
			}
		}
		Main.menuactions.console.menuDraw();
		System.out.println("==================================================================================");

	}

}
