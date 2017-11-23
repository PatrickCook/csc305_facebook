package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pcook01.models.User;
import singletons.Decorator;

public class UserPanel extends JPanel {
	private User user;
	private JLabel userHeader;
	private JLabel profileImage;
	private JPanel profileImagePanel;
	private JButton uploadButton;
	
	public UserPanel (User user) {
		this.user = user;
		
		setLayout(new BorderLayout());
		
		uploadButton = new JButton("Change Photo");
		
		userHeader = new JLabel(user.getUsername(), JLabel.CENTER);
		userHeader.setBorder(new EmptyBorder(5,0,0,0));
		
		Decorator.setBoldWithSize(userHeader, 16);
		
		ImageIcon image = new ImageIcon(user.getProfileImgUrl());
		profileImage = new JLabel("", image, JLabel.CENTER);
		profileImage.setPreferredSize(new Dimension(100,100));
		
		profileImagePanel = new JPanel(new BorderLayout());
		JPanel profileNamePanel = new JPanel(new BorderLayout());
		
		profileImagePanel.add(profileImage, BorderLayout.CENTER );
		profileNamePanel.add(userHeader, BorderLayout.CENTER);
		
		add(profileNamePanel, BorderLayout.NORTH);
		add(profileImagePanel, BorderLayout.CENTER);
		add(uploadButton, BorderLayout.SOUTH);
		
	}
	
	public void reloadProfilePhoto() {
		profileImagePanel.removeAll();
		
		ImageIcon image = new ImageIcon(user.getProfileImgUrl());
		profileImage = new JLabel("", image, JLabel.CENTER);
		profileImage.setPreferredSize(new Dimension(100,100));
		
		profileImagePanel.add(profileImage, BorderLayout.CENTER );
		
		revalidate();
		repaint();
	}
	
	public void addUploadListener(ActionListener a) {
		uploadButton.addActionListener(a);
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(getWidth(), 100);
     }
}
