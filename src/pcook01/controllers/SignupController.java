package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.SignupView;
import singletons.FacebookDB;
import singletons.PasswordAuth;

public class SignupController {
	private Facebook parent;
	private User model;
	private SignupView view;
	private final int MIN_USERNAME_LENGTH = 6;
	private final int MIN_PASSWORD_LENGTH = 8;
	
	public SignupController(Facebook parent, User user, SignupView view) {
		this.model = user;
		this.view = view;
		this.parent = parent;
		

		this.view.addCancelListener(new CancelListener());
		this.view.addSignupListener(new SignupListener());
	}
	
	class SignupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        		String passwordHash = "";
            String username = view.getUsernameInput();
            String password = view.getPasswordInput();
            String password2 = view.getPassword2Input();
            
            FacebookDB db = FacebookDB.getInstance();
            
            if (username.length() < MIN_USERNAME_LENGTH) {
            		JOptionPane.showMessageDialog(view, "Please choose a username longer than " +
        				MIN_USERNAME_LENGTH + " characters long.");
            		return;
            }
            
            if (password.length() < MIN_PASSWORD_LENGTH) {
            		JOptionPane.showMessageDialog(view, "Please choose a password longer than " +
            				MIN_PASSWORD_LENGTH + " characters long.");
            		return;
            }
            
            if (!password.equals(password2)) {
            		JOptionPane.showMessageDialog(view, "Passwords do not match.");
            		return;
            }
            
            if (db.containsUser(username)) {
            		JOptionPane.showMessageDialog(view, "Username already chosen.");
            		return;
            }
            try {
				passwordHash = PasswordAuth.getSaltedHash(password);
				
				if (db.createUser(model, username, passwordHash)) {
					System.out.println(model.getId());
					parent.changeState(State.HOME);		
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}    
        }
    }
	
	class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            	parent.changeState(State.LOGIN);
        }
    }
}
