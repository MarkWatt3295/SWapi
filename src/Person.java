import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Person {
	
	public void personPrint() {
		System.out.println("Character information : \nName : "+getName()+"\nGender : "+getGender()+"\n"
				+ "Species : "+getSpecies());
	}

	  private String name;

	    @SerializedName("birth_year")
	    private String birthYear;

	    @SerializedName("films")
	    private List<String> filmsUrls;

	    private String gender;

	    @SerializedName("hair_color")
	    private String hairColor;

	    private String height;

	    @SerializedName("homeworld")
	    private String homeWorldUrl;

	    private String mass;

	    @SerializedName("skin_color")
	    private String skinColor;

	   ;
	    @SerializedName("species")
	    private String species;

	    public String getSpecies() {
			return species;
		}

		public void setSpecies(String species) {
			this.species = species;
		}

		@SerializedName("starships")
	    private List<String> starshipsUrls;

	    @SerializedName("vehicles")
	    private List<String> vehiclesUrls;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBirthYear() {
			return birthYear;
		}

		public void setBirthYear(String birthYear) {
			this.birthYear = birthYear;
		}

		public List<String> getFilmsUrls() {
			return filmsUrls;
		}

		public void setFilmsUrls(List<String> filmsUrls) {
			this.filmsUrls = filmsUrls;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getHairColor() {
			return hairColor;
		}

		public void setHairColor(String hairColor) {
			this.hairColor = hairColor;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public String getHomeWorldUrl() {
			return homeWorldUrl;
		}

		public void setHomeWorldUrl(String homeWorldUrl) {
			this.homeWorldUrl = homeWorldUrl;
		}

		public String getMass() {
			return mass;
		}

		public void setMass(String mass) {
			this.mass = mass;
		}

		public String getSkinColor() {
			return skinColor;
		}

		public void setSkinColor(String skinColor) {
			this.skinColor = skinColor;
		}

		

		public List<String> getStarshipsUrls() {
			return starshipsUrls;
		}

		public void setStarshipsUrls(List<String> starshipsUrls) {
			this.starshipsUrls = starshipsUrls;
		}

		public List<String> getVehiclesUrls() {
			return vehiclesUrls;
		}

		public void setVehiclesUrls(List<String> vehiclesUrls) {
			this.vehiclesUrls = vehiclesUrls;
		}
	    
}
