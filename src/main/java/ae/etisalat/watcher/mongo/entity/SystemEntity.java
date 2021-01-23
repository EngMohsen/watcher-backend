package ae.etisalat.watcher.mongo.entity;




//@Document(collection =  "watcher")
public class SystemEntity {
	
	
//	@NotBlank
//    @Size(max = 100)
//    @Indexed(unique = true)
	private String title;
	private String description;
	private boolean published;

	public SystemEntity() {

	}

	public SystemEntity(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [ title=" + title + ", desc=" + description + ", published=" + published + "]";
	}

}
