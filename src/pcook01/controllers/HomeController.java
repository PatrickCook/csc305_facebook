package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.HomeView;
import singletons.FacebookDB;

public class HomeController {
	private Facebook parent;
	private User model;
	private HomeView view;
	
	public HomeController(Facebook parent, User user, HomeView view) {
		this.model = user;
		this.view = view;
		this.parent = parent;
		
		this.view.getTopPanel().addSignoutListener(new SignoutListener());
		this.view.getCenterPanel().addNewPostListener(new NewPostListener());
		this.view.getCenterPanel().refreshFeed(model);
	}
	
	class SignoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model = null;
            parent.changeState(State.LOGIN);
        }
    }
	
	class NewPostListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String post = view.getCenterPanel().getNewPost();
            FacebookDB db = FacebookDB.getInstance();
            
            if (post.length() == 0) { 
            		return; 
            	}
            
            if (db.createPost(model, post)) {
            		System.out.println("Created new post!");
            		view.getCenterPanel().refreshFeed(model);
            		view.getCenterPanel().resetNewPostPanel();
            		view.getCenterPanel().addNewPostListener(new NewPostListener());
            }
        }
    }
	
}
