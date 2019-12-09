package console;

/**
 * This class contains everything that a Film object should have.
 * Getters and setters are used to access the film strings.
 * @author Mark
 *
 */
public class Films {

    private String title;

    private String episodeId;
    private String openingCrawl;

    private String director;
    private String producer;
    private String release_date;
    private String url;
    private String created;
    private String edited;
    
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(String episodeId) {
		this.episodeId = episodeId;
	}
	public String getOpeningCrawl() {
		return openingCrawl;
	}
	public void setOpeningCrawl(String openingCrawl) {
		this.openingCrawl = openingCrawl;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

   
   
}
