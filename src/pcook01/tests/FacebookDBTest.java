package pcook01.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pcook01.models.User;
import singletons.FacebookDB;
import singletons.PasswordAuth;

class FacebookDBTest {

	@Test
	void testDatabaseAuthentication() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "pcook01";
		String password = "password";
		
		assertEquals(db.containsUser(username), true);
		assertEquals(db.containsUser("does not exist"), false);
		assertEquals(db.validateUser(user, username, password), true);
		assertEquals(db.validateUser(user, username, "incorrect"), false);
		assertEquals(user.getUsername(), username);
		
	}
	
	@Test
	void testCreateAndDeleteUser() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String hash = PasswordAuth.getSaltedHash("password");
		
		assertEquals(db.containsUser(username), false);
		assertEquals(db.createUser(user, username, hash), true);
		assertEquals(db.validateUser(user, username, "password"), true);
		assertEquals(db.validateUser(user, username, "incorrect"), false);
		assertEquals(user.getUsername(), username);
		
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);
	}
	
	@Test
	void testCreateUserThatAlreadyExists() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "pcook01";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		String post = "Test Post";
		
		assertEquals(db.createUser(user, username, hash), false);
		
	}
	
	@Test
	void testCreatePost() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		String post = "Test Post";
		
		assertEquals(db.createUser(user, username, hash), true);
		assertEquals(db.validateUser(user, username, password), true);
		assertEquals(db.createPost(user, post), true);
		assertEquals(db.getAllPosts(user).size(), 1);
		
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);
		
	}
	
	@Test
	void testGetPostsFromUserAndFriends() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		User user2 = new User();
		String username2 = "testUser2";
		String password2 = "password";
		String hash2 = PasswordAuth.getSaltedHash(password);
		String post = "Test Post";
		
		assertEquals(db.createUser(user, username, hash), true);
		assertEquals(db.createUser(user2, username2, hash2), true);
		
		assertEquals(db.createPost(user, post), true);
		assertEquals(db.getAllPosts(user).size(), 1);
		
		// Should only return 1 post since they aren't friend yet
		assertEquals(db.createPost(user2, post), true);
		assertEquals(db.getAllPosts(user).size(), 1);
		
		// Add as friend and make sure their posts are returned too
		assertEquals(db.followUser(user, user2), true);
		assertEquals(db.getAllPosts(user).size(), 2);
		
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);
		
		db.deleteUser(user2);
		assertEquals(db.containsUser(username2), false);
		
	}
	
	@Test
	void testAddAndGetFriend() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		User user2 = new User();
		String username2 = "testUser2";
		String password2 = "password";
		String hash2 = PasswordAuth.getSaltedHash(password);
		String post = "Test Post";
		
		assertEquals(db.createUser(user, username, hash), true);
		assertEquals(db.createUser(user2, username2, hash2), true);
		
		
		assertEquals(db.followUser(user, user2), true);
		assertEquals(db.getAllFriends(user).size(), 1);
		
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);
		
		db.deleteUser(user2);
		assertEquals(db.containsUser(username2), false);
		
	}

	@Test
	void testCreateUpdateDeleteUser() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		
		assertEquals(db.createUser(user, username, hash), true);
		assertEquals(db.updateUser(user, "changed", hash), true);
		assertEquals(user.getUsername(), "changed");
		
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);
	}
	
	@Test
	void testDeleteUser() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		
		assertEquals(db.createUser(user, username, hash), true);
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);	
	}
	
	@Test
	void testGetPostsFromUserAndFriendsAndUnfollowFriend() throws Exception {
		FacebookDB db = FacebookDB.getInstance();
		User user = new User();
		String username = "testUser";
		String password = "password";
		String hash = PasswordAuth.getSaltedHash(password);
		User user2 = new User();
		String username2 = "testUser2";
		String password2 = "password";
		String hash2 = PasswordAuth.getSaltedHash(password);
		String post = "Test Post";
		
		assertEquals(db.createUser(user, username, hash), true);
		assertEquals(db.createUser(user2, username2, hash2), true);
		
		assertEquals(db.createPost(user, post), true);
		assertEquals(db.getAllPosts(user).size(), 1);
		
		// Should only return 1 post since they aren't friend yet
		assertEquals(db.createPost(user2, post), true);
		assertEquals(db.getAllPosts(user).size(), 1);
		
		// Add as friend and make sure their posts are returned too
		assertEquals(db.followUser(user, user2), true);
		assertEquals(db.getAllPosts(user).size(), 2);
		
		//Unfollow and check if you can see their posts
		db.unfollowUser(user, user2);
		assertEquals(db.getAllPosts(user).size(), 1);
		
		db.deleteUser(user);
		assertEquals(db.containsUser(username), false);
		
		db.deleteUser(user2);
		assertEquals(db.containsUser(username2), false);
		
	}
}
