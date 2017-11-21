package pcook01.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import singletons.Decorator;

public class UserPanel extends JPanel {
	
	public UserPanel () {
		setLayout(new BorderLayout());

		JLabel userHeader = new JLabel("Patrick Cook", JLabel.CENTER);
		userHeader.setBorder(new EmptyBorder(5,0,0,0));
		
		Decorator.setBoldWithSize(userHeader, 16);
		
		ImageIcon image = new ImageIcon("src/images/user-icon.png");
		JLabel profileImage = new JLabel("", image, JLabel.CENTER);
		profileImage.setPreferredSize(new Dimension(100,100));
		
		JPanel profileImagePanel = new JPanel(new BorderLayout());
		JPanel profileNamePanel = new JPanel(new BorderLayout());
		
		profileImagePanel.add(profileImage, BorderLayout.CENTER );
		profileNamePanel.add(userHeader, BorderLayout.CENTER);
		
		add(profileNamePanel, BorderLayout.NORTH);
		add(profileImagePanel, BorderLayout.CENTER);
		
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(getWidth(), 100);
     }
}
