package pcook01.models;

import singletons.FacebookDB;

public class User {
	private String name;
	private String username;
	private String hashedPassword;
	private String profileImgUrl;
	
	public User () {
		name = "Default Name";
		username = "defualt_username";
		hashedPassword = "default_hashed_password";
		profileImgUrl = "default_img_url";
		
	}
	
	public User (String uname, String hpass, String url) {
		username = uname;
		hashedPassword = hpass;
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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
	
	public boolean validateUser(String username, String password) {
		FacebookDB db = FacebookDB.getInstance();
		
		return db.validateUser(username, password);
	}
}
