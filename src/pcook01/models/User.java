package pcook01.models;

import java.util.ArrayList;

public class User {
	private int id;
	private String username;
	private String profileImgUrl;
	
	public User () {
		this.id = -1;
		this.username = "default_username";
		this.profileImgUrl = "src/images/user-icon.png";
	}
	
	public User (int id, String uname, String url) {
		this.id = id;
		this.username = uname;
		setProfileImgUrl(url);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		if (profileImgUrl == null) return;
		
		this.profileImgUrl = profileImgUrl;
	}
}
