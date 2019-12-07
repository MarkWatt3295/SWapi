import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class App2 {

	public static boolean debug_mode = false;
	public static boolean networkConnected = false;
	public static boolean directory_exists = false;

	public static int character_count = 0;
	public String next_page = "null";
	public boolean allow_next = true;
	public Thread thread = new Thread();
	public boolean initialised = false;
	public boolean enable_logs = true;
	private boolean new_char = true;
	private boolean advanced_print = false;


	private String temptype = "";

	List<Person> People = new ArrayList<>();
	List<Films> Films = new ArrayList<>();
	List<Vehicles> Vehicles = new ArrayList<>();

	private BufferedReader reader;
	private HttpResponse response;
	public JsonArray temp_array;
	public boolean first_run = true;

	private String name = "";
	private String gender = "";
	private String height = "";
	private String mass = "";
	private String hair_color = "";
	private String skin_color = "";
	private String eye_color = "";
	private String birth_year = "";
	private String home_world = "";



	public boolean isAdvanced_print() {
		return advanced_print;
	}

	public void setAdvanced_print(boolean advanced_print) {
		this.advanced_print = advanced_print;
	}

	/**
	 * 
	 * @param searchquery - (specifically named e.g Luke)
	 * @return
	 * @throws Exception
	 */
	public void swapiCharacterSearch(String searchquery, String searchnumber)  {

		HttpGet httpGet;

		if(searchnumber == null) {
			httpGet = new HttpGet("https://swapi.co/api/people/?search=" + searchquery);
		}
		else {
			httpGet = new HttpGet("https://swapi.co/api/people/" + searchnumber);
			System.err.println("Searhcing by number using : "+httpGet.getURI());
		}
		System.out.println("\nParsing Midi-chlorians, This may take a second...\n");

		Logger.appLog("[SWapiCharachterSearch] Get is : "+httpGet);

		try {
			personRequest(httpGet);
		}
		catch (IllegalArgumentException e) {
			System.err.println("Unable to search for : "+searchquery+" as it contains an illegal character.\nPlease try again.");
			e.printStackTrace();

		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void AllCharacters()  {

		HttpGet httpGet = new HttpGet("https://swapi.co/api/people");
		System.out.println("\nParsing Midi-chlorians, This may take a second...\n");

		Logger.appLog("[AllCharacters] Get is : "+httpGet);

		try {
			requestAll(httpGet);
		}
		catch (IllegalArgumentException e) {

			e.printStackTrace();

		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void StraightSwapiSearch(String uri, String type) {
		HttpGet httpGet = new HttpGet(uri);
		Logger.appLog("[ StraightSwapiSearch ] The straight get is : "+httpGet); 
		try {
			httpRequest(httpGet, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public JsonObject httpRequest(HttpGet getRequest, String type) throws IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		Logger.appLog("[HttpRequest] : "+ getRequest + " "+type);
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);


		if (response.getStatusLine().getStatusCode() != 200) {
			errorCode(response);
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		reader = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			Logger.appLog(line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());

		if(type == "species") {
			String name =  jsonObject.get("name").getAsString();
			temptype = name;
		}
		else if(type == "films") {
			String title =  jsonObject.get("title").getAsString();
			Films f = new Films();
			f.setTitle(title);
			Films.add(f);
		}

		else if(type == "vehicles") {
			String name =  jsonObject.get("name").getAsString();
			Vehicles v = new Vehicles();
			v.setName(name);
			Vehicles.add(v);
		}

		return jsonObject;
	}

	public JsonObject personRequest(HttpGet getRequest) throws Exception {

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
			Logger.appLog("[PersonRequest-SB]"+line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());
		Logger.appLog("[PersonRequest jsonObject] : "+jsonObject.toString()+"\n");

		if(jsonObject.toString().contains("results")) {
			JsonArray arr = jsonObject.getAsJsonArray("results");
			Main.menuactions.console.twoChoices();
			displayAll(arr);
		}
		else {
			Logger.appLog("[PersonRequest] : Doesn't contain an Array of Results\n");
			Main.menuactions.console.twoChoices();
			displaySingle(jsonObject);
		}

		reader.close();
		return jsonObject;
	}

	/**
	 * A starwars related http error catch
	 * @param response
	 */
	private void errorCode(HttpResponse response) {
		AsciiArt.errorDraw("HTTP ERROR : "+response.getStatusLine().getStatusCode());
		if(response.getStatusLine().getStatusCode() == 503) {
			System.out.println("Let me do the talking Chewie...");
		}
		else if(response.getStatusLine().getStatusCode() == 500) {
			System.out.println("I've got a bad feeling about this Chewie...");
		}
		else if(response.getStatusLine().getStatusCode() == 504) {
			System.out.println("I've got a bad feeling about this Chewie...");
		}
		else if(response.getStatusLine().getStatusCode() == 403) {
			System.out.println("I don't think we're supposed to be here Chewie.");
		}
		else {
			System.out.println("Blast it Chewie! We didn't account for this");
		}
		Main.menuactions.console.menuDraw();
		System.out.println("==================================================================================");

	}

	public void requestAll(HttpGet getRequest) throws Exception {

		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			errorCode(response);
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		reader = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			Logger.appLog("[RequestAll-StringBuilder] : "+line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());
		JsonElement count = jsonObject.get("count");
		Logger.appLog("COUNT IS : "+count);
		JsonElement next = jsonObject.get("next");
		Logger.appLog("THE NEXT PAGE TO SEARCH IS : "+next);
		next_page = getRequest.getURI().toString();

		character_count= count.getAsInt();

		temp_array = jsonObject.getAsJsonArray("results");
		reader.close();
		if(character_count > 0) {
			Main.menuactions.console.continueSearch(getRequest);
		}
	}



	public void displayAll(JsonArray arr) {

		if(arr.size() != 0) {
			Logger.appLog("Total Search Results per page : "+arr.size()+"\n");
		}
		if(arr.size() == 0) {
			System.out.println("\n==================================================================================");
			System.out.println("Mmm... No results are there. Try again you will.");
			System.out.println("==================================================================================\n");

		}

		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
		}

		for (int i = 0; i < arr.size(); i++) {	
			new_char = true;
			//System.out.println("NEW CHAR IS "+new_char);

			Person p = new Person();
			Films f = new Films();
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


			if(App2.networkConnected == false){  
				System.err.println("ERRRRRRRROOOOOORRRR"); 
				App2.networkError();
				break;
			}

			duplicationCheck(p, result);
		}
	}

	/**
	 * duplicationCheck checks the person ArrayList to see if the person allready exists.
	 * If the person exists the data is pulled from the ArrayList to save sending repeated
	 * requests. If the person doesn't exist they are searched for normally.
	 * @param p - The person to check
	 * @param result - The JSON result
	 */
	private void duplicationCheck(Person p , JsonObject result) {
		Logger.appLog("[Display All Duplication Check]");
		
		for(Person pp: People) {
			if(pp.getName().equals(p.getName())) {
				System.out.println("Not a new Character");
				Logger.appLog(("SNAP! : "+p.getName()));
				new_char = false;
				pp.personPrint(advanced_print);

			}
		}

		if(new_char == true) {
			
			System.out.println("New Character Detected\n");
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
	 * Call the Print for a single passed user. This is used when there isn't a results array
	 * @param job - A json object
	 */
	public void displaySingle(JsonObject job) {

		name = job.get("name").getAsString();
		gender = job.getAsJsonObject().get("gender").getAsString();

		Person p = new Person();
		Films f = new Films();
		p.setName(name);
		p.setGender(gender);

		duplicationCheck(p, job);

		if(App2.networkConnected == false){  
			System.err.println("ERRRRRRRROOOOOORRRR"); 
			App2.networkError();
		}

	}


	public void countedRequest(HttpGet getRequest) throws Exception {

		String getty = getRequest.getURI().toString();
		Logger.appLog(getty +  " : getty - Requested URI");

		int i = 0;
		while(allow_next != false) {
			i++;
			getRequest = new HttpGet(next_page);
			Logger.appLog("Actual get is : "+getRequest);

			HttpClient httpClient = HttpClientBuilder.create().build();
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				errorCode(response);
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			reader = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				Logger.appLog("[countedRequest-SB] : "+line);
			}

			JsonObject jsonObject = deserialize(stringBuilder.toString());
			JsonArray arr = jsonObject.getAsJsonArray("results");
			JsonElement next = jsonObject.get("next");
			String s = "";
			displayAll(arr);
			try{
				s = next.getAsString();
				next_page = s;
			}
			catch(UnsupportedOperationException e) {
				break;
			}


			reader.close();
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
				if(App2.networkConnected == false){  
					System.err.println("A connection error has occured"); 
					App2.networkError();
					break;
				}
				JsonElement character = jsonArray.get(j);
				String uri = character.getAsString();
				Logger.appLog("print uri is : "+uri);

				if(uri.contains("species")){
					try {
						StraightSwapiSearch(uri, "species");
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
				else if(uri.contains("films")){

					//System.out.println("contains films");
					try {
						StraightSwapiSearch(uri, "films");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else if(uri.contains("vehicles")){

					//System.out.println("contains films");
					try {
						StraightSwapiSearch(uri, "vehicles");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Not in URI check");
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

	public void createDir() {
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
	public void initialiseApp() {
		thread.runTask1();
		Logger.appLog("SWapi started at : ");
	}

}