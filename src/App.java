import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.TimeLimitExceededException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {

	public static boolean networkConnected = false;
	private String temptype = "";

	List<Person> People = new ArrayList<>();
	List<Films> Films = new ArrayList<>();

	private BufferedReader reader;
	private HttpResponse response;
	private StringBuilder stringBuilder = new StringBuilder();

	/**
	 * 
	 * @param type - (Search type people/spaceship etc)
	 * @param searchquery - (specifically named e.g Luke)
	 * @return
	 * @throws Exception
	 */



	public void swapiCharacterSearch(String searchquery)  {
		HttpGet httpGet = new HttpGet("https://swapi.co/api/people/?search=" + searchquery);
		//System.out.println("The get is : "+httpGet); 
		try {
			personRequest(httpGet);
		} catch (TimeLimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NoRouteToHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void StraightSwapiSearch(String uri, String type) throws Exception {
		HttpGet httpGet = new HttpGet(uri);
		//System.out.println("The straight get is : "+httpGet); 
		httpRequest(httpGet, type);

	}

	public void httpConnect(HttpGet getRequest) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		if(App.networkConnected = true) {
			reader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		

		String line;

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			// System.out.println("http : "+line);
		}
		}
	}


	public JsonObject httpRequest(HttpGet getRequest, String type) throws IOException {

		httpConnect(getRequest);

		JsonObject jsonObject = deserialize(stringBuilder.toString());


		closeReader();

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

	private void closeReader() throws IOException {
		reader.close();
		stringBuilder.setLength(0); //Empty the builder ready for next search

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
			//System.out.println(line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());

		String name = "Not Set";
		String gender = "Not Set";


		JsonArray arr = jsonObject.getAsJsonArray("results");
		System.out.println("Total Search Results : "+arr.size()+"\n");
		if(arr.size() > 1) {
			System.err.println("There are multiple results for this search.\n");
		}
		for (int i = 0; i < arr.size(); i++) {
			//System.out.println("arr = "+arr);
			JsonObject result = arr.get(i).getAsJsonObject();
			name = arr.get(i).getAsJsonObject().get("name").getAsString();
			gender = arr.get(i).getAsJsonObject().get("gender").getAsString();
			Person p = new Person();
			Films f = new Films();
			p.setName(name);
			p.setGender(gender);

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


		reader.close();

		return jsonObject;
	}


	public JsonObject swapiSearch(String type, String searchquery) throws Exception {
		HttpGet httpGet;
		if (searchquery == null) {
			httpGet = new HttpGet("https://swapi.co/api/" + type + "/");
		} else {
			httpGet = new HttpGet("https://swapi.co/api/" + type + "/?search=" + searchquery);
		}
		System.out.println("httpGet is : "+httpGet);
		return getRequest(httpGet);
	}

	public JsonObject getRequest(HttpGet getRequest) throws IOException {

		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
			//System.out.println(line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());

		String name = "Not Set";
		String gender = "Not Set";


		JsonArray arr = jsonObject.getAsJsonArray("results");
		for (int i = 0; i < arr.size(); i++) {
			name = arr.get(i).getAsJsonObject().get("name").getAsString();
			gender = arr.get(i).getAsJsonObject().get("gender").getAsString();


		}
		System.out.println("Name is : "+name);
		System.out.println("Gender is : "+gender);


		return jsonObject;
	}

	public JsonObject deserialize(String json) {
		Gson gson = new Gson();
		JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
		return jsonClass;
	}

	public JsonObject innerRequest(String uri) throws IOException {
		HttpGet httpGet = new HttpGet(uri);
		return getRequest(httpGet);
	}



	public void printSubCall(String entity, JsonArray jsonArray)  {
		//System.out.println("SubPrint call: "+entity+" "+jsonArray);
		if(App.networkConnected == true) {
		if (jsonArray.size() != 0) {
			for (int j = 0; j < jsonArray.size(); j++) {
				JsonElement character = jsonArray.get(j);
				String uri = character.getAsString();
				//System.out.println("print uri is : "+uri);

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
			System.out.println("nothing here");
		}
		}
		else {
			Main.networkError();
		}
	}




}
