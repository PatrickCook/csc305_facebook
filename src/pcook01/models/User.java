package pcook01.models;

import singletons.FacebookDB;

public class User {
	private String name;
	private String username;
	private String passwordHash;
	private String profileImgUrl;
	
	public User () {
		name = "Default Name";
		username = "defualt_username";
		profileImgUrl = "default_img_url";
		
	}
	
	public User (String uname, String url) {
		username = uname;
		profileImgUrl = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
}
