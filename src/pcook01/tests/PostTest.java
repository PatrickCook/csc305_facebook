package pcook01.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pcook01.models.Post;
import pcook01.models.User;

class PostTest {

	@Test
	void testCreatePost() {
		User user = new User();
		Post post = new Post(user.getUsername(), "Test post");
		
		assertEquals(post.getOwner(), "default_username");
		assertEquals(post.getPost(), "Test post");
	}

}
