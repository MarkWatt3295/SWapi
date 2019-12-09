package console;

/**
 * Define everything a Vehicle object should have.
 * Variables are accessed using getters and setters
 * @author Mark
 *
 */
public class Vehicles {

	private String name;
	private String model;
	private String vehicle_class;
	private String manufacturer;
	private String cost_in_credits;
	private String length;
	private String crew;
	private String passengers;
	private String max_atmosphering_speed;
	private String cargo_capacity;
	private String consumables;




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
	public String getVehicle_class() {
		return vehicle_class;
	}
	public void setVehicle_class(String vehicle_class) {
		this.vehicle_class = vehicle_class;
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
	public String getMax_atmosphering_speed() {
		return max_atmosphering_speed;
	}
	public void setMax_atmosphering_speed(String max_atmosphering_speed) {
		this.max_atmosphering_speed = max_atmosphering_speed;
	}
	public String getCargo_capacity() {
		return cargo_capacity;
	}
	public void setCargo_capacity(String cargo_capacity) {
		this.cargo_capacity = cargo_capacity;
	}
	public String getConsumables() {
		return consumables;
	}
	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}


	/**
	 * A console print to display vehicle information
	 */
	public void vehiclePrint() {
		System.out.println("----------------------------------------------");
		System.out.println("  V E H I C L E   I N F O R M A T I O N");
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
				+"\nCargo Capacity : "+getCargo_capacity()
				+"\nConsumables : "+getConsumables()
				+"\nVehicle Class : "+getVehicle_class());
		System.out.println("----------------------------------------------");

	}

}
