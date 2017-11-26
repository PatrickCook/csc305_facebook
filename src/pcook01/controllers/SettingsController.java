package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.SettingsView;
import pcook01.views.Facebook.State;
import singletons.FacebookDB;
import singletons.PasswordAuth;

public class SettingsController {
	private Facebook parent;
	private User client;
	private SettingsView view;
	private final int MIN_USERNAME_LENGTH = 6;
	private final int MIN_PASSWORD_LENGTH = 8;

	public SettingsController(Facebook parent, User client, SettingsView view) {
		this.client = client;
		this.view = view;
		this.parent = parent;

		this.view.addSaveListener(new SaveListener());
		this.view.addBackListener(new BackListener());
		this.view.addDeactivateListener(new DeactivateListener());
	}

	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String passwordHash = "";
			String username = view.getUsernameInput();
			String password = view.getPasswordInput();

			FacebookDB db = FacebookDB.getInstance();

			if (username.length() < MIN_USERNAME_LENGTH) {
				JOptionPane.showMessageDialog(view,
						"Please choose a username longer than " + MIN_USERNAME_LENGTH + " characters long.");
				return;
			}

			if (password.length() < MIN_PASSWORD_LENGTH) {
				JOptionPane.showMessageDialog(view,
						"Please choose a password longer than " + MIN_PASSWORD_LENGTH + " characters long.");
				return;
			}

			if (!username.equals(client.getUsername()) && 
					db.containsUser(username)) {
				
				JOptionPane.showMessageDialog(view, "Username already chosen.");
				return;
			}

			try {
				passwordHash = PasswordAuth.getSaltedHash(password);

				if (db.updateUser(client, username, passwordHash)) {
					JOptionPane.showMessageDialog(view, "Your changes have been saved!");
					client.setUsername(username);
				} else {
					JOptionPane.showMessageDialog(view, "Error occured while updating user");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changeState(State.HOME);
		}
	}

	class DeactivateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FacebookDB db = FacebookDB.getInstance();
			db.deleteUser(client);
			parent.changeState(State.SIGNUP);
		}
	}
}
