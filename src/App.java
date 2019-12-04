import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class App {

	public static boolean debug_mode = false;
	public static boolean networkConnected = false;
	public static boolean directory_exists = false;
	public static int character_count = 0;
	public String next_page = "null";
	public boolean allow_next = true;

	private String temptype = "";

	List<Person> People = new ArrayList<>();
	List<Films> Films = new ArrayList<>();

	private BufferedReader reader;
	private HttpResponse response;
	public JsonArray temp_array;
	public boolean first_run = true;
	
	String name = "Not Set";
	String gender = "Not Set";
	




	/**
	 * 
	 * @param searchquery - (specifically named e.g Luke)
	 * @return
	 * @throws Exception
	 */
	public void swapiCharacterSearch(String searchquery)  {

		HttpGet httpGet = new HttpGet("https://swapi.co/api/people/?search=" + searchquery);
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

		return jsonObject;
	}

	public JsonObject personRequest(HttpGet getRequest) throws Exception {

		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);


		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
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

		String name = "Not Set";
		String gender = "Not Set";


		JsonArray arr = jsonObject.getAsJsonArray("results");
		displayAll(arr);

		reader.close();
		return jsonObject;
	}


	public void requestAll(HttpGet getRequest) throws Exception {

		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		reader = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			Logger.appLog("[RequestAll-SB]"+line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());
		JsonElement count = jsonObject.get("count");
		System.err.println("COUNT IS : "+count);
		JsonElement next = jsonObject.get("next");
		System.err.println("NEXT IS : "+next);
		next_page = getRequest.getURI().toString();
		
		character_count= count.getAsInt();
		
		temp_array = jsonObject.getAsJsonArray("results");
		reader.close();
		if(character_count > 0) {
		Main.menuactions.console.continueSearch(getRequest);
		}
	}
	
	

	public void displayAll(JsonArray arr) {
		System.out.println("Total Search Results : "+arr.size()+"\n");
		int arrsize = arr.size();
		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
		}
		for (int i = 0; i < arr.size(); i++) {
			JsonObject result = arr.get(i).getAsJsonObject();
			name = arr.get(i).getAsJsonObject().get("name").getAsString();
			gender = arr.get(i).getAsJsonObject().get("gender").getAsString();
			Person p = new Person();
			Films f = new Films();
			p.setName(name);
			p.setGender(gender);
			if(App.networkConnected == false){  
				System.err.println("ERRRRRRRROOOOOORRRR"); 
				App.networkError();
				break;
			}
			// System.out.println("Name is : "+name);
			//   System.out.println("Gender is : "+gender);
			JsonArray species = result.getAsJsonArray("species");
			JsonArray films = result.getAsJsonArray("films");
			//System.out.println("Films size : "+films.size());
			//System.out.println("species is : "+species);
			printSubCall("name", species);
			p.setSpecies(temptype);
			printSubCall("title", films);

			Films.forEach(film -> {
				//System.out.println(film.getTitle());
				p.addFilm(film);

			});
			Films.clear();
			People.add(p);
			p.personPrint();
			System.out.println("\n");

		}
	}

	public void countedRequest(HttpGet getRequest) throws Exception {
		
		String getty = getRequest.getURI().toString();
		System.out.println(getty +  " : getty");
		System.out.println("NEXT IS : "+next_page);
		
		int i = 0;
		while(allow_next != false) {
		i++;
		getRequest = new HttpGet(next_page);
		System.out.println("Actual get is : "+getRequest);
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		reader = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			Logger.appLog("[countedRequest-SB]"+line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());
		JsonArray arr = jsonObject.getAsJsonArray("results");
		System.err.println("Person Number : "+i);
		JsonElement next = jsonObject.get("next");
		System.err.println("NEXT IS : "+next);
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
						StraightSwapiSearch(uri, "species");
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
			}
		} else {
			System.out.println("Not in URI check - Only in One Movie");
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
	

}
