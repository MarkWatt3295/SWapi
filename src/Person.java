import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Person {

	String allfilms = "";
	public List<Films> Films = new ArrayList<>();

	public void addFilm(Films e) {
		Films.add(e);
		//System.out.println("Added : "+e.getTitle());
	}

	public void personPrint() {

		Films.forEach(films -> {
			allfilms = allfilms + " | "+ films.getTitle();
		});
		System.out.println("C H A R A C T E R   I N F O R M A T I O N\nName    : "+getName()+"\nGender   : "+getGender()+"\n"
				+ "Species : "+getSpecies()+"\nFilms Appeared In : "+ allfilms);

		allfilms = ""; //Clear stored string
	}

	private String name;


	private String birthYear;
	private List<String> filmsUrls;
	private String gender;
	private String hairColor;
	private String height;
	private String homeWorldUrl;
	private String mass;
	private String skinColor;
	private String species;

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	private List<String> starshipsUrls;
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
