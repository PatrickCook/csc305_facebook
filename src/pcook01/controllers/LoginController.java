package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.LoginView;

public class LoginController {
	private Facebook parent;
	private User model;
	private LoginView view;
	
	public LoginController(Facebook parent, User user, LoginView view) {
		this.model = user;
		this.view = view;
		this.parent = parent;
		
		this.view.addLoginListener(new LoginListener());
	}
	
	class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = "";
            String password = "";
            
            username = view.getUsernameInput();
            password = view.getPasswordInput();
            
            if (model.validateUser(username, password)) {
            		parent.changeState(State.HOME);
            }
        }
    }
}
