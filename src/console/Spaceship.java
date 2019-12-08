package console;

public class Spaceship {

	private String name;
	private String model;
	private String manufacturer;
	private String cost_in_credits;
	private String length;
	private String max_atmosphering_speed;
	private String crew;
	private String passengers;
	private String cargo_capactity;
	private String consumables;
	private String hyperdrive_rating;
	private String mglt;
	private String starship_class;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCost_in_credits() {
		return cost_in_credits;
	}
	public void setCost_in_credits(String cost_in_credits) {
		this.cost_in_credits = cost_in_credits;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getMax_atmosphering_speed() {
		return max_atmosphering_speed;
	}
	public void setMax_atmosphering_speed(String max_atmosphering_speed) {
		this.max_atmosphering_speed = max_atmosphering_speed;
	}
	public String getCrew() {
		return crew;
	}
	public void setCrew(String crew) {
		this.crew = crew;
	}
	public String getPassengers() {
		return passengers;
	}
	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}
	public String getCargo_capactity() {
		return cargo_capactity;
	}
	public void setCargo_capactity(String cargo_capactity) {
		this.cargo_capactity = cargo_capactity;
	}
	public String getConsumables() {
		return consumables;
	}
	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}
	public String getHyperdrive_rating() {
		return hyperdrive_rating;
	}
	public void setHyperdrive_rating(String hyperdrive_rating) {
		this.hyperdrive_rating = hyperdrive_rating;
	}
	public String getMglt() {
		return mglt;
	}
	public void setMglt(String mglt) {
		this.mglt = mglt;
	}
	public String getStarship_class() {
		return starship_class;
	}
	public void setStarship_class(String starship_class) {
		this.starship_class = starship_class;
	}

	public void planetsPrint() {
		System.out.println("----------------------------------------------");
		System.out.println("  S P A C E S H I P   I N F O R M A T I O N");
		System.out.println("----------------------------------------------\n");
		System.out.println(
				"Name    : "+getName()
				+"\nModel   : "+getModel()
				+"\nManufacturer : "+getManufacturer()
				+"\nCost in Credits : "+getCost_in_credits()
				+"\nLength : "+getLength()
				+"\nMax Atmosphering Speed : "+getMax_atmosphering_speed()
				+"\nCrew : "+getCrew()
				+"\nPassengers : "+getPassengers()
				+"\nCargo Capacity : "+getCargo_capactity()
				+"\nConsumables : "+getConsumables()
				+"\nHyperdrive Rating : "+getHyperdrive_rating()
				+"\nMGLT : "+getMglt()
				+"\nStarship Class : "+getStarship_class());
				System.out.println("----------------------------------------------");
	}
}
