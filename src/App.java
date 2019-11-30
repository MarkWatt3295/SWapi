import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;


public class App {

	private static HttpURLConnection con;
	private String appVersion = "v0.1";
	String auth;

	public void initialiseApp() {
		//DrawMenu();
		try {
			test2("luke");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DrawMenu() {
		System.out.println("\n=========================================================\n\n");
		System.out.println("SWapi - "+getAppVersion());
		System.out.println("\n\n=========================================================\n");
	}


	
	
	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	public void test2(String resource) throws IOException {
		
	
		URL url = new URL("https://swapi.co/api/people/?");	
		String urlParameters = "search="+resource;
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		
		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
			
			wr.write(postData);
		}

		StringBuilder content;

		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()))) {

			String line;
			content = new StringBuilder();

			while ((line = in.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
		}
		
		System.out.println("Last History - JPASS ANSWER : "+content.toString());


		// Create root JSON Object
		JSONObject object = new JSONObject(content.toString());
	

		JSONObject dval = object.getJSONObject("data");


		JSONArray rowvalues = dval.getJSONArray("result");

		  // for (int y = 0; y < dval.length(); y++) {
			   for (int y = 0; y < rowvalues.length(); y++) {
		
			String n1 = rowvalues.getJSONObject(y).get("name").toString();
			String n2 = rowvalues.getJSONObject(y).get("issue").toString();
			String n3 = rowvalues.getJSONObject(y).get("date").toString();
			

		   

	}
		
	}
	
	
	

	
	
	
}
