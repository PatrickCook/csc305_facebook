package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import singletons.FacebookDB;
import pcook01.views.ProfileView;


public class ProfileController {
	private Facebook parent;
	private User user, loggedInUser;
	private ProfileView view;

	public ProfileController(Facebook parent, User loggedInUser, User user, ProfileView view) {
		
		this.user = user;
		this.loggedInUser = loggedInUser;
		this.view = view;
		this.parent = parent;

		this.view.getTopPanel().addSignoutListener(new SignoutListener());
		this.view.getSidePanel().addBackListener(new BackListener());
		this.view.getSidePanel().addFollowListener(new FollowListener());
		this.view.getCenterPanel().refreshFeed();
		
	}

	class SignoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user = null;
			JOptionPane.showMessageDialog(view, 
    				"Just logged out! Please sign in to continue "
    				+ "or you may choose to exit.");
			
			parent.changeState(State.LOGIN);
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user = null;

			parent.changeState(State.HOME);
		}
	}
	
	class FollowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FacebookDB db = FacebookDB.getInstance();
			JButton clicked = (JButton) e.getSource();
			
			if (clicked.getText().equals("Follow")) {		
				db.followUser(loggedInUser, user);
				clicked.setText("Unfollow");
				JOptionPane.showMessageDialog(view, 
	    				"You just followed " + user.getUsername() + "!");
				
			} else {
				db.unfollowUser(loggedInUser, user);
				clicked.setText("Follow");
				JOptionPane.showMessageDialog(view, 
	    				"You just unfollowed " + user.getUsername() + "!");
			}
			
			view.getCenterPanel().refreshFeed();
		}
	}
}

