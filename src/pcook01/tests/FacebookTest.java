package pcook01.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.HomeView;
import pcook01.views.LoginView;

class FacebookTest {

	@Test
	void testStateChangeAndRootPaneType() {
		Facebook facebook = new Facebook();
		
		assertEquals(facebook.getState(), State.LOGIN);
		assertThat(facebook.getFrame().getContentPane().getComponent(0), instanceOf(LoginView.class));
		
		facebook.changeState(State.HOME);
		
		assertEquals(facebook.getState(), State.HOME);
		assertThat(facebook.getFrame().getContentPane().getComponent(0), instanceOf(HomeView.class));
	}

}
