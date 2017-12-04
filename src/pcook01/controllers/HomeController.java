package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.HomeView;
import pcook01.views.components.FriendPanel;
import singletons.FacebookDB;
import singletons.ImageResizer;

public class HomeController {
	private Facebook parent;
	private User client, selectedUser;
	private HomeView view;

	public HomeController(Facebook parent, User client, User selectedUser,
			HomeView view) {
		
		this.client = client;
		this.selectedUser = selectedUser;
		this.view = view;
		this.parent = parent;


		this.view.getTopPanel().addSearchListener(new SearchListener());
		this.view.getTopPanel().addSettingsListener(new SettingsListener());
		this.view.getTopPanel().addSignoutListener(new SignoutListener());
		this.view.getCenterPanel().addNewPostListener(new NewPostListener());
		this.view.getSidePanel().addUploadListener(new UploadListener());
		this.view.getSidePanel().addSelectUserListener(
				new SelectUserListener());
		this.view.getSearchResultsPanel().addSelectUserListener(
				new SelectUserListener());
		
		this.view.getCenterPanel().refreshFeed();
		this.view.getSidePanel().loadUserFriends();		
	}

	public class SignoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			client = null;
			JOptionPane.showMessageDialog(view, "Just logged out!");
			
			parent.changeState(State.EXIT);
		}
	}
	
	public class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String query = view.getTopPanel().getSearchText();
			view.getSearchResultsPanel().loadSearchResults(query);
			view.getSearchResultsPanel().setVisible(true);
		}
	}
	
	public class SettingsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changeState(State.SETTINGS);
		}
	}
	
	public class SelectUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FriendPanel fp = (FriendPanel) e.getSource();
			User f = fp.getFriend();
			
			selectedUser.setId(f.getId());
			selectedUser.setUsername(f.getUsername());
			selectedUser.setProfileImgUrl(f.getProfileImgUrl());
			
			parent.changeState(State.USER_PROFILE);
		}
	}

	class NewPostListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String post = view.getCenterPanel().getNewPost();
			FacebookDB db = FacebookDB.getInstance();

			if (post.length() == 0) {
				return;
			}

			if (db.createPost(client, post)) {
				System.out.println("Created new post!");
				view.getCenterPanel().refreshFeed();
				view.getCenterPanel().resetNewPostPanel();
				view.getCenterPanel().addNewPostListener(new NewPostListener());
			}
		}
	}

	class UploadListener implements ActionListener {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG/PNG Images", "jpg", "png");
		FacebookDB db;
		
		public void actionPerformed(ActionEvent e) {
			fc.setFileFilter(filter);
			db = FacebookDB.getInstance();
			
			int returnVal = fc.showOpenDialog(view);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String dest = "src/images/users/"+ client.getUsername() + ".png";
				File src = fc.getSelectedFile();
				
				try {
					ImageResizer.resize(src.getPath(), dest, 100, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				client.setProfileImgUrl("src/images/users/"+ client.getUsername() + ".png");
				view.getSidePanel().reloadProfilePhoto();
				db.updateUserProfileImage(client);
			} else {
				System.out.println("Open command cancelled by user.");
			}
		}
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
 }
