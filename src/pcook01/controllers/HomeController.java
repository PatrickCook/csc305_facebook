package pcook01.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import pcook01.models.User;
import pcook01.views.Facebook;
import pcook01.views.Facebook.State;
import pcook01.views.HomeView;
import singletons.FacebookDB;
import singletons.ImageResizer;

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
		this.view.getSidePanel().addUploadListener(new UploadListener());
		
		this.view.getCenterPanel().refreshFeed();
		this.view.getSidePanel().loadUserFriends();
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
				String dest = "src/images/users/"+ model.getUsername() + ".png";
				File src = fc.getSelectedFile();
				
				try {
					ImageResizer.resize(src.getPath(), dest, 100, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				model.setProfileImgUrl("src/images/users/"+ model.getUsername() + ".png");
				view.getSidePanel().reloadProfilePhoto();
				db.updateUserProfileImage(model);
			} else {
				System.out.println("Open command cancelled by user.");
			}
		}
	}

}
