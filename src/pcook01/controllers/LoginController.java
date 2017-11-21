package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.LoginView;
import singletons.FacebookDB;

public class LoginController {
	private Facebook parent;
	private User model;
	private LoginView view;
	
	public LoginController(Facebook parent, User user, LoginView view) {
		this.model = user;
		this.view = view;
		this.parent = parent;
		
		this.view.addLoginListener(new LoginListener());
		this.view.addCancelListener(new CancelListener());
		this.view.addSignupListener(new SignupListener());
	}
	
	class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = "";
            String password = "";
            
            username = view.getUsernameInput();
            password = view.getPasswordInput();
            
            FacebookDB db = FacebookDB.getInstance();
            
            if (db.validateUser(model, username, password)) {        		
            		parent.changeState(State.HOME);
            } else {
            		JOptionPane.showMessageDialog(view, 
            				"Incorrect username/password.");
            }
        }
    }
	
	class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            	parent.changeState(State.EXIT);
        }
    }
	
	class SignupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            	parent.changeState(State.SIGNUP);
        }
    }
}
