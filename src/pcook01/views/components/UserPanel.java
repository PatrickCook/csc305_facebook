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
	private JButton followButton;
	private JButton backButton;
	
	public UserPanel (User user, boolean isFollowing) {
		this.user = user;
		
		setLayout(new BorderLayout());
		
		if (isFollowing) {
			followButton = new JButton("Unfollow");
		} else {
			followButton = new JButton("Follow");
		}
		
		backButton = new JButton("Back");
		
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
		
		JPanel buttons = new JPanel();
		buttons.add(followButton);
		buttons.add(backButton);
		
		add(profileNamePanel, BorderLayout.NORTH);
		add(profileImagePanel, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
	}
	
	public void addBackListener(ActionListener a) {
		backButton.addActionListener(a);
	}
	
	public void addFollowListener(ActionListener a) {
		followButton.addActionListener(a);
	}
}
