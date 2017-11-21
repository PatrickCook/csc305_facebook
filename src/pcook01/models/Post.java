package pcook01.models;

public class Post {
	private String owner;
	private String post;
	
	public Post(String owner, String post) {
		this.owner = owner;
		this.post = post;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getPost() {
		return post;
	}
}
