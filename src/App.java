import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

	Person person = new Person();
	
	BufferedReader reader;
	 HttpResponse response;
	/**
	 * 
	 * @param type - (Search type people/spaceship etc)
	 * @param searchquery - (specifically named e.g Luke)
	 * @return
	 * @throws Exception
	 */
	
	 public void swapiCharacterSearch(String searchquery) throws Exception {
	        HttpGet httpGet = new HttpGet("https://swapi.co/api/people/?search=" + searchquery);
	        System.out.println("The get is : "+httpGet); 
	        personRequest(httpGet);
	       
	 }
	       
	 public void StraightSwapiSearch(String uri, String type) throws Exception {
	        HttpGet httpGet = new HttpGet(uri);
	        System.out.println("The straight get is : "+httpGet); 
	        httpRequest(httpGet, type);
	       
	 }
	 
	 
	 public JsonObject httpRequest(HttpGet getRequest, String type) throws IOException {

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
	            System.out.println("http : "+line);
	        }

	        JsonObject jsonObject = deserialize(stringBuilder.toString());
	        String name =  jsonObject.get("name").getAsString();
	       
	        reader.close();
	        if(type == "species") {
	        	person.setSpecies(name);
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
	            //System.out.println(line);
	        }

	        JsonObject jsonObject = deserialize(stringBuilder.toString());
	       
	       String name = "Not Set";
	       String gender = "Not Set";
	       
	      
	        JsonArray arr = jsonObject.getAsJsonArray("results");
	        System.out.println("Array size is : "+arr.size());
	        for (int i = 0; i < arr.size(); i++) {
	        	System.out.println("arr = "+arr);
	        	 JsonObject film = arr.get(i).getAsJsonObject();
	        name = arr.get(i).getAsJsonObject().get("name").getAsString();
	        gender = arr.get(i).getAsJsonObject().get("gender").getAsString();
	        person.setName(name);
	        person.setGender(gender);
	          
	       // System.out.println("Name is : "+name);
		    //   System.out.println("Gender is : "+gender);
		       JsonArray species = film.getAsJsonArray("species");
		       System.out.println("species is : "+species);
		      printSubCall("name", species);
		      
	          
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
	    
	    
	    
	    public void printSubCall(String entity, JsonArray jsonArray) throws Exception {
	    	System.out.println("SubPrint call: "+entity+" "+jsonArray);
	        if (jsonArray.size() != 0) {
	            for (int j = 0; j < jsonArray.size(); j++) {
	                JsonElement character = jsonArray.get(j);
	                String uri = character.getAsString();
	                System.out.println("print uri is : "+uri);
	                if(uri.contains("species")){
	                StraightSwapiSearch(uri, "species");
	                }
	            }
	        } else {
	            System.out.println("nothing here");
	        }
	    }
	
	
	
	
}
