import com.google.gson.JsonObject;

public class Model {

	private App app;
	
	public Model(App app) {
		this.app = app;
	}
	
	  public JsonObject getAll(String type, String searchquery) {
	        JsonObject jsonObject = null;
	        try {
	            jsonObject = app.swapiSearch(type, searchquery);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return jsonObject;
	    }

	    public JsonObject innerRequest(String uri) {
	        JsonObject jsonObject = new JsonObject();
	        try {
	            jsonObject = app.innerRequest(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return jsonObject;
	    }
}
