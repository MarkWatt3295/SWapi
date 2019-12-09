package console;

/**
 * Define everything a planet object should have
 * Planets are used to create individual planet objects
 * 
 * Getters and setters are used to access its variables.
 * Planet also contains a print to display planet information in a formatted console print.
 * @author Mark
 *
 */
public class Planets {

	private String name;
	private String diameter;
	private String gravity;
	private String population;
	private String climate;
	private String terrain;
	private String created;
	private String edited;
	private String url;
	private String rotationPeriod;
	private String orbitalPeriod;
	private String surfaceWater;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiameter() {
		return diameter;
	}
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	public String getGravity() {
		return gravity;
	}
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getEdited() {
		return edited;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRotationPeriod() {
		return rotationPeriod;
	}
	public void setRotationPeriod(String rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}
	public String getOrbitalPeriod() {
		return orbitalPeriod;
	}
	public void setOrbitalPeriod(String orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}
	public String getSurfaceWater() {
		return surfaceWater;
	}
	public void setSurfaceWater(String surfaceWater) {
		this.surfaceWater = surfaceWater;
	}

	/**
	 * Print all planet information in a formatted console print.
	 */
	public void planetsPrint() {
		System.out.println("----------------------------------------------");
		System.out.println("  P L A N E T S   I N F O R M A T I O N");
		System.out.println("----------------------------------------------\n");
		System.out.println(
				"Name    : "+getName()
				+"\nRotational Period   : "+getRotationPeriod()
				+"\nOrbital Period : "+getOrbitalPeriod()
				+"\nDiameter : "+getDiameter()
				+"\nGravity : "+getGravity()
				+"\nTerrain : "+getTerrain()
				+"\nSurface Water : "+getSurfaceWater()
				+"\nPopulation : "+getPopulation());
		System.out.println("----------------------------------------------");
	}


}
