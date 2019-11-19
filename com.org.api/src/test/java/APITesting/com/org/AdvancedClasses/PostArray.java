package APITesting.com.org.AdvancedClasses;

public class PostArray {

	private String id;
	private String title;
	private String author;
	private InfoAdv[] info;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public InfoAdv[] getInfo(){
		return info;
	}
	
	public void setInfo(InfoAdv[] info){
		this.info=info;
	}

}
