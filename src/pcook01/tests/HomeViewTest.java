package pcook01.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pcook01.models.User;
import pcook01.views.HomeView;
import pcook01.views.HomeView.CenterPanel;
import pcook01.views.HomeView.TopPanel;

class HomeViewTest {

	@Test
	void testDefaultTopPanel() {
		User user = new User(1, "pcook01", null);
		HomeView view = new HomeView(user);
		TopPanel tView = view.getTopPanel();
		
		assertEquals(tView.getSearchText(), "Search...");
	}
	
	@Test
	void testDefaultCenterPanel() {
		User user = new User(1, "pcook01", null);
		HomeView view = new HomeView(user);
		CenterPanel cView = view.getCenterPanel();
		
		assertEquals(cView.getNewPost(), "");
		
	}
}
