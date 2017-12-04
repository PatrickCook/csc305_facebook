package pcook01.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pcook01.models.User;
import pcook01.views.components.FriendPanel;

class FriendPanelTest {

	@Test
	void testFriendPanelUser() {
		User friend = new User(1, "friend", null);
		FriendPanel fp = new FriendPanel(friend, null);
		
		assertEquals(fp.getFriend().getUsername(), "friend");
	}

}
