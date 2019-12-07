package console;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Person {

	private String all_films = ""; //Initialise empty to avoid null being added
	private String all_vehicles = ""; //Initialise empty to avoid null being added
	private List<Films> Films = new ArrayList<>();
	private List<Vehicles> Vehicles = new ArrayList<>();
	private String name;
	private String birthYear;
	private List<String> films_urls;
	private String gender;
	private String hair_color;
	private String height;
	private String home_world_url;
	private String mass;
	private String skin_color;
	private String species;
	private String eye_color;

	public void addFilm(Films e) {
		Films.add(e);
		//System.out.println("Added : "+e.getTitle());
	}

	public void addVehicles(Vehicles e) {
		Vehicles.add(e);

	}

	public void personPrint(boolean advanced) {

		if(advanced == true) {
			Films.forEach(films -> {
				all_films = all_films + " | "+ films.getTitle();
			});
			Vehicles.forEach(vehicles -> {
				all_vehicles = all_vehicles + " | "+ vehicles.getName();
			});
			
			if(all_vehicles == "") {
				all_vehicles = " None";
			}
			System.out.println("----------------------------------------------");
			System.out.println("  C H A R A C T E R   I N F O R M A T I O N");
			System.out.println("----------------------------------------------\n");
			System.out.println(
					"Name    : "+getName()
					+"\nGender   : "+getGender()
					+"\nSpecies : "+getSpecies()
					+"\nHeight : "+getHeight()
					+"\nMass : "+getMass()
					+"\nHair Colour : "+getHair_color()
					+"\nSkin Colour : "+getSkin_color()
					+"\nEye Colour : "+getEye_color()
					+"\nBirth Year : "+getBirthYear());
			System.out.println("----------------------------------------------");

			System.out.println("\nFilms Appeared In :"+ all_films
					+"\nVehicles used :"+ all_vehicles+"\n");


		}
		else {
			basicPrint();

		}
		all_films = "";
		all_vehicles = "";
	}


	public void basicPrint() {
		Films.forEach(films -> {
			all_films = all_films + " | "+ films.getTitle();
		});
		System.out.println("C H A R A C T E R   I N F O R M A T I O N\nName    : "+getName()+"\nGender   : "+getGender()+"\n"
				+ "Species : "+getSpecies()+"\nFilms Appeared In : "+ all_films);
	}
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


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}


	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
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

	public String getAllfilms() {
		return all_films;
	}

	public void setAllfilms(String allfilms) {
		this.all_films = allfilms;
	}

	public List<Films> getFilms() {
		return Films;
	}

	public void setFilms(List<Films> films) {
		Films = films;
	}

	public List<String> getFilms_urls() {
		return films_urls;
	}

	public void setFilms_urls(List<String> films_urls) {
		this.films_urls = films_urls;
	}

	public String getHair_color() {
		return hair_color;
	}

	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}

	public String getHome_world_url() {
		return home_world_url;
	}

	public void setHome_world_url(String home_world_url) {
		this.home_world_url = home_world_url;
	}

	public String getSkin_color() {
		return skin_color;
	}

	public void setSkin_color(String skin_color) {
		this.skin_color = skin_color;
	}

	public String getEye_color() {
		return eye_color;
	}

	public void setEye_color(String eye_color) {
		this.eye_color = eye_color;
	}


}
