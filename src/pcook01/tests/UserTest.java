package pcook01.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pcook01.models.User;

class UserTest {

	@Test
	void defaultUserTest() {
		User user = new User();
		
		assertEquals(user.getId(), -1);
		assertEquals(user.getUsername(), "default_username");
		assertEquals(user.getProfileImgUrl(), "src/images/user-icon.png");
	}
	
	@Test
	void customUserTest() {
		User user = new User(15, "pcook01", "/url");
		
		assertEquals(user.getId(), 15);
		assertEquals(user.getUsername(), "pcook01");
		assertEquals(user.getProfileImgUrl(), "/url");
	}
	
	@Test
	void setProfileImageToNull() {
		User user = new User();
		
		assertEquals(user.getProfileImgUrl(), "src/images/user-icon.png");
		user.setProfileImgUrl(null);
		assertEquals(user.getProfileImgUrl(), "src/images/user-icon.png");
	}

}
