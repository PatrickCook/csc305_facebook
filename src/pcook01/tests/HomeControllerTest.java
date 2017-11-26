package pcook01.tests;

import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pcook01.controllers.HomeController;
import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.HomeView;

class HomeControllerTest {
	static Facebook facebook;
	static User model, selected;
	static HomeView view;
	static HomeController controller;
	
	@BeforeClass
	public static void setUp() {
		facebook = new Facebook();
		model = new User(0, "pcook01", null);
		selected = new User(0, "pcook01", null);
		view = new HomeView(model);
		controller = new HomeController(facebook, model, selected, view);
	}
	
	@Test
	void test() {
		
		
		
	}

}
